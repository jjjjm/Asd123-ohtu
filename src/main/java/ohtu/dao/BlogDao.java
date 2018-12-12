package ohtu.dao;

import java.util.List;
import ohtu.model.Blog;

public interface BlogDao {
    
    public boolean add(Blog blog); // add blog to database
    
    public List<Blog> list();   // return a list of blogs in database
    
    public List<Blog> searchBlogs(String keyword);  // search for blogs that contain the keyword    
    
    public Blog getBlog(int id); // search blog by id
    
    public boolean update(Blog blog); //updates blog values in database
    
    public boolean deleteBlog(int id); //deletes blog by id
    
}
