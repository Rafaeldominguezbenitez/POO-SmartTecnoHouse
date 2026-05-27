package modelo;

public abstract class Actuador implements IDispositivo {
    //Esta clase abstracta implementa IDispositivo y la lógica común a todos los actuadores

    //Definicion de variables
    protected String id;
    protected String nombre;
    protected String estado;

    //Constructor
    public Actuador(String id, String nombre, String estado){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    //Implementacion funciones interfaz IDispositivo
    @Override
    public String getID(){
        return id;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override
    public String getEstadoActual(){
        return estado;
    }

    //Implementacion funciones especificas de actuadores
    //Cada actuador implementará una lógica de forma individual
    public abstract void ejecutarAccion(String accion);

    public abstract String[] getAccionesPosibles();
}
