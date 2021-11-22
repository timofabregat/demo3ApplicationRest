package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.CalificacionRestService;
import com.example.demo3.services.OperadorRestService;
import com.example.demo3.ui.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ActividadAdminController implements Initializable {

    ActividadDTO actividad;
    OperadorDTO operador;

    public void setActividad(ActividadDTO act) {
        this.actividad = act;
        this.operador = operadorRestService.finById(act.getIdoperador());
    }

    @Autowired
    private OperadorRestService operadorRestService;

    @Autowired
    private CalificacionRestService calificacionRestService;

    @FXML
    private AnchorPane act_view_pane;

    @FXML
    private Label label_nombre_actividad;

    @FXML
    private Label descripcion;

    @FXML
    private Label horario;

    @FXML
    private Label admite_reservas;

    @FXML
    private Label operador_actividad;

    @FXML
    private ImageView imageView_actividad;

    @FXML
    private Label puntuacion_label;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ValidarActividad.fxml"));
        act_view_pane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_nombre_actividad.setText(actividad.getTitulo());
        descripcion.setWrapText(true);
        descripcion.setText(actividad.getDescripcion());
        horario.setText(actividad.getApertura() + " - " + actividad.getCierre());
        admite_reservas.setText( actividad.getUtiliza_reserva() ? "Sí" : "No");
        operador_actividad.setText(operador.getEmpresa());
        Float puntos_promedio = calificacionRestService.puntuacionPromedio(actividad.getId());
        if (puntos_promedio == 0) {
            puntuacion_label.setText("Sin calificaciones.");
        } else {
            puntuacion_label.setText(puntos_promedio + " estrellas");
        }

        if (actividad.getImagenactividad() != null) {
            InputStream is = new ByteArrayInputStream(actividad.getImagenactividad());
            imageView_actividad.setImage(new Image(is));
        }
    }

}