package ar.com.evento.service.fechaHora;

import java.io.IOException;
import java.time.LocalDateTime;

public interface FechaHoraService {
    LocalDateTime leerFechaHora() throws IOException;
}
