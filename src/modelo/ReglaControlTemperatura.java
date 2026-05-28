package modelo;

import java.util.List;

public class ReglaControlTemperatura implements Regla {
    //Esta clase controlará la potencia del ventilador según la temperatura de la sala


    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) { //Metodo que controlará el funcionamiento de los dispositivos según una lógica

        //Declaración de dispositivos
        SensorTemperatura sensorTemp = null;
        ActuadorVentilador ventilador = null;

        //Se buscan los dispositivos dentro de las listas que se pasan por parámetro
        for (Sensor s : sensores) {
            if (s.getID().equals("temp")) sensorTemp = (SensorTemperatura) s;
        }
        for (Actuador a : actuadores) {
            if (a.getID().equals("fan")) ventilador = (ActuadorVentilador) a;
        }

        //Si no se encuentra alguno de los dispositivos necesarios, no se ejecutará la lógica
        if (sensorTemp == null || ventilador == null) {
            return;
        }


        //Según la temperatura que haga, se pondrá el ventilador a una velocidad u otra

        //Se ha usado una estructura if-else porque en Java no se puede hacer un switch con un valor double
        if (sensorTemp.getValor() > 30.0) {
            ventilador.ejecutarAccion("high");
        } else if (sensorTemp.getValor() >= 24.0) {
            ventilador.ejecutarAccion("medium");
        } else if (sensorTemp.getValor() >= 20.0) {
            ventilador.ejecutarAccion("low");
        } else {
            ventilador.ejecutarAccion("off");
        }
    }
}
