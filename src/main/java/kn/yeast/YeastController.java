package kn.yeast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YeastController {
	@Autowired
	private YeastService yeastService;
	
	@RequestMapping("/all")
	public List<Master> getAll(){
		return yeastService.getAll();
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping("/addstrain")
    public String greetingForm(Model model) {
    	Master newStrain = new Master();
        model.addAttribute("strain", newStrain);
        return "greeting";
    }
	
	@PostMapping("/addstrain")
	public String addStrain(@ModelAttribute Master strain) {	
		System.out.println(strain.toString());
		System.out.println(strain.getId());
		try {
			yeastService.addStrain(strain);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "success";
		
	}
}
