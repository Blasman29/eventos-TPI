package ar.com.evento;
import ar.com.evento.domain.SistemaEventos;
import ar.com.evento.service.chef.ChefService;
import ar.com.evento.service.evento.EventoService;
import ar.com.evento.service.fechaHora.FechaHoraService;
import ar.com.evento.service.menu.*;
import ar.com.evento.service.chef.impl.ChefServiceImpl;
import ar.com.evento.service.evento.impl.EventoServiceImpl;
import ar.com.evento.service.fechaHora.impl.FechaHoraServiceImpl;
import ar.com.evento.service.participante.impl.ParticipanteServiceImpl;
import ar.com.evento.service.menu.impl.MenuServiceImpl;
import ar.com.evento.service.participante.ParticipanteService;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        SistemaEventos sistema = new SistemaEventos();
        Scanner scanner = new Scanner(System.in);

        //Instancias de servicios
        EventoService eventoService = new EventoServiceImpl(sistema, scanner);
        ParticipanteService participanteService = new ParticipanteServiceImpl(sistema, scanner);
        ChefService chefService = new ChefServiceImpl(sistema, scanner);
        FechaHoraService fechaHoraService = new FechaHoraServiceImpl(scanner);

        // Instancia de MenuServiceImpl con las interfaces de servicios
        MenuService menuService = new MenuServiceImpl(eventoService, participanteService, chefService, fechaHoraService, scanner);

        menuService.mostrarMenu();
    }
}