package Client.login;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class testController implements Initializable {


//    public static class testController {
    
        @FXML
        private TextField usernameField;
    
        @FXML
        private PasswordField passwordField;



//        public testController(TextField usernameField, PasswordField passwordField) {
//            this.usernameField = usernameField;
//            this.passwordField = passwordField;
//        }
    public testController(){

    }
    
        public void setPrimaryStage(Stage primaryStage) {
        }
    


        @FXML
        public void handleLoginButton() {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Client.username = username;

            Client.sender.println("[+-]login-==-" + username + "-==-" + password);
        }
    
        @FXML
        public void handleRegisterButton() {
            String username = usernameField.getText();
            String password = passwordField.getText();
            Client.sender.println("[+-]register-==-" + username + "-==-" + password);
//            try {
//                Connection();
//                // 查询用户名是否已存在
//                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usermessage WHERE username=?");
//                preparedStatement.setString(1, username);
//                ResultSet resultSet = preparedStatement.executeQuery();
//                if (resultSet.next()) {
//                    // 用户名已存在
//                    //System.out.println("注册失败,用户名已存在！");
//                    showAlert(Alert.AlertType.ERROR, "注册失败", "用户名已存在！");
//                } else {
//                    // 插入新用户信息
//                    PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO usermessage (username, password) VALUES (?, ?)");
//                    insertStatement.setString(1, username);
//                    insertStatement.setString(2, password);
//                    insertStatement.executeUpdate();
//                    //System.out.println("恭喜您，注册成功！");
//                    showAlert(Alert.AlertType.INFORMATION, "注册成功", "恭喜您，注册成功！");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }

    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

