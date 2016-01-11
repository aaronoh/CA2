/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA1V0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aaron
 */
public class DBConnection {

    private static final String USERNAME = "N00143888";//the username and password used in phpmyadmin
    private static final String PASSWORD = "N00143888";
    private static final String URL = "jdbc:mysql://localhost/N00143888playground";//the location of the database on the server 

    private static DBConnection instance = null;
    private Connection dbConnection = null;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();

        }

        return instance;

    }

    private boolean openConnection() {
        boolean connected = true;
        try {
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            connected = false;
            System.err.println("Error no connection: " + e.getMessage());
        }
        return connected;

    }

    public Connection getDbConnection() {
        Connection connection = null;
        if (dbConnection == null) {
            if (openConnection()) {
                connection = dbConnection;
            }
        } else {
            connection = dbConnection;

        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (dbConnection != null) {//if the connection is open, close it
                dbConnection.close();
                dbConnection = null;
            }
        } catch (SQLException e) {

        }
    }

}
