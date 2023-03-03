import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class chat extends Application {

    private TextField usernameField;
    private PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Login Registration");

        // Create the login form grid pane
        GridPane loginForm = new GridPane();
        loginForm.setPadding(new Insets(10, 10, 10, 10));
        loginForm.setVgap(5);
        loginForm.setHgap(5);

        // Add the login form controls
        Label usernameLabel = new Label("Username:");
        loginForm.add(usernameLabel, 0, 0);

        usernameField = new TextField();
        loginForm.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        loginForm.add(passwordLabel, 0, 1);

        passwordField = new PasswordField();
        loginForm.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        loginForm.add(loginButton, 0, 2);

        Button registerButton = new Button("Register");
        loginForm.add(registerButton, 1, 2);

        // Add event handler for login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check if the username and password are valid
            if (username.equals("admin") && password.equals("password")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setContentText("You have successfully logged in.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        // Add event handler for register button
        registerButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setContentText("You have successfully registered.");
            alert.showAndWait();
        });

        Scene scene = new Scene(loginForm, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
