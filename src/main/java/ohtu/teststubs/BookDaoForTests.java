
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
        book.setId(books.size() + 1);
        books.add(book);
    }

    @Override
    public List<Book> list() {
        return books;
    }
    
    @Override
    public Book getBook(int id){
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }
    
}
