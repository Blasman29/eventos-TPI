package ar.com.evento.domain;

public class Resenia {
    private int identificador;
    private EventoGastronómico eventoReferenciado;
    private Participante participanteReferenciado;
    private int calificación;
    private String comentario;

    public Resenia(int identificador, EventoGastronómico eventoReferenciado, Participante participanteReferenciado, int calificación, String comentario) {
        this.identificador = identificador;
        this.eventoReferenciado = eventoReferenciado;
        this.participanteReferenciado = participanteReferenciado;
        this.calificación = calificación;
        this.comentario = comentario;
    }

    // Getters y Setters
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public EventoGastronómico getEventoReferenciado() {
        return eventoReferenciado;
    }

    public void setEventoReferenciado(EventoGastronómico eventoReferenciado) {
        this.eventoReferenciado = eventoReferenciado;
    }

    public Participante getParticipanteReferenciado() {
        return participanteReferenciado;
    }

    public void setParticipanteReferenciado(Participante participanteReferenciado) {
        this.participanteReferenciado = participanteReferenciado;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

