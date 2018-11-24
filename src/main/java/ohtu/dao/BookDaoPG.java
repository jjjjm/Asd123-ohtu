package ohtu.dao;

import java.sql.Timestamp;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.model.Book;
import org.h2.tools.RunScript;

/**
 * BookDao implementation for postgresql database.
 */
public class BookDaoPG implements BookDao {

    @Override
    public void add(Book book) {
        try {
            // get connection
            Connection connection = getDatabaseConnection();
            if (connection == null) {
                return;
            }
            addBookToDatabase(connection, book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> list() {
        // get connection to database
        Connection connection = getDatabaseConnection();
        if (connection == null) {
            return new ArrayList<>();
        }
        // get list of books
        List<Book> books = fetchAllBooks(connection);
        try {
            connection.close();
        } catch (Exception e) {
        }
        return books;
    }

    private Connection getDatabaseConnection() {
        try {
            // Open connection to a database -- do not alter this code

            //Try to get db address from env. variable if possible
            String databaseAddress = System.getenv("JDBC_DATABASE_URL");
            if (databaseAddress != null && databaseAddress.length() > 0) {
                Connection connection = DriverManager.getConnection(databaseAddress);
            } else {
                databaseAddress = "jdbc:postgresql:archive";
            }
            Connection connection = DriverManager.getConnection(databaseAddress, "yysmjircbarfyc", "cw3AqC5dvyDnSo73HJJb4mcgKs"); // salasana ja käyttäjä ympäristömuuttujiin?
            try {
                // If database has not yet been created, insert content --> maybe this should be done somewhere else
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

    private List<Book> fetchAllBooks(Connection connection) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM BOOK");
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = createBookFromResultSetRow(resultSet);
                if (book != null) {
                    books.add(book);
                }
            }

            resultSet.close();  // close resultset
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Book createBookFromResultSetRow(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("ID");
            String writer = resultSet.getString("WRITER");
            String title = resultSet.getString("TITLE");
            String isbn = resultSet.getString("ISBN");
            boolean isRead = resultSet.getBoolean("IS_READ");
            Date date = null;
            if (isRead) {
                date = new Date(resultSet.getTimestamp("DATE_OF_READ").getTime());
            }
            Book book = new Book(id, title, writer, isbn, isRead, date);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addBookToDatabase(Connection connection, Book book) {
        try {

            String statement = "INSERT INTO BOOK (WRITER, TITLE, ISBN) VALUES ('"
                    + book.getWriter() + "', '" + book.getTitle() + "', '" + book.getIsbn() + "')";

            connection.createStatement().executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
