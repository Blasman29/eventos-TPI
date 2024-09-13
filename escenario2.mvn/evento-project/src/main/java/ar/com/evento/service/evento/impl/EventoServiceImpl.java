package ar.com.evento.service.evento.impl;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronómico;
import ar.com.evento.domain.SistemaEventos;
import ar.com.evento.enumeration.UbicacionEnum;
import ar.com.evento.service.evento.EventoService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EventoServiceImpl implements EventoService {

    private final SistemaEventos sistema;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public EventoServiceImpl(SistemaEventos sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public void crearEvento() throws IOException {
        System.out.print("Ingrese el identificador del evento: ");
        int idEvento = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el nombre del evento: ");
        String nombreEvento = scanner.nextLine();
        System.out.print("Ingrese la descripción del evento: ");
        String descripcionEvento = scanner.nextLine();
        LocalDateTime fechaHoraEvento = leerFechaHora();

        System.out.println("Seleccione la ubicación del evento:");
        for (UbicacionEnum ubicacion : UbicacionEnum.values()) {
            System.out.println((ubicacion.ordinal() + 1) + ". " + ubicacion.name());
        }
        int ubicacionOpcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        UbicacionEnum ubicacionEvento = UbicacionEnum.values()[ubicacionOpcion - 1];

        System.out.print("Ingrese la capacidad del evento: ");
        int capacidadEvento = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el identificador del chef a cargo: ");
        int idChef = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Chef chefACargo = sistema.buscarChefPorId(idChef);
        if (chefACargo != null) {
            EventoGastronómico evento = new EventoGastronómico(
                    idEvento, nombreEvento, descripcionEvento, fechaHoraEvento, ubicacionEvento, capacidadEvento, chefACargo);
            sistema.agregarEvento(evento);
        } else {
            System.out.println("Chef no encontrado.");
        }
    }

    @Override
    public EventoGastronómico buscarEventoPorId(int id) {
        return sistema.buscarEventoPorId(id);
    }

    @Override
    public List<EventoGastronómico> listarEventosDisponibles(LocalDateTime fecha) {
        return sistema.listarEventosDisponibles(fecha);
    }

    @Override
    public void exportarEventosNoDisponibles(LocalDateTime fecha) throws IOException {
        sistema.exportarEventosNoDisponibles(fecha);
    }

    private LocalDateTime leerFechaHora() throws IOException {
        System.out.print("Ingrese la fecha y hora (yyyy-MM-dd HH:mm): ");
        String fechaHoraInput = scanner.nextLine();
        try {
            return LocalDateTime.parse(fechaHoraInput, formatter);
        } catch (DateTimeParseException e) {
            throw new IOException("Formato de fecha incorrecto. Use el formato yyyy-MM-dd HH:mm.");
        }
    }
}
