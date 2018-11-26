package ohtu.controller;

import java.util.List;
import ohtu.dao.BookDao;
import ohtu.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    // interface to database
    private BookDao bDao;
    
    // controller with dependency injection
    @Autowired
    public BookController(BookDao bookDao) {
        this.bDao = bookDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Book> books = bDao.list();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(value = "/new")
    public String newBookForm(Book book) {
        return "new_book";
    }

    @PostMapping(value = "/new")
    public String addBook(@ModelAttribute Book book) {
        bDao.add(book);
        return "redirect:/books.html";
    }
}
