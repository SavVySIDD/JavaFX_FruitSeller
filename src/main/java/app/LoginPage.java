package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage extends GridPane {
    public LoginPage(Stage primaryStage, UserManager userManager) {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button createAccountButton = new Button("Create Account");

        // Error label is created here but not added to the layout yet
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
            // Clear any previous error message
            errorLabel.setText("");

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (userManager.login(username, password)) {
                System.out.println("Login successful! Navigating to HomePage...");

                // Create a CartPage specific to the user
                CartPage cartPage = new CartPage(primaryStage, username);

                // Navigate directly to HomePage, passing CartPage and username
                primaryStage.setScene(new Scene(new HomePage(primaryStage, cartPage, username), 800, 600));
            } else {
                System.out.println("Login failed. Invalid username or password.");
                errorLabel.setText("Invalid username or password.");

                // Add the error label to the layout if not already added
                if (!getChildren().contains(errorLabel)) {
                    add(errorLabel, 1, 4);
                }
            }
        });

        createAccountButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new CreateAccountPage(primaryStage, userManager), 400, 300));
        });

        add(usernameLabel, 0, 0);
        add(usernameField, 1, 0);
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        add(loginButton, 1, 2);
        add(createAccountButton, 1, 3);
    }
}
