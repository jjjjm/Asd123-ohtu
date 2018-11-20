package ohtu.controller;

import ohtu.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
//        List<Book> books = fetchAllBooks();
//        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book add(@RequestBody Book book) {
        // add new book to database ...
        return book;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBookForm() {
//        List<Book> books = fetchAllBooks();
//        model.addAttribute("books", books);
        return "new_book";
    }

}
