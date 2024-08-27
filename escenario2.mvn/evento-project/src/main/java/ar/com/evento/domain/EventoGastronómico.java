package ar.com.evento.domain;

import ar.com.evento.enumeration.UbicacionEnum;

import java.time.LocalDateTime;
import java.util.List;

public class EventoGastronómico {
    private int identificador;
    private String nombre;
    private String descripción;
    private LocalDateTime fechaHora;
    private String ubicación;
    private int capacidad;
    private int inscritos;
    private Chef chefACargo;
    private List<Participante> participantes;

    public EventoGastronómico(int identificador, String nombre, String descripción, LocalDateTime fechaHora, UbicacionEnum ubicación, int capacidad, Chef chefACargo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripción = descripción;
        this.fechaHora = fechaHora;
        this.ubicación = String.valueOf(ubicación);
        this.capacidad = capacidad;
        this.inscritos = 0;
        this.chefACargo = chefACargo;
    }

    public boolean inscribirParticipante(Participante participante) {
        if (inscritos < capacidad) {
            inscritos++;
            participante.inscribirseEnEvento(this);
            return true;
        } else {
            System.out.println("Capacidad máxima alcanzada para el evento: " + nombre);
            return false;
        }
    }


    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getInscritos() {
        return inscritos;
    }

    public Chef getChefACargo() {
        return chefACargo;
    }

    public void setChefACargo(Chef chefACargo) {
        this.chefACargo = chefACargo;
    }

}
