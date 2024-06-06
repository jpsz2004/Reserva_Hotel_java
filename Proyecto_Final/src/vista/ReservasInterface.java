package vista;

import javax.swing.*;

import controlador.ReservaManager;
import controlador.RoomManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservasInterface extends JFrame{

    private String emailUsuario;
    private ReservaManager reservaManager;
    private RoomManager roomManager;

    
    public ReservasInterface(String emailUsuario, ReservaManager reservaManager, RoomManager roomManager) {
        this.emailUsuario = emailUsuario;
        this.reservaManager = reservaManager;
        this.roomManager = roomManager;
        initComponents();
    }

    public void initComponents() {
        setTitle("Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnVerReservas = new JButton("Ver Reservas");
        JButton btnHistorialReservas = new JButton("Historial de Reservas");
        JButton btnBuscarHabitacion = new JButton("Buscar Habitación");
        JButton btnModificarReserva = new JButton("Modificar Reserva");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        JButton btnReservar = new JButton("Reservar");

        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerReservasInterface(emailUsuario, reservaManager).setVisible(true);
            }
        });

        btnHistorialReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistorialReservasInterface(emailUsuario, reservaManager).setVisible(true);
            }
        });

        btnBuscarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacionInterface(roomManager, reservaManager, emailUsuario).setVisible(true);
            }
        });

        btnModificarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerReservasInterface(emailUsuario, reservaManager).setVisible(true);
            }
        });

        btnCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CancelarReservaInterface(emailUsuario, reservaManager).setVisible(true);
            }
        });

        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacionInterface(roomManager, reservaManager, emailUsuario).setVisible(true);
            }
        });

        mainPanel.add(btnBuscarHabitacion);
        mainPanel.add(btnVerReservas);
        mainPanel.add(btnReservar);
        mainPanel.add(btnModificarReserva);
        mainPanel.add(btnCancelarReserva);
        mainPanel.add(btnHistorialReservas);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
