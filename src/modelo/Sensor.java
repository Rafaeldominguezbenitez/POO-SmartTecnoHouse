package modelo;

public abstract class Sensor implements IDispositivo {
    //Esta clase abstracta implementa IDispositivo y la lógica común a todos los sensores

    //Definicion de variables
    protected String id; //id unico
    protected String nombre; //nombre
    protected String estado; //estado (ON, OFF)
    protected String unidadMedida; //unidad de medida
    protected double ultimoValor; //ultimo valor leido

    //Constructor
    public Sensor(String id, String nombre, String unidadMedida, String estado){
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.estado = estado.toLowerCase();
    }

    //Implementacion metodos de la interfaz IDispositivo
    @Override
    public String getID(){
        return this.id;
    }

    @Override
    public String getNombre(){
        return this.nombre;
    }

    @Override
    public String getEstadoActual(){
        return this.estado + " " + this.ultimoValor + this.unidadMedida; //Devuelve estado (ON, OFF) y el último valor con su unidad
    }

    //Metodos propios de los sensores
    public double getValor(){
        return this.ultimoValor;
    }

    public String getUnidadMedida(){
        return unidadMedida;
    }


    public abstract void actualizarValor(); //Cada sensor específico implementará la lógica
}
