package com.example.projet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainApp extends Application {
    BorderPane rootLayout;
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Address App");
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResource("icon.png").toString()));
        rootmethod();
    }
    public void rootmethod()throws IOException {
        FXMLLoader loader1=new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
        rootLayout=(BorderPane) loader1.load();
        FXMLLoader loader2=new FXMLLoader(MainApp.class.getResource("PersonOverview.fxml"));
        AnchorPane PersonOverviewRoot=loader2.load();
        rootLayout.setCenter(PersonOverviewRoot);
        PersonOverviewController controller=loader2.getController();
        controller.setMainApp(this);
        Scene scene=new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch();
    }
    private ObservableList<Person> personData= FXCollections.observableArrayList();
    public ObservableList<Person> getPersonData(){
        return personData;
    }
    public MainApp(){
        personData.add(new Person("Elazhari","Khadija"));
        personData.add(new Person("Tadarb","Oumaima"));
        personData.add(new Person("Elasfar","Nouhaila"));
        personData.add(new Person("Mohtaram","Imane"));
        personData.add(new Person("Youssfi","Amina"));
        personData.add(new Person("Fennane","Ziad"));
        personData.add(new Person("Fennane","Hafssa"));
        personData.add(new Person("Elyamani","Ilyass"));
    }

    public Window getPrimaryStage() {
        return null;
    }
    public boolean showPersonEditDialog(Person person){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page=(AnchorPane) loader.load();

            Stage dialogStage=new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.getIcons().add(new Image(MainApp.class.getResource("person.png").toString()));

            PersonEditDialogController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
