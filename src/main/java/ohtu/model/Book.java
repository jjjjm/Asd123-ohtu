
package ohtu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Book {
    
    private int id;
    private String title;
    private String writer;
    private String isbn;
    private String description;
    private boolean isRead;
    private Date dayOfRead;
    private List<Tag> tags;

    public Book(int id, String title, String writer, String isbn, String description, boolean isRead, Date dayOfRead) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.isbn = isbn;
        this.description = description;
        this.isRead = isRead;
        this.dayOfRead = dayOfRead;
        tags = new ArrayList<>();
    }
    
    //tyhjä konstruktori, jotta lomakkeen saa nettisivuilla näkyviin. myöhemmin ehkä turha
    public Book() {    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Date getDayOfRead() {
        return dayOfRead;
    }

    public void setDayOfRead(Date dayOfRead) {
        this.dayOfRead = dayOfRead;
    }
    
    @Override
    public String toString() {
        return title + " by " + writer + ", " + description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    
    
}
