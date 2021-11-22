package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.dtos.ReservaDTO;
import com.example.demo3.services.ActividadRestService;
import com.example.demo3.services.ClienteRestService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ValidarReservaController implements Initializable {

    @Autowired
    private ReservaRestService reservaRestService;

    @Autowired
    private ClienteRestService clienteRestService;

    @Autowired
    private ActividadRestService actividadRestService;

    OperadorDTO operador;
    public void setOperador(OperadorDTO operador) {
        this.operador = operador;
    }

    @FXML
    private AnchorPane val_pane;

    @FXML
    private TableView<ReservaDTO> tablaReservas;

    @FXML
    private TableColumn<ReservaDTO, String> userColumn;

    @FXML
    private TableColumn<ReservaDTO, String> actividadColumn;

    @FXML
    private TableColumn<ReservaDTO, Integer> cupoColumn;

    @FXML
    private TableColumn<ReservaDTO, LocalDate> fechaColumn;

    @FXML
    private TableColumn<ReservaDTO, LocalTime> horaColumn;

    @FXML
    private TableColumn<ReservaDTO, String> estadoColumn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeOperador.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    @FXML
    void rechazarReserva(ActionEvent event) {
        ReservaDTO reserva = tablaReservas.getSelectionModel().getSelectedItem();
        reservaRestService.updateValidada(reserva, false);

        List<ReservaDTO> q = new ArrayList<>();
        List<Integer> actividadesDelOperador = actividadRestService.findIdByOperador(operador.getId());

        for (int i = 0; i < actividadesDelOperador.size(); i++) {
            List<ReservaDTO> resDeAct = reservaRestService.findByActividad(actividadesDelOperador.get(i));
            for (int j = 0; j < resDeAct.size(); j++) {
                q.add(resDeAct.get(j));
            }
        }

        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaReservas.setItems(lista);
        userColumn.setCellValueFactory(cellData -> {
            String username = clienteRestService.findUsernameById(cellData.getValue().getIdcliente());
            return new ReadOnlyStringWrapper(username);
        });
        actividadColumn.setCellValueFactory(cellData -> {
            String titulo = actividadRestService.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(titulo);
        });
        cupoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        estadoColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getValidada() != null) {
                boolean estado = cellData.getValue().getValidada();
                String estadoAsString;
                if (estado) {
                    estadoAsString = "Confirmada";
                } else {
                    estadoAsString = "Rechazada";
                }
                return new ReadOnlyStringWrapper(estadoAsString);
            }
            else {
                return new ReadOnlyStringWrapper("Pendiente");
            }
        });

    }

    @FXML
    void validarReserva(ActionEvent event) {
        ReservaDTO reserva = tablaReservas.getSelectionModel().getSelectedItem();
        reservaRestService.updateValidada(reserva, true);

        List<ReservaDTO> q = new ArrayList<>();
        List<Integer> actividadesDelOperador = actividadRestService.findIdByOperador(operador.getId());

        for (int i = 0; i < actividadesDelOperador.size(); i++) {
            List<ReservaDTO> resDeAct = reservaRestService.findByActividad(actividadesDelOperador.get(i));
            for (int j = 0; j < resDeAct.size(); j++) {
                q.add(resDeAct.get(j));
            }
        }

        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaReservas.setItems(lista);
        userColumn.setCellValueFactory(cellData -> {
            String username = clienteRestService.findUsernameById(cellData.getValue().getIdcliente());
            return new ReadOnlyStringWrapper(username);
        });
        actividadColumn.setCellValueFactory(cellData -> {
            String titulo = actividadRestService.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(titulo);
        });
        cupoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        estadoColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getValidada() != null) {
                boolean estado = cellData.getValue().getValidada();
                String estadoAsString;
                if (estado) {
                    estadoAsString = "Confirmada";
                } else {
                    estadoAsString = "Rechazada";
                }
                return new ReadOnlyStringWrapper(estadoAsString);
            }
            else {
                return new ReadOnlyStringWrapper("Pendiente");
            }
        });
    }

    ObservableList<ReservaDTO> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ReservaDTO> q = new ArrayList<>();
        List<Integer> actividadesDelOperador = actividadRestService.findIdByOperador(operador.getId());

        for (int i = 0; i < actividadesDelOperador.size(); i++) {
            List<ReservaDTO> resDeAct = reservaRestService.findByActividad(actividadesDelOperador.get(i));
            for (int j = 0; j < resDeAct.size(); j++) {
                q.add(resDeAct.get(j));
            }
        }

        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaReservas.setItems(lista);
        userColumn.setCellValueFactory(cellData -> {
            String username = clienteRestService.findUsernameById(cellData.getValue().getIdcliente());
            return new ReadOnlyStringWrapper(username);
        });
        actividadColumn.setCellValueFactory(cellData -> {
            String titulo = actividadRestService.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(titulo);
        });
        cupoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        estadoColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getValidada() != null) {
                boolean estado = cellData.getValue().getValidada();
                String estadoAsString;
                if (estado) {
                    estadoAsString = "Confirmada";
                } else {
                    estadoAsString = "Rechazada";
                }
                return new ReadOnlyStringWrapper(estadoAsString);
            }
            else {
                return new ReadOnlyStringWrapper("Pendiente");
            }
        });
    }
}
