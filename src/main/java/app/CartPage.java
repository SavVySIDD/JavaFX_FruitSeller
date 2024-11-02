package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends VBox {
    private static final String CART_FILE_PREFIX = "cart_";
    private List<String> items;
    private Label cartLabel;
    private String currentUsername; // Change UserManager to String

    public CartPage(Stage primaryStage, String username) {
        this.currentUsername = username;
        items = loadCartItems(); // Load items specific to user
        cartLabel = new Label("Cart Items:");
        Button backButton = new Button("Back to Home");

        backButton.setOnAction(e -> {
            // Pass the existing CartPage back to HomePage
            primaryStage.setScene(new Scene(new HomePage(primaryStage, this, currentUsername), 800, 600));
        });

        getChildren().addAll(cartLabel, backButton);
        setPadding(new Insets(10));
        updateCartDisplay();
    }

    public void addItem(String item) {
        items.add(item);
        updateCartDisplay();
        saveCartItems(); // Save items to the file after each addition
    }

    private void updateCartDisplay() {
        StringBuilder cartContents = new StringBuilder("Cart Items:\n");
        for (String item : items) {
            cartContents.append(item).append("\n");
        }
        cartLabel.setText(cartContents.toString());
    }

    private void saveCartItems() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_FILE_PREFIX + currentUsername + ".txt"))) {
            for (String item : items) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadCartItems() {
        List<String> loadedItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CART_FILE_PREFIX + currentUsername + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedItems.add(line);
            }
        } catch (IOException e) {
            System.out.println("No previous cart found for user: " + currentUsername);
        }
        return loadedItems;
    }
}
