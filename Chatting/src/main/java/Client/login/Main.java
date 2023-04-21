package Client.login;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import Client.Client;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../../test.fxml"));
        AnchorPane root = loader.load();
        Client.controller_ = loader.getController();
        System.out.println("init window");
//        System.out.println(controller_);
//      controller.setPrimaryStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login and Register System");
        primaryStage.show();

    }

    public void run(String[] args) {
        launch(args);
    }


}

