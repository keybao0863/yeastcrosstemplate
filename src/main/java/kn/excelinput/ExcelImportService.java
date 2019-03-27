package kn.excelinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kn.yeast.Master;
import kn.yeast.YeastService;

@Service
public class ExcelImportService {
	@Autowired
	private YeastService yeastService;
	
	public Map<Integer, List<String>> readExcel(File f) throws IOException{
		FileInputStream file = new FileInputStream(f);
		Workbook workbook = new XSSFWorkbook(file);
		
		//get first worksheet
		Sheet sheet = workbook.getSheetAt(0);
		
		//initialize data storage in a map.
		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		
		//read each row. Within each row, read each cell and store the string in data.
		for (Row row : sheet) {
						
		    data.put(i, new ArrayList<String>());
		    for (Cell cell : row) {
		    	if (cell.getCellType()==CellType.STRING || cell.getCellType()==CellType.BLANK) {
		    		data.get(i).add(cell.getRichStringCellValue().getString());
		    	}
		    	else {
		    		System.out.println("Wrong type!");
		    	}
		    }	   
		    i++;
		}
		
		workbook.close();
		return data;
	}
	
	public void excelToStrain(Map<Integer, List<String>> data) {
		//iterate through rows of data
		for (int i : data.keySet()) {
			if (i!=0) {
				List<String> curRowData = data.get(i);
				String name = curRowData.get(0);
				String mat = curRowData.get(1);
				String leu1 = curRowData.get(2);
				String his2 = curRowData.get(3);
				String ura4 = curRowData.get(4);
				String ade6 = curRowData.get(5);
				String addgeno = curRowData.get(6);
				//make the strain
				Master curStrain = new Master(name, mat, leu1, his2, ura4, ade6, addgeno);
				System.out.println(curStrain.toString());
				
				
			}
		}
	}
}
