package modelo;

//Se importan las librerías necesarias
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RegistradorCambiosLog { //Clase que registra los cambios en el estado de los actuadores en un .log
    private static RegistradorCambiosLog instance; //Singleton
    private final String RUTA_LOG = "actuators.log"; //Ruta del .log
    private final DateTimeFormatter formateador; //Se usará para dar formato a las entradas del .log

    //Constructor privado para el Singleton
    private RegistradorCambiosLog() {
        //Las entradas en el .log se escribirán con el formato dia-mes-año hora-minuto-segundo
        this.formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    }

    //Controla que no haya más de una instancia
    public static RegistradorCambiosLog getInstance() {
        if (instance == null) {
            instance = new RegistradorCambiosLog();
        }
        return instance;
    }

    //Metodo que registra los cambios en el estado de los actuadores en el .log a partir del formato elegido
    public void registrarAccion(String idActuador, String nuevoEstado) {
        String marcaTiempo = LocalDateTime.now().format(formateador);
        String lineaLog = String.format("[%s] Actuador '%s' cambiado a estado: %s", marcaTiempo, idActuador, nuevoEstado);

        //Como se usa un archivo externo, hay que controlar la posible excepción
        try (FileWriter fw = new FileWriter(RUTA_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(lineaLog); //Se escribe en el .log

        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en actuators.log", e);
        }
    }
}