package game_store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application{

    public static String url = "jdbc:mysql://127.0.0.1:3306/summative_database?serverTimezone=GMT";
    public static String user = "root";
    public static String password = "Password";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login_window.fxml")));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Video Game Store");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("Login.css")).toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}



