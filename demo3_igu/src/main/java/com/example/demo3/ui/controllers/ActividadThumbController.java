package com.example.demo3.ui.controllers;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.services.CalificacionRestService;
import com.example.demo3.services.OperadorRestService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActividadThumbController {

    AnchorPane home_pane;
    void setPane(AnchorPane pane) {
        this.home_pane = pane;
    }

    @Autowired
    private OperadorRestService operadorRestService;

    @Autowired
    private CalificacionRestService calificacionRestService;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label puntuacionLabel;

    @FXML
    private Label operadorLabel;

    @FXML
    private Label descripcionLabel;

    public List<ActividadDTO> actividades = new ArrayList<>();

    public void setData(ActividadDTO actividad) {
        actividades.add(actividad);
        if (actividad.getImagenactividad() != null) {
            InputStream is = new ByteArrayInputStream(actividad.getImagenactividad());
            imagen.setImage(new Image(is));
        }
        nombreLabel.setText(actividad.getTitulo());
        puntuacionLabel.setText(calificacionRestService.puntuacionPromedio(actividad.getId())+ " estrellas");
        operadorLabel.setText(operadorRestService.finById(actividad.getIdoperador()).getEmpresa());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setText(actividad.getDescripcion());
    }

}