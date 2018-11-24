
package ohtu.dao;


import ohtu.model.Book;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import java.sql.Timestamp;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.h2.tools.RunScript;

/**
 *
 * @author root
 */
public class BookDaoPGTest {
    
    BookDaoPG bdaopg;
    private Book book;
    private Date today;
    private Date changedDate;
    
    @Before
    public void setUp() {
        bdaopg = new BookDaoPG(); 
    }

    @Test
    public void listBooksNotNull(){
       List<Book> books = bdaopg.list();
       assertNotEquals(null, books);
    }
    
    @Test
    public void titleOfFirstBookDatabaseImportSql(){
        List<Book> books = bdaopg.list();
        assertEquals("Clean Code: A Handbook of Agile Software Craftsmanship", books.get(0).getTitle());
    }
    
    @Test
    public void addNewBook(){
        int id = 3;
        String writer = "HaamuKirjoittaja";
        String title = "Tietokannan salat";
        String isbn = "345765123";
        boolean isRead = false;
        today = new Date();
        changedDate = new Date(2000, 10, 1);
        book = new Book(id, title, writer, isbn, isRead, today);
        bdaopg.add(book);
        List<Book> books = bdaopg.list();
        assertEquals("Tietokannan salat", books.get(books.size()-1).getTitle());
   }
    
}
