
package ohtu.teststubs;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.TagDao;
import ohtu.model.Tag;

public class TagDaoForTests implements TagDao{

    private List<Tag> tags;

    public TagDaoForTests() {
        tags = new ArrayList<>();
    }
    
    @Override
    public boolean add(Tag tag) {
        tag.setId(tags.size() + 1);
        tags.add(tag);
        return true;
    }

    @Override
    public List<Tag> list() {
        return tags;
    }

    @Override
    public Tag getTag(int id) {
        for(Tag tag :tags) {
            if(tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }
    
    
    
}
