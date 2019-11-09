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
		//if already there, update
		if(yeastRepository.findByName(s.getName())!=null) {
			Master toUpdate = yeastRepository.findByName(s.getName());
			toUpdate.setName(s.getName());
			toUpdate.setHis2(s.getHis2());
			toUpdate.setAde6(s.getAde6());
			toUpdate.setLeu1(s.getLeu1());
			toUpdate.setUra4(s.getUra4());
			toUpdate.setMat(s.getMat());
			toUpdate.setAdditionalGenotype(s.getAdditionalGenotype());
			yeastRepository.save(toUpdate);
		}
		
		//else save
		else {
			yeastRepository.save(s);	
		}
		}
		

	public Master getStrain(String strainName) {
		// TODO Auto-generated method stub
		return yeastRepository.findByName(strainName);
	
	}

	
	
}
