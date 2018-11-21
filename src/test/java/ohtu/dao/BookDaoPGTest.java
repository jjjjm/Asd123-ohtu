
package ohtu.dao;

import ohtu.model.Book;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class BookDaoPGTest {
    
    BookDaoPG bdaopg;
    
    @Before
    public void setUp() {
        bdaopg = new BookDaoPG(); 
    }

}
