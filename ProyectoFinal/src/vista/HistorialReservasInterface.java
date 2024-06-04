package vista;

import javax.swing.*;

public class HistorialReservasInterface extends JFrame{
    
    public HistorialReservasInterface() {
        
        JFrame ventana = new JFrame("Historial de Reservas");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(300, 300);

        ventana.setLocationRelativeTo(null); 
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new HistorialReservasInterface();
    }
}
