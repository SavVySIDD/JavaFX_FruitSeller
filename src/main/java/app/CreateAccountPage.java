package main.java.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateAccountPage extends GridPane {
    public CreateAccountPage(Stage primaryStage, UserManager userManager) {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        Label usernameLabel = new Label("New Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
        Button createButton = new Button("Create Account");
        Button backButton = new Button("Back");

        createButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (userManager.createUser(username, password)) {
                System.out.println("Account created successfully, returning to LoginPage.");
                primaryStage.setScene(new Scene(new LoginPage(primaryStage, userManager), 400, 300));
            } else {
                System.out.println("Account creation failed. Username already exists.");
                Label errorLabel = new Label("Username already exists.");
                add(errorLabel, 1, 4);
            }
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new LoginPage(primaryStage, userManager), 400, 300));
        });

        add(usernameLabel, 0, 0);
        add(usernameField, 1, 0);
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        add(createButton, 1, 2);
        add(backButton, 1, 3);
    }
}
