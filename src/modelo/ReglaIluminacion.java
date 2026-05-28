package modelo;

import java.util.List;

public class ReglaIluminacion implements Regla{
    //Esta clase encenderá la bombilla al detectar movimiento y una iluminación insuficiente


    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores){ //Metodo que controlará el funcionamiento de los dispositivos según una lógica

        //Declaración de dispositivos
        SensorLuz sensorLuz = null;
        SensorPresencia sensorPresencia = null;
        ActuadorBombilla bombilla = null;

        //Se recorren las listas en busca de los dispositivos
        for(Sensor s : sensores){
            if (s.getID().equals("light")){
                sensorLuz = (SensorLuz) s;
            }
            if (s.getID().equals("pir")){
                sensorPresencia = (SensorPresencia) s;
            }
        }
        for(Actuador a : actuadores){
            if (a.getID().equals("bulb")){
                bombilla = (ActuadorBombilla) a;
            }
        }

        //Si no se ha encontrado alguno de los dispositivos necesarios, no se ejecutará la lógica
        if (sensorLuz == null || sensorPresencia == null || bombilla == null) {
            return;
        }

        //Si se detecta presencia y la iluminación es insuficiente, se encenderá la bombilla. Cuando deje de haber presencia se apagará
        if (sensorPresencia.getValor() == 1.0) {
            if (sensorLuz.getValor() < 200.0) {
                bombilla.ejecutarAccion("on");
            }
        } else {
            bombilla.ejecutarAccion("off");
        }

    }
}
