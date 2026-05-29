package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionDatosGuardados {
    private Map<String, String> actuadoresEstados;
    private List<String> reglasLista;

    public ConfiguracionDatosGuardados(){
        this.actuadoresEstados = new HashMap<>();
    }
    public ConfiguracionDatosGuardados(Map<String, String> actuadoresEstados, List<String> reglasLista){
        this.actuadoresEstados = actuadoresEstados;
        this.reglasLista = reglasLista;
    }

    public Map<String, String> getActuadoresEstados(){
        return  actuadoresEstados;
    }
    public List<String> getReglasLista(){
        return  reglasLista;
    }
}
