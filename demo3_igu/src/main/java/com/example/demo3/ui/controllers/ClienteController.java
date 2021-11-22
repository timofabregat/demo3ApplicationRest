package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.services.ClienteRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class ClienteController implements Initializable {

    @Autowired
    private InteresesClienteController interesesClienteController;

    @Autowired
    private ClienteRestService clienteRestService;

    @FXML
    private TextField mail_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField document_field;

    @FXML
    private ChoiceBox<String> document_choicebox;

    @FXML
    private DatePicker date_picker;

    @FXML
    private CheckBox vacuna_check;

    @FXML
    private ChoiceBox<String> pais_choicebox;

    @FXML
    private AnchorPane cliente_pane;

    @FXML
    private ImageView perfil_image = new ImageView();

    @FXML
    private Button seleccionar_button;

    private byte[] image_bytes;
    private final String[] paises = {"Afganistán","Albania","Alemania","Andorra","Angola","Antigua y Barbuda","Arabia Saudita","Argelia","Argentina","Armenia","Australia","Austria","Azerbaiyán","Bahamas","Bangladés","Barbados","Baréin","Bélgica","Belice","Benín","Bielorrusia","Birmania","Bolivia","Bosnia y Herzegovina","Botsuana","Brasil","Brunéi","Bulgaria","Burkina Faso","Burundi","Bután","Cabo Verde","Camboya","Camerún","Canadá","Catar","Chad","Chile","China","Chipre","Ciudad del Vaticano","Colombia","Comoras","Corea del Norte","Corea del Sur","Costa de Marfil","Costa Rica","Croacia","Cuba","Dinamarca","Dominica","Ecuador","Egipto","El Salvador","Emiratos Árabes Unidos","Eritrea","Eslovaquia","Eslovenia","España","Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Fiyi","Francia","Gabón","Gambia","Georgia","Ghana","Granada","Grecia","Guatemala","Guyana","Guinea","Guinea ecuatorial","Guinea-Bisáu","Haití","Honduras","Hungría","India","Indonesia","Irak","Irán","Irlanda","Islandia","Islas Marshall","Islas Salomón","Israel","Italia","Jamaica","Japón","Jordania","Kazajistán","Kenia","Kirguistán","Kiribati","Kuwait","Laos","Lesoto","Letonia","Líbano","Liberia","Libia","Liechtenstein","Lituania","Luxemburgo","Madagascar","Malasia","Malaui","Maldivas","Malí","Malta","Marruecos","Mauricio","Mauritania","México","Micronesia","Moldavia","Mónaco","Mongolia","Montenegro","Mozambique","Namibia","Nauru","Nepal","Nicaragua","Níger","Nigeria","Noruega","Nueva Zelanda","Omán","Países Bajos","Pakistán","Palaos","Palestina","Panamá","Papúa Nueva Guinea","Paraguay","Perú","Polonia","Portugal","Reino Unido","República Centroafricana","República Checa","República de Macedonia","República del Congo","República Democrática del Congo","República Dominicana","República Sudafricana","Ruanda","Rumanía","Rusia","Samoa","San Cristóbal y Nieves","San Marino","San Vicente y las Granadinas","Santa Lucía","Santo Tomé y Príncipe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Siria","Somalia","Sri Lanka","Suazilandia","Sudán","Sudán del Sur","Suecia","Suiza","Surinam","Tailandia","Tanzania","Tayikistán","Timor Oriental","Togo","Tonga","Trinidad y Tobago","Túnez","Turkmenistán","Turquía","Tuvalu","Ucrania","Uganda","Uruguay","Uzbekistán","Vanuatu","Venezuela","Vietnam","Yemen","Yibuti","Zambia","Zimbabue"};
    private final String[] documentos = {"Pasaporte", "Cédula"};

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Main.fxml"));
        cliente_pane.getChildren().setAll(pane);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    //private void clean() {
    //    document_field.setText(null);
    //    mail_field.setText(null);
    //    document_field.setText(null);
    //}

    @FXML
    void seleccionarImagen(ActionEvent Action) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        Stage stage = (Stage)cliente_pane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            this.image_bytes = Files.readAllBytes(path);
            
            Image image = new Image("file:" + ((File) file).getAbsolutePath());
            perfil_image.setImage(image);

            double w = 0;
            double h = 0;

            double ratioX = perfil_image.getFitWidth() / image.getWidth();
            double ratioY = perfil_image.getFitHeight() / image.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = image.getWidth() * reducCoeff;
            h = image.getHeight() * reducCoeff;

            perfil_image.setX((perfil_image.getFitWidth() - w) / 2);
            perfil_image.setY((perfil_image.getFitHeight() - h) / 2);
        }
    }

    @FXML
    void addClient(ActionEvent event) {
        if (document_field.getText() == null || document_field.getText().equals("") || //chequeamos que nada sea nulo
                mail_field.getText() == null || mail_field.getText().equals("") ||
                document_field.getText() == null || document_field.getText().equals("")) {

            showAlert(
                    "Faltan datos",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                String mail = mail_field.getText();
                String contrasena = password_field.getText();
                Long documento = Long.valueOf(document_field.getText());
                String tipo_documento = document_choicebox.getValue();
                LocalDate fecha_nacimiento = date_picker.getValue();
                Boolean vacuna_covid = vacuna_check.isSelected();
                String pais = pais_choicebox.getValue();
                byte[] imagen_cliente = image_bytes;

                //Nuevo
                ClienteDTO cliente = new ClienteDTO();
                cliente.setMail(mail);
                cliente.setContrasena(contrasena);
                cliente.setDocumento(documento);
                cliente.setTipo_documento(tipo_documento);
                cliente.setFecha_nacimiento(fecha_nacimiento);
                cliente.setVacuna_covid(vacuna_covid);
                cliente.setPais(pais);
                cliente.setImagencliente(imagen_cliente);

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

                    //Nuevo
                    ResponseEntity response = clienteRestService.addCliente(cliente);

                    if (response.getStatusCode() == HttpStatus.OK) {
                        showAlert("Cliente agregado", "Se agrego un cliente!");

                        ClienteDTO clienteDTO = clienteRestService.findByMailandPassword(mail,contrasena);

                        interesesClienteController.setCliente(clienteDTO);

                        AnchorPane pane = fxmlLoader.load(InteresesClienteController.class.getResourceAsStream("InteresesCliente.fxml"));
                        cliente_pane.getChildren().setAll(pane);
                    }

                } catch (HttpClientErrorException error) {
                    if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                    } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                        showAlert(
                                "Documento ya registrado !",
                                "El documento indicado ya ha sido registrado en el sistema.");
                    } else {
                        showAlert(
                                "Error Generico",
                                "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }catch (NumberFormatException e) {
                showAlert("Datos incorrectos", "El documento no tiene el formato esperado (numerico).");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pais_choicebox.getItems().addAll(paises);
        document_choicebox.getItems().addAll(documentos);
    }


}

