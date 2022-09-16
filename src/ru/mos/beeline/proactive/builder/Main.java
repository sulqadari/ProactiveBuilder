package ru.mos.beeline.proactive.builder;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    MainController controller = new MainController();
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
		URL xmlUrl = getClass().getResource("fxmlProactiveBuilder.fxml");
		loader.setLocation(xmlUrl);
        //loader.setLocation(getClass().getResource("fxmlProactiveBuilder.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root, 1024, 768);
		stage.setTitle("Proactive Builder");
		stage.setScene(scene);
		stage.show();
    }
}