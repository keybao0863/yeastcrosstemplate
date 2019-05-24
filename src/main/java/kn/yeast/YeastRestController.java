package kn.yeast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YeastRestController {
	@Autowired
	private YeastService yeastService;
	
	@RequestMapping(path="/yeasts", method=RequestMethod.GET)
	public List<Master> getAllYeast(){
		return yeastService.getAll();
	}
	
	@RequestMapping(path="/yeasts/{name}", method = RequestMethod.GET)
	public Master getYeastByName(@PathVariable("name") String name) {
		return yeastService.getStrain(name);
	}
}
