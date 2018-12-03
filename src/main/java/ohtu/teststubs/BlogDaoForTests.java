
package ohtu.teststubs;

import java.util.List;
import ohtu.dao.BlogDao;
import ohtu.model.Blog;

public class BlogDaoForTests implements BlogDao{

    @Override
    public void add(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Blog> list() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Blog getBlog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteBlog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
