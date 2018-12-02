/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.model;

/**
 *
 * @author tkarkine
 */
public class Tip {
    private int id;
    private String type;
    private String text;
    
    public void setId (int id) {
        this.id = id;
    }
    
    public void setType (String type) {
        this.type = type;
    }
    
    public void setText (String text) {
        this.text = text;
    }
    
    public int getId () {
        return id;
    }
    
    public String getType () {
        return type;
    }
    
    @Override
    public String toString () {
        return text;
    }
}
