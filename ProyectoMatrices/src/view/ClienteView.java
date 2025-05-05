package view;

public class ClienteView {
        private final String menuRegistro = 
            "=== Menú de Registro de Cliente ===\n" +
            "Por favor, ingrese los siguientes datos:\n" +
            "1. Número de Identificación\n" +
            "2. Tipo de Identificación\n" +
            "3. Departamento del Cliente\n" +
            "4. Municipio del Cliente\n" +
            "5. Dirección Física\n" +
            "6. Nombre Completo\n" +
            "7. Correo Electrónico\n" +
            "====================================";
        public String GetMenuRegistro() {
            return menuRegistro;
        }
}
