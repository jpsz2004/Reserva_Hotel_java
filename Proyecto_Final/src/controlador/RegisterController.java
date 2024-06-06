package controlador;

public class RegisterController {
    private UserManager users;
    private boolean registroExitoso = false;

    public RegisterController(UserManager users) {
        this.users = users;
    }

    public boolean registerUser(String tipoIdentificacion, String documentoIdentificacion, String nombre, String apellido,
                                String correo, String direccionResidencia, String ciudadResidencia, String telefono,
                                String contrasena, String confirmarContrasena, String rol) {
        if (contrasena.equals(confirmarContrasena)) {
            users.agregarUsuario(tipoIdentificacion, documentoIdentificacion, nombre, apellido, correo, 
                                 direccionResidencia, ciudadResidencia, telefono, confirmarContrasena, rol);
            return true;
        }
        return false;
    }

    public boolean verificarDatos(String tipoIdentificacion, String documentoIdentificacion, String nombre, String apellido,
                                  String correo, String direccionResidencia, String ciudadResidencia, String telefono,
                                  String contrasena, String confirmarContrasena, String rol) {
        if (tipoIdentificacion.isEmpty() || documentoIdentificacion.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
            correo.isEmpty() || direccionResidencia.isEmpty() || ciudadResidencia.isEmpty() || telefono.isEmpty() ||
            contrasena.isEmpty() || confirmarContrasena.isEmpty() || rol.isEmpty()) {
            registroExitoso = false;
            return registroExitoso;
        } else {
            registroExitoso = true;
            return registroExitoso;
        }
    }
}
