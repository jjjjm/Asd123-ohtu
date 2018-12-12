
package ohtu.dao;

import java.util.List;
import ohtu.model.Book;

public interface BookDao {
    
    public boolean add(Book book); // add book to database
    
    public List<Book> list();   // retun list of books in database
    
    public List<Book> searchBooks(String keyword);  // search books that contain keyword    
    
    public Book getBook(int id); // search book by id
    
    public boolean update(Book book); //updates book values in database
    
    public boolean deleteBook(int id); //deletes book by id
    
}
