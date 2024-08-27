package ar.com.evento.service.menu;

import java.io.IOException;

public interface MenuService {

    void mostrarMenu();

    void crearEvento() throws IOException;

    void inscribirParticipante() throws IOException;

    void agregarChef() throws IOException;

    void dejarReseña() throws IOException;

    void listarEventosDisponibles() throws IOException;

    void exportarEventosNoDisponibles() throws IOException;
}

