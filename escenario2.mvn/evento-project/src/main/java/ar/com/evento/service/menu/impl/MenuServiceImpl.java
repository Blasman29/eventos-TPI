package ar.com.evento.service.menu.impl;
import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronómico;
import ar.com.evento.domain.Participante;
import ar.com.evento.domain.SistemaEventos;
import ar.com.evento.enumeration.UbicacionEnum;
import ar.com.evento.service.menu.MenuService;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService{

    private final SistemaEventos sistema;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public MenuServiceImpl(SistemaEventos sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public void mostrarMenu() {
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
                        crearEvento();
                        break;
                    case 2:
                        inscribirParticipante();
                        break;
                    case 3:
                        agregarChef();
                        break;
                    case 4:
                        dejarReseña();
                        break;
                    case 5:
                        listarEventosDisponibles();
                        break;
                    case 6:
                        exportarEventosNoDisponibles();
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

    public void crearEvento() throws IOException {
        System.out.print("Ingrese el identificador del evento: ");
        int idEvento = scanner.nextInt();
        scanner.nextLine();
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
        scanner.nextLine();
        UbicacionEnum ubicacionEvento = UbicacionEnum.values()[ubicacionOpcion - 1];

        System.out.print("Ingrese la capacidad del evento: ");
        int capacidadEvento = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el identificador del chef a cargo: ");
        int idChef = scanner.nextInt();
        scanner.nextLine();
        Chef chefACargo = sistema.buscarChefPorId(idChef);
        if (chefACargo != null) {
            EventoGastronómico evento = new EventoGastronómico(
                    idEvento, nombreEvento, descripcionEvento, fechaHoraEvento, ubicacionEvento, capacidadEvento, chefACargo);
            sistema.agregarEvento(evento);
        } else {
            System.out.println("Chef no encontrado.");
        }
    }

    public void inscribirParticipante() throws IOException {
        System.out.print("Ingrese el identificador del participante: ");
        int idParticipante = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del participante: ");
        String nombreParticipante = scanner.nextLine();
        System.out.print("Ingrese el apellido del participante: ");
        String apellidoParticipante = scanner.nextLine();
        Participante participante = new Participante(idParticipante, nombreParticipante, apellidoParticipante);
        sistema.agregarParticipante(participante);
        System.out.print("Ingrese el identificador del evento al que desea inscribirse: ");
        int idEventoInscripcion = scanner.nextInt();
        scanner.nextLine();
        EventoGastronómico eventoInscripcion = sistema.buscarEventoPorId(idEventoInscripcion);
        if (eventoInscripcion != null) {
            eventoInscripcion.inscribirParticipante(participante);
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    public void agregarChef() throws IOException {
        System.out.print("Ingrese el identificador del chef: ");
        String idNuevoChefInput = scanner.nextLine();

        if (idNuevoChefInput.isEmpty()) {
            throw new IOException("Error: El identificador del chef no puede estar vacío.");
        }

        int idNuevoChef;
        try {
            idNuevoChef = Integer.parseInt(idNuevoChefInput);
        } catch (NumberFormatException e) {
            throw new IOException("Error: El identificador del chef debe ser un número entero válido.");
        }

        System.out.print("Ingrese el nombre del chef: ");
        String nombreChef = scanner.nextLine();
        System.out.print("Ingrese la especialidad del chef: ");
        String especialidadChef = scanner.nextLine();

        Chef nuevoChef = new Chef(idNuevoChef, nombreChef, especialidadChef);
        sistema.agregarChef(nuevoChef);
        System.out.println("Chef agregado exitosamente.");
    }

    public void dejarReseña() throws IOException {
        System.out.print("Ingrese el identificador del participante: ");
        int idParticipanteReseña = scanner.nextInt();
        scanner.nextLine();
        Participante participanteReseña = sistema.buscarParticipantePorId(idParticipanteReseña);
        if (participanteReseña != null) {
            System.out.print("Ingrese el identificador del evento: ");
            int idEventoReseña = scanner.nextInt();
            scanner.nextLine();
            EventoGastronómico eventoReseña = sistema.buscarEventoPorId(idEventoReseña);
            if (eventoReseña != null) {
                System.out.print("Ingrese la calificación (1-5): ");
                int calificación = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el comentario: ");
                String comentario = scanner.nextLine();
                participanteReseña.dejarReseña(eventoReseña, calificación, comentario);
            } else {
                System.out.println("Evento no encontrado.");
            }
        } else {
            System.out.println("Participante no encontrado.");
        }
    }

    public void listarEventosDisponibles() throws IOException {
        LocalDateTime fechaListado = leerFechaHora();
        List<EventoGastronómico> eventosDisponibles = sistema.listarEventosDisponibles(fechaListado);
        System.out.println("Eventos disponibles a partir de " + fechaListado + ":");
        for (EventoGastronómico evento : eventosDisponibles) {
            System.out.println(evento.getNombre() + " - " + evento.getFechaHora());
        }
    }

    public void exportarEventosNoDisponibles() throws IOException {
        LocalDateTime fechaExportacion = leerFechaHora();
        sistema.exportarEventosNoDisponibles(fechaExportacion);
    }

    private LocalDateTime leerFechaHora() throws IOException {
        System.out.print("Ingrese la fecha y hora (yyyy-MM-dd HH:mm): ");
        String fechaHoraInput = scanner.nextLine();
        if (fechaHoraInput.isEmpty()) {
            throw new IOException("Fecha vacía");
        }
        try {
            return LocalDateTime.parse(fechaHoraInput, formatter);
        } catch (DateTimeParseException e) {
            throw new IOException("Formato de fecha incorrecto");
        }
    }
}
