package modelo;

public class ActuadorHumidificador extends Actuador { // Subclase para humidificadores (actuadores)

    // Constructor
    public ActuadorHumidificador() {
        super("humi", "Humidificador", "off"); // Se construye el objeto a partir del constructor padre
    }

    // Implementación del metodo ejecutarAccion de la clase padre Actuador
    @Override
    public void ejecutarAccion(String accion) {
        String accionNormalizada = accion.toLowerCase();

        //Si la accion es "on", "off", "low", "medium" o "high" se cambia el estado a la acción. Si no lo es, se lanza una excepcion
        if (accionNormalizada.equals("on") || accionNormalizada.equals("off") || accionNormalizada.equals("low") || accionNormalizada.equals("high")) {
            this.estado = accionNormalizada;
        } else {
            throw new IllegalArgumentException("La acción '" + accion + "' es inválida");
        }
    }

    // Implementación del metodo getAccionesPosibles de la clase padre Actuador
    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"on", "off", "low", "high"};
    }
}