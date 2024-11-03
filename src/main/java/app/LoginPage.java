package main.java.app;


import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
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

        Label loadingLabel = new Label();
        loadingLabel.setStyle("-fx-text-fill: green;"); // Style it as desired
        loadingLabel.setVisible(false); // Initially not visible

        loginButton.setOnAction(e -> {
            // Clear any previous error message
            errorLabel.setText("");
            loadingLabel.setVisible(false);

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (userManager.login(username, password)) {
                System.out.println("Login successful! Navigating to HomePage...");
                loadingLabel.setText("Logging in..."); // Show logging in message
                loadingLabel.setVisible(true); // Make loading label visible
            
                PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Adjust the duration as needed
                pause.setOnFinished(event -> {
                    try {
                        // Create a CartPage specific to the user
                CartPage cartPage = new CartPage(primaryStage, username);

                // Navigate directly to HomePage, passing CartPage and username
                primaryStage.setScene(new Scene(new HomePage(primaryStage, cartPage, username), 800, 600));
                    } catch (Exception ex) {
                        System.out.println("Failed to load the market view: " + ex.getMessage());
                    }
                });
                pause.play();
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
        add(loadingLabel, 1, 4);
    }
}
