package vista;

import javax.swing.*;

import controlador.RoomManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarHabitacionInterface extends JFrame {

    private RoomManager roomManager;
    private JTextField txtId;

    public EliminarHabitacionInterface(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField(20);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarHabitacion();
            }
        });

        mainPanel.add(lblId);
        mainPanel.add(txtId);
        mainPanel.add(new JLabel());
        mainPanel.add(btnEliminar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void eliminarHabitacion() {
        String id = txtId.getText();
        roomManager.eliminarHabitacion(id);

        if(id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la habitación a eliminar.");
            return;
        }

        //verificar si la habitacion existe
        if(roomManager.buscarHabitacion(id) == null) {
            JOptionPane.showMessageDialog(this, "La habitación con ID " + id + " no existe.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Habitación eliminada exitosamente.");
        dispose();
    }

}
