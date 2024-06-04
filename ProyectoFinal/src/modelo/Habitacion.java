package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Habitacion implements Serializable {

    private String id;
    private String tipo;
    private int capacidad;
    private double precioPorNoche;
    private String comodidades;
    private LocalDateTime fechaInicioDisponibilidad;
    private LocalDateTime fechaFinDisponibilidad;
    private boolean reservada;

    public Habitacion(String id, String tipo, int capacidad, double precioPorNoche, String comodidades, LocalDateTime fechaInicioDisponibilidad, LocalDateTime fechaFinDisponibilidad, boolean reservada) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioPorNoche = precioPorNoche;
        this.comodidades = comodidades;
        this.fechaInicioDisponibilidad = fechaInicioDisponibilidad;
        this.fechaFinDisponibilidad = fechaFinDisponibilidad;
        this.reservada = false;
    }
    

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public LocalDateTime getFechaInicioDisponibilidad() {
        return fechaInicioDisponibilidad;
    }

    public void setFechaInicioDisponibilidad(LocalDateTime fechaInicioDisponibilidad) {
        this.fechaInicioDisponibilidad = fechaInicioDisponibilidad;
    }

    public LocalDateTime getFechaFinDisponibilidad() {
        return fechaFinDisponibilidad;
    }

    public void setFechaFinDisponibilidad(LocalDateTime fechaFinDisponibilidad) {
        this.fechaFinDisponibilidad = fechaFinDisponibilidad;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }
}
