
package ohtu.dao;

import java.util.List;
import ohtu.model.Book;

/**
 * BookDao implementation for postgresql database.
 */
public class BookDaoPG implements BookDao {

    @Override
    public void add(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //    private List<Book> fetchAllBooks() {
//        try {
//            // Open connection to a database -- do not alter this code
//            String databaseAddress = "jdbc:postgresql:archive";
//
//            Connection connection = DriverManager.getConnection(databaseAddress, "postgres", "admin"); // salasana ja käyttäjä ympäristömuuttujiin?
//
//            try {
//                // If database has not yet been created, insert content
//                RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
//                RunScript.execute(connection, new FileReader("sql/database-import.sql"));
//            } catch (Throwable t) {
//            }
//
//            // Add the code that reads the books from the database 
//            // and prints them here
//            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Book");
//
//            List<Book> books = new ArrayList<>();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                books.add(new Book(id, name));
//            }
//
//            // close the connection
//            resultSet.close();
//            connection.close();
//
//            return books;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // failed --> return empty list
//            return new ArrayList<>();
//        }
//    }
    
}
