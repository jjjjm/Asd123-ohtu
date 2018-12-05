
package ohtu.model;

import org.junit.Test;
import static org.junit.Assert.*;


public class TipTest {
    

    @Test
    public void tipWorksAsExpected() {
        int id = 1;
        String type = "Book";
        String text = "HackersCookBook by Anonymous, Introduction to Ethical Hacking";
        Tip tip = new Tip();
        tip.setId(id);
        tip.setType(type);
        tip.setText(text);
        assertEquals(type, tip.getType());
        assertEquals(id, tip.getId());
        assertEquals(text, tip.toString());
    }
}
