package kn.excelinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kn.yeast.Master;
import kn.yeast.YeastRepository;
import kn.yeast.YeastService;

@Service
public class ExcelImportService {
	@Autowired
	private YeastService yeastService;
	@Autowired
	private YeastRepository yeastRepository;
	
	private Map<Integer, List<String>> readExcel(MultipartFile mf) throws IOException{
		File f = convert(mf);
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
		    for (int nRow=0; nRow <7; nRow++) {
		    	//if cell is null, create a blank cell
		    	Cell cell = row.getCell(nRow, MissingCellPolicy.CREATE_NULL_AS_BLANK);

		    	if (cell.getCellType()==CellType.STRING || cell.getCellType()==CellType.BLANK) {
		    		data.get(i).add(cell.getStringCellValue());
	    		
		    		
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
	
	private List<Master> excelToStrain(Map<Integer, List<String>> data) {
		List<Master> ret = new ArrayList<Master>();
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
				ret.add(curStrain);
	
			}
		}
		return ret;
	}
	
	//convert multipartfile to file
	private File convert(MultipartFile file) throws IOException
	{    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
	
	public void excelToDatabase(MultipartFile mf) throws IOException {
		Map<Integer, List<String>> data = readExcel(mf);
		List<Master> strainList = excelToStrain(data);
		
		//add strain to database
		for (Master m: strainList) {
			try {
				yeastRepository.save(m);
				System.out.println("strain added" + m.getName());
			}
			catch (Exception e) {
				System.out.println("exception");
			}
		}
	}
}
