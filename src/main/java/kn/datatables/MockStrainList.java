package kn.datatables;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import kn.yeast.Master;
import kn.yeast.YeastService;

public class MockStrainList {
	private ArrayList<Master> strainList = new ArrayList<Master>();
	
	@Autowired
	private YeastService yeastService;
	
	public MockStrainList() {
		// TODO Auto-generated constructor stub
		
		
		strainList.add(yeastService.getStrain("br172"));
		
	}

	public ArrayList<Master> getStrainList() {
		return strainList;
	}

	public void setStrainList(ArrayList<Master> strainList) {
		this.strainList = strainList;
	}
	
	
}
