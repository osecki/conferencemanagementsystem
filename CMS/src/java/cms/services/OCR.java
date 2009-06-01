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
public interface OCR
{
    /*
     * Method:  invokeOCR
     * Input:  Paper Object
     * Output:  Paper Object
     * Algorithm:  The method will take in the Paper object and send off the file
     * to the off the shelf OCR service. It will return hopefully a text file,
     * which will be opened and parsed for its keywords and abstract. This
     * information will be put into the Paper object and that one will be returned.
    */
    @WebMethod
    public Paper invokeOCR(@WebParam Paper p);
}
