
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

    @Override
    public void update(Book book) {
        for(Book b : this.books){
            if(b.getId() == book.getId()){
                b.setTitle(book.getTitle());
                b.setWriter(book.getWriter());
                b.setISBN(book.getISBN());
                b.setDescription(book.getDescription());
            }
        }
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> matches = new ArrayList<>();
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(book);
            }
        }
        return matches;
    }
    
    @Override
    public void deleteBook(int id) {
        for(int i = books.size() - 1; i >= 0; i--) {
            if(books.get(i).getId() == id) {
                books.remove(i);
                return;
            }
        }
    }
    
}
