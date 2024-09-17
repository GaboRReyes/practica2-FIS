package org.example.practica2fis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ComboBox<String> cmb_tipo_persona = new ComboBox<>();
    private ArrayList<String> tipos = new ArrayList<>(Arrays.asList("Profesor", "Egresado", "Familiar"));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> itemsList = FXCollections.observableArrayList(tipos);

        cmb_tipo_persona.setItems(itemsList);


    }
}