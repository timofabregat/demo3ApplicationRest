package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.services.ClienteRestService;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BloqueoClienteController implements Initializable {

    @Autowired
    private ClienteRestService clienteRestService;

    @FXML
    private AnchorPane bloq_pane;

    @FXML
    private TableView<ClienteDTO> tablaClientes;

    @FXML
    private TableColumn<ClienteDTO, String> emailColumn;

    @FXML
    private TableColumn<ClienteDTO, Long> docColumn;

    @FXML
    private TableColumn<ClienteDTO, String> estadoColumn;

    @FXML
    private TextField field;

    @FXML
    void busqueda(KeyEvent event) {
        List<ClienteDTO> q = clienteRestService.findByContainingEmail(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);

        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });
    }

    @FXML
    void bloquearCliente(ActionEvent event) {
        ClienteDTO cli = tablaClientes.getSelectionModel().getSelectedItem();
        clienteRestService.bloquearCliente(cli);

        List<ClienteDTO> q = clienteRestService.findByContainingEmail(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });
    }

    @FXML
    void desbloquearCliente(ActionEvent event) {
        ClienteDTO cli = tablaClientes.getSelectionModel().getSelectedItem();
        clienteRestService.desbloquearCliente(cli);

        List<ClienteDTO> q = clienteRestService.findByContainingEmail(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        bloq_pane.getChildren().setAll(pane);
    }

    ObservableList<ClienteDTO> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ClienteDTO> q = clienteRestService.getAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });
    }

}
