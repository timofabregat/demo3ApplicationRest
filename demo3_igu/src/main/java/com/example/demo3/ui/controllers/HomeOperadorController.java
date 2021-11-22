package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.services.ActividadRestService;
import com.example.demo3.ui.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HomeOperadorController implements Initializable {

    OperadorDTO operador;
    void setOperador(OperadorDTO operador) {
        this.operador = operador;
    }

    @Autowired
    private EditarPerfilOperadorController editarPerfilOperadorController;

    @Autowired
    private ActividadRestService actividadRestService;

    @Autowired
    private EditActividadController editActividadController;

    @Autowired
    private AddActividadController addActividadController;

    @Autowired
    private ValidarReservaController validarReservaController;

    @FXML
    private AnchorPane home_pane;

    @FXML
    private TableView<ActividadDTO> tabla_actividades;

    @FXML
    private TableColumn<ActividadDTO, String> actvidadColumn;

    @FXML
    private TableColumn<ActividadDTO, String> descripcionColumn;

    @FXML
    private Label username_label;

    @FXML
    private TextField field;

    @FXML
    void agregarActividad(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        addActividadController.setOperador(operador);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("AddActividad.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void ampliar_actividad(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        ActividadDTO act = tabla_actividades.getSelectionModel().getSelectedItem();
        editActividadController.setActividad(act);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("EditActividad.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void busqueda(KeyEvent event) {
        List<ActividadDTO> q = actividadRestService.findByTituloContaining(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);

        tabla_actividades.setItems(lista);
        actvidadColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    @FXML
    void editarPerfil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        editarPerfilOperadorController.setOperador(operador);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("EditarPerfilOperador.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Login.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void verReservas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        validarReservaController.setOperador(operador);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ValidarReserva.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    ObservableList<ActividadDTO> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_label.setText(operador.getEmpresa());
        List<ActividadDTO> q = actividadRestService.findByOperador(operador.getId());
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tabla_actividades.setItems(lista);
        actvidadColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

}