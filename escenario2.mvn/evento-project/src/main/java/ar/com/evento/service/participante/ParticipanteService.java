package ar.com.evento.service.participante;

import ar.com.evento.domain.Participante;
import java.io.IOException;

public interface ParticipanteService {
    void inscribirParticipante() throws IOException;
    void dejarReseña() throws IOException;
    Participante buscarParticipantePorId(int id);
}

