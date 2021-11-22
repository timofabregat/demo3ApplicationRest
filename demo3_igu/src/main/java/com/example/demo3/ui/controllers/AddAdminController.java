package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.AdminDTO;
import com.example.demo3.services.AdminRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Component
public class AddAdminController {

    @Autowired
    private AdminRestService adminRestService;

    @FXML
    private AnchorPane reg_admin_pane;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField password_field;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como si lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void addAdmin(ActionEvent event) {
        if (username_field.getText() == null || username_field.getText().equals("") || //chequeamos que nada sea nulo
                password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                    "Faltan datos",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            String username = username_field.getText();
            String password = password_field.getText();

            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setEmail(username);
            adminDTO.setPassword(password);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                reg_admin_pane.getChildren().setAll(pane);

                ResponseEntity response = adminRestService.addAdmin(adminDTO);
            } catch (HttpClientErrorException error) {
                if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                    showAlert(
                            "Admin ya registrado !",
                            "El admin indicado ya ha sido registrado en el sistema.");
                } else {
                    showAlert(
                            "Error Generico",
                            "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                }
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        reg_admin_pane.getChildren().setAll(pane);
    }

}
