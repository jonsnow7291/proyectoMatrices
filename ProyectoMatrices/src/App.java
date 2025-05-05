import view.*;
import java.util.Scanner;
import controller.*;
public class App {
    public static void main(String[] args) throws Exception {
        IndexView index = new IndexView();

        String menuPrincipal = index.GetMenu();

        System.out.println(menuPrincipal);
        System.out.println("Por favor, seleccione una opción:");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        switch(opcion)
        {
            case 1:
                // Creamos al cliente
                ClienteView clienteView = new ClienteView();
                ClienteController clienteController = new ClienteController();

                System.out.println(clienteView.GetMenuRegistro());
                System.out.println("Por favor, ingrese los datos del cliente:");
                System.out.print("Número de Identificación: ");
                int numeroIdentificacion = scanner.nextInt();
                System.out.print("Tipo de Identificación: ");
                scanner.nextLine();
                String tipoIdentificacion = scanner.nextLine();

                System.out.print("Departamento del Cliente: ");
                String departamentoCliente = scanner.nextLine();

                System.out.print("Municipio del Cliente: ");
                String municipioCliente = scanner.nextLine();

                System.out.print("Dirección Física: ");
                String direccionFisica = scanner.nextLine();

                System.out.print("Nombre Completo: ");
                String nombreCompleto = scanner.nextLine();

                System.out.print("Correo Electrónico: ");
                String correoElectronico = scanner.nextLine();
                String[] arrayCliente = clienteController.CreateClient(numeroIdentificacion, tipoIdentificacion, direccionFisica, nombreCompleto, correoElectronico, municipioCliente, departamentoCliente);
                System.out.println("Cliente creado con éxito. Datos del registrador:");
                break;
            case 2:
                
                // Editar cliente
                break;
            case 3:
                // Crear registrador o contador
                break;
            case 4:
                // Editar registrador
                break;
            case 5:
                // Cargar consumos de todos los clientes
                break;
            case 6:
                // Cargar consumos de un cliente
                break;
            case 7:
                // Cambiar consumo de una hora de un mes de un registrador de un cliente
                break;
            case 8:
                // Generar factura del cliente
                break;
            case 9:
                // Hallar consumo mínimo de un mes
                break;
            case 10:
                // Hallar consumo máximo de un mes
                break;
            case 11:
                // Hallar consumo por franjas
                break;
            case 12:
                // Hallar consumo por días
                break;
            case 13:
                // Calcular valor de la factura a pagar mes seleccionado
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");

        }
        scanner.nextLine(); // Limpiar el buffer de entrada


    }
}
