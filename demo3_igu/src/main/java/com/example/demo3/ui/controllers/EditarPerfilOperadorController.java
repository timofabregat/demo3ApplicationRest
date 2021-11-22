package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.OperadorRestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditarPerfilOperadorController implements Initializable {

    OperadorDTO operador;
    void setOperador(OperadorDTO operador) {
        this.operador = operador;
    }

    @Autowired
    private OperadorRestService operadorRestService;

    @Autowired
    private HomeOperadorController homeOperadorController;

    @FXML
    private AnchorPane pane_empresa;

    @FXML
    private TextField operador_name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private ChoiceBox<String> depto_choice;

    @FXML
    private TextField email_field;

    @FXML
    private TextField direccion_field;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void updateOperador(ActionEvent event) throws IOException {
        try {
            String nombreEmpresa = operador_name_field.getText();
            String departamento = depto_choice.getValue();
            Long telefono = 0L;
            if (!phone_field.getText().equals("")) {
                telefono = Long.valueOf(phone_field.getText());
            }
            String emailContacto = email_field.getText();
            String direccion = direccion_field.getText();

            try {
                operadorRestService.updateOperador(operador.getId(), nombreEmpresa,departamento,telefono,emailContacto,direccion);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                homeOperadorController.setOperador(operador);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
                pane_empresa.getChildren().setAll(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {

            showAlert(
                    "Datos incorrectos",
                    "El teléfono no tiene el formato esperado (numerico).");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operador_name_field.setText(operador.getEmpresa());
        phone_field.setText(operador.getTelefono().toString());
        email_field.setText(operador.getEmailcontacto());
        direccion_field.setText(operador.getDireccion());

        String[] deptos = {"Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José", "Soriano", "Tacuarembó", "Treinta y Tres",};
        depto_choice.getItems().addAll(deptos);
    }
}