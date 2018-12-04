/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.dao;

import java.util.List;
import ohtu.model.Tip;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import ohtu.model.Blog;
import ohtu.model.Book;
import org.h2.tools.RunScript;


public class AllDaoPG implements AllDao {
    private BookDao bookDao;
    private BlogDao blogDao;
 
 
 
    @Override
    public List<Tip> list() {
        bookDao = new BookDaoPG();
        blogDao = new BlogDaoPG();
        List<Tip> tips = new ArrayList<>();
        // books to tips
        List<Book> books = bookDao.list();          
        for (Book book: books){
            Tip tip = new Tip();
            tip.setId(book.getId());
            tip.setType("/books/");
            tip.setText(book.toString());
            tips.add(tip);
        }
        //blogs to tips
        List<Blog> blogs = blogDao.list();
        for (Blog blog: blogs){
            Tip tip = new Tip();
            tip.setId(blog.getId());
            tip.setType("/blogs/");
            tip.setText(blog.toString());
            tips.add(tip);
        }
        return tips; 
    }    
}
