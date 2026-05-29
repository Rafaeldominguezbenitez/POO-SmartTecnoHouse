package modelo;

//Se importan las librerías necesarias
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformadorJsonDatosGuardados { //Clase que servirá para convertir el objeto que almacena los datos en texto y viceversa
    private static TransformadorJsonDatosGuardados instance; //Singleton
    private final String RUTA_ARCHIVO = "configuracion_datos_guardados.json"; //Ruta del json
    private final Gson gson;

    private TransformadorJsonDatosGuardados(){ //Constructor privado para bloquear la creación de instancias
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //Se usa setPrettyPrinting para que se pueda acceder al json
    }

    public static TransformadorJsonDatosGuardados getInstance(){ //Esto controla que solo haya una instancia, Singleton
        if(instance == null){
            instance = new TransformadorJsonDatosGuardados();
        }
        return instance;
    }

    public void guardarDatos(ConfiguracionDatosGuardados config){ //Metodo que escribe los datos en el json
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)){ //Como está accediendo a un archivo externo, se debe controlar la excepción
            gson.toJson(config, writer);
        } catch (IOException e){
            throw new RuntimeException("Error al escribir los datos en el json", e);
        }
    }

    public ConfiguracionDatosGuardados cargarDatos(){ //Metodo que lee los datos (es decir, los carga) del json
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(archivo)) { //Como está accediendo a un archivo externo, se debe controlar la excepción
            return gson.fromJson(reader, ConfiguracionDatosGuardados.class); //Se instancia un objeto almacenador de datos
        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos del json", e);
        }
    }
}
