package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

public class HomePage extends BorderPane {
    private Stage primaryStage;
    private String username;

    public HomePage(Stage primaryStage, CartPage cartPage, String username) {
        this.primaryStage = primaryStage;
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

        homeButton.setOnAction(e -> {
            System.out.println("Already on Home Page!");
        });

        cartButton.setOnAction(e -> {
            System.out.println("Navigating to CartPage...");

            // Create a new instance of CartPage each time the button is clicked
            CartPage cartPage = new CartPage(primaryStage, username);
            primaryStage.setScene(new Scene(cartPage, 800, 600)); // Navigate to new CartPage instance
        });

        dashboard.getChildren().addAll(homeButton, cartButton);
        return dashboard;
    }

    private GridPane createItemGrid() {
        GridPane itemGrid = new GridPane();
        itemGrid.setPadding(new Insets(20));
        itemGrid.setHgap(10);
        itemGrid.setVgap(10);

        int []arr= {5,12,7,9,15,23,21,2,27,20};
        String []name={"Apple","Kiwi","Musk Melon","Mango","Pear","Plums","Watermelon","Grapes","Guava","Banana"};
        for (int i = 1; i <= 10; i++) {
            String imagePath = "/images/item" + i + ".png"; // Ensure you have the correct paths
            URL imageUrl = getClass().getResource(imagePath);

            // Sample names and prices for each item
            String itemName = name[i-1];
            String itemPrice = "Rs " + arr[i-1]; // Example price calculation

            if (imageUrl != null) {
                ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Label nameLabel = new Label(itemName); // Label for item name
                Label priceLabel = new Label(itemPrice); // Label for item price

                Button addButton = new Button("Add to Cart");
                String itemId = itemName;
                addButton.setOnAction(e -> {
                    System.out.println("Adding " + itemName + " to cart.");

                    // Create a CartPage instance and add item
                    CartPage cartPage = new CartPage(primaryStage, username);
                    cartPage.addItem(itemName + " - " + itemPrice); // Add item with name and price to CartPage
                });

                // Position items in the grid
                itemGrid.add(imageView, (i - 1) % 5, (i - 1) / 5 * 4);
                itemGrid.add(nameLabel, (i - 1) % 5, (i - 1) / 5 * 4 + 1); // Position name label
                itemGrid.add(priceLabel, (i - 1) % 5, (i - 1) / 5 * 4 + 2); // Position price label
                itemGrid.add(addButton, (i - 1) % 5, (i - 1) / 5 * 4 + 3); // Position add button
            } else {
                System.out.println("Image not found for path: " + imagePath);
            }
        }

        return itemGrid;
    }
}
