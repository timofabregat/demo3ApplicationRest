package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.dtos.GustosDTO;
import com.example.demo3.dtos.GustosIdDTO;
import com.example.demo3.dtos.InteresDTO;
import com.example.demo3.services.GustosRestService;
import com.example.demo3.services.InteresRestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditarInteresesClienteController implements Initializable {

    @Autowired
    private GustosRestService gustosRestService;

    @Autowired
    private InteresRestService interesRestService;

    @Autowired
    private HomeClienteController homeClienteController;

    ClienteDTO cliente;
    void setCliente(ClienteDTO cliente){this.cliente = cliente;}

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private ChoiceBox<String> interesChoiceBox;

    @FXML
    private Button agregarButton;

    @FXML
    private TableView<InteresDTO> tablaIntereses;

    @FXML
    private TableColumn<InteresDTO, String> interesesColumn;

    @FXML
    private Button finalizarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    void agregarPreferencia(ActionEvent event){
        String tempInteres = interesChoiceBox.getValue();
        String usuario = this.cliente.getMail();

        InteresDTO interes = interesRestService.findByNombre(tempInteres);
        Integer idgusto = interes.getIdinteres();

        GustosIdDTO gustosIdDTO = new GustosIdDTO();
        gustosIdDTO.setUsuario(usuario);
        gustosIdDTO.setIdgustos(idgusto);

        GustosDTO gustosDTO = new GustosDTO();
        gustosDTO.setId(gustosIdDTO);


        try{
            ResponseEntity response = gustosRestService.addGusto(gustosDTO);

        }catch (HttpClientErrorException error) {
            if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                showAlert("Informacion invalida", "Se ecnontro un error en los datos");
            } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                showAlert(
                        "Gusto ya registrado !",
                        "El documento indicado ya ha sido registrado en el sistema.");
            } else {
                showAlert(
                        "Error Generico",
                        "Se recibio el siguiente codigo de error: " + error.getStatusCode());
            }
        }

        List<GustosDTO> todos_gustos = gustosRestService.getAll();
        List<Integer> gustos_cliente = new ArrayList<>();
        if (todos_gustos.size() > 0) {
            for (GustosDTO g : todos_gustos) {
                String usu = g.getId().getUsuario();
                if (usu.equals(cliente.getMail())) {
                    assert false;
                    gustos_cliente.add(g.getId().getIdgustos());
                }
            }
        }
        List<InteresDTO> intereses = new ArrayList<>();
        // hasta este punto tengo los id de los intereses del cliente (en gustos_cliente)
        if (gustos_cliente.size() > 0) {
            for (Integer i : gustos_cliente) {
                intereses.add(interesRestService.findById(i));
            }
        }
        ObservableList<InteresDTO> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    void eliminarInteres(ActionEvent event){
        String tempInteres = interesChoiceBox.getValue();
        String usuario = this.cliente.getMail();

        InteresDTO interes = interesRestService.findByNombre(tempInteres);
        Integer idgusto = interes.getIdinteres();

        gustosRestService.deleteGusto(usuario, idgusto);

        List<GustosDTO> todos_gustos = gustosRestService.getAll();
        List<Integer> gustos_cliente = new ArrayList<>();
        if (todos_gustos.size() > 0) {
            for (GustosDTO g : todos_gustos) {
                String usu = g.getId().getUsuario();
                if (usu.equals(cliente.getMail())) {
                    assert false;
                    gustos_cliente.add(g.getId().getIdgustos());
                }
            }
        }
        List<InteresDTO> intereses = new ArrayList<>();
        // hasta este punto tengo los id de los intereses del cliente (en gustos_cliente)
        if (gustos_cliente.size() > 0) {
            for (Integer i : gustos_cliente) {
                intereses.add(interesRestService.findById(i));
            }
        }
        ObservableList<InteresDTO> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    void updateIntereses(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        homeClienteController.setCliente(cliente);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
        pane_intereses.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<InteresDTO> q = (List<InteresDTO>) interesRestService.getAll();
        String[] inters = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters[i] = q.get(i).toString();
            }
        }
        interesChoiceBox.getItems().addAll(inters);

        List<GustosDTO> todos_gustos = gustosRestService.getAll();
        List<Integer> gustos_cliente = new ArrayList<>();
        if (todos_gustos.size() > 0) {
            for (GustosDTO g : todos_gustos) {
                String usu = g.getId().getUsuario();
                if (usu.equals(cliente.getMail())) {
                    assert false;
                    gustos_cliente.add(g.getId().getIdgustos());
                }
            }
        }
        List<InteresDTO> intereses = new ArrayList<>();
        // hasta este punto tengo los id de los intereses del cliente (en gustos_cliente)
        if (gustos_cliente.size() > 0) {
            for (Integer i : gustos_cliente) {
                intereses.add(interesRestService.findById(i));
            }
        }
        ObservableList<InteresDTO> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
