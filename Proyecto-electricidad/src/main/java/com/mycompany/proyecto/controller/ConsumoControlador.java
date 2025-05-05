package controller;

import model.*;

import java.util.Scanner;

public class ConsumoControlador {
    private final SistemaEnergia sistema;
    private final Scanner scanner;

    public ConsumoControlador() {
        sistema = new SistemaEnergia();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear cliente");
            System.out.println("2. Editar cliente");
            System.out.println("3. Crear registrador");
            System.out.println("4. Editar registrador");
            System.out.println("5. Cargar consumo automáticamente de un cliente");
            System.out.println("6. Cargar consumo automáticamente de todos los clientes");
            System.out.println("7. Cambiar consumo de una hora");
            System.out.println("8. Generar factura (.pdf)");
            System.out.println("9. Salir");
            System.out.print("Seleccione opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> editarCliente();
                case 3 -> crearRegistrador();
                case 4 -> editarRegistrador();
                case 5 -> cargarConsumoCliente();
                case 6 -> cargarConsumoTodos();
                case 7 -> modificarConsumoHora();
                case 8 -> generarFactura();
                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 9);
    }

    private void crearCliente() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        sistema.agregarCliente(new Cliente(id, nombre, direccion));
        System.out.println("Cliente creado.");
    }

    private void editarCliente() {
        System.out.print("ID cliente a editar: ");
        String id = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = scanner.nextLine();

        if (sistema.editarCliente(id, nombre, direccion)) {
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void crearRegistrador() {
        System.out.print("ID cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("ID registrador: ");
        String idReg = scanner.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        boolean ok = sistema.agregarRegistradorACliente(idCliente, new Registrador(idReg, ubicacion));
        System.out.println(ok ? "Registrador agregado." : "Cliente no encontrado.");
    }

    private void editarRegistrador() {
        System.out.print("ID cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("ID registrador: ");
        String idReg = scanner.nextLine();
        System.out.print("Nueva ubicación: ");
        String ubicacion = scanner.nextLine();

        if (sistema.editarRegistrador(idCliente, idReg, ubicacion)) {
            System.out.println("Registrador actualizado.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
    }

    private void cargarConsumoCliente() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        Cliente c = sistema.buscarCliente(id);
        if (c != null) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().generarDatos(); // regenerar datos
            }
            System.out.println("Consumo generado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void cargarConsumoTodos() {
        for (Cliente c : sistema.getClientes()) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().generarDatos();
            }
        }
        System.out.println("Consumo cargado para todos.");
    }

    private void modificarConsumoHora() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        Cliente c = sistema.buscarCliente(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("ID registrador: ");
        String idReg = scanner.nextLine();
        Registrador r = c.buscarRegistrador(idReg);
        if (r == null) {
            System.out.println("Registrador no encontrado.");
            return;
        }

        System.out.print("Mes (0=enero...11=diciembre): ");
        int mes = scanner.nextInt();
        System.out.print("Día (0–30): ");
        int dia = scanner.nextInt();
        System.out.print("Hora (0–23): ");
        int hora = scanner.nextInt();
        System.out.print("Nuevo consumo (kWh): ");
        int kw = scanner.nextInt();

        r.getConsumo().modificarConsumoHora(mes, dia, hora, kw);
        System.out.println("Consumo actualizado.");
    }

    private void generarFactura() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        Cliente c = sistema.buscarCliente(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Mes (0=enero...11=diciembre): ");
        int mes = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre archivo PDF (sin .pdf): ");
        String nombreArchivo = scanner.nextLine();
        FacturaGenerator.generarFactura(c, mes, nombreArchivo + ".pdf");
    }
}