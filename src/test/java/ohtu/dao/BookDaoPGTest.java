package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import ohtu.model.Book;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.h2.tools.RunScript;

public class BookDaoPGTest {

    BookDaoPG bdaopg;
    private Date today;

    @Before
    public void setUp() {
        bdaopg = new BookDaoPG();
        // try to clear database before each test
        try {
            String databaseAddress = "jdbc:postgresql:" + System.getenv("DB_NAME");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection connection = DriverManager.getConnection(databaseAddress, user, password);
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listBooksNotNull() {
        // list of books should never be null
        List<Book> books = bdaopg.list();
        assertNotEquals(null, books);
    }

    @Test
    public void addNewBookToDatabaseWorks() {
        int id = 3;
        String writer = "HaamuKirjoittaja";
        String title = "Tietokannan salat";
        String isbn = "345765123";
        String description = "bad";
        boolean isRead = false;
        today = new Date();
        Book book = new Book(id, title, writer, isbn, description, isRead, today);
        bdaopg.add(book);
        List<Book> books = bdaopg.list();
        assertEquals("Tietokannan salat", books.get(books.size() - 1).getTitle());
    }

    @Test
    public void getBookByIdWorks() {
        // lisätään kirja kantaan
        int id = 3;
        String writer = "HaamuKirjoittaja";
        String title = "Tietokannan salat";
        String isbn = "345765123";
        String description = "bad";
        boolean isRead = false;
        today = new Date();
        Book book = new Book(id, title, writer, isbn, description, isRead, today);
        bdaopg.add(book);
        // haetaan lisätty kirja --> id vaihtelee, eikä voi suoraan päätellä
        book = bdaopg.list().get(0);
        book = bdaopg.getBook(book.getId());
        assertEquals("Tietokannan salat", book.getTitle());
    }
    
    @Test
    public void updateBookWorks() {
        // lisätään kirja kantaan
        int id = 3;
        String writer = "HaamuKirjoittaja";
        String title = "Tietokannan salat";
        String isbn = "345765123";
        String description = "bad";
        boolean isRead = false;
        today = new Date();
        Book book = new Book(id, title, writer, isbn, description, isRead, today);
        bdaopg.add(book);
        // haetaan juuri lisätty kirja kannasta
        book = bdaopg.list().get(0);
        // muutetaan description
        book.setDescription("Very good");
        bdaopg.update(book);
        // haetaan päivitetty kirja kannasta
        book = bdaopg.list().get(0);
        assertEquals("Very good", book.getDescription());
    }

}
