package vista;

import javax.swing.*;

import controlador.ReservaManager;
import modelo.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ModificarReservaInterface extends JFrame {

    private Reserva reserva;
    private ReservaManager reservaManager;
    private JTextField txtFechaLlegada;
    private JTextField txtFechaSalida;
    private JTextField txtNumeroHuespedes;
    private JLabel lblTotal;

    public ModificarReservaInterface(String emailUsuario, Reserva reserva, ReservaManager reservaManager) {
        this.reserva = reserva;
        this.reservaManager = reservaManager;
        initComponents(emailUsuario);
    }

    private void initComponents(String emailUsuario) {
        setTitle("Modificar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblFechaLlegada = new JLabel("Fecha de Llegada (YYYY-MM-DD):");
        txtFechaLlegada = new JTextField(reserva.getFechaInicio().toString());

        JLabel lblFechaSalida = new JLabel("Fecha de Salida (YYYY-MM-DD):");
        txtFechaSalida = new JTextField(reserva.getFechaFin().toString());

        JLabel lblNumeroHuespedes = new JLabel("Número de Huéspedes:");
        txtNumeroHuespedes = new JTextField(String.valueOf(reserva.getNumeroHuespedes()));

        lblTotal = new JLabel("Total: $0.00");

        JButton btnCalcularTotal = new JButton("Calcular Total");
        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarReserva(emailUsuario);
            }
        });

        mainPanel.add(lblFechaLlegada);
        mainPanel.add(txtFechaLlegada);
        mainPanel.add(lblFechaSalida);
        mainPanel.add(txtFechaSalida);
        mainPanel.add(lblNumeroHuespedes);
        mainPanel.add(txtNumeroHuespedes);
        mainPanel.add(lblTotal);
        mainPanel.add(btnCalcularTotal);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(btnModificar);

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
            double total = dias * reserva.getPrecioPorNoche();
            lblTotal.setText("Total: $" + total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de las fechas.");
        }
    }

    private void modificarReserva(String emailUsuario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaLlegada = LocalDate.parse(txtFechaLlegada.getText().trim(), formatter);
            LocalDate fechaSalida = LocalDate.parse(txtFechaSalida.getText().trim(), formatter);
            int numeroHuespedes = Integer.parseInt(txtNumeroHuespedes.getText().trim());

            if (fechaLlegada.isBefore(reserva.getFechaInicio()) || 
                fechaSalida.isAfter(reserva.getFechaFin())) {
                JOptionPane.showMessageDialog(this, "Las fechas de reserva están fuera del rango de disponibilidad de la habitación.");
                JOptionPane.showMessageDialog(this, "Fecha de inicio: " + reserva.getFechaInicio() + 
                                                " Fecha de fin: " + reserva.getFechaFin());
                return;
            }
            if (numeroHuespedes > reserva.getNumeroHuespedes()) {
                JOptionPane.showMessageDialog(this, "El número de huéspedes excede la capacidad. (" + reserva.getNumeroHuespedes() + ")");
                return;
            }

            // Modificar la reserva
            reserva.setFechaInicio(fechaLlegada);
            reserva.setFechaFin(fechaSalida);
            reserva.setNumeroHuespedes(numeroHuespedes);
            reservaManager.modificarReserva(emailUsuario, reserva);
            
            JOptionPane.showMessageDialog(this, "Reserva modificada exitosamente. Reinicie la aplicación para ver los cambios.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos de la reserva.");
        }
    }
}
