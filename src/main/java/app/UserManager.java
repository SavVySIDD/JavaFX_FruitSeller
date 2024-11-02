package main.java.app;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final String FILE_PATH = "user_data.txt";
    private Map<String, String> userCredentials = new HashMap<>();

    public UserManager() {
        loadUsers();
    }

    public boolean createUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return false; // Username already exists
        }
        userCredentials.put(username, password);
        saveUsers();
        return true;
    }

    public boolean authenticate(String username, String password) {
        return userCredentials.getOrDefault(username, "").equals(password);
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("User data file not found, starting with empty data.");
        }
    }

    public boolean login(String username, String password) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'login'");
        return authenticate(username, password);
    }
}
