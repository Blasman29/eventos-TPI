package ar.com.evento.service.evento;

import ar.com.evento.domain.EventoGastronómico;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {
    void crearEvento() throws IOException;
    EventoGastronómico buscarEventoPorId(int id);
    List<EventoGastronómico> listarEventosDisponibles(LocalDateTime fecha);
    void exportarEventosNoDisponibles(LocalDateTime fecha) throws IOException;
}