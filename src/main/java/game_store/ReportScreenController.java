package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReportScreenController {
    @FXML private Button stock_screen_switch;
    @FXML private Button stock_location_screen_switch;
    @FXML private Button genre_screen_switch;
    @FXML private Button report_screen_switch;
    private Stage stage;
    @FXML
    private GridPane gridPane;
    public void setStage(Stage stage) {
        this.stage = stage;
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

    @FXML protected void handleStockLocationCountsTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("location_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) stock_location_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("location_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 300);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController LocationScreenController = loader.getController(); // Get the controller for the home screen
    }

    @FXML protected void handleGenreTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("genre_popularity_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) genre_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("genre_popularity_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 300);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController GenrePopularityScreenController = loader.getController(); // Get the controller for the home screen
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