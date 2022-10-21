package id.ac.sgu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) throws InterruptedException {
		SystemController systemController = SystemController.getInstance();
		systemController.run();
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
		primaryStage.setTitle("Simple Smart System");
		primaryStage.setScene(new Scene(root, 1000, 550));
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
