package vista;

import javax.swing.*;

import controlador.ReservaManager;
import controlador.RoomManager;
import modelo.Habitacion;
import modelo.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservarInterface extends JFrame {

    private Habitacion habitacion;
    private RoomManager roomManager;
    private String userEmail;

    private JTextField txtNombre;
    private JTextField txtFechaLlegada;
    private JTextField txtFechaSalida;
    private JTextField txtNumeroHuespedes;
    private JLabel lblTotal;

    public ReservarInterface(Habitacion habitacion, RoomManager roomManager, String userEmail) {
        this.habitacion = habitacion;
        this.roomManager = roomManager;
        this.userEmail = userEmail;
        initComponents();
    }

    private void initComponents() {
        setTitle("Realizar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblFechaLlegada = new JLabel("Fecha de Llegada (YYYY-MM-DD):");
        txtFechaLlegada = new JTextField();

        JLabel lblFechaSalida = new JLabel("Fecha de Salida (YYYY-MM-DD):");
        txtFechaSalida = new JTextField();

        JLabel lblNumeroHuespedes = new JLabel("Número de Huéspedes:");
        txtNumeroHuespedes = new JTextField();

        lblTotal = new JLabel("Total: $0.00");

        JButton btnCalcularTotal = new JButton("Calcular Total");
        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva();
            }
        });

        mainPanel.add(lblNombre);
        mainPanel.add(txtNombre);
        mainPanel.add(lblFechaLlegada);
        mainPanel.add(txtFechaLlegada);
        mainPanel.add(lblFechaSalida);
        mainPanel.add(txtFechaSalida);
        mainPanel.add(lblNumeroHuespedes);
        mainPanel.add(txtNumeroHuespedes);
        mainPanel.add(lblTotal);
        mainPanel.add(btnCalcularTotal);
        mainPanel.add(new JLabel()); 
        mainPanel.add(btnReservar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void calcularTotal() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaLlegada = LocalDate.parse(txtFechaLlegada.getText().trim(), formatter);
            LocalDate fechaSalida = LocalDate.parse(txtFechaSalida.getText().trim(), formatter);
            long dias = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
            double total = dias * habitacion.getPrecioPorNoche();
            lblTotal.setText("Total: $" + total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de las fechas");
        }
    }

    private void realizarReserva() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaLlegada = LocalDate.parse(txtFechaLlegada.getText().trim(), formatter);
            LocalDate fechaSalida = LocalDate.parse(txtFechaSalida.getText().trim(), formatter);
            int numeroHuespedes = Integer.parseInt(txtNumeroHuespedes.getText().trim());

            // Verificar si las fechas están dentro del rango de disponibilidad
            if (fechaLlegada.isBefore(habitacion.getFechaInicioDisponibilidad().toLocalDate()) || 
                fechaSalida.isAfter(habitacion.getFechaFinDisponibilidad().toLocalDate())) {
                JOptionPane.showMessageDialog(this, "Las fechas de reserva están fuera del rango de disponibilidad de la habitación.");
                JOptionPane.showMessageDialog(this, "Fecha de inicio: " + habitacion.getFechaInicioDisponibilidad().toLocalDate() + 
                                                " Fecha de fin: " + habitacion.getFechaFinDisponibilidad().toLocalDate());
                return;
            }

            // Verificar la capacidad de la habitación
            if (numeroHuespedes > habitacion.getCapacidad()) {
                JOptionPane.showMessageDialog(this, "El número de huéspedes excede la capacidad de la habitación. (" + habitacion.getCapacidad() + ")");
                return;
            }

            // Verifica si la habitación ya está reservada
            if (habitacion.isReservada()) {
                JOptionPane.showMessageDialog(this, "La habitación ya está reservada");
                return;
            }

            // Crear una nueva reserva
            Reserva reserva = new Reserva(userEmail, habitacion.getId(), fechaLlegada, fechaSalida, numeroHuespedes, habitacion.getPrecioPorNoche());

            // Guardar la reserva usando ReservaManager
            ReservaManager reservaManager = new ReservaManager(roomManager);
            reservaManager.agregarReserva(reserva);

            // Actualizar el estado de la habitación a reservada
            habitacion.setReservada(true);
            roomManager.editarHabitacion(habitacion); // Actualiza el estado de la habitación en el archivo

            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos de la reserva");
        }
    }
}
