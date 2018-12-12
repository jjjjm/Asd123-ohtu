/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.dao;

import java.sql.Connection;
import java.util.List;
import ohtu.model.Tag;

/**
 *
 * @author inkeriv
 */

public interface TagDao {
    
    public void add(Tag tag); // add tag to database
    
    public List<Tag> list();   // return a list of tags in database
    
    public Tag getTag(int id); // return Tag based on id
    
    public boolean tagAlreadyExistsWithName(String name); //return true if tag with name given in params is already in the database    
}
