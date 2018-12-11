/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.model;

import java.util.Date;

/** Tag for tagging Books and Blogs with keywords.
 * 
 * @author inkeriv
 */
public class Tag {
    
    private int id;
    private String tag;
    private Date created;
    
    public Tag(int id, String tag, Date created) {
        
        this.id = id;
        this.tag = tag;
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
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    @Override
    public String toString() {
        return tag;
    }  
}
