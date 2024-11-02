package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

public class HomePage extends BorderPane {
    private CartPage cartPage;
    private Stage primaryStage;
    private String username;

    public HomePage(Stage primaryStage, CartPage cartPage, String username) {
        this.primaryStage = primaryStage;
        this.cartPage = cartPage;
        this.username = username;

        // Create the dashboard with Home and Cart navigation
        HBox dashboard = createDashboard();

        // Create the item grid layout
        GridPane itemGrid = createItemGrid();

        // Set up the layout with dashboard at the top and item grid in the center
        setTop(dashboard);
        setCenter(itemGrid);
        setPadding(new Insets(10));
    }

    private HBox createDashboard() {
        HBox dashboard = new HBox(20);
        dashboard.setPadding(new Insets(10));

        Button homeButton = new Button("Home");
        Button cartButton = new Button("View Cart");

        homeButton.setOnAction(e ->{
            System.out.println("Already on Home Page1!!");
        });

        cartButton.setOnAction(e -> {
            System.out.println("Navigating to CartPage...");
            primaryStage.setScene(new Scene(cartPage, 800, 600)); // Navigate to CartPage
        });

        dashboard.getChildren().addAll(homeButton, cartButton);
        return dashboard;
    }

    private GridPane createItemGrid() {
        GridPane itemGrid = new GridPane();
        itemGrid.setPadding(new Insets(20));
        itemGrid.setHgap(10);
        itemGrid.setVgap(10);

        for (int i = 1; i <= 10; i++) {
            String imagePath = "/images/item" + i + ".png"; // Ensure you have the correct paths
            URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Button addButton = new Button("Add to Cart");
                int itemId = i;
                addButton.setOnAction(e -> {
                    System.out.println("Adding Item " + itemId + " to cart.");
                    cartPage.addItem("Item " + itemId); // Add item to CartPage
                });

                itemGrid.add(imageView, (i - 1) % 5, (i - 1) / 5 * 2);
                itemGrid.add(addButton, (i - 1) % 5, (i - 1) / 5 * 2 + 1);
            } else {
                System.out.println("Image not found for path: " + imagePath);
            }
        }

        return itemGrid;
    }
}
