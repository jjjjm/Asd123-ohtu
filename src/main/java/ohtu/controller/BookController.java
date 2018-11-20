package ohtu.controller;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ohtu.model.Book;
import org.h2.tools.RunScript;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Book> books = fetchAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    private List<Book> fetchAllBooks() {
        try {
        // Open connection to a database -- do not alter this code
        String databaseAddress = "jdbc:postgresql:archive";
        
        Connection connection = DriverManager.getConnection(databaseAddress, "postgres", "admin"); // salasana ja käyttäjä ympäristömuuttujiin?

        try {
            // If database has not yet been created, insert content
            RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
            RunScript.execute(connection, new FileReader("sql/database-import.sql"));
        } catch (Throwable t) {
        }
        
        // Add the code that reads the books from the database 
        // and prints them here
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Book");
        
        List<Book> books = new ArrayList<>();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            books.add(new Book(id, name));
        }

        // close the connection
        resultSet.close();
        connection.close();
        
        return books;
        
        } catch (Exception e) {
            e.printStackTrace();
            // failed --> return empty list
            return new ArrayList<>();
        }
    }

}