package controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UserFileManager {

    private static final String FILE_NAME = "usuarios.dat";

    public static void guardarUsuarios(List<Usuario> usuarios) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        }
    }

    // @SuppressWarnings("unchecked")
    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Usuario>) ois.readObject();
        }
    }

}
