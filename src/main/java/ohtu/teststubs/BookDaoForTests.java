
package ohtu.teststubs;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.BookDao;
import ohtu.model.Book;

public class BookDaoForTests implements BookDao {

    List<Book> books;
    
    public BookDaoForTests() {
        books = new ArrayList<>();
    }
    
    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> list() {
        return books;
    }
    
}
