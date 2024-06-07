package vista;

import javax.swing.*;

import controlador.ReservaManager;
import modelo.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerReservasInterface extends JFrame {

    private String emailUsuario;
    private ReservaManager reservaManager;

    public VerReservasInterface(String emailUsuario, ReservaManager reservaManager) {
        this.emailUsuario = emailUsuario;
        this.reservaManager = reservaManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Ver Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Reserva> reservas = reservaManager.obtenerReservasPorUsuario(emailUsuario);

        if (reservas.isEmpty()) {
            mainPanel.add(new JLabel("No tiene reservas hechas."));
        } else {
            for (Reserva reserva : reservas) {
                if(reserva.isActiva() == true){
                    JPanel reservaPanel = new JPanel();
                    reservaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                    reservaPanel.add(new JLabel(reserva.getIdHabitacion() + " - " + reserva.getFechaInicio() + " - " + reserva.getFechaFin()));

                    JButton btnModificar = new JButton("Modificar");
                    btnModificar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ModificarReservaInterface(emailUsuario, reserva, reservaManager).setVisible(true);
                        }
                    });

                    reservaPanel.add(btnModificar);
                    mainPanel.add(reservaPanel);
                }else{
                    JOptionPane.showMessageDialog(this, "No tiene reservas activas.");
                }
            }
        }

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
