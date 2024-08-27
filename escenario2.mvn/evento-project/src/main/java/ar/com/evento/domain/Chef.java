package ar.com.evento.domain;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private int identificador;
    private String nombre;
    private String especialidad;
    private List<EventoGastronómico> eventosAsignados;

    public Chef(int identificador, String nombre, String especialidad) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.eventosAsignados = new ArrayList<>();
    }

    public void agregarEvento(EventoGastronómico evento) {
        this.eventosAsignados.add(evento);
        System.out.println("Evento agregado: " + evento.getNombre() + " al chef: " + this.nombre);
    }

    // Getters y Setters
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<EventoGastronómico> getEventosAsignados() {
        return eventosAsignados;
    }

    public void setEventosAsignados(List<EventoGastronómico> eventosAsignados) {
        this.eventosAsignados = eventosAsignados;
    }
}
