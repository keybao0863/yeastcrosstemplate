package kn.yeast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import kn.pdfProcessor.PdfService;

@Service
public class YeastService {
	@Autowired
	private YeastRepository yeastRepository;
	

	
	public  List<Master> getAll() {
		// TODO Auto-generated method stub
		List <Master> ans = new ArrayList<Master>();
		yeastRepository.findAll().forEach(ans::add);
		return ans;
	}

	public void addStrain(Master s) {
		
		yeastRepository.save(s);
		
		
	}

	public Master getStrain(String strainName) {
		// TODO Auto-generated method stub
		return yeastRepository.findByName(strainName);
	
	}

	
	
}
