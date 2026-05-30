package vista;

//se importan las clases y librerías necesarias
import controlador.Controlador;
import modelo.Sensor;
import modelo.Actuador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Vista extends JFrame {
    private final Controlador controlador; //Referencia al controlador
    private JTextArea areaPantalla;

    //Constructor
    public Vista(Controlador controlador){
        this.controlador = controlador; //Inicializa el controlador

        //Configuración visual de la ventana
        setTitle("Smart TecnoHouse SA | Control IoT"); //Título
        setSize(750, 600); //Tamaño
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Se cancela el funcionamiento por defecto de la "x" de cierre para crearle un comportamiento personalizado
        setLocationRelativeTo(null); //Se centra la ventana
        setLayout(new BorderLayout(10, 10));

        //Zona que muestra el estado actual de los dispositivos (centro)
        areaPantalla = new JTextArea(); //Se crea un area de texto (para mostrar los datos)
        areaPantalla.setEditable(false); //El usuario no debe poder escribir donde se muestran los datos
        areaPantalla.setFont(new Font("Monospaced", Font.PLAIN, 12)); //Se da formato a la tipografía
        add(new JScrollPane(areaPantalla), BorderLayout.CENTER);


        //Botones:

        //Panel
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 2, 2)); //Grid Layout de 3 filas y una columna para los botones

        //Bombilla (fila 1)
        JPanel panelBombilla = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblBombilla = new JLabel("Iluminación: ");
        lblBombilla.setFont(new Font("Arial", Font.BOLD, 11));
        JButton btnBombillaOn = new JButton("Luz on");
        JButton btnBombillaOff = new JButton("Luz off");

        panelBombilla.add(lblBombilla);
        panelBombilla.add(btnBombillaOn);
        panelBombilla.add(btnBombillaOff);

        //Ventilador (fila 2)
        JPanel panelVentilador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblVentilador = new JLabel("Ventilador: ");
        lblVentilador.setFont(new Font("Arial", Font.BOLD, 11));
        JButton btnVentiladorOn = new JButton("Ventilador on");
        JButton btnVentiladorOff = new JButton("Ventilador off");
        JButton btnVentiladorLow = new JButton("Ventilador low");
        JButton btnVentiladorMedium = new JButton("Ventilador medium");
        JButton btnVentiladorHigh = new JButton("Ventilador high");

        panelVentilador.add(lblVentilador);
        panelVentilador.add(btnVentiladorOn);
        panelVentilador.add(btnVentiladorOff);
        panelVentilador.add(btnVentiladorLow);
        panelVentilador.add(btnVentiladorMedium);
        panelVentilador.add(btnVentiladorHigh);

        //Humidificador (fila 3)
        JPanel panelHumi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblHumi = new JLabel("Humidificador: ");
        lblHumi.setFont(new Font("Arial", Font.BOLD, 11));
        JButton btnHumiOn = new JButton("Humidificador on");
        JButton btnHumiOff = new JButton("Humidificador off");
        JButton btnHumiLow = new JButton("Humidificador low");
        JButton btnHumiHigh = new JButton("Humidificador high");

        panelHumi.add(lblHumi);
        panelHumi.add(btnHumiOn);
        panelHumi.add(btnHumiOff);
        panelHumi.add(btnHumiLow);
        panelHumi.add(btnHumiHigh);

        //Se añaden las 3 filas al panel
        panelBotones.add(panelBombilla);
        panelBotones.add(panelVentilador);
        panelBotones.add(panelHumi);

        //Se añade el panel con los botones a la parte inferior del panel principal
        add(panelBotones, BorderLayout.SOUTH);


        //Asignacion de eventos a los botones:
        btnBombillaOn.addActionListener(e -> controlador.cambiarEstadoActuador("bulb", "on"));
        btnBombillaOff.addActionListener(e -> controlador.cambiarEstadoActuador("bulb", "off"));
        btnVentiladorOff.addActionListener(e -> controlador.cambiarEstadoActuador("fan", "off"));
        btnVentiladorOn.addActionListener(e -> controlador.cambiarEstadoActuador("fan", "on"));
        btnVentiladorLow.addActionListener(e -> controlador.cambiarEstadoActuador("fan", "low"));
        btnVentiladorMedium.addActionListener(e -> controlador.cambiarEstadoActuador("fan", "medium"));
        btnVentiladorHigh.addActionListener(e -> controlador.cambiarEstadoActuador("fan", "high"));
        btnHumiOn.addActionListener(e -> controlador.cambiarEstadoActuador("humi", "on"));
        btnHumiOff.addActionListener(e -> controlador.cambiarEstadoActuador("humi", "off"));
        btnHumiLow.addActionListener(e -> controlador.cambiarEstadoActuador("humi", "low"));
        btnHumiHigh.addActionListener(e -> controlador.cambiarEstadoActuador("humi", "high"));


        //Gestion de cierre
        configurarCierre();
        //Se carga el mostrador de estados por primera vez:
        actualizarInterfaz();

    }

    //Metodo que actualiza la interfaz
    public void actualizarInterfaz() {
        //Cabecera
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADO ACTUAL DEL SISTEMA IOT\n\n");

        //Se crea un apartado para cada sensor
        sb.append("SENSORES\n");
        for (Sensor s : controlador.obtenerSensores()) {
            sb.append("- ").append(s.getNombre()).append(": ").append(s.getEstadoActual()).append("\n");
        }

        //Se crea un apartado para cada actuador
        sb.append("\nACTUADORES\n");
        for (Actuador a : controlador.obtenerActuadores()) {
            sb.append("- ").append(a.getNombre()).append(" [ID: ").append(a.getID()).append("] -> Estado: ").append(a.getEstadoActual()).append("\n");
        }

        //Se añade todo este texto a la pantalla
        areaPantalla.setText(sb.toString());
    }

    //Metodo que controla el cierre
    private void configurarCierre() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.cerrarAplicacion(); //LLama al metodo del controlador que guarda los datos y prepara el cierre
                dispose(); //Se "destruye" la ventana
                System.exit(0); //Se cierra el programa
            }
        });
    }
}
