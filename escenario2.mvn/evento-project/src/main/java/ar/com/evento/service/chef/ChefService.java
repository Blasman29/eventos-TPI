package ar.com.evento.service.chef;

import ar.com.evento.domain.Chef;
import java.io.IOException;

public interface ChefService {
    void agregarChef() throws IOException;
    Chef buscarChefPorId(int id);
}
