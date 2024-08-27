package ar.com.evento.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaEventos {
    private List<EventoGastronómico> eventos;
    private List<Chef> chefs;
    private List<Participante> participantes;

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        this.chefs = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }

    public void agregarEvento(EventoGastronómico evento) {
        eventos.add(evento);
        System.out.println("Evento agregado: " + evento.getNombre());
    }

    public void agregarChef(Chef chef) {
        chefs.add(chef);
        System.out.println("Chef agregado: " + chef.getNombre());
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
        System.out.println("Participante agregado: " + participante.getNombre());
    }

    public EventoGastronómico buscarEventoPorId(int id) {
        return eventos.stream()
                .filter(evento -> evento.getIdentificador() == id)
                .findFirst()
                .orElse(null);
    }

    public Chef buscarChefPorId(int id) {
        return chefs.stream()
                .filter(chef -> chef.getIdentificador() == id)
                .findFirst()
                .orElse(null);
    }

    public Participante buscarParticipantePorId(int id) {
        return participantes.stream()
                .filter(participante -> participante.getIdParticipante() == id)
                .findFirst()
                .orElse(null);
    }

    public List<EventoGastronómico> listarEventosDisponibles(LocalDateTime fecha) {
        return eventos.stream()
                .filter(evento -> evento.getFechaHora().isAfter(fecha))
                .collect(Collectors.toList());
    }

    public void exportarEventosNoDisponibles(LocalDateTime fecha) {
        List<EventoGastronómico> eventosNoDisponibles = eventos.stream()
                .filter(evento -> evento.getInscritos() >= evento.getCapacidad() && evento.getFechaHora().isAfter(fecha))
                .collect(Collectors.toList());

        try (FileWriter writer = new FileWriter("eventos_no_disponibles.csv")) {
            writer.append("IDEvento,Nombre,Fecha y Hora,Capacidad,Inscritos\n");
            for (EventoGastronómico evento : eventosNoDisponibles) {
                writer.append(evento.getIdentificador() + ",")
                        .append(evento.getNombre() + ",")
                        .append(evento.getFechaHora().toString() + ",")
                        .append(evento.getCapacidad() + ",")
                        .append(evento.getInscritos() + "");

            }
            System.out.println("Eventos no disponibles exportados a eventos_no_disponibles.csv");
        } catch (IOException e) {
            System.out.println("Error al exportar eventos: " + e.getMessage());
        }
    }

}