import controlador.Controlador;
import vista.Vista;

public class Main {
    public static void main(String[] args) {
        //Se crea el controlador
        Controlador controlador = new Controlador();

        //Se crea la vista pasándole el controlado
        Vista vista = new Vista(controlador);

        //Se le pasa al controlador la vista
        controlador.setVista(vista);

        //Se muestra la interfaz de usuario
        vista.setVisible(true);

        //Se inicia el timer del controlador, comenzando la lógica
        controlador.startTimer();
    }
}