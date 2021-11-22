package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.AdminDTO;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.dtos.EmpleadoDTO;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.AdminRestService;
import com.example.demo3.services.ClienteRestService;
import com.example.demo3.services.EmpleadoRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    @Autowired
    private HomeClienteController homeClienteController;

    @Autowired
    private HomeOperadorController homeOperadorController;

    @Autowired
    private AdminRestService adminRestService;

    @Autowired
    private EmpleadoRestService empleadoRestService;

    @Autowired
    private ClienteRestService clienteRestService;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField user_field;

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Main.fxml"));
        login_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {

        if (user_field.getText() == null || user_field.getText().equals("") || //chequeamos que nada sea nulo
            password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                "Faltan datos",
                "Por favor, ingrese toda la informacion requerida");

        } else {

            String mail = user_field.getText();
            String contrasena = password_field.getText();

            AdminDTO admin = adminRestService.findByMailandPassword(mail, contrasena);
            ClienteDTO cliente = clienteRestService.findByMailandPassword(mail, contrasena);
            EmpleadoDTO empleado = empleadoRestService.findByEmpleadoByEmailAndPassword(mail, contrasena);

            if (admin != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                login_pane.getChildren().setAll(pane);

            } else if (cliente != null) {
                if (!cliente.getBloqueado()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    homeClienteController.setCliente(cliente);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                    login_pane.getChildren().setAll(pane);
                }
                else {
                    showAlert(
                            "Usuario bloqueado",
                            "Por favor, comuníquese con nosotros.");
                }

            } else if (empleado != null) {
                OperadorDTO op = empleadoRestService.findOperadorFromEmpleado(empleado.getId());
                if (!op.getBloqueado()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    homeOperadorController.setOperador(op);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
                    login_pane.getChildren().setAll(pane);
                }
                else {
                    showAlert(
                            "Operador bloqueado",
                            "Por favor, comuníquese con nosotros.");
                }

            } else {
                showAlert("Datos incorrectos", "Verifique los datos ingresados.");
            }

        }

    }

}