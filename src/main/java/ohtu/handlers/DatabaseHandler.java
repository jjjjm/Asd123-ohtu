package ohtu.handlers;

import java.io.FileReader;
import java.sql.Connection;
import org.h2.tools.RunScript;

/**
 * Class for handling database.
 */
public class DatabaseHandler {

    private Connection connection;  // database connection

    /**
     * Constructor for DatabaseHandler.
     *
     * @param connection
     */
    public DatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Initialize database. Return true if database was initialized.
     *
     * @return true if database was initialized
     */
    public boolean initDatabase() {
        try {
            RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
            RunScript.execute(connection, new FileReader("sql/database-import.sql"));
            return true;
        } catch (Throwable t) {
            // database is allready initialized or connection was invalid
        }
        return false;
    }

    /**
     * Get database connection which handler uses.
     *
     * @return connection
     */
    public Connection getConnection() {
        return connection;
    }

}
