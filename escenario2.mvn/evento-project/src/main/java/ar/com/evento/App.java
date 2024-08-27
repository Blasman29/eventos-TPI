package ar.com.evento;
import ar.com.evento.domain.*;
import ar.com.evento.service.menu.impl.MenuServiceImpl;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SistemaEventos sistema = new SistemaEventos();
        Scanner scanner = new Scanner(System.in);
        MenuServiceImpl menuService = new MenuServiceImpl(sistema, scanner);

        menuService.mostrarMenu();
    }
}
