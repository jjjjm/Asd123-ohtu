package ohtu.dao;

import ohtu.handlers.ConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Blog;
import ohtu.model.Tag;

public class BlogDaoPG implements BlogDao {

    private final ConnectionHandler conHandler;   // database connection handler

    private final int PRDSTM_INDEX_1 = 1;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_2 = 2;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_3 = 3;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_4 = 4;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_5 = 5;   // constant for preparedstatement variable placement

    /**
     * Default constructor.
     */
    public BlogDaoPG() {
        conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }

    /**
     * Constructor which takes ConnectionHandler-objects as parameter.
     *
     * @param conHandler
     */
    public BlogDaoPG(ConnectionHandler conHandler) {
        this.conHandler = conHandler;
    }

    @Override
    public boolean add(Blog blog) {
        boolean success = false;
        // get database connection
        Connection connection = conHandler.getDatabaseConnection();
        // try to add blog to database
        try {
            addBlogToDatabase(connection, blog);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close database connection
        conHandler.closeDatabaseConnection(connection);
        return success;
    }

    @Override
    public List<Blog> list() {
        List<Blog> blogs = new ArrayList<>();
        // get databse connection
        Connection connection = conHandler.getDatabaseConnection();
        // try to get all blogs from database
        try {
            blogs = fetchAllBlogs(connection);
        } catch (Exception e) {
            // operation failed... print error message
            e.printStackTrace();
        }
        // connection has nod been closed yet, so close it now
        conHandler.closeDatabaseConnection(connection);
        return blogs;
    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        List<Blog> blogs = new ArrayList<>();
        // get databse connection
        Connection connection = conHandler.getDatabaseConnection();
        // try to get all blogs from database
        try {
            blogs = fetchBlogsByKeyword(connection, keyword);
        } catch (Exception e) {
            // operation failed... print error message
            e.printStackTrace();
        }
        // connection has nod been closed yet, so close it now
        conHandler.closeDatabaseConnection(connection);
        return blogs;
    }

    @Override
    public Blog getBlog(int id) {
        Blog blog = null;
        Connection connection = conHandler.getDatabaseConnection();
        try {
            blog = getBlogById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conHandler.closeDatabaseConnection(connection);
        return blog;
    }

    @Override
    public boolean update(Blog blog) {
        boolean success = false;
        // get databse connection
        Connection connection = conHandler.getDatabaseConnection();
        // try to update blog
        try {
            updateBlog(connection, blog);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close database connection
        conHandler.closeDatabaseConnection(connection);
        return success;
    }

    @Override
    public boolean deleteBlog(int id) {
        boolean success = false;
        // get database connection
        Connection connection = conHandler.getDatabaseConnection();
        // try to delete blog
        try {
            deleteBlogById(connection, id);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close connection
        conHandler.closeDatabaseConnection(connection);
        return success;
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
                List<Tag> tags = getTagsForBlog(connection, blog.getId());
                blog.setTags(tags);
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
            List<Tag> tags = getTagsForBlog(connection, blog.getId());
            blog.setTags(tags);
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
     * Helper method for getting all blogs in database.
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
                List<Tag> tags = getTagsForBlog(connection, blog.getId());
                blog.setTags(tags);
            }
        }
        resultSet.close();
        return blogs;
    }

    /**
     * Helper method for deleting tags from blog
     */
    private void deleteTagsByBlogId(Connection connection, int blogId) throws Exception {
        String statement = "DELETE FROM BOOK_TAG WHERE BOOK_ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, blogId);
        prdstm.executeUpdate();
    }

    /**
     * Helper method for geting tags for blog
     */
    private List<Tag> getTagsForBlog(Connection connection, int blogId) throws Exception {
        String statement = "SELECT t.* FROM TAG t, BLOG_TAG bt WHERE bt.BLOG_ID = ? AND bt.TAG_ID = t.ID;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, blogId);
        ResultSet resultSet = prdstm.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while (resultSet.next()) {
            Tag tag = createTagFromResultSet(resultSet);
            tags.add(tag);
        }
        resultSet.close();
        return tags;
    }

    /**
     * Helper method for creating tag model from resultSet.
     */
    private Tag createTagFromResultSet(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("ID");
        String writer = resultSet.getString("NAME");
        Timestamp created = resultSet.getTimestamp("DATE_CREATED");
        return new Tag(id, writer, created);
    }

    /**
     * Helper method for creating tag for blog.
     */
    private void createTagsForBlogById(Connection connection, List<Tag> tags, int blogId) throws Exception {
        // loop through all tags and add those to database that dont already exist
        for (Tag tag : tags) {
            if (!blogHasTag(connection, blogId, blogId)) {
                // did not have tag yet --> create i
                createTagForBlog(connection, tag.getId(), blogId);
            }
        }
    }

    /**
     * Helper method to check if blog has certain tag.
     */
    private boolean blogHasTag(Connection connection, int tagId, int blogId) throws Exception {
        String statement = "SELECT * FROM BOOK_TAG bt WHERE bt.TAG_ID = ? AND bt.BOOK_ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, tagId);
        prdstm.setInt(PRDSTM_INDEX_2, blogId);
        ResultSet resultSet = prdstm.executeQuery();
        boolean result = resultSet.next();
        resultSet.close();
        return result;
    }

    /**
     * Helper method for creating tag for blog
     */
    private void createTagForBlog(Connection connection, int tagId, int blogId) throws Exception {
        String statement = "INSERT INTO BOOK_TAG (BOOK_ID, TAG_ID) VALUES (?, ?);";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, tagId);
        prdstm.setInt(PRDSTM_INDEX_2, blogId);
        prdstm.execute();
    }

}
