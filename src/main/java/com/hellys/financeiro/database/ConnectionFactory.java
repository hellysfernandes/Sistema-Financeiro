package com.hellys.financeiro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:financeiro.db");
    }
}
