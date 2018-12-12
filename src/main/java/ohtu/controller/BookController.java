package ohtu.controller;

import java.util.List;
import ohtu.dao.BookDao;
import ohtu.dao.TagDao;
import ohtu.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/books")
public class BookController {

    // interface to database
    final private BookDao bDao;
    final private TagDao tDao;

    // controller with dependency injection
    @Autowired
    public BookController(BookDao bookDao, TagDao tagDao) {
        this.bDao = bookDao;
        this.tDao = tagDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(name="keyword", required=false, defaultValue="") String keyword, Model model) {
        List<Book> books;
        if(keyword.isEmpty()) {
            books = bDao.list();
        }else{
            books = bDao.searchBooks(keyword);
        }
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
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showBook(Model model, @PathVariable Integer id) {
        Book book = bDao.getBook(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("tags", tDao.list());
        return "show_book";
    }

    @PostMapping(value = "/{id}")
    public String updateBook(@ModelAttribute Book book, @PathVariable Integer id) {
        bDao.update(book);
        return "redirect:/books";
    }
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET) // RequestMethod.DELETE?
    public String deleteBook(@PathVariable Integer id) {
        bDao.deleteBook(id);
        return "redirect:/books";
    }
}
