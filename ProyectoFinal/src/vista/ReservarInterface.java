package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import modelo.Habitacion;
import controlador.RoomManager;

public class ReservarInterface extends JFrame {
    private Habitacion habitacion;
    private RoomManager roomManager;

    private JTextField txtNombre;
    private JTextField txtFechaLlegada;
    private JTextField txtFechaSalida;
    private JTextField txtNumeroHuespedes;
    private JLabel lblTotal;

    public ReservarInterface(Habitacion habitacion, RoomManager roomManager) {
        this.habitacion = habitacion;
        this.roomManager = roomManager;
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
        mainPanel.add(new JLabel()); // Empty label for spacing
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

    @SuppressWarnings("unused")
    private void realizarReserva() {
        String nombre = txtNombre.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaLlegada = LocalDate.parse(txtFechaLlegada.getText().trim(), formatter);
            LocalDate fechaSalida = LocalDate.parse(txtFechaSalida.getText().trim(), formatter);
            int numeroHuespedes = Integer.parseInt(txtNumeroHuespedes.getText().trim());

            // Verifica si la habitación ya está reservada
            if (habitacion.isReservada()) {
                JOptionPane.showMessageDialog(this, "La habitación ya está reservada");
                return;
            }

            // Realizar la reserva
            habitacion.setReservada(true);
            roomManager.editarHabitacion(habitacion); // Actualiza el estado de la habitación en el archivo

            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos de la reserva");
        }
    }
}
