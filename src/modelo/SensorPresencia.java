package modelo;
import java.util.Random;

public class SensorPresencia extends Sensor{ //Subclase para sensores de presencia
    private Random random; //Se define un objeto Random para generar números aleatorios

    //Constructor
    public SensorPresencia(){
        //Se crea el objeto siguiendo el constructor padre y luego se añaden comportamientos específicos
        super("pir", "Sensor_Presencia", "", "on"); //no hay unidad de medida para un valor que será booleano
        this.random = new Random();
        actualizarValor(); //Se genera un valor inicial al instanciar un objeto
    }

    @Override
    public void actualizarValor(){
        if(this.estado.equals("on")){ //Si el sensor no está activo, no se hace nada
            //Se genera un boolean aleatorio. Si es true, se le da a ultimoValor 1.0 (hay presencia). Si sale false, se le da 0.0 (no hay presencia)
            this.ultimoValor = random.nextBoolean() ? 1.0 : 0.0;
        }
        else{
            return;
        }
    }

    @Override
    public String getEstadoActual(){ //Funcion polimórfica para a partir del double ultimoValor devolver un string
        if(!this.estado.equals("on")){ //Si el dispositivo no está encendido, no se hace nada
            return "El dispositivo está apagado";
        }
        else{
            return (this.ultimoValor == 1.0) ? "Movimiento Detectado" : "Sin Movimiento";
        }

    }
}
