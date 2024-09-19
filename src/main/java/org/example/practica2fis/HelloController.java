package org.example.practica2fis;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class HelloController implements Initializable {
    @FXML
    private ComboBox<String> cmb_tipo_persona;
    @FXML
    private Button salir;
    @FXML
    private TextField txt_asiento_seleccionado;

    // Declarar cada asiento como un StackPane con @FXML
    @FXML
    private StackPane asientoA1, asientoA2, asientoA3, asientoA4, asientoA5, asientoA6;
    @FXML
    private StackPane asientoB1, asientoB2, asientoB3, asientoB4, asientoB5, asientoB6;
    @FXML
    private StackPane asientoC1, asientoC2, asientoC3, asientoC4, asientoC5, asientoC6;

    // Mapa para almacenar los asientos y asociarlos con sus StackPane
    private Map<String, StackPane> asientosMap = new HashMap<>();
    private ArrayList<String> asientos_escogidos = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar el ComboBox
        cmb_tipo_persona.getItems().addAll("Profesor", "Egresado", "Familiar");

        // Asociar los StackPane a sus identificadores de asiento
        asientosMap.put("A1", asientoA1);
        asientosMap.put("A2", asientoA2);
        asientosMap.put("A3", asientoA3);
        asientosMap.put("A4", asientoA4);
        asientosMap.put("A5", asientoA5);
        asientosMap.put("A6", asientoA6);
        asientosMap.put("B1", asientoB1);
        asientosMap.put("B2", asientoB2);
        asientosMap.put("B3", asientoB3);
        asientosMap.put("B4", asientoB4);
        asientosMap.put("B5", asientoB5);
        asientosMap.put("B6", asientoB6);
        asientosMap.put("C1", asientoC1);
        asientosMap.put("C2", asientoC2);
        asientosMap.put("C3", asientoC3);
        asientosMap.put("C4", asientoC4);
        asientosMap.put("C5", asientoC5);
        asientosMap.put("C6", asientoC6);

        // Inicializa los colores de los asientos (opcional)
        asientosMap.forEach((key, stackPane) -> {
            Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
            rect.setFill(Color.TRANSPARENT); // Asignar color transparente inicialmente
        });
    }

    @FXML
    public void onSalirBoton() {
        Platform.exit();
    }

    @FXML
    public void OnAsignarBtn() {
        String asientoSeleccionado = txt_asiento_seleccionado.getText();
        String tipoPersonaSeleccionado = cmb_tipo_persona.getSelectionModel().getSelectedItem();

        if (tipoPersonaSeleccionado == null) {
            mostrarAlerta("Error", "Por favor selecciona un tipo de persona antes de escoger un asiento.");
            return;
        }

        if (!validarAsientoPorTipo(asientoSeleccionado, tipoPersonaSeleccionado)) {
            mostrarAlerta("Error", "No puedes seleccionar este asiento. Solo puedes escoger asientos del grupo " + obtenerGrupoPermitido(tipoPersonaSeleccionado));
            return;
        }

        if (!asientos_escogidos.contains(asientoSeleccionado) && asientosMap.containsKey(asientoSeleccionado)) {
            asientos_escogidos.add(asientoSeleccionado);
            System.out.println("Los asientos ocupados son:" + asientos_escogidos);

            // Cambia el color del asiento seleccionado
            StackPane asiento = asientosMap.get(asientoSeleccionado);
            Rectangle rect = (Rectangle) asiento.getChildren().get(0);
            rect.setFill(Color.RED); // Cambiar color a rojo cuando se selecciona

            // Deshabilitar el asiento para evitar que sea seleccionado nuevamente
            asiento.setDisable(true);
        } else {
            mostrarAlerta("Error", "Asiento ya seleccionado o no válido.");
        }
    }

    // Validar si el asiento pertenece al grupo permitido según el tipo de persona
    private boolean validarAsientoPorTipo(String asiento, String tipoPersona) {
        String grupoPermitido = obtenerGrupoPermitido(tipoPersona);
        return asiento.startsWith(grupoPermitido);
    }

    // Obtener el grupo permitido según el tipo de persona
    private String obtenerGrupoPermitido(String tipoPersona) {
        switch (tipoPersona) {
            case "Profesor":
                return "A"; // Asientos A para profesores
            case "Egresado":
                return "B"; // Asientos B para egresados
            case "Familiar":
                return "C"; // Asientos C para familiares
            default:
                return "";
        }
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alerta.setTitle(titulo);
        alerta.showAndWait();
    }
}