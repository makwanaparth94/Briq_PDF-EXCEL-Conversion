package conversion.project.main;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.TextFragmentAbsorber;
import com.aspose.pdf.TextFragmentCollection;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Transport {

    public static void main(String[] args) throws InvalidPasswordException, IOException {
    	Transport pdf = new Transport();
    	File file = new File("transport.pdf");
    	PDDocument pdfDocument = PDDocument.load(file);
    	String projectName = "PUBLICATION OF AWARD OF CONTRACTS UNDER THE TRANSPORT SECTOR PROJECT (P102000)";
    	//1. Get ProjectName
    	String validateProjectName = pdf.getprojectName(pdfDocument,projectName);
    	
    	//2. Replace project name with null
        pdf.replaceText(projectName, "");
        	
        //3. Convert runtime generated PDF to Excel
        pdf.convertPDF2Excel("Updated_Text.pdf",projectName);
        	
        System.out.println("DONE");
    	
    	
    }
    
	/*
	 * private void convertPDF2ExcelAspose(String string, String projectName) { //
	 * For complete examples and data files, please go to
	 * https://github.com/aspose-pdf/Aspose.Pdf-for-Java Document doc = new
	 * Document("Updated_Text.pdf"); // Initialize ExcelSaveOptions ExcelSaveOptions
	 * options = new ExcelSaveOptions(); // Set output format
	 * options.setFormat(ExcelSaveOptions.ExcelFormat.XLSX); // Save output file
	 * doc.save("ouput.xlsx", options); }
	 */

	public void convertPDF2Excel(String filePath,String projectName) {try
	{
		
    	PrintWriter csvFile = new PrintWriter("upadted_text.csv");
		PDDocument document = PDDocument.load(new File(filePath));// here file1.pdf is the name of pdf file which we want to read....
		document.getClass();
		if (!document.isEncrypted())
		{
			PDFTextStripper Tstripper = new PDFTextStripper();
			String str = Tstripper.getText(document);
			
			Scanner scnLine = new Scanner(str);
			
			String line="";
			String sno="";
			String description="";
			String cno="";
			String method="";
			String contractor="";
			String addr="";
			String amount="";
			String currency="";
			String date="";
			String estimated="";
			String remarks="";

			while (scnLine.hasNextLine()) 
			{		
			try {
				line = scnLine.nextLine();
				System.out.println(line);
				Scanner scnWord = new Scanner(line);	
				sno=scnWord.next();
				description=scnWord.next();
				cno=scnWord.next();
				method=scnWord.next();
				contractor=scnWord.next();
				addr=scnWord.next();
				amount=scnWord.next();
				currency=scnWord.next();
				date=scnWord.next();
				estimated=scnWord.next();
				remarks=scnWord.next();
				csvFile.println(sno+","+description+","+cno+","+contractor+","+addr+","+amount+","+currency+","+date+","+estimated+","+remarks);
			}catch(NoSuchElementException e) {}
			}	
		}
		document.close();
		csvFile.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
		
	}

	public void replaceText(String searchStr,String replaceStr) throws IOException {
    	Document pdfDocument = new Document("transport.pdf");

    	// Create TextAbsorber object to find all instances of the input search phrase
    	TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(searchStr);

    	// Accept the absorber for first page of document
    	pdfDocument.getPages().accept(textFragmentAbsorber);

    	// Get the extracted text fragments into collection
    	TextFragmentCollection textFragmentCollection = textFragmentAbsorber.getTextFragments();

    	// Loop through the fragments
    	for (TextFragment textFragment : (Iterable<TextFragment>) textFragmentCollection) {
    		// Update text and other properties
    		textFragment.setText(replaceStr);
    	}
    	// Save the updated PDF file
    	pdfDocument.save("Updated_Text.pdf");
		/*
		 * PDDocument pddDocument = PDDocument.load(new File("Updated_Text.pdf"));
		 * PDFTextStripper pdfTextStripper = new PDFTextStripper(); String pdfFileInText
		 * = pdfTextStripper.getText(pddDocument); String lines[] =
		 * pdfFileInText.split("\\n"); for (String line : lines) {
		 * System.out.println(line); }
		 */
	}
    
    public String getprojectName(PDDocument pdfDocument,String projectName) throws InvalidPasswordException, IOException {

    	
    	pdfDocument.getClass();
    	String s="";
        if (!pdfDocument.isEncrypted()) {
		
            PDFTextStripperByArea pdfTextStripperByArea = new PDFTextStripperByArea();
            pdfTextStripperByArea.setSortByPosition(Boolean.TRUE);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String pdfFileInText = pdfTextStripper.getText(pdfDocument);
          
            //int count=1;   
            String lines[] = pdfFileInText.split("\\n");
            for (String line : lines) {
            	//System.out.println(count+" "+line);
            	if(line.contains(projectName)) {
            		s = line;
            	}
            	
				/*
				 * if(count == 3) {
				 * 
				 * //System.out.println(s); } count++;
				 */
            }
        }
		return s;
    }

}

