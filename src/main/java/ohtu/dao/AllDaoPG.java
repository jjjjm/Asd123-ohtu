package ohtu.dao;

import java.util.List;
import ohtu.model.Tip;
import java.util.ArrayList;
import ohtu.model.Blog;
import ohtu.model.Book;

public class AllDaoPG implements AllDao {

    private final BookDao bookDao;
    private final BlogDao blogDao;

    /**
     * Constructor for AllDaoPG.
     *
     * @param bookDao
     * @param blogDao
     */
    public AllDaoPG(BookDao bookDao, BlogDao blogDao) {
        this.bookDao = bookDao;
        this.blogDao = blogDao;
    }

    @Override
    public List<Tip> list() {
        // get tips from books
        List<Tip> tips = getBookTips();
        //blogs to tips
        tips.addAll(getBlogTips());
        // return all
        return tips;
    }

    /**
     * Get tips from books in database.
     */
    private List<Tip> getBookTips() {
        List<Tip> tips = new ArrayList<>();
        for (Book book : bookDao.list()) {
            Tip tip = new Tip();
            tip.setId(book.getId());
            tip.setType("/books/");
            tip.setText(book.toString());
            tips.add(tip);
        }
        return tips;
    }

    /**
     * Get tips from blogs in database.
     */
    private List<Tip> getBlogTips() {
        List<Tip> tips = new ArrayList<>();
        for (Blog blog : blogDao.list()) {
            Tip tip = new Tip();
            tip.setId(blog.getId());
            tip.setType("/blogs/");
            tip.setText(blog.toString());
            tips.add(tip);
        }
        return tips;
    }

}
