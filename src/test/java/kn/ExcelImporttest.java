package kn;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import kn.excelinput.ExcelImportService;
import kn.yeast.Master;
import kn.yeast.YeastRepository;


public class ExcelImporttest {
	
	private String excelFile  = "src/sample.xlsx";
	@Autowired
	private YeastRepository yeastRepository;
	
	ExcelImportService excelImportService;
	
	
	@Before
	public void setUp() throws Exception 
	{
		excelImportService = new ExcelImportService();
		System.out.println(yeastRepository.count());
		
	}
	
	@Test
	public void test() throws IOException {
//		MultipartFile f = new Multi
//		Map<Integer, List<String>> data = excelImportService.readExcel(f);
//		excelImportService.excelToStrain(data);
		yeastRepository.save(new Master("br172","smt0","leu1","his2","ura4","ade6-216","aaa"));
	}
	
}
