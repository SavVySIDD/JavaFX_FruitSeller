package main.java.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        UserManager userManager = new UserManager(); // Initialize the UserManager
        LoginPage loginPage = new LoginPage(primaryStage, userManager); // Create the LoginPage

        Scene scene = new Scene(loginPage, 400, 300); // Start with the LoginPage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fruit Market App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
