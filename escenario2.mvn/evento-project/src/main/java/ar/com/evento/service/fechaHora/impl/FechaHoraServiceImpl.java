package ar.com.evento.service.fechaHora.impl;

import ar.com.evento.service.fechaHora.FechaHoraService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FechaHoraServiceImpl implements FechaHoraService {

    private final DateTimeFormatter formatter;

    public FechaHoraServiceImpl(Scanner scanner) {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public LocalDateTime leerFechaHora() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha y hora (yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine();
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha y hora incorrecto.");
            throw new IOException("Formato de fecha y hora incorrecto.");
        }
    }
}
