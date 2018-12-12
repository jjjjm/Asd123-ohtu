package ohtu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

    private int id;
    private String title;
    private String writer;
    private String url;
    private String description;
    private boolean isRead;
    private Date dayOfRead;
    private List<Tag> tags;

    public Blog(int id, String title, String writer, String url, String description, boolean isRead, Date dayOfRead) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.url = url;
        this.description = description;
        this.isRead = isRead;
        this.dayOfRead = dayOfRead;
        this.tags = new ArrayList();
    }

    public Blog() {

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return title + " by " + writer + ", " + description;
    }

}
