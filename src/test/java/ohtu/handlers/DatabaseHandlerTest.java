
package ohtu.handlers;

import java.io.FileReader;
import java.sql.Connection;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class DatabaseHandlerTest {

    
    @Before
    public void setUp() {
        // before each test, the database tables must be dropped
        ConnectionHandler conHandler = new ConnectionHandler();
        Connection connection = conHandler.getDatabaseConnection();
        try {
            RunScript.execute(connection, new FileReader("sql/database-drop.sql"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ConstructorWorks() {
        DatabaseHandler dbHandler = new DatabaseHandler(null);
        assertTrue(dbHandler.getConnection() == null);
    }
    
    @Test
    public void initDatabaseWithNullConnectionWorksAsExpected() {
        DatabaseHandler dbHandler = new DatabaseHandler(null);
        assertFalse(dbHandler.initDatabase());
    }
    
    @Test 
    public void initDatabaseWithValidConnectionWorks() {
        ConnectionHandler conHandler = new ConnectionHandler();
        DatabaseHandler dbHandler = new DatabaseHandler(conHandler.getDatabaseConnection());
        assertTrue(dbHandler.initDatabase());
    }
}
