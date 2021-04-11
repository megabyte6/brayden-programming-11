import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game Of Chance");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Start.fxml"))));
        primaryStage.show();
    }
}