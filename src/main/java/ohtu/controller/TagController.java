
package ohtu.controller;

import java.util.List;
import ohtu.dao.TagDao;
import ohtu.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    @RequestMapping(method = RequestMethod.GET)
    public String koe(Model model) {
        return "redirect:/newtag";
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
