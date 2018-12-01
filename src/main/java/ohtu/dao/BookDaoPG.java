package ohtu.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Book;
import org.h2.tools.RunScript;

/**
 * BookDao implementation for postgreSQL database.
 */
public class BookDaoPG implements BookDao {
    
    private final int PRDSTM_INDEX_1 = 1;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_2 = 2;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_3 = 3;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_4 = 4;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_5 = 5;   // constant for preparedstatement variable placement
    
    /**
     * Add book to database.
     *
     * @param book
     */
    @Override
    public void add(Book book) {
        // get database connection
        Connection connection = getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to add book to database
            try {
                addBookToDatabase(connection, book);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has not been closed yet, so close it now
            closeDatabaseConnection(connection);
        }
    }

    /**
     * Get list of all books in database.
     *
     * @return list of books
     */
    @Override
    public List<Book> list() {
        List<Book> books = new ArrayList<>();
        // get databse connection
        Connection connection = getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to get all books from database
            try {
                books = fetchAllBooks(connection);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has nod been closed yet, so close it now
            closeDatabaseConnection(connection);
        }
        return books;
    }

    /**
     * Get book from database with specific id.
     *
     * @param id book id
     * @return book
     */
    @Override
    public Book getBook(int id) {
        Book book = null;
        // get database connection
        Connection connection = getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to retrieve book from database
            try {
                book = getBookById(connection, id);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has not been closed yet, so close it now
            closeDatabaseConnection(connection);
        }
        return book;
    }

    /**
     * Update book in database.
     *
     * @param book
     */
    @Override
    public void update(Book book) {
        // get database connection
        Connection connection = getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to update the book in database
            try {
                updateBook(connection, book);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has no been closed yet, sol close it now
            closeDatabaseConnection(connection);
        }
    }

    /**
     * Helper method for getting database connection.
     */
    private Connection getDatabaseConnection() {
        try {
            // Open connection to a database -- do not alter this code
            //Try to get db address from env. variable if possible
            String databaseAddress = System.getenv("JDBC_DATABASE_URL");
            if (databaseAddress == null || databaseAddress.length() == 0) {
                databaseAddress = "jdbc:postgresql:" + System.getenv("DB_NAME");
            }
            Connection connection = DriverManager.getConnection(databaseAddress, System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            try { // If database has not yet been created, insert content
                RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
                RunScript.execute(connection, new FileReader("sql/database-import.sql"));
            } catch (Throwable t) {
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method for closing database connection.
     *
     * @param connection
     */
    private void closeDatabaseConnection(Connection connection) {
        try {
            // try to close the databse connection
            connection.close();
        } catch (Exception e) {
            // something went wrong and connection was not closed properly
            // print the error message
            e.printStackTrace();
        }
    }

    /**
     * Helper method for getting all books in database.
     */
    private List<Book> fetchAllBooks(Connection connection) throws Exception {
        String query = "SELECT * FROM BOOK;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        ResultSet resultSet = prdstm.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = createBookFromResultSet(resultSet);
            if (book != null) {
                books.add(book);
            }
        }
        resultSet.close();
        return books;
    }

    /**
     * Helper method for creating book model from resultSet.
     */
    private Book createBookFromResultSet(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("ID");
        String writer = resultSet.getString("WRITER");
        String title = resultSet.getString("TITLE");
        String isbn = resultSet.getString("ISBN");
        String description = resultSet.getString("DESCRIPTION");
        boolean isRead = resultSet.getBoolean("IS_READ");
        Date date = null;
        if (isRead) {
            date = new Date(resultSet.getTimestamp("DATE_OF_READ").getTime());
        }
        Book book = new Book(id, title, writer, isbn, description, isRead, date);
        return book;
    }

    /**
     * Helper method for adding book to database.
     */
    private void addBookToDatabase(Connection connection, Book book) throws Exception {
        String statement = "INSERT INTO BOOK (WRITER, TITLE, ISBN, DESCRIPTION) VALUES (?, ?, ?, ?);";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setString(PRDSTM_INDEX_1, book.getWriter());
        prdstm.setString(PRDSTM_INDEX_2, book.getTitle());
        prdstm.setString(PRDSTM_INDEX_3, book.getISBN());
        prdstm.setString(PRDSTM_INDEX_4, book.getDescription());
        prdstm.execute();
    }

    /**
     * Helper method for getting book from database with specific id.
     */
    private Book getBookById(Connection connection, Integer id) throws Exception {
        String query = "SELECT * FROM BOOK WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setInt(PRDSTM_INDEX_1, id);
        ResultSet resultSet = prdstm.executeQuery();
        Book book = null;
        if (resultSet.next()) {
            book = createBookFromResultSet(resultSet);
        }
        resultSet.close();
        return book;
    }

    /**
     * Helper method for updating book in database.
     */
    private void updateBook(Connection connection, Book book) throws Exception {
        String statement = "UPDATE BOOK SET WRITER = ?, TITLE = ?, ISBN = ?, DESCRIPTION = ? WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setString(PRDSTM_INDEX_1, book.getWriter());
        prdstm.setString(PRDSTM_INDEX_2, book.getTitle());
        prdstm.setString(PRDSTM_INDEX_3, book.getISBN());
        prdstm.setString(PRDSTM_INDEX_4, book.getDescription());
        prdstm.setInt(PRDSTM_INDEX_5, book.getId());
        prdstm.executeUpdate();
    }

}
