package kn.pdfProcessor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import kn.crosstemplate.Cross;
import kn.yeast.Master;
import kn.yeast.YeastService;

import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {
	
//	private Strain strainOne = new Strain("Br172", "smt0","leu1","his2","ura4","ade6-210"," ");
//	private Strain strainTwo = new Strain("SPJ1577", "h+","leu1","","ura4","ade6-210"," ");
	@Autowired
	private YeastService yeastSerivce;
	
	
	private ByteArrayOutputStream append(String firstStrainName, String secondStrainName) throws DocumentException, IOException {
		
		
		
		// Create output PDF
		Document document = new Document(PageSize.A4);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/iTextHelloWorld.pdf"));
		PdfWriter writer = PdfWriter.getInstance(document, output);
		document.open();
		
		PdfContentByte cb = writer.getDirectContent();
		
		// Load existing PDF
		PdfReader reader = new PdfReader("src/cross_template.pdf");
		PdfImportedPage page = writer.getImportedPage(reader, 1); 
		
		// Copy first page of existing PDF into output PDF
		document.newPage();
		cb.addTemplate(page, 0, 0);

		// Add your new data / text here
		// for example... 
		
		//font
		BaseFont bf = BaseFont.createFont("/arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		bf.setSubset(true);
		
		Font f=new Font(bf,9.0f);
		
		
		//get the strains
		Master firstStrain = yeastSerivce.getStrain(firstStrainName);
		Master secondStrain = yeastSerivce.getStrain(secondStrainName);
		
		//adding text and catch if strain is not found
		
		try {
			Paragraph strainOnePara = new Paragraph(100, firstStrain.toString(),f);
			strainOnePara.setAlignment(Element.ALIGN_CENTER);
			document.add(strainOnePara); 
			}
		catch (NullPointerException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strain 1 not found:" + firstStrainName, e);
		}

		
		try {
			Paragraph strainTwoPara = new Paragraph(23, secondStrain.toString(),f);
			strainTwoPara.setAlignment(Element.ALIGN_CENTER);
			document.add(strainTwoPara); 
			}
		catch (NullPointerException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strain 2 not found:" + secondStrainName, e);
		}
		
		
		
		
		document.close();
		
		return output;
	}
/**
 * Get a input of a list of crosses. Process each cross and produce a pdf page
 * Return a multi-page pdf document including all the crosses in the cross list.
 * @param crossList
 * @throws IOException 
 * @throws DocumentException 
 */
	public ByteArrayOutputStream processCrossList(List<Cross> crossList) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		
		//create a master pdf file
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, output);
		document.open();
		
		PdfContentByte cb = writer.getDirectContent();
		
		//process each cross and return a pdf page and append to master pdf file
		for (Cross cross : crossList) {
			//only process if both strain names are provided
			if (!cross.getFirstStrainName().isEmpty() && !cross.getSecondStrainName().isEmpty()) {
				String firstStrainName = cross.getFirstStrainName();
				String secondStrainName = cross.getSecondStrainName();
				//generate one page of cross template using the append helper method
				ByteArrayOutputStream curPage = this.append(firstStrainName, secondStrainName);
				//put the new page in the master pdf document
				PdfReader reader = new PdfReader(curPage.toByteArray());
				PdfImportedPage page = writer.getImportedPage(reader, 1); 
				document.newPage();
				cb.addTemplate(page, 0, 0);
			}
		}
		
		document.close();
		
		//return 
		return output;
		
		
	}
}
