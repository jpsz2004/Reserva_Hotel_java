package vista;

import javax.swing.*;

import controlador.RoomManager;
import modelo.Habitacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetalleHabitacionInterface extends JFrame {

    private Habitacion habitacion;
    private RoomManager roomManager;
    private String userEmail;

    public DetalleHabitacionInterface(Habitacion habitacion, RoomManager roomManager, String userEmail) {
        this.habitacion = habitacion;
        this.roomManager = roomManager;
        this.userEmail = userEmail;
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de la Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblDetalles = new JLabel("<html><b>Detalles de la Habitación:</b><br>"
                + "ID: " + habitacion.getId() + "<br>"
                + "Tipo: " + habitacion.getTipo() + "<br>"
                + "Capacidad: " + habitacion.getCapacidad() + "<br>"
                + "Precio por noche: $" + habitacion.getPrecioPorNoche() + "<br>"
                + "Comodidades: " + habitacion.getComodidades() + "</html>");

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservarInterface(habitacion, roomManager, userEmail).setVisible(true);
            }
        });

        mainPanel.add(lblDetalles, BorderLayout.CENTER);
        mainPanel.add(btnReservar, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }
}
