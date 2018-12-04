package ohtu.dao;

import ohtu.handlers.ConnectionHandler;
import java.io.FileReader;
import java.sql.Connection;
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
            ConnectionHandler connectionHandler = new ConnectionHandler();
            Connection connection = connectionHandler.getDatabaseConnection();
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

    @Test
    public void searchBooksByKeywordWorks() {
        // lisätään kolme kirjaa kantaan
        today = new Date();
        bdaopg.add(new Book(1, "Testikirja", "Anon", "123", "Kuvaus", false, today));
        bdaopg.add(new Book(1, "Kirja", "Anon", "124", "Kuvaus", false, today));
        bdaopg.add(new Book(1, "Booker", "Anon", "125", "Kuvaus", false, today));
        // haetaan lista kirjoja jotka sisältävät hakusanan "KIRJA" --> 2 kpl
        List<Book> books = bdaopg.searchBooks("KIRJA");
        assertEquals(2, books.size());
    }

    @Test
    public void deleteBookWorks() {
        // lisätään kaksi kirjaa kantaan
        today = new Date();
        bdaopg.add(new Book(1, "Testikirja", "Anon", "123", "Kuvaus", false, today));
        bdaopg.add(new Book(2, "Kirja", "Anon", "124", "Kuvaus", false, today));
        // poistetaan kirja joka lisättiin viimeisenä
        List<Book> books = bdaopg.list();
        String titleRemaining = books.get(1).getTitle();    // title of book which was not deleted
        bdaopg.deleteBook(books.get(0).getId());
        books = bdaopg.list();
        assertEquals(1, books.size());
        assertEquals(titleRemaining, books.get(0).getTitle());
    }
}
