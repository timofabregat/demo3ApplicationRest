package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.dtos.CalificacionDTO;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.dtos.ComentarioDTO;
import com.example.demo3.services.CalificacionRestService;
import com.example.demo3.services.ClienteRestService;
import com.example.demo3.services.ComentarioRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ComentariosController implements Initializable {

    @Autowired
    private CalificacionRestService calificacionRestService;

    @Autowired
    private ComentarioRestService comentarioRestService;

    @Autowired
    private ClienteRestService clienteRestService;

    ActividadDTO actividad;
    ClienteDTO cliente;
    List<ComentarioDTO> comentarios;

    public void setData(ActividadDTO actividad, ClienteDTO cliente) {
        this.actividad = actividad;
        this.comentarios = comentarioRestService.findByActivity(actividad.getId());
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane paneComentarios;

    @FXML
    private ChoiceBox<Integer> puntosChoiceBox;

    @FXML
    private TextArea comentField;

    @FXML
    private Label usernamelabel;

    @FXML
    private Label datelabel;

    @FXML
    private Label commentlabel;

    @FXML
    private Label numComlabel;

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle("Demasiados caracteres");
        alert.setHeaderText(null);
        alert.setContentText("El límite de caracteres para un comentario es de 200.");
        alert.showAndWait();
    }

    private void showAlert2(String title,String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void calificar(ActionEvent event) {
        if (puntosChoiceBox.getValue() != null) {
            CalificacionDTO calificacionDTO = new CalificacionDTO();
            calificacionDTO.setIdcliente(cliente.getId());
            calificacionDTO.setIdactividad(actividad.getId());
            calificacionDTO.setPuntos(puntosChoiceBox.getValue());
            try{
                ResponseEntity response = calificacionRestService.upsortCalificacion(calificacionDTO);

            }catch (HttpClientErrorException error){
                if(error.getStatusCode()==HttpStatus.BAD_REQUEST){
                    showAlert2("Informacion invalida","Pruebe con valores correctos");
                }
            }
        }

    }

    @FXML
    void comentar(ActionEvent event) {
        String comentario = comentField.getText();
        if (comentario.length() >= 200) {
            showAlert(
            );
        }
        else {
            try{
                ComentarioDTO comentarioDTO = new ComentarioDTO();
                comentarioDTO.setIdcliente(cliente.getId());
                comentarioDTO.setIdactividad(actividad.getId());
                comentarioDTO.setComentario(comentField.getText());

                ResponseEntity response = comentarioRestService.addComentario(comentarioDTO);

                comentarios = comentarioRestService.findByActivity(actividad.getId());

            }catch (HttpClientErrorException error){
                if (error.getStatusCode()==HttpStatus.BAD_REQUEST){
                    showAlert2("Informacion invalida","Pruebe de vuelta con informacion valida");
                }
            }
        }

    }

    @FXML
    void siguientecomentario(ActionEvent event) {
        if (comentarios.size() > index + 1) {
            index++;
            ComentarioDTO com = comentarios.get(index);
            usernamelabel.setText(clienteRestService.findUsernameById(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
            numComlabel.setText(index+1 + "/" + comentarios.size());
        }
        else {
            index = 0;
            ComentarioDTO com = comentarios.get(index);
            usernamelabel.setText(clienteRestService.findUsernameById(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
            numComlabel.setText(index+1 + "/" + comentarios.size());
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        paneComentarios.getChildren().setAll(pane);
    }

    private final Integer[] puntos = {1, 2, 3, 4, 5};
    int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        puntosChoiceBox.getItems().addAll(puntos);
        comentField.setWrapText(true);
        if (comentarios.size() > 0) {
            ComentarioDTO com = comentarios.get(0);
            usernamelabel.setText(clienteRestService.findUsernameById(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
            numComlabel.setText(1 + "/" + comentarios.size());
        }
        else if (comentarios.size() == 0) {
            usernamelabel.setText("No hay comentarios.");
            datelabel.setText("");
            commentlabel.setText("");
            numComlabel.setText("");
        }
    }

}