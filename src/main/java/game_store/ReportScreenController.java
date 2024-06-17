package game_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ReportScreenController {
    @FXML private Button home_screen_switch;
    @FXML private Button stock_screen_switch;
    @FXML private Button report_screen_switch;
    @FXML private Button generate_report;
    private Stage stage;
    @FXML
    private GridPane gridPane;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML protected void handleHomeSwitch(ActionEvent event) throws Exception
    {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.createHomeScreen(home_screen_switch).show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_screen.fxml"));
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

        ReportScreenController ReportScreenController = loader.getController();
    }


    @FXML
    private void handleReportGeneration() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = (Stage) generate_report.getScene().getWindow();
        java.io.File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            String outputFile = file.getAbsolutePath();
            exportDataToFile(outputFile);
        }
    }
    private void exportDataToFile(String outputFile) {
        try (Connection connection = DriverManager.getConnection(MainApp.url, MainApp.user, MainApp.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM summative_database.order;");
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                writer.print(resultSet.getMetaData().getColumnName(i));
                if (i < resultSet.getMetaData().getColumnCount()) {
                    writer.print(", ");
                }
            }
            writer.println();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    writer.print(resultSet.getString(i));
                    if (i < resultSet.getMetaData().getColumnCount()) {
                        writer.print(", ");
                    }
                }
                writer.println();
            }

            System.out.println("Data exported to " + outputFile);

        } catch (IOException | SQLException error) {
            System.err.println("Error exporting data to file: " + error.getMessage());
            error.printStackTrace();
        }
    }

}