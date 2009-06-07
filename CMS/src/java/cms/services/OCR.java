package cms.services;
import cms.entities.Paper;
import java.io.InputStream;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public interface OCR
{

    public String getTextFromStream(InputStream s);
    public String getTextFromPaper(Paper p);

}
