package controlador;

//Se importan las librerías necesarias y las clases del modelo a las que vamos a acceder
import modelo.SmartTecnoHouse;
import modelo.Actuador;
import modelo.Sensor;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Controlador {

    private final SmartTecnoHouse modelo; //Referencia a la clase principal del modelo
    private Timer timer; //Temporizador para actualizar los sensores constantemente
    // private Vista vista; //Referencia a la vista

    //Constructor: instancia del modelo e inicio del temporizador
    public Controlador() {
        this.modelo = SmartTecnoHouse.getInstance();
        inicializarTemporizador();
    }

    //Metodo que configura el temporizador
    private void inicializarTemporizador(){

        this.timer = new Timer(1000, new ActionListener() { //Se crea un temporizador que se actualiza cada segundo
            @Override
            public void actionPerformed(ActionEvent e) { //Metodo de ActionListener que marca qué se hace cada el tiempo definido en el timer
                //Se actualizan los sensores
                modelo.actualizarSensores();

                //Aquí se llamaría al metodo de la vista que actualice la interfaz de usuario
            }
        });

    }

    //Metodo que inicia el temporizador
    public void startTimer() {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }

    //Metodo que detiene el temporizador
    public void stopTimer(){
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    //Metodo que permitirá cambiar el estado de los actuadores desde la interfaz de usuario
    public void cambiarEstadoActuador(String idActuador, String nuevoEstado) {
        for (Actuador actuador : modelo.getActuadores()) { //Foreach para encontrar todos los actuadores
            if (actuador.getID().equalsIgnoreCase(idActuador)) { //Si justo el actuador correspondiente a esta iteración es el que queremos cambiar (especificado por parámetro), se cambia el estado del actuador
                try {
                    actuador.ejecutarAccion(nuevoEstado);
                } catch (IllegalArgumentException e) {
                    throw new IllegalStateException("Ha ocurrido un error al ejecutar la accion en el actuador " + idActuador, e);
                }
                break;
            }
        }
        //Aqui iria llamar a la vista para actualizar el boton

    }

    //Metodo para cerrar la aplicación y guardar los datos al hacerlo (detenemos el timer antes de salir)
    public void cerrarAplicacion() {
        stopTimer(); //Se detiene el timer
        modelo.guardarDatos(); //Se guardan los datos
    }

    //Getters
    public List<Sensor> obtenerSensores() { return modelo.getSensores(); }
    public List<Actuador> obtenerActuadores() { return modelo.getActuadores(); }

}
