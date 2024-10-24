package com.sports.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-connection")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Database connection is active!";
        } catch (SQLException e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
