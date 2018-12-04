package ohtu.model;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {
    
    private Blog blog;
    private Date today;
    private Date changedDate;
    
    @Before
    public void setUp() {
        today = new Date();
        changedDate = new Date(2000, 10, 1);
        blog = new Blog(1, "Unit tests 101", "Author Macauthorface", "www.blogs.com/akuankka", "the basic of unit testing in java explained", false, today);
    }
    
    @Test
    public void contructorMakesSometing() {
        assertNotEquals(null, blog);
    }
    
    @Test
    public void getIdWorks() {
        assertEquals(1, blog.getId());
    }
    
    @Test
    public void setIdWorks() {
        blog.setId(20);
        assertEquals(20, blog.getId());
    }
    
    @Test
    public void getWriterWorks() {
        assertEquals("Author Macauthorface", blog.getWriter());
    }
    
    @Test
    public void settWriterWorks() {
        blog.setWriter("muuttuu");
        assertEquals("muuttuu", blog.getWriter());
    }
    
    @Test
    public void getTitleWorks() {
        assertEquals("Unit tests 101", blog.getTitle());
    }
    
    @Test
    public void setTitleWorks() {
        blog.setTitle("muutettunimi");
        assertEquals("muutettunimi", blog.getTitle());
    }
    
    @Test
    public void getUrlWorks() {
        assertEquals("www.blogs.com/akuankka", blog.getUrl());
    }
    
    @Test
    public void setUrlWorks() {
        blog.setUrl("www.reddit.com");
        assertEquals("www.reddit.com", blog.getUrl());
    }
    
    @Test
    public void getIsReadWorks() {
        assertEquals(false, blog.isIsRead());
    }
    
    @Test
    public void setIsReadWorks() {
        blog.setIsRead(true);
        assertEquals(true, blog.isIsRead());
    }
    
    @Test
    public void getDateOfReadWorks() {
        assertEquals(today, blog.getDayOfRead());
    }
    
    @Test
    public void setDateOfReadWorks() {
        blog.setDayOfRead(changedDate);
        assertEquals(changedDate, blog.getDayOfRead());
    }
    
    @Test
    public void toStringWorks() {
        blog.setTitle("Muumipappa ja internet");
        blog.setWriter("Tove Jansson");
        blog.setDescription("Nipsu:Hackerman");
        assertEquals("Muumipappa ja internet by Tove Jansson, Nipsu:Hackerman", blog.toString());
    }
    
}
