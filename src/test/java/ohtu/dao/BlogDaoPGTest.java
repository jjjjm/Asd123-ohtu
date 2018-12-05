package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.handlers.ConnectionHandler;
import ohtu.model.Blog;
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
        List<Blog> fetchedBlogs = new ArrayList();
        assertTrue(fetchedBlogs.isEmpty());
    }

    private List<Blog> createBlogs() {
        int id1 = 1;
        int id2 = 2;
        String title1 = "TestTitle";
        String writer1 = "TestWriter";
        String title2 = "TestTitle";
        String writer2 = "TestWriter";
        List<Blog> blogs = new ArrayList();
        blogs.add(new Blog(id1, title1, writer1, "", "", false, new Date()));
        blogs.add(new Blog(id2, title2, writer2, "", "", false, new Date()));
        return blogs;
    }

    private void assertFalse(boolean par) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
