package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UserManager;
import controlador.RoomManager;

public class MainInterface extends JFrame {

    private static RoomManager roomManager; // Añadido como atributo de la clase

    public MainInterface(UserManager userManager, RoomManager roomManager) {
        MainInterface.roomManager = new RoomManager(); // Inicializado en el constructor
        initComponents(userManager, roomManager);
    }

    private void initComponents(UserManager userManager, RoomManager roomManager) {
        setTitle("Bienvenido a My Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Bienvenido a My Hotel", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirLogin(userManager, roomManager);
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro(userManager);
            }
        });

        mainPanel.add(welcomeLabel);
        mainPanel.add(btnIniciarSesion);
        mainPanel.add(btnRegistrar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void abrirLogin(UserManager userManager, RoomManager roomManager) {
        LoginInterface loginInterface = new LoginInterface(userManager, roomManager);
        loginInterface.setVisible(true);
    }

    private void abrirRegistro(UserManager userManager) {
        new RegisterInterface(userManager).setVisible(true);
    }

    public static void main(String[] args) {
        UserManager users = new UserManager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainInterface(users, roomManager).setVisible(true);
            }
        });
    }
}
