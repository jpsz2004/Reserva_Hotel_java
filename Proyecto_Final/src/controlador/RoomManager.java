package controlador;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Habitacion;

public class RoomManager {

    private List<Habitacion> habitaciones;

    public RoomManager() {
        try {
            habitaciones = RoomFileManager.cargarHabitaciones();
        } catch (IOException | ClassNotFoundException e) {
            habitaciones = new ArrayList<>();
        }
    }

    public List<Habitacion> obtenerHabitaciones() {
        return habitaciones;
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
        guardarHabitaciones();
    }

    public void eliminarHabitacion(String id) {
        habitaciones.removeIf(habitacion -> habitacion.getId().equals(id));
        guardarHabitaciones();
    }

    public void editarHabitacion(Habitacion habitacionEditada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getId().equals(habitacionEditada.getId())) {
                habitaciones.set(i, habitacionEditada);
                break;
            }
        }
        guardarHabitaciones();
    }

    public Habitacion buscarHabitacion(String id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId().equals(id)) {
                return habitacion;
            }
        }
        return null;
    }

    public List<Habitacion> buscarHabitaciones(LocalDateTime fechaInicio, LocalDateTime fechaFin, int numeroHuespedes, String tipo) {
        List<Habitacion> resultados = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && habitacion.getCapacidad() >= numeroHuespedes &&
                habitacion.getFechaInicioDisponibilidad().isBefore(fechaFin) &&
                habitacion.getFechaFinDisponibilidad().isAfter(fechaInicio) &&
                !habitacion.isReservada()) {
                resultados.add(habitacion);
            }
        }
        return resultados;
    }

    public void actualizarHabitacion(Habitacion habitacionActualizada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getId().equals(habitacionActualizada.getId())) {
                habitaciones.set(i, habitacionActualizada);
                break;
            }
        }
        guardarHabitaciones();
    }

    void actualizarDisponibilidad(String idHabitacion, boolean reservada) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId().equals(idHabitacion)) {
                habitacion.setReservada(reservada);
                break;
            }
        }
        guardarHabitaciones();
    }

    private void guardarHabitaciones() {
        try {
            RoomFileManager.guardarHabitaciones(habitaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
