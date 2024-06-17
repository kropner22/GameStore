package game_store;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.query.Query;

import java.io.IOException;

import static game_store.DatabaseConnection.databaseSession;

public class HomeScreen {

    public Stage createHomeScreen(Button home_screen_switch) throws IOException {
        Stage stage = (Stage) home_screen_switch.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HomeScreen.class.getResource("home_screen.fxml"));
        Parent root = loader.load();
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        HomeScreenController homeScreenController = loader.getController();
        homeScreenController.getGameBarChartData();
        homeScreenController.getConsoleBarChartData();
        //homeScreenController.getLowestStockGame();
        return stage;
    }
}
