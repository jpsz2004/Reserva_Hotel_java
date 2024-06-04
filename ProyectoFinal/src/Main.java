import javax.swing.SwingUtilities;
import controlador.UserManager;
import controlador.RoomManager;
import vista.MainInterface;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de UserManager y RoomManager para manejar los usuarios y habitaciones
        UserManager users = new UserManager();
        RoomManager roomManager = new RoomManager(); // Inicializa RoomManager aquí
        
        // Crear una instancia de MainInterface y pasarle las instancias de UserManager y RoomManager
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainInterface main = new MainInterface(users, roomManager);
                main.setVisible(true);
            }
        });
    }
}
