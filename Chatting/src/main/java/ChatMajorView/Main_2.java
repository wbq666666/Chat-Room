package ChatMajorView;

import Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_2 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        //加载FXML文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatView.fxml"));
        AnchorPane root = loader.load();

        //设置Scene和Stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Room");
        primaryStage.show();
    }
//
    public static void main(String[] args) {
        launch(args);
    }

    public void newStage() throws Exception {
        //加载FXML文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../ChatView.fxml"));
        AnchorPane root = loader.load();
        Client.controller_2 = loader.getController();

        //设置Scene和Stage
        Scene scene = new Scene(root, 600, 400);
        Stage secondaryStage=new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Chat Room");
        secondaryStage.show();

        Client.sender.println(Client.username + ": has joined chat-room.");
    }
}
