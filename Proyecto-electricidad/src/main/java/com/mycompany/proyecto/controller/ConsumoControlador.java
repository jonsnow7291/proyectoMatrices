package com.mycompany.proyecto.controller;

import java.util.Scanner;

import com.mycompany.proyecto.model.Cliente;
import com.mycompany.proyecto.model.Consumo;
import com.mycompany.proyecto.model.FacturaGenerator;
import com.mycompany.proyecto.model.Registrador;

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
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> editarCliente();
                case 3 -> crearRegistrador();
                case 4 -> editarRegistrador();
                case 5 -> cargarConsumoCliente();
                case 6 -> cargarConsumoTodos();
                case 7 -> modificarConsumoHora();
                case 8 -> generarFactura();
                case 9 -> consumoPorFranjas();
                case 10 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 9);
    }

    private void crearCliente() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        System.out.print("TipoIdentificación: ");
        String tIdent = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        try {
            sistema.agregarCliente(new Cliente(id, nombre, direccion, tIdent, correo, ciudad));
            System.out.println("Cliente creado.");
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
        }
    }

    private void editarCliente() {
        boolean registrado = false;
        System.out.print("ID cliente a editar: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese su Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su Nueva dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese su Nueva Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese su Nueva Correo: ");
        String correo = scanner.nextLine();
        try {
            registrado = sistema.editarCliente(id, nombre, direccion, correo, ciudad);
        } catch (Exception e) {
            System.out.println("Error al editar cliente: " + e.getMessage());
        }
        if (registrado) {
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void crearRegistrador() {
        String ubicacion = "Desconocida";
        String direccion = "Desconocida";
        String ciudad = "Desconocida";
        System.out.print("ID cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("ID registrador: ");
        String idReg = scanner.nextLine();
        System.out.println("La ubicacion se asociara al cliente??");
        System.out.println("1. Si");
        System.out.println("2. No");
        int respuesta = scanner.nextInt();
        if (respuesta == 2) {
            System.out.print("Ubicación: ");
            ubicacion = scanner.nextLine();
        } else if (respuesta == 1) {
            Cliente client = sistema.buscarCliente(idCliente);
            if (client != null) {
                direccion = client.getDireccion();
                ciudad = client.getCiudad();
                ubicacion = direccion + ", " + ciudad;
            } else {
                System.out.println("Cliente no encontrado. Se asignará ubicación por defecto.");
            }
        }
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
        if (c != null)
            System.out.println("Cliente encontrado.");
        else
            System.out.println("Cliente no encontrado.");

        if (c != null) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().generarDatos();
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

        System.out.print("Mes (1=enero...12=diciembre): ");
        int mes = scanner.nextInt();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }

        int diasDelMes = r.getConsumo().getDiasDelMes(mes);
        int dia;
        do {
            System.out.print("Día (1–" + diasDelMes + "): ");
            dia = scanner.nextInt();
            if (dia < 1 || dia > diasDelMes) {
                System.out.println("Día inválido para este mes.");
            }
        } while (dia < 1 || dia > diasDelMes);

        int hora;
        do {
            System.out.print("Hora (0–23): ");
            hora = scanner.nextInt();
            if (hora < 0 || hora > 23) {
                System.out.println("Hora inválida.");
            }
        } while (hora < 0 || hora > 23);

        System.out.print("Nuevo consumo (kWh): ");
        int kw = scanner.nextInt();

        r.getConsumo().modificarConsumoHora(mes, dia - 1, hora, kw);
        System.out.println("Consumo actualizado.");
    }
    
    private void consumoPorFranjas(){
        System.out.println("Ingrese el Id del cliente :");
        String id = "0";
        try{
        id = scanner.nextLine();
        }catch (Exception e){
            System.out.println("Error al ingresar el id del cliente error: "+ e.getMessage());
        }
        Cliente c =  sistema.buscarCliente(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        } else {
            System.out.println("Cliente encontrado.");
        }
        System.out.println("Ingrese el mes (1-12):");
        int mes = 0;
        try{
        mes = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Error al ingresar el mes error: " + e.getMessage());
        }
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }
        System.out.println("Ingrese el dia (1-31):");
        int dia = 0;
        try{
        dia = scanner.nextInt();
        }catch(Exception ex ){
            System.out.println("Error al ingresar el dia, error: " + ex.getMessage());
        }
        Consumo consumo = c.getRegistradores().get(0).getConsumo();
        int[][] consumoMensual = consumo.getConsumoMensual(mes);
        int[] franjas = new int[consumoMensual[dia - 1].length];
        // se debe de hacer profranja horaria
        System.out.println("Ahora haremos el Consumo por franjas horarias:");
        int HoraDeInicio = 0;
        int HoraDeFin = 0;
        try{
            System.out.println("Ingrese la hora de inicio (0-23):");
            HoraDeInicio = scanner.nextInt();
            System.out.println("Ingrese la hora de fin (0-23):");
            HoraDeFin = scanner.nextInt();
            for (int i = HoraDeInicio; i <= HoraDeFin; i++) {
                franjas[i] = consumoMensual[dia - 1][i];
            }
        }catch( Exception ex ){
            System.out.println("Error al ingresar las horas, error: " + ex.getMessage());
        }

        

    }

    private void generarFactura() {
        System.out.print("ID cliente: ");
        String id = scanner.nextLine();
        Cliente c = sistema.buscarCliente(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        } else {
            System.out.println("Cliente encontrado.");
        }

        System.out.print("Mes (1=enero...12=diciembre): ");
        int mes = scanner.nextInt();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }

        String[] nombresMes = {
                "enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
        };
        String nombreMes = nombresMes[mes - 1];

        scanner.nextLine();
        System.out.print("Nombre archivo PDF (sin .pdf): ");
        String nombreArchivo = scanner.nextLine();

        int diaMenorConsumo = -1;
        int horaMenorConsumo = -1;
        int diaMayorConsumo = -1;
        int horaMayorConsumo = -1;
        int menorConsumo = Integer.MAX_VALUE;
        int mayorConsumo = Integer.MIN_VALUE;

        for (Registrador r : c.getRegistradores()) {
            int[][] consumoMensual = r.getConsumo().getConsumoMensual(mes);
            for (int dia = 0; dia < consumoMensual.length; dia++) {
                for (int hora = 0; hora < consumoMensual[dia].length; hora++) {
                    if (consumoMensual[dia][hora] < menorConsumo) {
                        menorConsumo = consumoMensual[dia][hora];
                        diaMenorConsumo = dia + 1;
                        horaMenorConsumo = hora;
                    }
                    if (consumoMensual[dia][hora] > mayorConsumo) {
                        mayorConsumo = consumoMensual[dia][hora];
                        diaMayorConsumo = dia + 1;
                        horaMayorConsumo = hora;
                    }
                }
            }
        }

        FacturaGenerator.generarFactura(c, mes, nombreArchivo + ".pdf", diaMenorConsumo, horaMenorConsumo, menorConsumo,
                diaMayorConsumo, horaMayorConsumo, mayorConsumo);
        System.out.println("Factura generada: " + nombreArchivo + ".pdf");
    }
}