package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.dtos.ReservaDTO;
import com.example.demo3.services.ActividadRestService;
import com.example.demo3.services.ReservaRestService;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ItinerarioClienteController implements Initializable {

    ClienteDTO cliente;

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Autowired
    private ReservaRestService reservaRestService;


    @Autowired
    private ActividadRestService actividadRestService;

    @FXML
    private AnchorPane backpane;

    @FXML
    private TableView<ReservaDTO> tabla_reservas;

    @FXML
    private TableColumn<ReservaDTO, String> actividadcolumn;

    @FXML
    private TableColumn<ReservaDTO, LocalDate> diacolumn;

    @FXML
    private TableColumn<ReservaDTO, LocalTime> horacolumn;

    @FXML
    private TableColumn<ReservaDTO, Integer> cantidadcolumn;

    @FXML
    private TableColumn<ReservaDTO, String> estadocolumn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeCliente.fxml"));
        backpane.getChildren().setAll(pane);
    }

    ObservableList<ReservaDTO> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ReservaDTO> q = reservaRestService.findByCliente(cliente.getId());
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tabla_reservas.setItems(lista);

        actividadcolumn.setCellValueFactory(cellData -> {
            String tit = actividadRestService.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(tit);
        });
        diacolumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horacolumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        cantidadcolumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        estadocolumn.setCellValueFactory(cellData -> {
            Boolean estado = cellData.getValue().getValidada();
            String estadoAsString;
            if (estado != null) {
                if (estado) {
                    estadoAsString = "Confirmada";
                } else {
                    estadoAsString = "Rechazada";
                }
            }
            else {
                estadoAsString = "Pendiente";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });

    }

}