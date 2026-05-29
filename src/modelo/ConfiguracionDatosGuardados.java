package modelo;

//Se importan librerías de colecciones
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionDatosGuardados { //Esta clase servirá para instanciar un objeto que almacene todos los datos a guardar
    private Map<String, String> actuadoresEstados; //Map que agrupará los ids de cada actuador con sus respectivos estados
    private List<String> reglasLista; //Lista que guardará las reglas

    public ConfiguracionDatosGuardados(){ //Constructor requerido para que la librería gson lea correctamente el json
        this.actuadoresEstados = new HashMap<>();
    }
    public ConfiguracionDatosGuardados(Map<String, String> actuadoresEstados, List<String> reglasLista){ //Constructor para instanciar el objeto que almacena los datos
        this.actuadoresEstados = actuadoresEstados;
        this.reglasLista = reglasLista;
    }

    //Getters para que gson pueda escribir o leer los datos
    public Map<String, String> getActuadoresEstados(){
        return  actuadoresEstados;
    }
    public List<String> getReglasLista(){
        return  reglasLista;
    }
}
