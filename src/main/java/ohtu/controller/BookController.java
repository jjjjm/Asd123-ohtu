package ohtu.controller;

import java.util.List;
import ohtu.dao.BookDao;
import ohtu.dao.BookDaoPG;
import ohtu.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    // interface to database
    private BookDao bDao;

    public BookController() {
        this.bDao = new BookDaoPG();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Book> books = bDao.list();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Book book) {
        // add new book to database ...
        bDao.add(book);
        // if operation was succesfull?
        return "redirect:/books.html";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBookForm() {
        return "new_book";
    }

}
