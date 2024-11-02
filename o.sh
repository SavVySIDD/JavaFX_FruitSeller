#!/bin/bash

# Paths
JAVAFX_SDK_PATH="/Users/siddhanbaranwal/Documents/javafx-sdk-23.0.1/lib"
SRC_PATH="src/main/java/app"
RESOURCES_PATH="src/main/resources"
OUTPUT_DIR="out/production/FruitMarket"

# Create output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# Compile the Java files
javac --module-path "$JAVAFX_SDK_PATH" --add-modules javafx.controls,javafx.fxml -d "$OUTPUT_DIR" -cp "$RESOURCES_PATH" "$SRC_PATH"/*.java

# Run the Java application
java --module-path "$JAVAFX_SDK_PATH" --add-modules javafx.controls,javafx.fxml -cp "$OUTPUT_DIR:$RESOURCES_PATH" main.java.app.Main

