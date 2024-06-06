package vista;

import javax.swing.*;
import controlador.ReservaManager;
import modelo.Reserva;
import java.awt.*;
import java.util.List;

public class HistorialReservasInterface extends JFrame {

    private String emailUsuario;
    private ReservaManager reservaManager;

    public HistorialReservasInterface(String emailUsuario, ReservaManager reservaManager) {
        this.emailUsuario = emailUsuario;
        this.reservaManager = reservaManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Ver Historial de Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Reserva> reservas = reservaManager.obtenerHistorialReservasPorUsuario(emailUsuario);
        for (Reserva reserva : reservas) {
            String estado = reserva.isActiva() ? "Activa" : "Cancelada";
            JLabel lblReserva = new JLabel(reserva.getIdHabitacion() + " - " + reserva.getFechaInicio() + " a " + reserva.getFechaFin() + " (" + estado + ")");
            mainPanel.add(lblReserva);
        }

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
