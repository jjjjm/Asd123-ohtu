/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.util.List;
import ohtu.handlers.ConnectionHandler;
import ohtu.model.Tag;
import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mevid
 */
public class TagDaoPGTest {

    private TagDaoPG tDao;

    @AfterClass
    public static void tearDownClass() {
        // after all tests have been done --> fill db with default values
        ConnectionHandler connectionHandler = new ConnectionHandler();
        Connection connection = connectionHandler.getDatabaseConnection();
        try {
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
            RunScript.execute(connection, new FileReader("sql/database-import.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionHandler.closeDatabaseConnection(connection);
    }

    @Before
    public void setUp() {
        tDao = new TagDaoPG();
        // try to clear database before each test
        ConnectionHandler connectionHandler = new ConnectionHandler();
        Connection connection = connectionHandler.getDatabaseConnection();
        try {
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionHandler.closeDatabaseConnection(connection);
    }

    @Test
    public void listTagsNotNull() {
        // list of books should never be null
        List<Tag> tags = tDao.list();
        assertNotEquals(null, tags);
    }
    
    @Test
    public void addingTagWorks() {
        tDao.add(new Tag(1, "Tagi", null));
        assertEquals(1, tDao.list().size());
        assertEquals("Tagi", tDao.list().get(0).getName());
    }
    
    @Test
    public void gettingTagByIdWorks() {
        tDao.add(new Tag(1, "Tagi", null));
        int id = tDao.list().get(0).getId();
        assertEquals("Tagi", tDao.getTag(id).getName());
    }
    
    @Test
    public void listingTagsWithInvalidConnectionWorksAsExpected() {
        ConnectionHandler connectionHandler = new ConnectionHandler("notvalid", "admin", "admin");
        TagDaoPG tagDao = new TagDaoPG(connectionHandler);
        assertEquals(0, tagDao.list().size());
    }
    
    @Test
    public void addingTagWithInvalidConnectionWorksAsExpected() {
        ConnectionHandler connectionHandler = new ConnectionHandler("notvalid", "admin", "admin");
        TagDaoPG tagDao = new TagDaoPG(connectionHandler);
        assertFalse(tagDao.add(new Tag(0, "UusiTagi", null)));
    }
    
    @Test
    public void getingTagWithInvalidConnectionWorksAsExpected() {
        ConnectionHandler connectionHandler = new ConnectionHandler("notvalid", "admin", "admin");
        TagDaoPG tagDao = new TagDaoPG(connectionHandler);
        assertEquals(null, tagDao.getTag(0));
    }
    
}
