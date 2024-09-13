package ar.com.evento.service.chef.impl;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.SistemaEventos;
import ar.com.evento.service.chef.ChefService;

import java.io.IOException;
import java.util.Scanner;

public class ChefServiceImpl implements ChefService {

    private final SistemaEventos sistema;

    public ChefServiceImpl(SistemaEventos sistema, Scanner scanner) {
        this.sistema = sistema;
    }

    @Override
    public void agregarChef() throws IOException {
        System.out.print("Ingrese el identificador del chef: ");
        int idChef = new Scanner(System.in).nextInt();
        System.out.print("Ingrese el nombre del chef: ");
        String nombreChef = new Scanner(System.in).nextLine();
        System.out.print("Ingrese el apellido del chef: ");
        String apellidoChef = new Scanner(System.in).nextLine();
        Chef chef = new Chef(idChef, nombreChef, apellidoChef);
        sistema.agregarChef(chef);
    }

    @Override
    public Chef buscarChefPorId(int id) {
        return sistema.buscarChefPorId(id);
    }
}
