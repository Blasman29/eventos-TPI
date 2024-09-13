package ar.com.evento.service.participante.impl;

import ar.com.evento.domain.EventoGastronómico;
import ar.com.evento.domain.Participante;
import ar.com.evento.domain.Resenia;
import ar.com.evento.domain.SistemaEventos;
import ar.com.evento.service.participante.ParticipanteService;

import java.io.IOException;
import java.util.Scanner;

public class ParticipanteServiceImpl implements ParticipanteService {

    private final SistemaEventos sistema;

    public ParticipanteServiceImpl(SistemaEventos sistema, Scanner scanner) {
        this.sistema = sistema;
    }

    @Override
    public void inscribirParticipante() throws IOException {
        System.out.print("Ingrese el identificador del participante: ");
        int idParticipante = new Scanner(System.in).nextInt();
        System.out.print("Ingrese el nombre del participante: ");
        String nombreParticipante = new Scanner(System.in).nextLine();
        System.out.print("Ingrese el apellido del participante: ");
        String apellidoParticipante = new Scanner(System.in).nextLine();
        Participante participante = new Participante(idParticipante, nombreParticipante, apellidoParticipante);
        sistema.agregarParticipante(participante);
        System.out.print("Ingrese el identificador del evento al que desea inscribirse: ");
        int idEventoInscripcion = new Scanner(System.in).nextInt();
        EventoGastronómico eventoInscripcion = sistema.buscarEventoPorId(idEventoInscripcion);
        if (eventoInscripcion != null) {
            eventoInscripcion.inscribirParticipante(participante);
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    @Override
    public void dejarReseña() throws IOException {
        System.out.print("Ingrese el identificador del participante: ");
        int idParticipanteReseña = new Scanner(System.in).nextInt();
        Participante participanteReseña = buscarParticipantePorId(idParticipanteReseña);
        if (participanteReseña != null) {
            System.out.print("Ingrese el identificador del evento: ");
            int idEventoReseña = new Scanner(System.in).nextInt();
            EventoGastronómico eventoReseña = sistema.buscarEventoPorId(idEventoReseña);
            if (eventoReseña != null) {
                System.out.print("Ingrese la calificación (1 a 5): ");
                int calificacion = new Scanner(System.in).nextInt();
                System.out.print("Ingrese el comentario: ");
                String comentario = new Scanner(System.in).nextLine();
                Resenia reseña = new Resenia(participanteReseña, eventoReseña, calificacion, comentario);
                eventoReseña.agregarResenia(reseña);
            } else {
                System.out.println("Evento no encontrado.");
            }
        } else {
            System.out.println("Participante no encontrado.");
        }
    }

    @Override
    public Participante buscarParticipantePorId(int id) {
        return sistema.buscarParticipantePorId(id);
    }
}
