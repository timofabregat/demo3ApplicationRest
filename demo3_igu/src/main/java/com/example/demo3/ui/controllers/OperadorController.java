package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.OperadorRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OperadorController implements Initializable {

    @Autowired
    private OperadorRestService operadorRestService;

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

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        pane_empresa.getChildren().setAll(pane);
    }


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    /*private void clean() {
        employee_name_field.setText(null);
        direccion_field.setText(null);
        phone_field.setText(null);
    }*/

    @FXML
    void addOperador(ActionEvent event) {
        if (operador_name_field.getText() == null || operador_name_field.getText().equals("")||
                depto_choice.getValue() == null || depto_choice.getValue().equals("") ||
                phone_field.getText() == null || phone_field.getText().equals("") ||
                email_field.getText() == null || email_field.getText().equals("") ||
                direccion_field.getText() == null || direccion_field.getText().equals(""))  {

            showAlert(
                    "Faltan datos!",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                String nombreEmpresa = operador_name_field.getText();
                String departamento = depto_choice.getValue();
                Long telefono = Long.valueOf(phone_field.getText());
                String emailContacto = email_field.getText();
                String direccion = direccion_field.getText();

                OperadorDTO operadorDTO = new OperadorDTO();
                operadorDTO.setEmpresa(nombreEmpresa);
                operadorDTO.setDepartamento(departamento);
                operadorDTO.setTelefono(telefono);
                operadorDTO.setEmailcontacto(emailContacto);
                operadorDTO.setDireccion(direccion);

                try{

                    ResponseEntity response = operadorRestService.addOperador(operadorDTO);

                    if (response.getStatusCode() == HttpStatus.OK){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                        pane_empresa.getChildren().setAll(pane);
                    }

                }catch (HttpClientErrorException error){
                    if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                    } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                        showAlert(
                                "Operador ya registrado",
                                "El documento indicado ya ha sido registrado en el sistema.");
                    } else {
                        showAlert(
                                "Error Generico",
                                "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos!",
                        "El teléfono no tiene el formato esperado (numerico).");

            }
        }

    }


    private final String[] deptos = {"Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José", "Soriano", "Tacuarembó", "Treinta y Tres",};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        depto_choice.getItems().addAll(deptos);
    }
}