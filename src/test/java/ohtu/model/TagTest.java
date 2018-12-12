package ohtu.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TagTest {
    private Timestamp now;
    private Tag tag;
    
    @Before
    public void setUp() {
        this.now = Timestamp.valueOf(LocalDateTime.now());
        tag = new Tag(1, "Testi", now);
    }
    
    @Test
    public void setNameWorks() {
        tag.setName("changed");
        assertEquals("changed", tag.getName());
    }
    
    @Test 
    public void setIDWorks(){
        tag.setId(3);
        assertEquals(3, tag.getId());
    }
    
    @Test
    public void setTimestampWorks() {
        Timestamp changed = Timestamp.valueOf("2000-02-01 02:34:45");
        tag.setCreated(changed);
        assertEquals(changed, tag.getCreated());
    }
    
    @Test
    public void toStringWorks() {
        assertEquals("Testi", tag.toString());
    }
    
}
