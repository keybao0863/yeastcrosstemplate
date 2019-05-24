package kn;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import kn.datatables.MockStrainList;
import kn.yeast.Master;

public class MockStrainListTest {
	
	
	private MockStrainList mockStrainList;
	@Before
	public void setup() {
		mockStrainList = new MockStrainList();
	}
	
	@Test
	public void showContent() {
		ArrayList<Master> ans = mockStrainList.getStrainList();
		for (Master master:ans) {
			System.out.println(master.toString());
		}
	}
}
