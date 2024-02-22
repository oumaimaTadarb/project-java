package com.example.projet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person,String> firstNameColumn;
    @FXML
    private TableColumn<Person,String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label ProfessionLabel;
    @FXML
    private Label CodePostalLabel;
    @FXML
    private Label AddressPostalLabel;
    @FXML
    private Label birthayLabel;

    private MainApp mainApp;

    public PersonOverviewController(){
    }

    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person){
        if(person != null){
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            ProfessionLabel.setText(person.getProfession());
            CodePostalLabel.setText(Integer.toString(person.getPostalCode()));
            AddressPostalLabel.setText(person.getAddress());

            birthayLabel.setText(DatUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText(" ");
            lastNameLabel.setText(" ");
            ProfessionLabel.setText(" ");
            CodePostalLabel.setText(" ");
            AddressPostalLabel.setText(" ");
        }
    }

    @FXML
    private void handleDeletePerson(){
        int selectedIndex=personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0) {
            personTable.getItems().remove(selectedIndex);
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson(){
        Person tmpPerson=new Person();
        boolean okClicked=mainApp.showPersonEditDialog(tmpPerson);
        if (okClicked){
            mainApp.getPersonData().add(tmpPerson);
        }
    }

    @FXML
    private void handleEditPerson(){
        Person selectedPerson=personTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null){
            boolean okClicked =mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked){
                showPersonDetails(selectedPerson);
            }
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table");
            alert.showAndWait();
        }
    }

}
