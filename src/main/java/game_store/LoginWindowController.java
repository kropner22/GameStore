package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.*;

import java.io.IOException;

public class LoginWindowController {
    @FXML
    private TextField userNameEntry;

    @FXML
    private TextField passwordField;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button loginButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {
        String username = userNameEntry.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            incorrect_input_message(Alert.AlertType.ERROR, "Error", "Please enter both username and password.");
            return;
        }

        try {
            List<?> list = DatabaseConnection.getEmployee(username, password);
            if (!list.isEmpty()) {
                Employee employee = (Employee)list.get(0);
                if (employee.getusername().equals(username)) {
                    if (employee.getPassword().equals(password)) {
                        incorrect_input_message(Alert.AlertType.INFORMATION, "Success", "Login successful!");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml"));

                        HomeScreen homeScreen = new HomeScreen();
                        homeScreen.createHomeScreen(loginButton).show();

                    } else {
                        incorrect_input_message(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
                    }
                }
            } else {
                incorrect_input_message(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
            }
        } catch (IOException error) {
            error.printStackTrace();
            incorrect_input_message(Alert.AlertType.ERROR, "Error", "Database connection error.");
        }
    }

    private void incorrect_input_message(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}