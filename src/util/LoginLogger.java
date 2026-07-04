package util;

import model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginLogger {

    private final String fileName;
    private final DateTimeFormatter formatter;

    public LoginLogger(String fileName) {
        this.fileName = fileName;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void logSuccess(User user) {
        if (user == null) {
            return;
        }
        String timestamp = LocalDateTime.now().format(formatter);
        String line = String.format(
                "%s | username: %s | role: %s | result: SUCCESS",
                timestamp,
                user.getUsername(),
                user.getRole()
        );
        writeLine(line);
    }

    public void logFailure(String username) {
        String timestamp = LocalDateTime.now().format(formatter);
        String safeUsername = (username == null || username.isEmpty()) ? "UNKNOWN" : username;
        String line = String.format(
                "%s | username: %s | role: %s | result: FAILURE",
                timestamp,
                safeUsername,
                "UNKNOWN"
        );
        writeLine(line);
    }

    private void writeLine(String line) {
        // Append mode true so the file keeps previous entries
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            // For this project printing the stack trace is enough
            e.printStackTrace();
        }
    }
}
