package modelo;
import java.util.Random;

public class SensorHumo extends Sensor{ //Subclase para sensores de humo
    private Random random; //Se define un objeto Random para generar números aleatorios

    //Constructor
    public SensorHumo(){
        //Se crea el objeto siguiendo el constructor padre y luego se añaden comportamientos específicos
        super("smoke", "Sensor_Humo", " ppm", "on"); //ppm será la unidad de medida del humo, partes por millón
        this.random = new Random();
        actualizarValor(); //Se genera un valor inicial al instanciar un objeto
    }

    @Override
    public void actualizarValor(){
        if(this.estado.equals("on")){ //Si el sensor no está activo, se lanza una excepción
            double valorRandom = 350.0 + (random.nextDouble() * 1650.0); //Se genera un numero del 350 (aire limpio) hasta el 2000 (aire muy contaminado)
            //Para limitar este valor a dos decimales, se multiplica por 100, se redondea a las unidades y luego se divide entre 100
            this.ultimoValor = Math.round(valorRandom * 100.0) / 100.0; //Le da a ultimoValor el numero generado redondeado a dos decimales
        }
        else{
            return;
        }
    }
}
