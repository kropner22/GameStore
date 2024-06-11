package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StockScreenController {
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    private Stage stage;
    @FXML
    private GridPane gridPane;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML protected void handleHomeTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml")); // Load the FXML

        Stage stage = (Stage) home_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 300);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); // Get the controller
    }

    @FXML protected void handleStockCountsTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stock_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) stock_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("stock_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 300);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); // Get the controller for the home screen
    }


    @FXML protected void handleReportTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("report_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) report_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("report_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 300);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController ReportScreenController = loader.getController(); // Get the controller for the home screen
    }


}

