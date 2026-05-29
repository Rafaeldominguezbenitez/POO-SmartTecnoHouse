package modelo;

public class ActuadorBombilla extends Actuador{ //Subclase para bombillas (actuadores)

    //Constructor
    public ActuadorBombilla(){
        super("bulb", "Bombilla", "off"); //Se construye el objeto a partir del constructor padre
    }

    //Implementación del metodo ejecutarAccion de la clase padre Actuador
    @Override
    public void ejecutarAccion(String accion){
        String accionNormalizada = accion.toLowerCase();

        //Si la accion es "on" u "off" se cambia el estado a la acción. Si no lo es, se lanza una excepcion
        if (accionNormalizada.equals("on") || accionNormalizada.equals("off")){
            this.estado = accionNormalizada;
        }
        else{
            throw new IllegalArgumentException("La acción '" + accion + "' es inválida");
        }
    }

    //Implementación del metodo getAccionesPosibles de la clase padre Actuador
    @Override
    public String[] getAccionesPosibles(){
        return new String[]{"on", "off"};
    }
}
