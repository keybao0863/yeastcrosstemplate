package kn.datatables;

import org.springframework.stereotype.Service;

import kn.yeast.Master;
@Service
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MockStrainList ans = new MockStrainList();
		
		for (Master master:ans.getStrainList()) {
			System.out.println(master.toString());
		}
	}

}
