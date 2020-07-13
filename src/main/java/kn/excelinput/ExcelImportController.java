package kn.excelinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;

@Controller
public class ExcelImportController {
	@Autowired
	private ExcelImportService excelImportService;
	
	@PostMapping("/uploadexcel")
	public String uploadExcel(Model model, @RequestParam("file") MultipartFile file) throws IOException {
		InputStream in = file.getInputStream();
	    File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    String fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
	    FileOutputStream f = new FileOutputStream(fileLocation);
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
	    model.addAttribute("message", "File: " + file.getOriginalFilename() 
	      + " has been uploaded successfully!");
	    
	    excelImportService.excelToDatabase(file);
	    
	    return "success";
	}
	
	@GetMapping("/downloadsample")
	public ResponseEntity<byte[]> downloadsample() throws DocumentException, IOException {
//    	FileInputStream in = new FileInputStream("src/sample.xlsx");
//    	byte[] output = in.readAllBytes();
//    	
//    	
    	 File file = new File("src/sample.xlsx");
    	  //init array with file length
    	  byte[] output = new byte[(int) file.length()]; 

    	  FileInputStream fis = new FileInputStream(file);
    	  fis.read(output); //read file into bytes[]
    	  fis.close();
    	//
    	 HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    	    // Here you have to set the actual filename of your pdf
    	    String filename = "sample.xlsx";
    	    headers.setContentDispositionFormData(filename, filename);
    	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    	    
    	//
    	
    	ResponseEntity<byte[]> response = new ResponseEntity<>(output, headers, HttpStatus.OK);
        return response;
    }
}
