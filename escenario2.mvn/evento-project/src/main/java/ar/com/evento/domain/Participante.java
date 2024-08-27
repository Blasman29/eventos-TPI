package ar.com.evento.domain;

import java.util.ArrayList;
import java.util.List;

public class Participante {
    private int idParticipante;
    private String nombre;
    private String apellido;
    private List<String> interesesCulinarios;
    private List<EventoGastronómico> historialEventos;

    public Participante(int idParticipante, String nombre, String apellidoParticipante) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.interesesCulinarios = new ArrayList<>();
        this.historialEventos = new ArrayList<>();
    }

    public void inscribirseEnEvento(EventoGastronómico evento) {
        this.historialEventos.add(evento);
        System.out.println(this.nombre + " se ha inscrito en el evento: " + evento.getNombre());
    }

    public void dejarReseña(EventoGastronómico evento, int calificación, String comentario) {
        Resenia reseña = new Resenia(historialEventos.size() + 1, evento, this, calificación, comentario);
        System.out.println("Reseña dejada por " + this.nombre + " para el evento: " + evento.getNombre());
    }

    // Getters y Setters
    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<String> getInteresesCulinarios() {
        return interesesCulinarios;
    }

    public void setInteresesCulinarios(List<String> interesesCulinarios) {
        this.interesesCulinarios = interesesCulinarios;
    }

    public List<EventoGastronómico> getHistorialEventos() {
        return historialEventos;
    }

    public void setHistorialEventos(List<EventoGastronómico> historialEventos) {
        this.historialEventos = historialEventos;
    }
}

