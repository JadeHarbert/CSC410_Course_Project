/**
 * EightCAPApplication.java --  Driver class to run a GUI that uses JOOQ to connect to a PostgreSQL database
 * CSC410
 * April 18th, 2023
 */
package edu.alma.teamleft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EightCAPApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EightCAPApplication.class.getResource("EightCAP_Scene_Builder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("EightCAP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}