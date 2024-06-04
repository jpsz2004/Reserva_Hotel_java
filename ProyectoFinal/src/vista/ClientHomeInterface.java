package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UserManager;
import controlador.RoomManager;

public class ClientHomeInterface extends JFrame {
    
    private RoomManager roomManager;
    public ClientHomeInterface(UserManager userManager, RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Bienvenido - My Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);
        setLocationRelativeTo(null); 

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
        
        // Label para el título
        JLabel tituloLabel = new JLabel("Bienvenido a My Hotel");
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 24)); 
        mainPanel.add(tituloLabel);
        
        // Crear un panel para los botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); 

        // Crear botones
        JButton reservasButton = new JButton("RESERVAS");
        JButton habitacionButton = new JButton("BUSCAR HABITACIONES");
        JButton usuariosButton = new JButton("USUARIOS");

        // Dimension de los botones principales
        reservasButton.setPreferredSize(new Dimension(130, 40));
        habitacionButton.setPreferredSize(new Dimension(130, 40));
        usuariosButton.setPreferredSize(new Dimension(130, 40));

        // Agregar los botones al panel de botones
        botonesPanel.add(reservasButton);
        botonesPanel.add(habitacionButton);
        botonesPanel.add(usuariosButton);

        mainPanel.add(botonesPanel);

        // Dar accion a los botones
        reservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservasInterface(null).setVisible(true);
            }
        });

        habitacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacionInterface(roomManager).setVisible(true);
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuariosInterface().setVisible(true);
            }
        });

        add(mainPanel);
    }

    public static void main(String[] args) {
        UserManager users = new UserManager();
        RoomManager rooms = new RoomManager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientHomeInterface(users, rooms).setVisible(true);
            }
        });
    }
}
