package view;

public class IndexView {

    private final String menu = """
        === Index Menu ===
        1. Crear cliente 
        2. Editar cliente (Cambiar atributos excepto el número único de identificación.)
        3. Crear registrador o contador
        4. Editar registrador (Cambiar sus atributos excepto su número de identificación)
        5. Cargar de forma automática los consumos de todos los clientes en un mes – año respectivo.
        6. Cargar de forma automática los consumos de un cliente del mes respectivo.
        7. Cambiar el consumo de una hora de un mes de un registrador de un cliente.
        8. Generar la factura del cliente de un mes respectivo, creación de un archivo.pdf como salida.
        9. Hallar de un cliente: el consumo mínimo de un mes.
        10. Hallar de un cliente: el consumo máximo de un mes.
        11. Hallar de un cliente: el consumo por franjas.
        12. Hallar de un cliente: el consumo por días.
        13. Calcular de un cliente: valor de la factura a pagar mes seleccionado
        Please select an option:
        """;
        public String Principal() {
            return menu;
        }
}
