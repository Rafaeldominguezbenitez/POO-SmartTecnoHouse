package modelo;

//Se importan las librerías de colecciones
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartTecnoHouse {
    private static SmartTecnoHouse instance; //Singleton

    //Declaración de colecciones de objetos (para el polimorfismo)
    private final List<Sensor> sensores;
    private final List<Actuador> actuadores;
    private final List<Regla> reglas;

    //Constructor privado que se ejecuta solo al iniciar la aplicación
    private SmartTecnoHouse() {
        this.sensores = new ArrayList<>();
        this.actuadores = new ArrayList<>();
        this.reglas = new ArrayList<>();

        //Se inicializan los dispositivos
        inicializarDispositivos();

        //Se cargan los datos anteriores
        cargarDatos();

        //Se inicializan las reglas
        inicializarReglas();
    }

    //Controla que solo haya una instancia
    public static SmartTecnoHouse getInstance() {
        if (instance == null) {
            instance = new SmartTecnoHouse();
        }
        return instance;
    }

    //Metodo para inicializar los dispositivos ("rellenar" las colecciones declaradas)
    private void inicializarDispositivos() {
        //Dispositivos base
        sensores.add(new SensorTemperatura()); // ID: "temp"
        sensores.add(new SensorLuz());         // ID: "light"
        sensores.add(new SensorPresencia());   // ID: "pir"
        actuadores.add(new ActuadorBombilla());   // ID: "bulb"
        actuadores.add(new ActuadorVentilador()); // ID: "fan"

        //Dispositivos añadidos
        sensores.add(new SensorHumo());              // ID: "smoke"
        actuadores.add(new ActuadorHumidificador()); // ID: "humi"
    }
    //Metodo para inicializar las reglas
    private void inicializarReglas() {
        reglas.add(new ReglaIluminacion());
        reglas.add(new ReglaControlTemperatura());
        reglas.add(new ReglaCalidadAire());
    }


    //Uso de TransformadorJsonDatosGuardados para "cargar" los datos anteriores a partir del json
    private void cargarDatos() {
        ConfiguracionDatosGuardados datosViejos = TransformadorJsonDatosGuardados.getInstance().cargarDatos(); //Se crea un objeto a partir de los datos que habían escritos en el json

        if (datosViejos != null && datosViejos.getActuadoresEstados() != null) {
            Map<String, String> mapaEstados = datosViejos.getActuadoresEstados(); //Map que almacenará los estados del objeto que guarda los datos

            for (Actuador actuador : actuadores) { //Foreach que dará a cada actuador sus correspondientes estados/datos
                String estadoGuardado = mapaEstados.get(actuador.getID());
                if (estadoGuardado != null) {
                    try {
                        actuador.ejecutarAccion(estadoGuardado); //Aquí se aplica el estado a cada actuador
                    } catch (IllegalArgumentException e) {
                        // Evita que un JSON externo corrupto rompa el arranque de la app
                        throw new IllegalStateException("Los datos presentan incompatibilidad con el actuador: " + actuador.getID(), e);
                    }
                }
            }
        }
    }

    //Metodo para guardar los datos
    public void guardarDatos() {
        Map<String, String> estadosAAlmacenar = new HashMap<>(); //Hashmap para almacenar los estados
        List<String> nombresReglas = new ArrayList<>(); //Lista para almacenar las reglas

        for (Actuador a : actuadores) { //Se "combinan" (mapean) los actuadores con sus respectivos estados
            estadosAAlmacenar.put(a.getID(), a.getEstadoActual());
        }

        //Se almacenan las reglas
        for (Regla r : reglas) {
            nombresReglas.add(r.getClass().getSimpleName());
        }

        //Se crea un objeto que almacena los datos y lo llenamos con los datos a guardar
        ConfiguracionDatosGuardados datosNuevos = new ConfiguracionDatosGuardados(estadosAAlmacenar, nombresReglas);
        TransformadorJsonDatosGuardados.getInstance().guardarDatos(datosNuevos); //Se guardan los datos (se escriben en el json)
    }

    //Metodo que actualiza el valor de los sensores
    public void actualizarSensores() {
        //Se actualizan los valores de los sensores de forma polimórfica
        for (Sensor s : sensores) {
            s.actualizarValor();
        }

        //Se aplican las reglas a los sensores de forma polimórfica
        for (Regla r : reglas) {
            r.aplicar(this.sensores, this.actuadores);
        }
    }

    //Getters & Setters
    public List<Sensor> getSensores() { return sensores; }
    public List<Actuador> getActuadores() { return actuadores; }
    public List<Regla> getReglas() { return reglas; }
}
