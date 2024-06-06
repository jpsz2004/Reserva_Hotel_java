package controlador;
import java.util.List;

import modelo.Usuario;

public class LoginController {

    public UserManager users;
    private boolean loginExitoso = false;

    public LoginController(UserManager usuarios) {
		this.users = usuarios;
	}

    //Verificar si el correo existe
    public boolean verificarCorreo(String correo) {
        List<Usuario> usuarios = users.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    //Verificar si la contraseña es correcta

    public boolean verificarContrasena(String correo, String contrasena) {
        List<Usuario> usuarios = users.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    //Metodo para obtener el rol del usuario
    public String obtenerRol(String correo){
        Usuario usuario = users.obtenerUsuarioPorCorreo(correo);
        return (usuario != null) ? usuario.getRol() : null;
    }

    //Metodo para hacer login
    public boolean login(String correo, String contrasena) {
        if (verificarCorreo(correo) && verificarContrasena(correo, contrasena)) {
            loginExitoso = true;
        }
        return loginExitoso;
    }

    public UserManager getUsers() {
        return users;
    }
}
