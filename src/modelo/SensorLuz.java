package modelo;
import java.util.Random;

public class SensorLuz extends Sensor{ //Subclase para sensores de luz
    private Random random; //Se define un objeto Random para generar números aleatorios

    //Constructor
    public SensorLuz(){
        //Se crea el objeto siguiendo el constructor padre y luego se añaden comportamientos específicos
        super("light", "Sensor_Luz", " lux", "on"); //lux es la unidad de medidad para la iluminancia, cantidad de luz que llega a una superficie
        this.random = new Random();
        actualizarValor(); //Se genera un valor inicial al instanciar un objeto
    }

    @Override
    public void actualizarValor(){
        if(this.estado.equals("on")){ //Si el sensor no está activo, no se hace nada
            double valorRandom = random.nextDouble() * 1000; //Genera un numero del 0 al 1000
            //Para limitar este valor a dos decimales, se multiplica por 100, se redondea a las unidades y luego se divide entre 100
            this.ultimoValor = Math.round(valorRandom * 100.0) / 100.0; //Le da a ultimoValor el numero generado redondeado a dos decimales
        }
        else{
            return;
        }
    }
}
