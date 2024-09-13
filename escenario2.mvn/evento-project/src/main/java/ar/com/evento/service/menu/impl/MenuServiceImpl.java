package ar.com.evento.service.menu.impl;

import ar.com.evento.service.evento.EventoService;
import ar.com.evento.service.chef.ChefService;
import ar.com.evento.service.fechaHora.FechaHoraService;
import ar.com.evento.service.participante.ParticipanteService;
import ar.com.evento.service.menu.MenuService;

import java.io.IOException;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private final EventoService eventoService;
    private final ParticipanteService participanteService;
    private final ChefService chefService;
    private final FechaHoraService fechaHoraService;
    private final Scanner scanner;

    public MenuServiceImpl(EventoService eventoService, ParticipanteService participanteService, ChefService chefService, FechaHoraService fechaHoraService, Scanner scanner) {
        this.eventoService = eventoService;
        this.participanteService = participanteService;
        this.chefService = chefService;
        this.fechaHoraService = fechaHoraService;
        this.scanner = scanner;
    }

    @Override
    public void mostrarMenu() throws IOException {
        while (true) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Crear Evento");
            System.out.println("2. Inscribir Participante");
            System.out.println("3. Agregar Chef");
            System.out.println("4. Dejar Reseña");
            System.out.println("5. Listar Eventos Disponibles");
            System.out.println("6. Exportar Eventos No Disponibles");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            try {
                switch (opcion) {
                    case 1:
                        eventoService.crearEvento();
                        break;
                    case 2:
                        participanteService.inscribirParticipante();
                        break;
                    case 3:
                        chefService.agregarChef();
                        break;
                    case 4:
                        participanteService.dejarReseña();
                        break;
                    case 5:
                        eventoService.listarEventosDisponibles(fechaHoraService.leerFechaHora());
                        break;
                    case 6:
                        eventoService.exportarEventosNoDisponibles(fechaHoraService.leerFechaHora());
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (IOException e) {
                System.out.println("Verifique el dato ingresado ya que uno de ellos no corresponde.");
            }
        }
    }
}