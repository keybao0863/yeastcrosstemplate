package kn.excelinput;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;


public class ExcelImporttest {
	
	private String excelFile  = "src/sample.xlsx";
	
	ExcelImportService excelImportService;
	@Before
	public void setUp() throws Exception 
	{
		excelImportService = new ExcelImportService();
		
	}
	
	@Test
	public void test() throws IOException {
		File f = new File(excelFile);
		Map<Integer, List<String>> data = excelImportService.readExcel(f);
		excelImportService.excelToStrain(data);
		
	}
	
}
