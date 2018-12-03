package ohtu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Book;

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
        Connection connection = ConnectionHandler.getDatabaseConnection();
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
            ConnectionHandler.closeDatabaseConnection(connection);
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
        Connection connection = ConnectionHandler.getDatabaseConnection();
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
            ConnectionHandler.closeDatabaseConnection(connection);
        }
        return books;
    }

    /**
     * Get list of all books that contain the given keyword.
     *
     * @param keyword
     * @return list of books
     */
    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        // get databse connection
        Connection connection = ConnectionHandler.getDatabaseConnection();
        // proceed if connection is not null
        if (connection != null) {
            // try to get all books from database
            try {
                books = fetchBooksByKeyword(connection, keyword);
            } catch (Exception e) {
                // operation failed... print error message
                e.printStackTrace();
            }
            // connection has nod been closed yet, so close it now
            ConnectionHandler.closeDatabaseConnection(connection);
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
        Connection connection = ConnectionHandler.getDatabaseConnection();
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
            ConnectionHandler.closeDatabaseConnection(connection);
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
        Connection connection = ConnectionHandler.getDatabaseConnection();
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
            ConnectionHandler.closeDatabaseConnection(connection);
        }
    }

    /**
     * Delete the book with given id
     *
     * @param id book id
     */
    @Override
    public void deleteBook(int id) {
        Connection connection = ConnectionHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                deleteBookById(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ConnectionHandler.closeDatabaseConnection(connection);
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
     * Helper method for getting all books in database.
     */
    private List<Book> fetchBooksByKeyword(Connection connection, String keyword) throws Exception {
        String query = "SELECT * FROM BOOK WHERE LOWER(TITLE) LIKE ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setString(PRDSTM_INDEX_1, "%" + keyword.toLowerCase() + "%");
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

    /**
     * Helper method for deleting a book by its id
     */
    private void deleteBookById(Connection connection, int id) throws Exception {
        String statement = "DELETE FROM BOOK WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setInt(PRDSTM_INDEX_1, id);
        prdstm.execute();
    }

}
