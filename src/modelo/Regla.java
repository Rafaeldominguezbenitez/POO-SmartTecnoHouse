package modelo;

import java.util.List;

//Interfaz que deben implementar todas las reglas
public interface Regla {

    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}
