package vista;

import javax.swing.*;

import controlador.ReservaManager;
import controlador.RoomManager;
import controlador.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {

    private static RoomManager roomManager; // Añadido como atributo de la clase

    public MainInterface(UserManager userManager, RoomManager roomManager, ReservaManager reservaManager) {
        MainInterface.roomManager = new RoomManager(); // Inicializado en el constructor
        initComponents(userManager, roomManager, reservaManager);
    }

    private void initComponents(UserManager userManager, RoomManager roomManager, ReservaManager reservaManager) {
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
                abrirLogin(userManager, roomManager, reservaManager);
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

    private void abrirLogin(UserManager userManager, RoomManager roomManager, ReservaManager reservaManager) {
        LoginInterface loginInterface = new LoginInterface(userManager, roomManager, reservaManager);
        loginInterface.setVisible(true);
    }

    private void abrirRegistro(UserManager userManager) {
        new RegisterInterface(userManager).setVisible(true);
    }

    public static void main(String[] args) {
        UserManager users = new UserManager();
        ReservaManager reservaManager = new ReservaManager(roomManager);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainInterface(users, roomManager, reservaManager).setVisible(true);
            }
        });
    }
}
