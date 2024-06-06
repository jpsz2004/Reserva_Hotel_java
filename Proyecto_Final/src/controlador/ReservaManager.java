package controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Reserva;

public class ReservaManager {

    private static final String ARCHIVO_RESERVAS = "reservas.dat";
    private List<Reserva> reservas;
    private RoomManager roomManager;

    public ReservaManager(RoomManager roomManager) {
        this.roomManager = roomManager;
        reservas = cargarReservas();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
        roomManager.actualizarDisponibilidad(reserva.getIdHabitacion(), true);
        guardarReservas();
    }

    public List<Reserva> obtenerReservasPorUsuario(String emailUsuario) {
        List<Reserva> reservasUsuario = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getEmailUsuario().equals(emailUsuario) && reserva.isActiva()) {
                reservasUsuario.add(reserva);
            }
        }
        return reservasUsuario;
    }

    public List<Reserva> obtenerHistorialReservasPorUsuario(String emailUsuario) {
        List<Reserva> reservasUsuario = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getEmailUsuario().equals(emailUsuario)) {
                reservasUsuario.add(reserva);
            }
        }
        return reservasUsuario;
    }

    public void modificarReserva(String emailUsuario, Reserva reservaModificada) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getEmailUsuario().equals(emailUsuario) && reservas.get(i).getIdHabitacion().equals(reservaModificada.getIdHabitacion())) {
                reservas.set(i, reservaModificada);
                break;
            }
        }
        guardarReservas();
    }

    public void cancelarReserva(String emailUsuario, String idHabitacion) {
        for (Reserva reserva : reservas) {
            if (reserva.getEmailUsuario().equals(emailUsuario) && reserva.getIdHabitacion().equals(idHabitacion)) {
                reserva.setEstado(false);
                roomManager.actualizarDisponibilidad(idHabitacion, false);
                break;
            }
        }
        guardarReservas();
    }

    private void guardarReservas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RESERVAS))) {
            oos.writeObject(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Reserva> cargarReservas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RESERVAS))) {
            return (List<Reserva>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean existeReserva(String emailUsuario, String idHabitacion) {
        for (Reserva reserva : reservas) {
            if (reserva.getEmailUsuario().equals(emailUsuario) && reserva.getIdHabitacion().equals(idHabitacion)) {
                return true;
            }
        }
        return false;
    }
    
}
