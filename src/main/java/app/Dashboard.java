package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends VBox {
    private Stage primaryStage;
    private HomePage homePage;
    private CartPage cartPage;

    public Dashboard(Stage primaryStage, UserManager userManager, String username) {
        this.primaryStage = primaryStage;

        // Initialize pages with the current username
        homePage = new HomePage(primaryStage, cartPage, username);
        cartPage = new CartPage(primaryStage, username);

        // Set up the dashboard layout
        setPadding(new Insets(10));
        setSpacing(10);

        // Create buttons for navigation
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> navigateToHome());

        Button cartButton = new Button("Cart");
        cartButton.setOnAction(e -> navigateToCart());

        // Create the dashboard layout
        HBox dashboard = new HBox(20);
        dashboard.getChildren().addAll(homeButton, cartButton);
        getChildren().addAll(dashboard); // Add dashboard buttons to the layout

        // Start with HomePage in the center
        navigateToHome();
    }

    private void navigateToHome() {
        primaryStage.setScene(new Scene(homePage, 800, 600)); // Show HomePage
    }

    private void navigateToCart() {
        primaryStage.setScene(new Scene(cartPage, 800, 600)); // Show CartPage
    }
}
