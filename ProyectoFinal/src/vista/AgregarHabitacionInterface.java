package vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import controlador.RoomManager;
import modelo.Habitacion;

public class AgregarHabitacionInterface extends JFrame {

    private RoomManager roomManager;

    private JTextField txtId, txtTipo, txtCapacidad, txtPrecioPorNoche, txtComodidades, txtFechaInicio, txtFechaFin;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AgregarHabitacionInterface(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Agregar Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField(20);
        JLabel lblTipo = new JLabel("Tipo:");
        txtTipo = new JTextField(20);
        JLabel lblCapacidad = new JLabel("Capacidad:");
        txtCapacidad = new JTextField(20);
        JLabel lblPrecioPorNoche = new JLabel("Precio por Noche:");
        txtPrecioPorNoche = new JTextField(20);
        JLabel lblComodidades = new JLabel("Comodidades:");
        txtComodidades = new JTextField(20);
        JLabel lblFechaInicio = new JLabel("Fecha Inicio (yyyy-MM-dd HH:mm):");
        txtFechaInicio = new JTextField(20);
        JLabel lblFechaFin = new JLabel("Fecha Fin (yyyy-MM-dd HH:mm):");
        txtFechaFin = new JTextField(20);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarHabitacion();
            }
        });

        mainPanel.add(lblId);
        mainPanel.add(txtId);
        mainPanel.add(lblTipo);
        mainPanel.add(txtTipo);
        mainPanel.add(lblCapacidad);
        mainPanel.add(txtCapacidad);
        mainPanel.add(lblPrecioPorNoche);
        mainPanel.add(txtPrecioPorNoche);
        mainPanel.add(lblComodidades);
        mainPanel.add(txtComodidades);
        mainPanel.add(lblFechaInicio);
        mainPanel.add(txtFechaInicio);
        mainPanel.add(lblFechaFin);
        mainPanel.add(txtFechaFin);
        mainPanel.add(new JLabel());
        mainPanel.add(btnAgregar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarHabitacion() {
        String id = txtId.getText();
        String tipo = txtTipo.getText();
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        double precioPorNoche = Double.parseDouble(txtPrecioPorNoche.getText());
        String comodidades = txtComodidades.getText();
        LocalDateTime fechaInicioDisponibilidad = LocalDateTime.parse(txtFechaInicio.getText(), formatter);
        LocalDateTime fechaFinDisponibilidad = LocalDateTime.parse(txtFechaFin.getText(), formatter);
        Boolean reservada = false;

        Habitacion habitacion = new Habitacion(id, tipo, capacidad, precioPorNoche, comodidades, fechaInicioDisponibilidad, fechaFinDisponibilidad, reservada);
        roomManager.agregarHabitacion(habitacion);

        JOptionPane.showMessageDialog(null, "Habitación agregada exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
