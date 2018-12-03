package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.h2.tools.RunScript;

/**
 * Class for handling database connection.
 */
public class ConnectionHandler {

    /**
     * Function which returns connection to database.
     *
     * @return database connection
     */
    public static Connection getDatabaseConnection() {
        try {
            // Open connection to a database -- do not alter this code
            //Try to get db address from env. variable if possible
            String databaseAddress = System.getenv("JDBC_DATABASE_URL");
            if (databaseAddress == null || databaseAddress.length() == 0) {
                databaseAddress = "jdbc:postgresql:" + System.getenv("DB_NAME");
            }
            Connection connection = DriverManager.getConnection(databaseAddress, System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            try { // If database has not yet been created, insert content
                RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
                RunScript.execute(connection, new FileReader("sql/database-import.sql"));
            } catch (Throwable t) {
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Function for closing a database connection
     *
     * @param connection
     */
    public static void closeDatabaseConnection(Connection connection) {
        try {
            // try to close the databse connection
            connection.close();
        } catch (Exception e) {
            // something went wrong and connection was not closed properly
            // print the error message
            e.printStackTrace();
        }
    }

}
