package cms.services;
import cms.entities.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */
@WebService(targetNamespace = "http://localhost:8080")
public interface FileSystem
{
    /*
     * Method:  uploadPaper
     * Input:  A Paper object, with a name, abstract, keywords, conference,
     * and file path
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the Paper database. It will return a boolean signifying if it was a
     * success or not.
    */
    @WebMethod
    public boolean uploadPaper (@WebParam Paper p);

    /*
     * Method:  downloadPaper
     * Input:  Paper name
     * Output:  Paper Object
     * Algorithm:  The method will take in this information and check the Paper
     * table for a paper with this name. It will return a Paper object with all
     * of its information.
    */
    @WebMethod
    public Paper downloadPaper (@WebParam String paperName);
}
