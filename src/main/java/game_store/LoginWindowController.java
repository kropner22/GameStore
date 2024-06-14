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
        String username = userNameEntry.getText(); // Get text from username field
        String password = passwordField.getText(); // Get text from password field

        if (username.isEmpty() || password.isEmpty()) { // Check if username or password is empty
            incorrect_input_message(Alert.AlertType.ERROR, "Error", "Please enter both username and password.");
            return;
        }

        try {
            List<?> list = DatabaseConnection.getEmployee(username, password); //gets the Employee from database
            if (!list.isEmpty()) {
                Employee emp = (Employee)list.get(0);
                if (emp.getusername().equals(username)) {
                    if (emp.getPassword().equals(password)) {
                        incorrect_input_message(Alert.AlertType.INFORMATION, "Success", "Login successful!");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml")); // Load the FXML for the home screen

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        Parent root = loader.load(); // Corrected loader usage
                        Scene changeScene = new Scene(root, 600, 400);
                        stage.setScene(changeScene);
                        stage.show();

                        HomeScreenController homeScreenController = loader.getController(); // Get the controller for the home screen
                        homeScreenController.receiveInformation(username);
                    } else {
                        incorrect_input_message(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
                    }
                }
            } else {
                incorrect_input_message(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            incorrect_input_message(Alert.AlertType.ERROR, "Error", "Database connection error.");
        }
    }

    private void incorrect_input_message(Alert.AlertType type, String title, String message) { // Makes the pop up for the alert messages
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}