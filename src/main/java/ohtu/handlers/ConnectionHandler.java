package ohtu.handlers;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class for handling database connection.
 */
public class ConnectionHandler {

    private String databaseAddress;         // database address
    private String username;                // database username
    private String password;                // database password

    /**
     * Default constructor which uses environmental variables.
     */
    public ConnectionHandler() {
        this("jdbc:postgresql:" + System.getenv("DB_NAME"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }

    /**
     * Constructor which accepts custom parameters.
     *
     * @param databaseAddress
     * @param username
     * @param password
     */
    public ConnectionHandler(String databaseAddress, String username, String password) {
        if (databaseAddress == null || databaseAddress.isEmpty()) {
            databaseAddress = "jdbc:postgresql:" + System.getenv("DB_NAME");
        }
        this.databaseAddress = databaseAddress;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns connection to database.
     *
     * @return database connection
     */
    public Connection getDatabaseConnection() {
        try {
            // try to create connection and return it
            Connection connection = DriverManager.getConnection(databaseAddress, username, password);
            return connection;
        } catch (Exception e) {
            // was not able to create connection
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Closing a database connection
     *
     * @param connection
     * @return true if connection was closed
     */
    public boolean closeDatabaseConnection(Connection connection) {
        try {
            // try to close the databse connection
            connection.close();
            return true;
        } catch (Exception e) {
            // something went wrong and connection was not closed properly
            // print the error message
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get database address.
     *
     * @return address
     */
    public String getDatabaseAddress() {
        return this.databaseAddress;
    }

}
