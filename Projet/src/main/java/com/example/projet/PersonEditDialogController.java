package com.example.projet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField professionField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize(){

    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }

    public void setPerson(Person person){
        this.person=person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        addressField.setText(person.getAddress());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        professionField.setText(person.getProfession());
        birthdayField.setText(DatUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk(){
        if (isInputValid()){
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setAddress(addressField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setProfession(professionField.getText());
            person.setBirthday(DatUtil.parse(birthdayField.getText()));

            okClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    private void hadeleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMessage="";
        if (firstNameField.getText()==null || firstNameField.getText().length()==0){
            errorMessage +="No valid first name!\n";
        }
        if (lastNameField.getText()==null || lastNameField.getText().length()==0){
            errorMessage +="No valid last name!\n";
        }
        if (professionField.getText()==null || professionField.getText().length()==0){
            errorMessage +="No valid profession!\n";
        }
        if (addressField.getText()==null || addressField.getText().length()==0){
            errorMessage +="No valid adrress!\n";
        }
        if (postalCodeField.getText()==null || postalCodeField.getText().length()==0){
            errorMessage +="No valid postal code!\n";
        }else {
            try {
                Integer.parseInt(postalCodeField.getText());
            }catch (NumberFormatException e){
                errorMessage +="No valid postal code (must be an integer)!\n";
            }
        }
        if (birthdayField.getText()==null || birthdayField.getText().length()==0){
            errorMessage +="No valid birthday!\n";
        }else {
            if (!DatUtil.validDate(birthdayField.getText())){
                errorMessage +="No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if (errorMessage.length()==0){
            return true;
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
