package ChatMajorView;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatViewController implements Initializable {
    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    @FXML
    private void handleSendButton() throws IOException {
//        System.out.println(inputField);
        String message = inputField.getText();
        //在这里添加发送消息的代码，例如将消息发送到客户端

//        chatArea.appendText(message + "\n");
        inputField.clear();
        Client.sender.println(Client.username+":"+message);
    }

    public void addMsg(String content){
        chatArea.appendText(content+"\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
