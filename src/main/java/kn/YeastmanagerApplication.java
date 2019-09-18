package kn;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.itextpdf.text.DocumentException;

import kn.pdfProcessor.PdfService;

@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class })
public class YeastmanagerApplication {
	
	public static void main(String[] args) throws DocumentException, IOException {
		SpringApplication.run(YeastmanagerApplication.class, args);
		System.out.println("started");
//		AppendPdf appendPdf = new AppendPdf();
//		appendPdf.append();
	}

}
