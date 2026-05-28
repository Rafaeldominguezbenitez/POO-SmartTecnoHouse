package modelo;

import java.util.List;

public class ReglaCalidadAire implements Regla {
    //Esta clase controlará la potencia del ventilador y del humidificador según la cantidad de CO2 detectado en el aire

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) { //Metodo que controlará el funcionamiento de los dispositivos según una lógica

        //Definicion de dispositivos
        SensorHumo sensorHumo = null;
        ActuadorVentilador ventilador = null;
        ActuadorHumidificador humidificador = null;

        //Se buscan los dispositivos dentro de las listas pasadas por parámetro
        for (Sensor s : sensores) {
            if (s.getID().equals("smoke")) sensorHumo = (SensorHumo) s;
        }
        for (Actuador a : actuadores) {
            if (a.getID().equals("fan")) ventilador = (ActuadorVentilador) a;
            if (a.getID().equals("humi")) humidificador = (ActuadorHumidificador) a;
        }


        //Si falta alguno de los dispositivos necesarios, no se ejecutará la lógica
        if (sensorHumo == null || ventilador == null || humidificador == null) {
            return;
        }


        // Si hay valores muy altos de humo, se pondrán el ventilador y el humidificador al máximo. Cuando se haya controlado, se apagarán
        if (sensorHumo.getValor() > 800.0) { //La regla solo saltará si se pasa el límite de CO2 establecido, hasta entonces el ventilador será controlado por ReglaControlTemperatura
            ventilador.ejecutarAccion("high");
            humidificador.ejecutarAccion("high");
        } else if (sensorHumo.getValor() < 500.0) { //Cuando se haya controlado el nivel de CO2, se apagará el humidificador
            humidificador.ejecutarAccion("off");

            //En la siguiente iteracion del bucle que recorre constantemente las reglas, ReglaControlTemperatura se encargará de ajustar el nivel del ventilador según su propia lógica
            //Pero por si acaso no se volviese a iterar y para no depender directamente del exterior, se apagará el ventilador de forma predeterminada tras controlar los niveles de CO2. Si se vuelve a iterar y ReglaControlTemperatura
            //lo ve adecuado, volverá a encender el ventilador a la velocidad que vea necesaria
            if (ventilador.getEstadoActual().equals("high")) {
                ventilador.ejecutarAccion("off");
            }

        }
    }
}