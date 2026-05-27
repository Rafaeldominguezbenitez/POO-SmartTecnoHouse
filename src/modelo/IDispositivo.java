package modelo;

public interface IDispositivo {
//Esta interfaz obligará a las clases Sensor y Actuador a poder dar una id, un nombre y un estado
    String getID();
    String getNombre();
    String getEstadoActual();
}
