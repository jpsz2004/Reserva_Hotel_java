package vista;

import javax.swing.*;

import controlador.RoomManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomeInterface extends JFrame {

    private RoomManager roomManager;

    public AdminHomeInterface(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Administración");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregarHabitacion = new JButton("Agregar Habitación");
        JButton btnEditarHabitacion = new JButton("Editar Habitación");
        JButton btnEliminarHabitacion = new JButton("Eliminar Habitación");
        JButton btnVerificarDisponibilidad = new JButton("Verificar Disponibilidad");

        btnAgregarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarHabitacionInterface(roomManager).setVisible(true);
            }
        });

        btnEditarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarHabitacionInterface(roomManager).setVisible(true);
            }
        });

        btnEliminarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarHabitacionInterface(roomManager).setVisible(true);
            }
        });

        btnVerificarDisponibilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarDisponibilidadInterface(roomManager).setVisible(true);
            }
        });

        mainPanel.add(btnAgregarHabitacion);
        mainPanel.add(btnEditarHabitacion);
        mainPanel.add(btnEliminarHabitacion);
        mainPanel.add(btnVerificarDisponibilidad);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminHomeInterface(new RoomManager()).setVisible(true);
            }
        });
    }
}
