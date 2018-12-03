
package ohtu.model;

import java.util.Date;

public class Blog {
    private int id;
    private String title;
    private String writer; 
    private String url;
    private String description;
    private boolean isRead;
    private Date dayOfRead;

    public Blog(int id, String title, String writer, String url, String description, boolean isRead, Date dayOfRead) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.url = url;
        this.description = description;
        this.isRead = isRead;
        this.dayOfRead = dayOfRead;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public Date getDayOfRead() {
        return dayOfRead;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setDayOfRead(Date dayOfRead) {
        this.dayOfRead = dayOfRead;
    }
    
    
}
