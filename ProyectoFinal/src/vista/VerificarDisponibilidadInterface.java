package vista;

import javax.swing.*;

import java.awt.*;
import controlador.RoomManager;
import modelo.Habitacion;

public class VerificarDisponibilidadInterface extends JFrame {

    private RoomManager roomManager;

    public VerificarDisponibilidadInterface(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Disponibilidad de Habitaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        StringBuilder sb = new StringBuilder();
        for (Habitacion habitacion : roomManager.obtenerHabitaciones()) {
            sb.append("ID: ").append(habitacion.getId()).append("\n");
            sb.append("Tipo: ").append(habitacion.getTipo()).append("\n");
            sb.append("Capacidad: ").append(habitacion.getCapacidad()).append("\n");
            sb.append("Precio por Noche: ").append(habitacion.getPrecioPorNoche()).append("\n");
            sb.append("Comodidades: ").append(habitacion.getComodidades()).append("\n\n");
            sb.append("Fecha Inicio: ").append(habitacion.getFechaInicioDisponibilidad()).append("\n");
            sb.append("Fecha Fin: ").append(habitacion.getFechaFinDisponibilidad()).append("\n\n");
            sb.append("Reservada: ").append(habitacion.isReservada()).append("\n\n");
        }
        textArea.setText(sb.toString());

        mainPanel.add(scrollPane);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
