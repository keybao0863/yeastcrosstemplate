package kn.datatables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kn.yeast.Master;
import kn.yeast.YeastService;

@Controller
public class DataTableController {
	@Autowired
	private YeastService yeastService;
	
	@RequestMapping(value = {"/table"}, method = RequestMethod.GET)
	public String table(Model model)
    {
        List<Master> strains = new ArrayList<>(100);
        for (int i = 0; i < 100; i++)
        {
            Master strain = new Master();
            strain.setName("testing");
            strain.setMat("smt0");
            strain.setLeu1("leu1");
            strain.setAdditionalGenotype("ha");
            strains.add(strain);
        	  
        	  
        }
        
        model.addAttribute("strains", strains);
        return "datatable";
    }
}
