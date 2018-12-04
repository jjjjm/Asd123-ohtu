
package ohtu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Blog;


public class BlogDaoPG implements BlogDao{

    @Override
    public void add(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Blog> list() {
        List<Blog> blogs = new ArrayList<>();
        // get databse connection
        Connection connection = ConnectionHandler.getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to get all books from database
            try {
                blogs = fetchAllBlogs(connection);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has nod been closed yet, so close it now
            ConnectionHandler.closeDatabaseConnection(connection);
        }
        return blogs;
    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Blog getBlog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteBlog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private List<Blog> fetchAllBlogs(Connection connection) throws Exception {
        String query = "SELECT * FROM BLOG;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        ResultSet resultSet = prdstm.executeQuery();
        List<Blog> blogs = new ArrayList<>();
        while (resultSet.next()) {
            Blog blog = createBlogFromResultSet(resultSet);
            if (blog != null) {
                blogs.add(blog);
            }
        }
        resultSet.close();
        return blogs;
    }
    
    private Blog createBlogFromResultSet(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("ID");
        String writer = resultSet.getString("WRITER");
        String title = resultSet.getString("TITLE");
        String url = resultSet.getString("URL");
        String description = resultSet.getString("DESCRIPTION");
        boolean isRead = resultSet.getBoolean("IS_READ");
        Date date = null;
        if (isRead) {
            date = new Date(resultSet.getTimestamp("DATE_OF_READ").getTime());
        }
        Blog blog = new Blog(id, title, writer, url, description, isRead, date);
        return blog;
    }
}
