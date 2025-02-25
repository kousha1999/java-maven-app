package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class Application {

    private static String[] appArgs; // Store command-line arguments

    public static void main(String[] args) {
        appArgs = args; // Save args for later use
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        Logger log = LoggerFactory.getLogger(Application.class);
        log.info("Java app started");

        if (appArgs == null || appArgs.length == 0) {
            log.error("No user input provided. Exiting.");
            return;
        }

        String userInput = appArgs[0];  // User input from command-line arguments
        log.info("User input: " + userInput);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
             Statement statement = connection.createStatement()) {

            // Vulnerable SQL query construction
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            log.info("Executing query: " + query);

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                log.info("Username: " + resultSet.getString("username"));
            }

            resultSet.close();
        } catch (Exception e) {
            log.error("An error occurred while executing the SQL query: ", e);
        }
    }

    public String getStatus() {
        return "OK";
    }
}
