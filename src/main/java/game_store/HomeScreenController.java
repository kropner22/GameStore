package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.dialect.Database;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

import static game_store.DatabaseConnection.databaseSession;
import static game_store.MainApp.*;

public class HomeScreenController {
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    @FXML private BarChart<String, Number> gameBarChart;
    @FXML private CategoryAxis caGameStock;
    @FXML private NumberAxis naGameStock;
    @FXML private BarChart<String, Number> consoleBarChart;
    @FXML private CategoryAxis caConsoleStock;
    @FXML private NumberAxis naConsoleStock;

    private Stage stage;
    @FXML
    private GridPane gridPane;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML protected void handleHomeSwitch(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml")); 

        Stage stage = (Stage) home_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); 
    }


    @FXML protected void handleStockCountsSwitch(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stock_screen.fxml")); 

        Stage stage = (Stage) stock_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("stock_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); 
    }

    @FXML protected void handleReportSwitch(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("report_screen.fxml")); 

        Stage stage = (Stage) report_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("report_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController ReportScreenController = loader.getController(); 
    }

    @FXML
    private Text welcomeMessage;

    public void getGivenName(String name){
        welcomeMessage.setText("Welcome to the game store " + name);
    }

    @FXML
    private Text lowestStockGame;

    List<?> list = DatabaseConnection.getLowestStockGame();

    public void getLowestStockGame(String list) {
        lowestStockGame.setText("Lowest Stock Game: " + list);
    }
    public void getGameBarChartData(){
//        XYChart.Series series = new XYChart.Series();
//        //Query game_name = databaseSession.createQuery("select name from game_store.Game");
//        //Query game_stock = databaseSession.createQuery("select stock from game_store.Game");
//        series.getData().add(new XYChart.Data("Legend of Zelda", 100));
//        series.getData().add(new XYChart.Data("Halo", 30));
//        series.getData().add(new XYChart.Data("Call of Duty", 323));
//        series.getData().add(new XYChart.Data("GTA 5", 123));
//        series.getData().add(new XYChart.Data("Mario Odyssey", 80));
//        series.getData().add(new XYChart.Data("Spiderman", 50));
//        series.getData().add(new XYChart.Data("Fifa", 500));
//        series.getData().add(new XYChart.Data("Stardew Valley", 10));

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // SQL query to retrieve product data
            String query = "SELECT name, stock FROM game";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Create a new series for the BarChart
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("game");

                // Populate the series with data from the result set
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int stock = resultSet.getInt("stock");
                    series.getData().add(new XYChart.Data<>(name, stock));
                }

                // Add the series to the BarChart
                gameBarChart.getData().add(series);
                gameBarChart.setAnimated(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getConsoleBarChartData(){
        XYChart.Series series = new XYChart.Series();
        //Query console_name = databaseSession.createQuery("select name from game_store.Console");
        //Query console_stock = databaseSession.createQuery("select stock from game_store.Console");
        series.getData().add(new XYChart.Data("Switch", 6));
        series.getData().add(new XYChart.Data("PS4", 7));
        series.getData().add(new XYChart.Data("PS5", 4));
        series.getData().add(new XYChart.Data("Xbox", 10));
        consoleBarChart.getData().addAll(series);
        consoleBarChart.setAnimated(false);

    }
}