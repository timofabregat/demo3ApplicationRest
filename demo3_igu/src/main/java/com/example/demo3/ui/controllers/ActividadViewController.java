package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.dtos.ClienteDTO;
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
public class ActividadViewController implements Initializable {

    @Autowired
    private OperadorRestService operadorRestService;

    @Autowired
    private ReservarController reservarController;

    @Autowired
    private ComentariosController comentariosController;

    @Autowired
    private CalificacionRestService calificacionRestService;

    ActividadDTO actividad;
    OperadorDTO operador;
    ClienteDTO cliente;

    public void setData(ActividadDTO act, ClienteDTO cliente) {
        this.actividad = act;
        this.operador = operadorRestService.finById(act.getIdoperador());
        this.cliente = cliente;
    }

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
    void realizarComentario(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        comentariosController.setData(actividad, cliente);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Comentarios.fxml"));
        act_view_pane.getChildren().setAll(pane);
    }

    @FXML
    void reservar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        reservarController.setData(actividad, cliente);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Reservar.fxml"));
        act_view_pane.getChildren().setAll(pane);
    }

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeCliente.fxml"));
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
        puntuacion_label.setText(calificacionRestService.puntuacionPromedio(actividad.getId()) + " estrellas");

        if (actividad.getImagenactividad() != null) {
            InputStream is = new ByteArrayInputStream(actividad.getImagenactividad());
            imageView_actividad.setImage(new Image(is));
        }


        //BufferedImage imagen_act = null;

        /*
        try {
            imagen_act = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(imagen_act, null);
        imageView_actividad.setImage(image);
        */


    }

}