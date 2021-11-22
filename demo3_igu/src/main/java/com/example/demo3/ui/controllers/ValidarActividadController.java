package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.services.ActividadRestService;
import com.example.demo3.services.OperadorRestService;
import com.example.demo3.ui.MainController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ValidarActividadController implements Initializable {

    @Autowired
    private ActividadRestService actividadRestService;

    @Autowired
    ActividadAdminController actividadAdminController;

    @Autowired
    private OperadorRestService operadorRestService;

    @FXML
    private AnchorPane val_pane;

    @FXML
    private TableView<ActividadDTO> tablaActividades;

    @FXML
    private TableColumn<ActividadDTO, String> nameColumn;

    @FXML
    private TableColumn<ActividadDTO, String> operadorColumn;

    @FXML
    void ampliarActividad(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        ActividadDTO act = tablaActividades.getSelectionModel().getSelectedItem();
        actividadAdminController.setActividad(act);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadAdmin.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    ObservableList<ActividadDTO> lista;

    @FXML
    void rechazarActividad(ActionEvent event) {
        ActividadDTO act = tablaActividades.getSelectionModel().getSelectedItem();
        actividadRestService.rechazarActividad(act);

        List<ActividadDTO> q = actividadRestService.getActividadesNoValidadas();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRestService.finById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }

    @FXML
    void validarActividad(ActionEvent event) {
        ActividadDTO act = tablaActividades.getSelectionModel().getSelectedItem();
        actividadRestService.validarActividad(act);

        List<ActividadDTO> q = actividadRestService.getActividadesNoValidadas();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRestService.finById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ActividadDTO> q = actividadRestService.getActividadesNoValidadas();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRestService.finById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }


}
