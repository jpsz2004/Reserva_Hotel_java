package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String emailUsuario;
    private String idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroHuespedes;
    private double getPrecioPorNoche;
    private boolean estado; // true para activa, false para cancelada

    public Reserva(String emailUsuario, String idHabitacion, LocalDate fechaInicio, LocalDate fechaFin, int numeroHuespedes, double getPrecioPorNoche) {
        this.emailUsuario = emailUsuario;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroHuespedes = numeroHuespedes;
        this.getPrecioPorNoche = getPrecioPorNoche;
        this.estado = true; // Por defecto, la reserva es activa
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getIdHabitacion() {
        return idHabitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaLlegada) {
        this.fechaInicio = fechaLlegada;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(int numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }

    public double getPrecioPorNoche() {
        return getPrecioPorNoche;
    }

    public void setPrecioPorNoche(double getPrecioPorNoche) {
        this.getPrecioPorNoche = getPrecioPorNoche;
    }

    public boolean isActiva() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
