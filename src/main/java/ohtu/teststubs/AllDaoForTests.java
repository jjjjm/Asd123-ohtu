/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.teststubs;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.AllDao;
import ohtu.dao.BlogDao;
import ohtu.dao.BookDao;
import ohtu.model.Blog;
import ohtu.model.Book;
import ohtu.model.Tip;

/**
 *
 * @author tkarkine
 */
public class AllDaoForTests implements AllDao {

    
    private BookDao bookDao;
    private BlogDao blogDao;

    public AllDaoForTests(BookDao bookDao, BlogDao blogDao) {
        this.bookDao = bookDao;
        this.blogDao = blogDao;
    }
    
    @Override
    public List<Tip> list() {
        List<Tip> tips = new ArrayList<>();
        for(Book book : bookDao.list()) {
            Tip tip = new Tip();
            tip.setId(book.getId());
            tip.setText(book.toString());
            tip.setType("/books/");
            tips.add(tip);
        }
        for(Blog blog : blogDao.list()) {
            Tip tip = new Tip();
            tip.setId(blog.getId());
            tip.setText(blog.toString());
            tip.setType("/blogs/");
            tips.add(tip);
        }
        return tips;
    }
    
}
