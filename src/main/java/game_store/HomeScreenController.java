//imports for Home Screen
package game_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

import static game_store.MainApp.*;

public class HomeScreenController {
    //calling FXML attributes for home screen
    public Text lowestStockConsole;
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    @FXML private BarChart<String, Number> gameBarChart;
    @FXML private BarChart<String, Number> consoleBarChart;

    //handling menu buttons for home screen
    @FXML protected void handleHomeSwitch() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml")); 

        Stage stage = (Stage) home_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home_screen.fxml")));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        loader.getController();
    }

    @FXML protected void handleStockCountsSwitch() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stock_screen.fxml")); 

        Stage stage = (Stage) stock_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stock_screen.fxml")));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        loader.getController();
    }

    @FXML protected void handleReportSwitch() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("report_screen.fxml"));

        Stage stage = (Stage) report_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("report_screen.fxml")));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        loader.getController();
    }
    //bar chart for game stocks
    public void getGameBarChartData(){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT name, stock FROM game";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("game");

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int stock = resultSet.getInt("stock");
                    series.getData().add(new XYChart.Data<>(name, stock));
                }

                gameBarChart.getData().add(series);
                gameBarChart.setAnimated(false);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    //bar chart for console stocks
    public void getConsoleBarChartData(){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT name, stock FROM console";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("console");

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int stock = resultSet.getInt("stock");
                    series.getData().add(new XYChart.Data<>(name, stock));
                }

                consoleBarChart.getData().add(series);
                consoleBarChart.setAnimated(false);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
