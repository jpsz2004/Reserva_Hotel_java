package vista;

import javax.swing.*;
import controlador.ReservaManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelarReservaInterface extends JFrame {

    private String emailUsuario;
    private ReservaManager reservaManager;
    private JTextField txtIdHabitacion;

    public CancelarReservaInterface(String emailUsuario, ReservaManager reservaManager) {
        this.emailUsuario = emailUsuario;
        this.reservaManager = reservaManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cancelar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblIdHabitacion = new JLabel("ID de Habitación:");
        txtIdHabitacion = new JTextField();

        JButton btnCancelar = new JButton("Cancelar Reserva");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarReserva();
            }
        });

        mainPanel.add(lblIdHabitacion);
        mainPanel.add(txtIdHabitacion);
        mainPanel.add(new JLabel()); 
        mainPanel.add(btnCancelar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cancelarReserva() {
        String idHabitacion = txtIdHabitacion.getText().trim();
        if(idHabitacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID de la habitación.");
            return;
        }
        //Que el ID exista  
        if (!reservaManager.existeReserva(emailUsuario, idHabitacion)) {
            JOptionPane.showMessageDialog(this, "No tienes una reserva para la habitacion con ID." + idHabitacion);
            return;
        }
        try {
            reservaManager.cancelarReserva(emailUsuario, idHabitacion);
            JOptionPane.showMessageDialog(this, "Reserva cancelada exitosamente.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cancelar la reserva.");
        }
    }
}
