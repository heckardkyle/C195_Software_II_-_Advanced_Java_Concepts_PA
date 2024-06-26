package com.c195_software_ii__advanced_java_concepts_pa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 /*
 * Retrieved from the WGU CI Software Team Resource C195 Code Repository and amended for the
 * purposes of this project.
 */

/**
 * Class for establishing a connection to a database.
 * @author Kyle Heckard
 * @version 1.0
 */
public abstract class JDBC {

    /* --Declarations-- */

    private static final String     protocol     = "jdbc";
    private static final String     vendor       = ":mysql:";
    private static final String     location     = "//localhost/";
    private static final String     databaseName = "client_schedule";
    private static final String     jdbcUrl  = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String     driver   = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String     userName = "sqlUser"; // Database Username
    private static       String     password = "Passw0rd!"; // Database Password
    public  static       Connection connection;  // Connection Interface


    /**
     * Opens a connection to the database
     * @return <code>connection</code>
     */
    public static Connection openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch (SQLException e)           { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }

        return connection;
    }


    /**
     * Gets connection for sql query's
     * @return <code>connection</code>
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Closes connection to the database
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

}
