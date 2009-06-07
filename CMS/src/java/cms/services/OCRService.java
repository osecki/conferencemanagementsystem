package cms.services;
import cms.entities.Paper;
import java.io.InputStream;
import com.aspose.pdf.kit.PdfExtractor;
import java.io.OutputStream;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class OCRService implements OCR
{

    public String getTextFromStream(InputStream is)
    {
        //Instantiate PdfExtractor object
        PdfExtractor extractor = new PdfExtractor();
//        String path = null;
        OutputStream os = System.out;


        try
        {
            //Bind the input PDF document to extractor
            extractor.bindPdf(is);

            //Extract text from the input PDF document
            extractor.extractText();

            //Save the extracted text to a text file
            extractor.getText(os);
        }
        catch (Exception e)
        {
            return "From within the catch block";
        }

        return "This could be it";
        
    }

    public String getTextFromPaper(Paper p)
    {
        return getTextFromStream(p.getInputStream());
    }

}
