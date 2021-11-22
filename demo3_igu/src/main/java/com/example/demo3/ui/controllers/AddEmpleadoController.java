package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.EmpleadoDTO;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.EmpleadoRestService;
import com.example.demo3.services.OperadorRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AddEmpleadoController implements Initializable {

    @Autowired
    private EmpleadoRestService empleadoRestService;

    @Autowired
    private OperadorRestService operadorRestService;

    @FXML
    private AnchorPane emp_pane;

    @FXML
    private TextField username_field;

    @FXML
    private ChoiceBox<Integer> operador_choiceBox;

    @FXML
    private PasswordField password_field;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        emp_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void registrarEmpleado(ActionEvent event) {
        if (username_field.getText() == null || username_field.getText().equals("") || //chequeamos que nada sea nulo
                password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                    "Faltan datos",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            String username = username_field.getText();
            String password = password_field.getText();
            Integer empresa = operador_choiceBox.getValue();

            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setIdoperador(empresa);
            empleadoDTO.setUsername(username);
            empleadoDTO.setPassword(password);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                emp_pane.getChildren().setAll(pane);

                ResponseEntity response = empleadoRestService.addEmpleado(empleadoDTO);

            } catch (HttpClientErrorException error) {
                if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                    showAlert(
                            "Empleado ya registrado !",
                            "El empleado indicado ya ha sido registrado en el sistema.");
                } else {
                    showAlert(
                            "Error Generico",
                            "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<OperadorDTO> q = operadorRestService.getAll();

        List<Integer> ids = new ArrayList<Integer>();

        for(OperadorDTO o: q){
            Integer i = o.getId();
            ids.add(i);
        }

        /*String[] opes = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                opes[i] = q.get(i).getEmpresa();
            }
        }*/
        operador_choiceBox.getItems().addAll(ids);
    }

}