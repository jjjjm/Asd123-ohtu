
package ohtu.controller;

import ohtu.dao.TagDao;
import ohtu.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author inkeriv
 */

@Controller
@RequestMapping("/tag")
public class TagController {
    
    private TagDao tDao;
    
    @Autowired
    public TagController(TagDao tagdao) {
        this.tDao = tagdao;
    }
    
    @GetMapping(value = "/new")
    public String newTagForm(Tag tag) {
        return "new_tag";
    }
    
    @PostMapping(value = "/new")
    public String addTag(@ModelAttribute Tag tag) {
        tDao.add(tag);
        return "new_tag";
    }
}
