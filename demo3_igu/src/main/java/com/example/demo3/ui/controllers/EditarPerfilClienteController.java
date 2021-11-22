package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.services.ClienteRestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@Component
public class EditarPerfilClienteController implements Initializable{

    private byte[] image_bytes;
    ClienteDTO cliente;
    void setCliente(ClienteDTO cliente){this.cliente = cliente;}

    @Autowired
    private ClienteRestService clienteRestService;

    @Autowired
    private HomeClienteController homeClienteController;

    @FXML
    private AnchorPane editar_pane;

    @FXML
    private PasswordField contrasena_field;

    @FXML
    private PasswordField confirmacion_field;

    @FXML
    private CheckBox vacunado_field;

    @FXML
    private Button editar_button;

    @FXML
    private ImageView perfil_view;

    @FXML
    private Button foto_button;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (cliente.getVacuna_covid()) {
            vacunado_field.setVisible(false);
        }
        if (cliente.getImagencliente() != null) {
            InputStream is = new ByteArrayInputStream(cliente.getImagencliente());
            Image foto = new Image(is);
            perfil_view.setImage(foto);
            vacunado_field.setSelected(cliente.getVacuna_covid());
            this.image_bytes = cliente.getImagencliente();

            double w = 0;
            double h = 0;

            double ratioX = perfil_view.getFitWidth() / foto.getWidth();
            double ratioY = perfil_view.getFitHeight() / foto.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = foto.getWidth() * reducCoeff;
            h = foto.getHeight() * reducCoeff;

            perfil_view.setX((perfil_view.getFitWidth() - w) / 2);
            perfil_view.setY((perfil_view.getFitHeight() - h) / 2);
        }
    }

    @FXML
    void updateCliente(ActionEvent event) {
        String contrasena = contrasena_field.getText();
        String confirmar = confirmacion_field.getText();
        Boolean vacuna = vacunado_field.isSelected();

        byte[] imagen_perfil = image_bytes;
        clienteRestService.updateImagen(cliente.getId(), imagen_perfil);

        if(!contrasena.equals(cliente.getContrasena())) {
            if (contrasena.equals(confirmar)) {
                try {
                    clienteRestService.updateCliente(cliente.getId(), contrasena, vacuna);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    homeClienteController.setCliente(cliente);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                    editar_pane.getChildren().setAll(pane);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
            else {
                showAlert("Datos incorrectos", "Las contraseñas no son iguales");
            }

        }
        else{
            showAlert("Datos incorrectos", "La contraseña es igual a la actual");
        }

/*        try {
            clienteMgr.updateCliente(cliente.getId(), contrasena, vacuna);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
            homeClienteController.setCliente(cliente);
            AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
            editar_pane.getChildren().setAll(pane);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }*/


    }

    @FXML
    public void seleccionar_imagen(ActionEvent action) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        Stage stage = (Stage)editar_pane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            this.image_bytes = Files.readAllBytes(path);

            //Mostrar la imagen seleccionada
            Image image = new Image("file:" + ((File) file).getAbsolutePath());
            perfil_view.setImage(image);
            perfil_view.autosize();
            double w = 0;
            double h = 0;

            double ratioX = perfil_view.getFitWidth() / image.getWidth();
            double ratioY = perfil_view.getFitHeight() / image.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = image.getWidth() * reducCoeff;
            h = image.getHeight() * reducCoeff;

            perfil_view.setX((perfil_view.getFitWidth() - w) / 2);
            perfil_view.setY((perfil_view.getFitHeight() - h) / 2);
        }
    }
}
