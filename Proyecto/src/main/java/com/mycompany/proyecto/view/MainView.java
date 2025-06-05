package com.mycompany.proyecto.view;

import com.mycompany.proyecto.controller.SistemaEnergia;
import com.mycompany.proyecto.model.Cliente;
import com.mycompany.proyecto.model.FacturaGenerator;
import com.mycompany.proyecto.model.Registrador;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {
    private final SistemaEnergia sistema;
    private TabPane tabPane;

    public MainView() {
        sistema = new SistemaEnergia();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Facturación de Energía");

        tabPane = new TabPane();

        // Crear pestañas para cada funcionalidad
        Tab inicioTab = createInicioTab();
        Tab clientesTab = createClientesTab();
        Tab registradoresTab = createRegistradoresTab();
        Tab consumoTab = createConsumoTab();
        Tab facturasTab = createFacturasTab();
        Tab modificarConsumoTab = createModificarConsumoTab();
        Tab consumoFranjasTab = createConsumoFranjasTab();
        Tab cargaMasivaTab = createCargaMasivaTab();

        tabPane.getTabs().addAll(
            inicioTab,
            clientesTab,
            registradoresTab,
            consumoTab,
            modificarConsumoTab,
            facturasTab,
            consumoFranjasTab,
            cargaMasivaTab // nueva pestaña
        );

        tabPane.getTabs().forEach(tab -> tab.setClosable(false));

        Scene scene = new Scene(tabPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Pestaña principal con botones de acceso rápido
    private Tab createInicioTab() {
        Tab tab = new Tab("Inicio");
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        Button btnClientes = new Button("Crear/Editar Cliente");
        btnClientes.setOnAction(e -> tabPane.getSelectionModel().select(1));

        Button btnRegistradores = new Button("Crear/Editar Registrador");
        btnRegistradores.setOnAction(e -> tabPane.getSelectionModel().select(2));

        Button btnCargarConsumo = new Button("Cargar Consumo");
        btnCargarConsumo.setOnAction(e -> tabPane.getSelectionModel().select(3));

        Button btnModificarConsumo = new Button("Modificar Consumo por Hora");
        btnModificarConsumo.setOnAction(e -> tabPane.getSelectionModel().select(4));

        Button btnFacturas = new Button("Generar Factura");
        btnFacturas.setOnAction(e -> tabPane.getSelectionModel().select(5));

        Button btnFranjas = new Button("Consumo por Franjas Horarias");
        btnFranjas.setOnAction(e -> tabPane.getSelectionModel().select(6));

        content.getChildren().addAll(
            new Label("Bienvenido al Sistema de Facturación de Energía"),
            btnClientes,
            btnRegistradores,
            btnCargarConsumo,
            btnModificarConsumo,
            btnFacturas,
            btnFranjas
        );
        tab.setContent(content);
        return tab;
    }

    private Tab createClientesTab() {
        Tab tab = new Tab("Clientes");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        // Formulario para crear cliente
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idField = new TextField();
        TextField nombreField = new TextField();
        TextField direccionField = new TextField();
        TextField tipoIdField = new TextField();
        TextField correoField = new TextField();
        TextField ciudadField = new TextField();

        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Nombre:"), 0, 1);
        form.add(nombreField, 1, 1);
        form.add(new Label("Dirección:"), 0, 2);
        form.add(direccionField, 1, 2);
        form.add(new Label("Tipo ID:"), 0, 3);
        form.add(tipoIdField, 1, 3);
        form.add(new Label("Correo:"), 0, 4);
        form.add(correoField, 1, 4);
        form.add(new Label("Ciudad:"), 0, 5);
        form.add(ciudadField, 1, 5);

        Button crearBtn = new Button("Crear Cliente");
        crearBtn.setOnAction(e -> {
            try {
                Cliente cliente = new Cliente(
                    idField.getText(),
                    nombreField.getText(),
                    direccionField.getText(),
                    tipoIdField.getText(),
                    correoField.getText(),
                    ciudadField.getText()
                );
                sistema.agregarCliente(cliente);
                showAlert("Cliente creado exitosamente", Alert.AlertType.INFORMATION);
                limpiarCampos(idField, nombreField, direccionField, tipoIdField, correoField, ciudadField);
            } catch (Exception ex) {
                showAlert("Error al crear cliente: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Formulario para editar cliente
        GridPane editForm = new GridPane();
        editForm.setHgap(10);
        editForm.setVgap(10);

        TextField editIdField = new TextField();
        TextField editNombreField = new TextField();
        TextField editDireccionField = new TextField();
        TextField editCorreoField = new TextField();
        TextField editCiudadField = new TextField();

        editForm.add(new Label("ID:"), 0, 0);
        editForm.add(editIdField, 1, 0);
        editForm.add(new Label("Nuevo Nombre:"), 0, 1);
        editForm.add(editNombreField, 1, 1);
        editForm.add(new Label("Nueva Dirección:"), 0, 2);
        editForm.add(editDireccionField, 1, 2);
        editForm.add(new Label("Nuevo Correo:"), 0, 3);
        editForm.add(editCorreoField, 1, 3);
        editForm.add(new Label("Nueva Ciudad:"), 0, 4);
        editForm.add(editCiudadField, 1, 4);

        Button editarBtn = new Button("Editar Cliente");
        editarBtn.setOnAction(e -> {
            boolean ok = false;
            try {
                ok = sistema.editarCliente(
                    editIdField.getText(),
                    editNombreField.getText(),
                    editDireccionField.getText(),
                    editCorreoField.getText(),
                    editCiudadField.getText()
                );
            } catch (Exception ex) {
                showAlert("Error al editar cliente: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
            if (ok) {
                showAlert("Cliente editado exitosamente", Alert.AlertType.INFORMATION);
                limpiarCampos(editIdField, editNombreField, editDireccionField, editCorreoField, editCiudadField);
            } else {
                showAlert("Cliente no encontrado", Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(
            new Label("Crear Nuevo Cliente"), form, crearBtn,
            new Label("Editar Cliente Existente"), editForm, editarBtn
        );
        tab.setContent(content);
        return tab;
    }

    private Tab createRegistradoresTab() {
        Tab tab = new Tab("Registradores");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idClienteField = new TextField();
        TextField idRegistradorField = new TextField();
        TextField ubicacionField = new TextField();

        form.add(new Label("ID Cliente:"), 0, 0);
        form.add(idClienteField, 1, 0);
        form.add(new Label("ID Registrador:"), 0, 1);
        form.add(idRegistradorField, 1, 1);
        form.add(new Label("Ubicación:"), 0, 2);
        form.add(ubicacionField, 1, 2);

        Button crearBtn = new Button("Crear Registrador");
        crearBtn.setOnAction(e -> {
            try {
                Registrador registrador = new Registrador(idRegistradorField.getText(), ubicacionField.getText());
                boolean success = sistema.agregarRegistradorACliente(idClienteField.getText(), registrador);
                if (success) {
                    showAlert("Registrador creado exitosamente", Alert.AlertType.INFORMATION);
                    limpiarCampos(idClienteField, idRegistradorField, ubicacionField);
                } else {
                    showAlert("Cliente no encontrado", Alert.AlertType.ERROR);
                }
            } catch (Exception ex) {
                showAlert("Error al crear registrador: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Formulario para editar registrador
        GridPane editForm = new GridPane();
        editForm.setHgap(10);
        editForm.setVgap(10);

        TextField editIdClienteField = new TextField();
        TextField editIdRegistradorField = new TextField();
        TextField editUbicacionField = new TextField();

        editForm.add(new Label("ID Cliente:"), 0, 0);
        editForm.add(editIdClienteField, 1, 0);
        editForm.add(new Label("ID Registrador:"), 0, 1);
        editForm.add(editIdRegistradorField, 1, 1);
        editForm.add(new Label("Nueva Ubicación:"), 0, 2);
        editForm.add(editUbicacionField, 1, 2);

        Button editarBtn = new Button("Editar Registrador");
        editarBtn.setOnAction(e -> {
            boolean ok = false;
            try {
                ok = sistema.editarRegistrador(
                    editIdClienteField.getText(),
                    editIdRegistradorField.getText(),
                    editUbicacionField.getText()
                );
            } catch (Exception ex) {
                showAlert("Error al editar registrador: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
            if (ok) {
                showAlert("Registrador editado exitosamente", Alert.AlertType.INFORMATION);
                limpiarCampos(editIdClienteField, editIdRegistradorField, editUbicacionField);
            } else {
                showAlert("Cliente o registrador no encontrado", Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(
            new Label("Crear Nuevo Registrador"), form, crearBtn,
            new Label("Editar Registrador Existente"), editForm, editarBtn
        );
        tab.setContent(content);
        return tab;
    }

 private Tab createConsumoTab() {
        Tab tab = new Tab("Consumo");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idClienteField = new TextField();

        form.add(new Label("ID Cliente:"), 0, 0);
        form.add(idClienteField, 1, 0);

        Button generarBtn = new Button("Generar Consumo");
        generarBtn.setOnAction(e -> {
            Cliente cliente = sistema.buscarCliente(idClienteField.getText());
            if (cliente != null) {
                for (Registrador r : cliente.getRegistradores()) {
                    r.getConsumo().generarDatos();
                }
                showAlert("Consumo generado exitosamente", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Cliente no encontrado", Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(new Label("Generar Consumo"), form, generarBtn);
        tab.setContent(content);
        return tab;
    }

    private Tab createFacturasTab() {
        Tab tab = new Tab("Facturas");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idClienteField = new TextField();
        ComboBox<Integer> mesCombo = new ComboBox<>();
        for (int i = 1; i <= 12; i++) {
            mesCombo.getItems().add(i);
        }
        TextField nombreArchivoField = new TextField();

        form.add(new Label("ID Cliente:"), 0, 0);
        form.add(idClienteField, 1, 0);
        form.add(new Label("Mes:"), 0, 1);
        form.add(mesCombo, 1, 1);
        form.add(new Label("Nombre Archivo:"), 0, 2);
        form.add(nombreArchivoField, 1, 2);

        Button generarBtn = new Button("Generar Factura");
        generarBtn.setOnAction(e -> {
            Cliente cliente = sistema.buscarCliente(idClienteField.getText());
            if (cliente != null && mesCombo.getValue() != null) {
                try {
                    int mes = mesCombo.getValue();
                    String nombreArchivo = nombreArchivoField.getText().isEmpty() ? 
                        "factura_" + cliente.getId() + "_" + mes : 
                        nombreArchivoField.getText();

                    // Encontrar el menor y mayor consumo
                    int diaMenorConsumo = -1;
                    int horaMenorConsumo = -1;
                    int diaMayorConsumo = -1;
                    int horaMayorConsumo = -1;
                    int menorConsumo = Integer.MAX_VALUE;
                    int mayorConsumo = Integer.MIN_VALUE;

                    for (Registrador r : cliente.getRegistradores()) {
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

                    // Generar la factura
                    FacturaGenerator.generarFactura(
                        cliente,
                        mes,
                        nombreArchivo + ".pdf",
                        diaMenorConsumo,
                        horaMenorConsumo,
                        menorConsumo,
                        diaMayorConsumo,
                        horaMayorConsumo,
                        mayorConsumo
                    );

                    showAlert("Factura generada exitosamente en: " + nombreArchivo + ".pdf", Alert.AlertType.INFORMATION);
                    limpiarCampos(idClienteField, nombreArchivoField);
                    mesCombo.setValue(null);
                } catch (Exception ex) {
                    showAlert("Error al generar factura: " + ex.getMessage(), Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Cliente no encontrado o mes no seleccionado", Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(new Label("Generar Factura"), form, generarBtn);
        tab.setContent(content);
        return tab;
    }

    // Nueva pestaña: Modificar consumo por hora
    private Tab createModificarConsumoTab() {
        Tab tab = new Tab("Modificar Consumo");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idClienteField = new TextField();
        TextField idRegistradorField = new TextField();
        ComboBox<Integer> mesCombo = new ComboBox<>();
        for (int i = 1; i <= 12; i++) mesCombo.getItems().add(i);
        TextField diaField = new TextField();
        TextField horaField = new TextField();
        TextField nuevoKwField = new TextField();

        form.add(new Label("ID Cliente:"), 0, 0);
        form.add(idClienteField, 1, 0);
        form.add(new Label("ID Registrador:"), 0, 1);
        form.add(idRegistradorField, 1, 1);
        form.add(new Label("Mes (1-12):"), 0, 2);
        form.add(mesCombo, 1, 2);
        form.add(new Label("Día:"), 0, 3);
        form.add(diaField, 1, 3);
        form.add(new Label("Hora (0-23):"), 0, 4);
        form.add(horaField, 1, 4);
        form.add(new Label("Nuevo Consumo (kWh):"), 0, 5);
        form.add(nuevoKwField, 1, 5);

        Button modificarBtn = new Button("Modificar Consumo");
        modificarBtn.setOnAction(e -> {
            try {
                Cliente c = sistema.buscarCliente(idClienteField.getText());
                if (c == null) {
                    showAlert("Cliente no encontrado", Alert.AlertType.ERROR);
                    return;
                }
                Registrador r = c.buscarRegistrador(idRegistradorField.getText());
                if (r == null) {
                    showAlert("Registrador no encontrado", Alert.AlertType.ERROR);
                    return;
                }
                int mes = mesCombo.getValue();
                int dia = Integer.parseInt(diaField.getText());
                int hora = Integer.parseInt(horaField.getText());
                int kw = Integer.parseInt(nuevoKwField.getText());
                r.getConsumo().modificarConsumoHora(mes, dia - 1, hora, kw);
                showAlert("Consumo actualizado", Alert.AlertType.INFORMATION);
                limpiarCampos(idClienteField, idRegistradorField, diaField, horaField, nuevoKwField);
                mesCombo.setValue(null);
            } catch (Exception ex) {
                showAlert("Error al modificar consumo: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(new Label("Modificar Consumo por Hora"), form, modificarBtn);
        tab.setContent(content);
        return tab;
    }

    // Nueva pestaña: Consumo por franjas horarias
    private Tab createConsumoFranjasTab() {
        Tab tab = new Tab("Consumo Franjas");
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        TextField idClienteField = new TextField();
        ComboBox<Integer> mesCombo = new ComboBox<>();
        for (int i = 1; i <= 12; i++) mesCombo.getItems().add(i);
        TextField diaField = new TextField();
        TextField horaInicioField = new TextField();
        TextField horaFinField = new TextField();

        form.add(new Label("ID Cliente:"), 0, 0);
        form.add(idClienteField, 1, 0);
        form.add(new Label("Mes (1-12):"), 0, 1);
        form.add(mesCombo, 1, 1);
        form.add(new Label("Día:"), 0, 2);
        form.add(diaField, 1, 2);
        form.add(new Label("Hora Inicio (0-23):"), 0, 3);
        form.add(horaInicioField, 1, 3);
        form.add(new Label("Hora Fin (0-23):"), 0, 4);
        form.add(horaFinField, 1, 4);

        Button calcularBtn = new Button("Calcular Consumo Franja");
        calcularBtn.setOnAction(e -> {
            try {
                Cliente c = sistema.buscarCliente(idClienteField.getText());
                if (c == null) {
                    showAlert("Cliente no encontrado", Alert.AlertType.ERROR);
                    return;
                }
                if (c.getRegistradores().isEmpty()) {
                    showAlert("El cliente no tiene registradores", Alert.AlertType.ERROR);
                    return;
                }
                int mes = mesCombo.getValue();
                int dia = Integer.parseInt(diaField.getText());
                int horaInicio = Integer.parseInt(horaInicioField.getText());
                int horaFin = Integer.parseInt(horaFinField.getText());
                int[][] consumoMensual = c.getRegistradores().get(0).getConsumo().getConsumoMensual(mes);
                int suma = 0;
                StringBuilder sb = new StringBuilder();
                for (int i = horaInicio; i <= horaFin; i++) {
                    int valor = consumoMensual[dia - 1][i];
                    sb.append("Hora ").append(i).append(": ").append(valor).append(" kWh\n");
                    suma += valor;
                }
                sb.append("Consumo total de la franja: ").append(suma).append(" kWh");
                showAlert(sb.toString(), Alert.AlertType.INFORMATION);
                limpiarCampos(idClienteField, diaField, horaInicioField, horaFinField);
                mesCombo.setValue(null);
            } catch (Exception ex) {
                showAlert("Error al calcular franja: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        content.getChildren().addAll(new Label("Consumo por Franjas Horarias"), form, calcularBtn);
        tab.setContent(content);
        return tab;
    }

    // Nueva pestaña: Carga masiva de consumos
    private Tab createCargaMasivaTab() {
        Tab tab = new Tab("Carga Masiva Consumos");
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        Button btnAnual = new Button("Cargar Consumo de Todo el Año (Todos los Clientes)");
        btnAnual.setOnAction(e -> {
            sistema.cargarConsumoTodosAnual();
            showAlert("Consumo anual generado para todos los clientes.", Alert.AlertType.INFORMATION);
        });

        Label lblMes = new Label("Cargar Consumo de un Mes Específico (Todos los Clientes):");
        ComboBox<Integer> mesCombo = new ComboBox<>();
        for (int i = 1; i <= 12; i++) mesCombo.getItems().add(i);

        Button btnMes = new Button("Cargar Consumo del Mes Seleccionado");
        btnMes.setOnAction(e -> {
            Integer mes = mesCombo.getValue();
            if (mes == null) {
                showAlert("Seleccione un mes.", Alert.AlertType.WARNING);
                return;
            }
            sistema.cargarConsumoTodosMes(mes);
            showAlert("Consumo del mes " + mes + " generado para todos los clientes.", Alert.AlertType.INFORMATION);
            mesCombo.setValue(null);
        });

        content.getChildren().addAll(
            new Label("Carga Masiva de Consumos"),
            btnAnual,
            lblMes,
            mesCombo,
            btnMes
        );
        tab.setContent(content);
        return tab;
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void limpiarCampos(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}