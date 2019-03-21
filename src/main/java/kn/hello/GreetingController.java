package kn.hello;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.ByteArray;

import kn.pdfProcessor.PdfService;
import kn.yeast.Master;
import kn.yeast.YeastService;

@Controller
public class GreetingController {
	@Autowired
	private YeastService yeastService;
	@Autowired
	private PdfService pdfService;

	
    @GetMapping("/crosstemplate")
    public String greetingForm(Model model) {
    	CrossList crossList = new CrossList();
    	//initiate 6 empty crosses to be filled by user.
    	for (int i = 1; i <= 6; i++) {
           crossList.addCross(new Cross());
        }
        model.addAttribute("cross", crossList);
        return "crossList";
    }

    @PostMapping("/crosstemplate")
    public ResponseEntity<byte[]> greetingSubmit(@ModelAttribute CrossList cross) throws DocumentException, IOException {
    	List<Cross> crossList  = cross.getCrosses();
    	ByteArrayOutputStream outputStream = pdfService.processCrossList(crossList);
    	
    	
    	byte[] output = outputStream.toByteArray();
    	
    	//
    	 HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    	    // Here you have to set the actual filename of your pdf
    	    String filename = "output.pdf";
    	    headers.setContentDispositionFormData(filename, filename);
    	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    	    
    	//
    	    
    	ResponseEntity<byte[]> response = new ResponseEntity<>(output, headers, HttpStatus.OK);
        return response;
    }

}