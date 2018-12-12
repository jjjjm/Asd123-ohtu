package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Date;
import ohtu.handlers.ConnectionHandler;
import ohtu.model.Blog;
import ohtu.model.Book;
import ohtu.model.Tip;
import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AllDaoPGTest {

    private final BookDaoPG bookDaoPG;
    private final BlogDaoPG blogDaoPG;
    private final AllDaoPG allDaoPG;
    
    /**
     * Constructor.
     */
    public AllDaoPGTest() {
        bookDaoPG = new BookDaoPG();
        blogDaoPG = new BlogDaoPG();
        allDaoPG = new AllDaoPG(bookDaoPG, blogDaoPG);
    }

    @AfterClass
    public static void tearDownClass() {
        // after all tests have been done --> fill db with default values
        try {
            ConnectionHandler connectionHandler = new ConnectionHandler();
            Connection connection = connectionHandler.getDatabaseConnection();
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
            RunScript.execute(connection, new FileReader("sql/database-import.sql"));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        // try to clear database before each test
        try {
            ConnectionHandler connectionHandler = new ConnectionHandler();
            Connection connection = connectionHandler.getDatabaseConnection();
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void listingTipsWorks() {
        // add book to database
        bookDaoPG.add(new Book(-1, "MyLife", "Mr.Nolife", "123456789", "Story of a some random dude", true, new Date()));
        // add blog to database
        blogDaoPG.add(new Blog(-1, "WeatherBlog", "Pekka", "www.blogs.net/weatherBlog", "All about weather", true, new Date()));
        // size comparison
        assertEquals(2, this.allDaoPG.list().size());
        // should contains book and blog
        boolean bookWasListed = false;
        boolean blogWasListed = false;
        for(Tip tip : this.allDaoPG.list()) {
            if(tip.toString().contains("MyLife")) {
                bookWasListed = true;
            }else if (tip.toString().contains("WeatherBlog")) {
                blogWasListed = true;
            }
        }
        assertTrue(bookWasListed);
        assertTrue(blogWasListed);
    }
}
