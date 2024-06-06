package vista;

import javax.swing.*;

import controlador.RoomManager;
import modelo.Habitacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditarHabitacionInterface extends JFrame {

    private RoomManager roomManager;
    private Habitacion habitacion;

    private JTextField txtId, txtTipo, txtCapacidad, txtPrecioPorNoche, txtComodidades, txtFechaInicio, txtFechaFin;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EditarHabitacionInterface(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Editar Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblId = new JLabel("ID (Buscar):");
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

        
        JButton btnEditar = new JButton("Editar");
        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHabitacion();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarHabitacion();
            }
        });

        mainPanel.add(lblId);
        mainPanel.add(txtId);
        mainPanel.add(new JLabel()); // Espacio en blanco
        mainPanel.add(btnBuscar);
        // mainPanel.add(new JLabel()); // Espacio en blanco
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
        mainPanel.add(new JLabel()); // Espacio en blanco
        mainPanel.add(btnEditar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarHabitacion() {
        String id = txtId.getText();
        habitacion = roomManager.buscarHabitacion(id);

        if (habitacion != null) {
            txtTipo.setText(habitacion.getTipo());
            txtCapacidad.setText(String.valueOf(habitacion.getCapacidad()));
            txtPrecioPorNoche.setText(String.valueOf(habitacion.getPrecioPorNoche()));
            txtComodidades.setText(habitacion.getComodidades());
            txtFechaInicio.setText(habitacion.getFechaInicioDisponibilidad().format(formatter));
            txtFechaFin.setText(habitacion.getFechaFinDisponibilidad().format(formatter));
        } else {
            JOptionPane.showMessageDialog(this, "Habitación no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarHabitacion() {
        if (habitacion != null) {
            String tipo = txtTipo.getText();
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            double precioPorNoche = Double.parseDouble(txtPrecioPorNoche.getText());
            String comodidades = txtComodidades.getText();
            LocalDateTime fechaInicioDisponibilidad = LocalDateTime.parse(txtFechaInicio.getText(), formatter);
            LocalDateTime fechaFinDisponibilidad = LocalDateTime.parse(txtFechaFin.getText(), formatter);

            habitacion.setTipo(tipo);
            habitacion.setCapacidad(capacidad);
            habitacion.setPrecioPorNoche(precioPorNoche);
            habitacion.setComodidades(comodidades);
            habitacion.setFechaInicioDisponibilidad(fechaInicioDisponibilidad);
            habitacion.setFechaFinDisponibilidad(fechaFinDisponibilidad);

            roomManager.editarHabitacion(habitacion);

            JOptionPane.showMessageDialog(this, "Habitación editada exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Primero debe buscar una habitación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
