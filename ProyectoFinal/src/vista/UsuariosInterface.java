package vista;

import javax.swing.JFrame;

public class UsuariosInterface extends JFrame{

    public UsuariosInterface() {
        
        JFrame ventana = new JFrame("Usuarios");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(300, 300);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new UsuariosInterface();
    }

}
