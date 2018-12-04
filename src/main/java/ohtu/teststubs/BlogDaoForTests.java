package ohtu.teststubs;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.BlogDao;
import ohtu.model.Blog;

public class BlogDaoForTests implements BlogDao {

    private List<Blog> blogs;

    public BlogDaoForTests() {
        this.blogs = new ArrayList<>();
    }

    @Override
    public void add(Blog blog) {
        blog.setId(this.blogs.size() + 1);
        this.blogs.add(blog);
    }

    @Override
    public List<Blog> list() {
        return this.blogs;
    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        List<Blog> matches = new ArrayList();
        for (Blog blog : this.blogs) {
            if (blog.getTitle().contains(keyword)) {
                matches.add(blog);
            }
        }
        return matches;
    }

    @Override
    public Blog getBlog(int id) {
        for (Blog blog : this.blogs) {
            if (blog.getId() == id) {
                return blog;
            }
        }
        return null;
    }

    @Override
    public void update(Blog blog) {
        for (Blog b : this.blogs) {
            if (blog.getId() == b.getId()) {
                b.setWriter(blog.getWriter());
                b.setTitle(blog.getWriter());
                b.setUrl(blog.getUrl());
                b.setDescription(blog.getDescription());
            }
        }
    }

    @Override
    public void deleteBlog(int id) {
        for (int i = this.blogs.size() - 1; i >= 0; i--) {
            if (this.blogs.get(i).getId() == id) {
                this.blogs.remove(i);
                return;
            }
        }
    }

}
