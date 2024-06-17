package game_store;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StockScreenController implements Initializable {
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    @FXML private Button upload_stocks;
    private Stage stage;
    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<Game> GameTable;

    @FXML
    private TableColumn<Game, String> name;
    @FXML
    private TableColumn<Game, Integer> stock;
    @FXML
    private TableColumn<Game, Float> price;
    @FXML
    private TableColumn<Game, Boolean> sale;
    @FXML
    private TableColumn<Game, String> developer;
    @FXML
    private TableColumn<Game, String> genre;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    public void initialize() {
//        List<Game> name = getName();
//
//        myTableView.setItems(FXCollections.observableArrayList(players));
//    }

    @FXML protected void handleHomeSwitch(ActionEvent event) throws Exception
    {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.createHomeScreen(home_screen_switch).show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml"));
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

    private void processCSV(File file) throws IOException, SQLException {
        // Database connection parameters
        try (Connection connection = DriverManager.getConnection(MainApp.url, MainApp.user, MainApp.password)) {
            // Read the CSV file
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // SQL query to update product stock based on CSV data
                    String sql = "UPDATE game SET stock = ? WHERE game_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        // Parse CSV data and update product stock
                        statement.setInt(1, Integer.parseInt(data[1])); // Value to add to current stock
                        statement.setString(2, data[0]); // Product ID
                        statement.executeUpdate();
                    } catch (NumberFormatException e) {
                        // Handle parsing errors gracefully
                        System.err.println("Error parsing stock value: " + e.getMessage());
                    }
                }
            }
        }
    }

//    private ObservableList<Game> getCharacters(){
//        ObservableList<Game> games = FXCollections.observableArrayList();
//        games.add(new GameOfThronesCharacter("Cersei","Lannister","Queen Regent",100000));
//
//        return characters;
//    }
//    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        //nameColumn
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        //allegianceColumn
//        allegianceColumn.setCellValueFactory(new PropertyValueFactory<>("allegiance"));
//
//        //positionColumn
//        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
//
//        //salaryColumn
//        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
//
//        //table = new TableView<>();
//        table.setItems(getCharacters());
    }


}

