/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.teststubs;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.AllDao;
import ohtu.model.Tip;

/**
 *
 * @author tkarkine
 */
public class AllDaoForTests implements AllDao {
    List<Tip> tips;
    
    @Override
    public List<Tip> list() {
        tips = new ArrayList<>();
        return tips;
    }
    
}
