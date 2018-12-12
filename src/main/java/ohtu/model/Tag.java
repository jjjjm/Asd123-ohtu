/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.model;

import java.sql.Timestamp;

/** Tag for tagging Books and Blogs with keywords.
 * 
 * @author inkeriv
 */
public class Tag {
    
    private int id;
    private String name;
    private Timestamp created;
    
    public Tag(int id, String tag, Timestamp created) {
        
        this.id = id;
        this.name = tag;
        this.created = created;
    }
    
    public Tag() {
        //empty constructor so website shows form
    }
    
    //get & set methods
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String tag) {
        this.name = tag;
    }
    
    public Timestamp getCreated() {
        return created;
    }
    
    public void setCreated(Timestamp created) {
        this.created = created;
    }
    
    @Override
    public String toString() {
        return name;
    }  
}
