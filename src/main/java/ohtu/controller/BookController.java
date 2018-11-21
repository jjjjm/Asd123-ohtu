package ohtu.controller;

import java.util.List;
import ohtu.dao.BookDao;
import ohtu.dao.BookDaoPG;
import ohtu.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(value = "/new")
    public String newBookForm(Book book) { //tähän kohtaan piti parametriks laittaa Book book, että sai formin nettisivuilla näkyviin
        return "new_book";
    }

    @PostMapping(value = "/new")
    public String addBook(@ModelAttribute Book book) {
        // add new book to database ...
        bDao.add(book);
        return "redirect:/books.html";
    }
}
