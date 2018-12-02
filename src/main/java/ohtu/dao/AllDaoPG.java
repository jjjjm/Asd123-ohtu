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
import ohtu.model.Book;
import org.h2.tools.RunScript;


public class AllDaoPG implements AllDao {
 private BookDao bDao;
 
 
 
    @Override
    public List<Tip> list() {
        bDao = new BookDaoPG();
      // get connection to database
                // get list of books
        List<Book> books = bDao.list();
       
        //books to tips
        List<Tip> tips = new ArrayList<>();
        for (Book book: books){
            Tip tip = new Tip();
            tip.setId(book.getId());
            tip.setType("/books/");
            tip.setText(book.toString());
            tips.add(tip);
            
        }
        
        return tips; }
    
    }
