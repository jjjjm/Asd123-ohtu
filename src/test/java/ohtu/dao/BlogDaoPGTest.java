package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.handlers.ConnectionHandler;
import ohtu.model.Blog;
import ohtu.model.Book;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlogDaoPGTest {

    BlogDaoPG bdaopg;

    public BlogDaoPGTest() {
        bdaopg = new BlogDaoPG();
    }

    @Before
    public void setUp() {
        // try to clear database before each test
        try {
            ConnectionHandler connectionHandler = new ConnectionHandler();
            Connection connection = connectionHandler.getDatabaseConnection();
            RunScript.execute(connection, new FileReader("sql/database-clear.sql"));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void blogListIsNotNull() {
        assertTrue(bdaopg.list() != null);
    }

    /* @Test
    public void blogCanBeAddedAndFetchedFromDatabase() {
        int id = 1;
        String title = "TestTitle";
        String writer = "TestWriter";
        bdaopg.add(new Blog(id, title, writer, "", "", false, new Date()));
        Blog fetchedBook = bdaopg.getBlog(id);
        assertTrue(fetchedBook.getTitle() == title && fetchedBook.getWriter() == writer);
    }*/
    @Test
    public void multipleBlogsCanBeAddedAndFetched() {
        int id1 = 1;
        int id2 = 2;
        String title1 = "TestTitle";
        String writer1 = "TestWriter";
        String title2 = "TestTitle";
        String writer2 = "TestWriter";
        bdaopg.add(new Blog(id1, title1, writer1, "", "", false, new Date()));
        bdaopg.add(new Blog(id2, title2, writer2, "", "", false, new Date()));
        assertTrue(bdaopg.list().size() == 2);
    }

    @Test
    public void correctBlogCanBeFetchedByID() {
        int id1 = 1;
        String title1 = "TestTitle";
        String writer1 = "TestWriter";
        bdaopg.add(new Blog(id1, title1, writer1, "", "", false, new Date()));
        Blog blog = new Blog();
        for (Blog b : bdaopg.list()) {
            if (b.getTitle().equals(title1)) {
                blog = b;
                break;
            }
        }
        Blog fetcedBlog = bdaopg.getBlog(blog.getId());
        assertTrue(fetcedBlog.getTitle().equals(title1));
    }

    @Test
    public void blogCanBeUpdated() {
        int id1 = 1;
        String title1 = "TestTitle";
        String writer1 = "TestWriter";
        bdaopg.add(new Blog(id1, title1, writer1, "", "", false, new Date()));
        Blog blog = new Blog();
        String newTitle = "NewTitle";
        for (Blog b : bdaopg.list()) {
            if (b.getTitle().equals(title1)) {
                blog = b;
                break;
            }
        }
        Blog fetcedBlog = bdaopg.getBlog(blog.getId());
        fetcedBlog.setTitle(newTitle);
        bdaopg.update(fetcedBlog);
        assertTrue(fetcedBlog.getTitle().equals(newTitle));
    }

    @Test
    public void blogCanBeDeleted() {
        int id = 1;
        String title = "TestTitle";
        String writer = "TestWriter";
        bdaopg.add(new Blog(id, title, writer, "", "", false, new Date()));
        List<Blog> fetchedBlogs = bdaopg.list();
        bdaopg.deleteBlog(fetchedBlogs.get(0).getId());
        assertTrue(bdaopg.list().isEmpty());
    }

    @Test
    public void searchBooksByKeywordWorks() {
        // lisätään kolme kirjaa kantaan
        Date today = new Date();
        bdaopg.add(new Blog(1, "Blogi1", "Blogger1", "123", "Kuvaus", false, today));
        bdaopg.add(new Blog(1, "Blogi2", "Blogger2", "124", "Kuvaus", false, today));
        bdaopg.add(new Blog(1, "Blog3", "Blogger3", "125", "Kuvaus", false, today));
        // haetaan lista kirjoja jotka sisältävät hakusanan "KIRJA" --> 2 kpl
        List<Blog> blogs = bdaopg.searchBlogs("BLOGI");
        assertEquals(2, blogs.size());
    }
}
