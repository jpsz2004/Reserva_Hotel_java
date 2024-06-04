package vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservasInterface extends JFrame{
	
	public ReservasInterface(JFrame parent) {
		
        JFrame ventana = new JFrame("Reservas");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(250, 300);

        // Crear un panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Usar un BoxLayout vertical
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear un panel para los botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30, 30));

        // Crear botones
        JButton btnReservar = new JButton("Realizar reserva");
        JButton btnConsultar = new JButton("Ver reservas");
        JButton btnHistorial = new JButton("Historial de reservas");

        // Dimension de los botones principales
        btnReservar.setPreferredSize(new Dimension(160, 40));
        btnConsultar.setPreferredSize(new Dimension(160, 40));
        btnHistorial.setPreferredSize(new Dimension(160, 40));

        // Agregar los botones al panel de botones
        botonesPanel.add(btnReservar);
        botonesPanel.add(btnConsultar);
        botonesPanel.add(btnHistorial);

        mainPanel.add(botonesPanel);

        // Se agrega el panel principal al marco
        ventana.getContentPane().add(mainPanel);
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        ventana.setVisible(true);

        // Dar accion a los botones
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RealizarReservaInterface();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerReservasInterface();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistorialReservasInterface();
            }
        });

	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReservasInterface(null);
            }
        });
    }
}
