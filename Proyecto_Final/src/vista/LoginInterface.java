package vista;

import javax.swing.*;

import controlador.LoginController;
import controlador.ReservaManager;
import controlador.RoomManager;
import controlador.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {

    private RoomManager roomManager;
    private LoginController loginController;
    private ReservaManager reservaManager;

    public LoginInterface(UserManager users, RoomManager roomManager, ReservaManager reservaManager) {
        this.loginController = new LoginController(users);
        this.roomManager = roomManager;
        this.reservaManager = reservaManager;
        crearComponentes();
    }

    // Componentes de la interfaz
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar;

    // Constructor de la interfaz
    public void crearComponentes() {
        setTitle("My Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Crear paneles
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField(20);

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(20);

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnRegistrar = new JButton("Registrar");

        // Agregar acciones a los botones
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Configurar ActionListener para el botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterInterface(loginController.getUsers()).setVisible(true);
            }
        });

        botonPanel.add(btnIniciarSesion);
        botonPanel.add(btnRegistrar);

        // Agregar paneles al panel principal
        mainPanel.add(lblCorreo);
        mainPanel.add(txtCorreo);
        mainPanel.add(lblContrasena);
        mainPanel.add(txtContrasena);
        mainPanel.add(botonPanel);

        // Agregar panel principal al JFrame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());
        if (loginController.login(correo, contrasena)) {
            String rol = loginController.obtenerRol(correo);
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            dispose();

            if (rol.equals("Administrador")) {
                new AdminHomeInterface(roomManager).setVisible(true);
            } else {
                new ClientHomeInterface(loginController.getUsers(), roomManager, reservaManager, correo).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            txtContrasena.setText("");
            txtCorreo.setText("");
        }
    }
}
