/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.handlers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class ConnectionHandlerTest {

    @Test
    public void closingNullConnectionWorksAsExpected() {
        ConnectionHandler conHandler = new ConnectionHandler();
        assertFalse(conHandler.closeDatabaseConnection(null));
    }
    
    @Test
    public void getDatabaseConnectionWithInvalidParameters() {
        ConnectionHandler conHandler = new ConnectionHandler("dbDoesNotExistHere", "admin", "admin");
        assertEquals(null, conHandler.getDatabaseConnection());
    }
    
    @Test
    public void getDatabaseConnectionWithValidParameters() {
        ConnectionHandler conHandler = new ConnectionHandler();
        assertTrue(conHandler.getDatabaseConnection() != null);
    }
}
