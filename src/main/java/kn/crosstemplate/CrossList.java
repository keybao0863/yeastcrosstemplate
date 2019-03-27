package kn.crosstemplate;

import java.util.ArrayList;
import java.util.List;

public class CrossList {
	private List<Cross> crosses;
	
	public CrossList() {
		crosses = new ArrayList<Cross>();
	}
	
	public void addCross(Cross c) {
		this.crosses.add(c);
	}

	public List<Cross> getCrosses() {
		return crosses;
	}

	public void setCrosses(List<Cross> crosses) {
		this.crosses = crosses;
	}

	
	
}
