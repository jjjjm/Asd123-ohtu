package ohtu.dao;

import ohtu.handlers.ConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Blog;

public class BlogDaoPG implements BlogDao {

    private final int PRDSTM_INDEX_1 = 1;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_2 = 2;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_3 = 3;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_4 = 4;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_5 = 5;   // constant for preparedstatement variable placement

    @Override
    public void add(Blog blog) {
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                addBlogToDatabase(connection, blog);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
    }

    @Override
    public List<Blog> list() {
        List<Blog> blogs = new ArrayList<>();
        // get databse connection
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
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
            conHandler.closeDatabaseConnection(connection);
        }
        return blogs;
    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        List<Blog> blogs = new ArrayList<>();
        // get databse connection
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to get all books from database
            try {
                blogs = fetchBlogsByKeyword(connection, keyword);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has nod been closed yet, so close it now
            conHandler.closeDatabaseConnection(connection);
        }
        return blogs;
    }

    @Override
    public Blog getBlog(int id) {
        Blog blog = null;
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                blog = getBlogById(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
        return blog;
    }

    @Override
    public void update(Blog blog) {
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                updateBlog(connection, blog);
            } catch (Exception e) {

                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
    }

    @Override
    public void deleteBlog(int id) {
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                deleteBlogById(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
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

    private void addBlogToDatabase(Connection connection, Blog blog) throws Exception {
        String statement = "INSERT INTO BLOG (TITLE, WRITER, URL, DESCRIPTION) VALUES (?, ?, ?, ?);";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setString(PRDSTM_INDEX_1, blog.getTitle());
        prdstm.setString(PRDSTM_INDEX_2, blog.getWriter());
        prdstm.setString(PRDSTM_INDEX_3, blog.getUrl());
        prdstm.setString(PRDSTM_INDEX_4, blog.getDescription());
        prdstm.execute();
    }

    private Blog getBlogById(Connection connection, int id) throws Exception {
        String query = "SELECT * FROM BLOG WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setInt(PRDSTM_INDEX_1, id);
        ResultSet resultSet = prdstm.executeQuery();
        Blog blog = null;
        if (resultSet.next()) {
            blog = createBlogFromResultSet(resultSet);
        }
        resultSet.close();
        return blog;
    }

    private void updateBlog(Connection connection, Blog blog) throws Exception {
        String statement = "UPDATE BLOG SET TITLE = ?, WRITER = ?, URL = ?, DESCRIPTION = ? WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setString(PRDSTM_INDEX_1, blog.getTitle());
        prdstm.setString(PRDSTM_INDEX_2, blog.getWriter());
        prdstm.setString(PRDSTM_INDEX_3, blog.getUrl());
        prdstm.setString(PRDSTM_INDEX_4, blog.getDescription());
        prdstm.setInt(PRDSTM_INDEX_5, blog.getId());
        prdstm.executeUpdate();
    }

    private void deleteBlogById(Connection connection, int id) throws Exception {
        String statement = "DELETE FROM BLOG WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, id);
        prdstm.executeUpdate();
    }

    /**
     * Helper method for getting all books in database.
     */
    private List<Blog> fetchBlogsByKeyword(Connection connection, String keyword) throws Exception {
        String query = "SELECT * FROM BLOG WHERE LOWER(TITLE) LIKE ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setString(PRDSTM_INDEX_1, "%" + keyword.toLowerCase() + "%");
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
}
