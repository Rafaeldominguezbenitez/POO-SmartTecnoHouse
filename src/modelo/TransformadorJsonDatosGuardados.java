package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformadorJsonDatosGuardados {
    private static TransformadorJsonDatosGuardados instance;
    private final String RUTA_ARCHIVO = "configuracion_datos_guardados.json";
    private final Gson gson;

    private TransformadorJsonDatosGuardados(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static TransformadorJsonDatosGuardados getInstance(){
        if(instance == null){
            instance = new TransformadorJsonDatosGuardados();
        }
        return instance;
    }

    public void guardarDatos(ConfiguracionDatosGuardados config){
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)){
            gson.toJson(config, writer);
        } catch (IOException e){
            throw new RuntimeException("Error al escribir los datos en el json", e);
        }
    }

    public ConfiguracionDatosGuardados cargarDatos(){
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, ConfiguracionDatosGuardados.class);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos del json", e);
        }
    }
}
