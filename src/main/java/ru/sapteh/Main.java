package ru.sapteh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        Parent parent=FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setTitle("School");
        stage.setScene(new Scene(parent));
        stage.show();

    }
}
