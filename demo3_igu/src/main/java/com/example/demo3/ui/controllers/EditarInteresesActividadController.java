package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.dtos.InteresDTO;
import com.example.demo3.dtos.TagsDTO;
import com.example.demo3.dtos.TagsIdDTO;
import com.example.demo3.services.InteresRestService;
import com.example.demo3.services.TagsRestService;
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
public class EditarInteresesActividadController implements Initializable {

    @Autowired
    private InteresRestService interesRestService;

    @Autowired
    private TagsRestService tagsRestService;


    ActividadDTO actividad;
    void setActividad(ActividadDTO actividad) {
        this.actividad = actividad;
    }

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private ChoiceBox<String> tagsChoiceBox;

    @FXML
    private TableView<InteresDTO> tablaTags;

    @FXML
    private TextField newField;

    @FXML
    private TableColumn<InteresDTO, String> interesesColumn;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void agregarPreferencia(ActionEvent event) {

        if(tagsChoiceBox.getValue() == null || tagsChoiceBox.getValue().equals("")){
            showAlert("Faltan datos", "Por favor ingrese toda la informacion requerida");
        }
        else{
            try {
                String tempTags = tagsChoiceBox.getValue();
                Integer actividad = this.actividad.getId();

                InteresDTO interes = interesRestService.findByNombre(tempTags);
                Integer idtag = interes.getIdinteres();

                TagsIdDTO tagsIdDTO = new TagsIdDTO();
                tagsIdDTO.setIdtags(idtag);
                tagsIdDTO.setActividad(actividad);

                TagsDTO tagsDTO = new TagsDTO();
                tagsDTO.setId(tagsIdDTO);

                ResponseEntity response = tagsRestService.addTag(tagsDTO);

                List<TagsDTO> todos_tags = tagsRestService.getAll();
                List<Integer> tags_actividad = new ArrayList<>();

                if(todos_tags.size()>0){
                    for(TagsDTO t: todos_tags){
                        Integer act = t.getId().getActividad();
                        if(act == actividad){
                            assert false;
                            tags_actividad.add(t.getId().getIdtags());
                        }
                    }
                }
                List<InteresDTO> intereses = new ArrayList<>();
                if(tags_actividad.size() > 0){
                    for(Integer i: tags_actividad){
                        intereses.add(interesRestService.findById(i));
                    }
                }
                ObservableList<InteresDTO> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaTags.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

            }catch (HttpClientErrorException error){
                if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                    showAlert(
                            "Tag ya registrado !",
                            "El Tag indicado ya ha sido registrado en la actividad.");
                } else {
                    showAlert(
                            "Error Generico",
                            "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                }
            }
        }
    }

    @FXML
    void crearInteres(ActionEvent event){

        if(newField.getText() == null || newField.getText().equals("")){
            showAlert("Tag invalido","Por favor registre un interes valido");
        }

        else{
            try {
                String nuevo = newField.getText();

                InteresDTO interesDTO = new InteresDTO();
                interesDTO.setNombre(nuevo);

                ResponseEntity response = interesRestService.addInteres(interesDTO);

                List<InteresDTO> q = (List<InteresDTO>) interesRestService.getAll();
                List<String> inters = new ArrayList<>();
                InteresDTO interes = interesRestService.findByNombre(nuevo);
                inters.add(interes.getNombre());
                tagsChoiceBox.getItems().add(interes.getNombre());

//                for (int i = 0; i < q.size(); i++) {
//                    if (q.get(i) != null) {
//                        inters.add(q.get(i).toString());
//                    }
//                }
//                tagsChoiceBox.getItems().addAll(inters);

            }catch (HttpClientErrorException error) {
                if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    showAlert("Informacion invalida", "Se ecnontro un error en los datos");
                } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                    showAlert(
                            "Interes ya registrado !",
                            "El Interes indicado ya ha sido registrado en la actividad.");
                } else {
                    showAlert(
                            "Error Generico",
                            "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                }
            }
        }

        //String nuevo = newField.getText();
        // crear objeto
        //inters.add(objeto)
//        List<Interes> q = (List<Interes>) interesMgr.getAll();
//        List<String> inters = new ArrayList<>();
//
//        for (int i = 0; i < q.size(); i++) {
//            if (q.get(i) != null) {
//                inters.add(q.get(i).toString());
//            }
//        }
//        tagsChoiceBox.getItems().addAll(inters);
    }

    @FXML
    void eliminarInteres(ActionEvent event) {

        if(tagsChoiceBox.getValue() == null || tagsChoiceBox.getValue().equals("")){
            showAlert("Faltan datos", "Por favor ingrese toda la informacion requerida");
        }

        else{

            try{
                String tempTags = tagsChoiceBox.getValue();
                Integer actividad = this.actividad.getId();

                InteresDTO interes = interesRestService.findByNombre(tempTags);
                Integer idtags = interes.getIdinteres();

                tagsRestService.deleteTag(actividad,idtags);

                List<TagsDTO> todos_tags = tagsRestService.getAll();
                List<Integer> tags_actividad = new ArrayList<>();
                if(todos_tags.size() > 0){
                    for(TagsDTO t: todos_tags){
                        Integer act = t.getId().getActividad();
                        if(act == actividad){
                            assert false;
                            tags_actividad.add(t.getId().getIdtags());
                        }
                    }
                }

                List<InteresDTO> intereses = new ArrayList<>();
                if(tags_actividad.size()>0){
                    for(Integer i : tags_actividad){
                        intereses.add(interesRestService.findById(i));
                    }
                }
                ObservableList<InteresDTO> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaTags.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

            }catch (HttpClientErrorException error){
                if(error.getStatusCode()== HttpStatus.BAD_REQUEST){
                    showAlert("Tag no existe","elegir un tag valido");
                }
            }
        }

    }

    @FXML
    void finalizar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
        pane_intereses.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<InteresDTO> q = (List<InteresDTO>) interesRestService.getAll();
        List<String> inters = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters.add(q.get(i).toString());
            }
        }


        tagsChoiceBox.getItems().addAll(inters);
        List<TagsDTO> todos_tags = tagsRestService.getAll();
        List<Integer> tags_actividad = new ArrayList<>();
        if(todos_tags.size() > 0){
            for(TagsDTO t: todos_tags){
                Integer act = t.getId().getActividad();
                if(act == actividad.getId()){
                    assert false;
                    tags_actividad.add(t.getId().getIdtags());
                }
            }
        }

        List<InteresDTO> intereses = new ArrayList<>();
        if(tags_actividad.size()>0){
            for(Integer i : tags_actividad){
                intereses.add(interesRestService.findById(i));
            }
        }
        ObservableList<InteresDTO> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaTags.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));





    }

}