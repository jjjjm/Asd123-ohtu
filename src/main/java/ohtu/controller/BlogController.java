
package ohtu.controller;

import java.sql.Connection;
import java.util.List;
import ohtu.dao.BlogDao;
import ohtu.model.Blog;
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

@Controller
@RequestMapping("/blogs")
public class BlogController {
    
    private BlogDao dao;
    
    @Autowired
    public BlogController(BlogDao blogDao) {
        this.dao = blogDao;
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Blog> blogs = dao.list();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }
    
    @GetMapping(value = "/new")
    public String newBookForm(Blog blog) {
        return "new_blog";
    }
    
    @PostMapping(value = "/new")
    public String addBlog(@ModelAttribute Blog blog) {
        dao.add(blog);
        return "redirect:/blogs";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showBlog(Model model, @PathVariable Integer id) {
        Blog blog = dao.getBlog(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "showblog";
    }
    
    @PostMapping(value = "/{id}")
    public String updateBlog(@ModelAttribute Blog blog, @PathVariable Integer id) {
        dao.update(blog);
        return "redirect:/blogs";
    }
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable Integer id) {
        dao.deleteBlog(id);
        return "redirect:/blogs";
    }
}
