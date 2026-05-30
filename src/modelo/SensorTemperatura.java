package modelo;
import java.util.Random;

public class SensorTemperatura extends Sensor{ //Subclase para sensores de temperatura
    private Random random; //Se define un objeto Random para generar números aleatorios

    //Constructor
    public SensorTemperatura(){
        //Se crea el objeto siguiendo el constructor padre y luego se añaden comportamientos específicos
        super("temp", "Sensor_Temperatura", " grados centígrados", "on");
        this.random = new Random();
        actualizarValor(); //Se genera un valor inicial al instanciar un objeto
    }

    @Override
    public void actualizarValor(){
        if(this.estado.equals("on")){ //Si el sensor no está activo, no se hace nada
            double valorRandom = random.nextDouble() * 50; //Genera un numero del 0 al 50
            //Para limitar este valor a dos decimales, se multiplica por 100, se redondea a las unidades y luego se divide entre 100
            this.ultimoValor = Math.round(valorRandom * 100.0) / 100.0; //Le da a ultimoValor el numero generado redondeado a dos decimales
        }
        else{
            return;
        }
    }
}
