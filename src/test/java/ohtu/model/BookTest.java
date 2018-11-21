package ohtu.model;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class BookTest {

    private Book book;
    private Date today;
    private Date changedDate;

    @Before
    public void setUp() {
        int id = 1;
        String writer = "TestiKirjoittaja";
        String title = "TestiNimi";
        String isbn = "123456789";
        boolean isRead = true;
        today = new Date();;
        changedDate = new Date(2000, 10, 1);
        book = new Book(id, title, writer, isbn, isRead, today);
    }

    @Test
    public void constructorWorks() {
        assertNotEquals(null, book);
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1, book.getId());
    }
    
    @Test
    public void setIdWorks() {
        book.setId(20);
        assertEquals(20, book.getId());
    }
    
    @Test
    public void getWriterWorks() {
        assertEquals("TestiKirjoittaja", book.getWriter());
    }
    
    @Test
    public void settWriterWorks() {
        book.setWriter("muuttuu");
        assertEquals("muuttuu", book.getWriter());
    }
    
    @Test
    public void getTitleWorks() {
        assertEquals("TestiNimi", book.getTitle());
    }
    
    @Test
    public void setTitleWorks() {
        book.setTitle("muutettunimi");
        assertEquals("muutettunimi", book.getTitle());
    }
    
    @Test
    public void getISBNWorks() {
        assertEquals("123456789", book.getISBN());
    }
    
    @Test
    public void setISBNWorks() {
        book.setISBN("987654321");
        assertEquals("987654321", book.getISBN());
    }
    
    @Test
    public void getIsReadWorks() {
        assertEquals(true, book.isIsRead());
    }
    
    @Test
    public void setIsReadWorks() {
        book.setIsRead(false);
        assertEquals(false, book.isIsRead());
    }
    
    @Test
    public void getDateOfReadWorks() {
        assertEquals(today, book.getDayOfRead());
    }
    
    @Test
    public void setDateOfReadWorks() {
        book.setDayOfRead(changedDate);
        assertEquals(changedDate, book.getDayOfRead());
    }
}
