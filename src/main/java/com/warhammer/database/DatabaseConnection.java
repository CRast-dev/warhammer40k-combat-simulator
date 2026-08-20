package com.warhammer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() throws SQLException{
        String url = dotenv.get("Database_URL");
        String username = dotenv.get("Database_username");
        String password = dotenv.get("Database_password");
        return DriverManager.getConnection(url, username, password);
    }

}
