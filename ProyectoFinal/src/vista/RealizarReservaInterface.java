package vista;

import javax.swing.*;

public class RealizarReservaInterface extends JFrame{
	
	public RealizarReservaInterface() {
		
		JFrame ventana = new JFrame("Realizar Reserva");
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setSize(300, 300);

		ventana.setLocationRelativeTo(null); 
		ventana.setVisible(true);
	}

	public static void main(String[] args) {
		new RealizarReservaInterface();
	}
}
