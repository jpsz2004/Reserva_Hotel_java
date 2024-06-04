package vista;

import javax.swing.*;
import modelo.Habitacion;
import controlador.RoomManager;

public class DetalleHabitacionInterface extends JFrame {

    private Habitacion habitacion;
    private RoomManager roomManager;

    public DetalleHabitacionInterface(Habitacion habitacion, RoomManager roomManager) {
        this.habitacion = habitacion;
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de la Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(new JLabel("Tipo de Habitación: " + habitacion.getTipo()));
        mainPanel.add(new JLabel("Capacidad: " + habitacion.getCapacidad() + " personas"));
        mainPanel.add(new JLabel("Precio por Noche: $" + habitacion.getPrecioPorNoche()));
        mainPanel.add(new JLabel("Comodidades: " + habitacion.getComodidades()));

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> new ReservarInterface(habitacion, roomManager).setVisible(true));
        mainPanel.add(btnReservar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }
}
