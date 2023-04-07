package com.example.project3;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class which sets the stage
 * @author Apurva Desai, Yehun Kim
 * */
public class TuitionManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerMain.class.getResource("TuitionManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600); //size of scene
        stage.setTitle("Project3: Tuition Manager!"); //title
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }






}