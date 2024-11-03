package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutPage extends VBox {
    public CheckoutPage(Stage primaryStage, List<String> items, String username) {
        setPadding(new Insets(20));
        setSpacing(10);

        Label titleLabel = new Label("Order Summary");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Map to hold item quantities
        Map<String, Integer> itemSummary = new HashMap<>();
        for (String item : items) {
            itemSummary.put(item, itemSummary.getOrDefault(item, 0) + 1);
        }

        // Calculate total price based on item prices from the string format
        double totalPrice = 0;
        StringBuilder summaryText = new StringBuilder();
        for (Map.Entry<String, Integer> entry : itemSummary.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            // Extract price from item name format "Item - RsX"
            String[] parts = itemName.split("- Rs");
            double price = 0;
            if (parts.length == 2) {
                try {
                    price = Double.parseDouble(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing price for item: " + itemName);
                }
            }

            double cost = quantity * price;
            totalPrice += cost;

            summaryText.append(parts[0].trim())
                       .append(" x ")
                       .append(quantity)
                       .append(" - Rs")
                       .append(cost)
                       .append("\n");
        }

        summaryText.append("\nTotal Price: Rs").append(totalPrice);
        Label summaryLabel = new Label(summaryText.toString());

        // Back to Cart button
        Button backButton = new Button("Back to Cart");
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(new CartPage(primaryStage, username), 400, 300)));
        
        
        final double a= totalPrice;
        
        
        // Finish Shopping button
        Button finishButton = new Button("Finish Shopping");
        finishButton.setOnAction(e -> showThankYouPage(primaryStage, a));

        getChildren().addAll(titleLabel, summaryLabel, backButton, finishButton);
    }

    private void showThankYouPage(Stage primaryStage, double totalPrice) {
        // Create a new layout for the thank-you page
        VBox thankYouLayout = new VBox(10);
        thankYouLayout.setPadding(new Insets(20));

        // Add thank you message and total price
        Label thankYouLabel = new Label("Thank you for visiting!");
        thankYouLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label totalLabel = new Label("Total Price: Rs" + totalPrice);
        totalLabel.setStyle("-fx-font-size: 16px;");

        // Add an image
        ImageView imageView = new ImageView(new Image("../resources/images/thankyou.png")); // Adjust path to your image
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> primaryStage.close());

        thankYouLayout.getChildren().addAll(thankYouLabel, totalLabel, imageView, closeButton);
        thankYouLayout.setStyle("-fx-alignment: center;");

        // Create and set the scene for the thank-you page
        Scene thankYouScene = new Scene(thankYouLayout, 400, 300);
        primaryStage.setScene(thankYouScene);
    }
}
