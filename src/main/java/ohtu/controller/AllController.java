package ohtu.controller;

import java.util.List;
import ohtu.dao.AllDao;
import ohtu.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/all")
public class AllController {
    
    private AllDao aDao;
    
    // controller with dependency injection
    @Autowired
    public AllController(AllDao aDao) {
        this.aDao = aDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model) {
        List<Tip> tips = aDao.list();
        model.addAttribute("tips", tips);
        return "all";
    }
}