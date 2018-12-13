
package ohtu.controller;

import ohtu.dao.TagDao;
import ohtu.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/newtag")
public class TagController {
    
    private TagDao tDao;
    
    @Autowired
    public TagController(TagDao tagdao) {
        this.tDao = tagdao;
    }
    
    @GetMapping("")
    public String redir(Tag tag) {
        return "new_tag";
    }
    
    @PostMapping(value = "")
    public String addTag(@ModelAttribute Tag tag) {
        tDao.add(tag);
        return "new_tag";
    }
}