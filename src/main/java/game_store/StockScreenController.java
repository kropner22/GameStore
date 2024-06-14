package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class StockScreenController {
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
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//        myTableView.setItems(FXCollections.observableArrayList(players));
//    }
    @FXML protected void handleHomeTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml")); // Load the FXML

        Stage stage = (Stage) home_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); // Get the controller
    }

    @FXML protected void handleStockCountsTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stock_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) stock_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("stock_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController stockScreenController = loader.getController(); // Get the controller for the home screen
    }


    @FXML protected void handleReportTransition(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("report_screen.fxml")); // Load the FXML for the home screen

        Stage stage = (Stage) report_screen_switch.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("report_screen.fxml"));
        Scene changeScene = new Scene(root, 600, 400);
        stage.setScene(changeScene);
        stage.show();

        StockScreenController ReportScreenController = loader.getController(); // Get the controller for the home screen
    }

    @FXML protected void handleStockUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Stocks CSV");
        File file = fileChooser.showOpenDialog(new Stage());
//        if (file != null){
//            try{
//                processCSV(file);
//            } catch(IOException | SQLException error){
//                error.printStackTrace();
//            }
//
//        }
    }
//    @FXML protected void processCSV(File file) throws IOException, SQLException {
//        String url = "jdbc:mysql://127.0.0.1:3306/project?serverTimezone=GMT";
//        String user = "root";
//        String password = "Password";
//
//        try (Connection connection = DriverManager.getConnection()) {
//            try (BufferedReader br = new BufferedReader(new FileReader(file))){
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] data = line.split(",");
//                }
//            }
//        }
//
//    }


}

