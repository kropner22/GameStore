//imports for stock screen
package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static game_store.MainApp.*;

public class StockScreenController {
    //calling FXML attributes for stock screen
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    @FXML private Button upload_stocks;

    @FXML private TableView<Game> GameTable;
    @FXML private TableColumn<Game, String> name;
    @FXML private TableColumn<Game, Integer> stock;
    @FXML private TableColumn<Game, Float> price;
    @FXML private TableColumn<Game, Boolean> sale;
    @FXML private TableColumn<Game, String> developer;
    @FXML private TableColumn<Game, String> genre;

    //menu buttons for stock screen
    @FXML protected void handleHomeSwitch() throws Exception
    {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.createHomeScreen(home_screen_switch).show();
        new FXMLLoader(getClass().getResource("home_screen.fxml"));
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

    @FXML protected void handleStockUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Stocks CSV");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null){
            try{
                processCSV(file);
            } catch(IOException | SQLException error){
                error.printStackTrace();
            }

        }
    }

    //uploading CSV
    private void processCSV(File file) throws IOException, SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Read the CSV file
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // SQL query to update product stock based on CSV data
                    String sql = "UPDATE summative_database.game SET stock = stock + ?, sale = ? WHERE name = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        // Parse CSV data and update product stock
                        statement.setInt(1, Integer.parseInt(data[1]));
                        statement.setBoolean(2, Boolean.parseBoolean(data[2]));
                        statement.setString(3, data[0]);
                        statement.executeUpdate();
                    } catch (NumberFormatException error) {
                        System.err.println("Error parsing stock value: " + error.getMessage());
                    }
                }
            }
        }
    }

    // Stock table
    @FXML
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        sale.setCellValueFactory(new PropertyValueFactory<>("Sale"));
        developer.setCellValueFactory(new PropertyValueFactory<>("Developer"));
        genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));


        GameTable.getColumns().clear();
        GameTable.getColumns().addAll(name,stock,price,sale,developer,genre);
        System.out.println(GameTable);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name,stock,price,sale,developer,genre FROM game;")) {

            List<Game> dataList = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int stock = resultSet.getInt("Stock");
                float price = resultSet.getFloat("Price");
                boolean sale = resultSet.getBoolean("Sale");
                String developer = resultSet.getString("Developer");
                String genre = resultSet.getString("Genre");

                dataList.add(new Game(name,stock,price,sale,developer,genre));
            }

            GameTable.getItems().addAll(dataList);

        } catch (SQLException error) {
            System.err.println("Error loading data from the database: " + error.getMessage());
            error.printStackTrace();
        }
    }
}

