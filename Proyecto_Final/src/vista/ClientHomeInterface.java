package vista;

import javax.swing.*;

import controlador.ReservaManager;
import controlador.RoomManager;
import controlador.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientHomeInterface extends JFrame {

    private RoomManager roomManager;
    private ReservaManager reservaManager;
    private String emailUsuario;
    @SuppressWarnings("unused")
    private UserManager userManager;

    public ClientHomeInterface(UserManager userManager, RoomManager roomManager, ReservaManager reservaManager, String emailUsuario) {
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.reservaManager = reservaManager;
        this.emailUsuario = emailUsuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cliente - My Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnBuscarHabitacion = new JButton("HABITACIONES");
        JButton btnVerReservas = new JButton("RESERVAS");

        btnBuscarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacionInterface(roomManager, reservaManager, emailUsuario).setVisible(true);
            }
        });

        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservasInterface(emailUsuario, reservaManager, roomManager).setVisible(true);
            }
        });

        mainPanel.add(btnBuscarHabitacion);
        mainPanel.add(btnVerReservas);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
