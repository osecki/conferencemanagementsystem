package cms.services;
import java.util.Vector;
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
public interface ListPaper
{
    /*
     * Method:  listFromConference
     * Input:  A Conference name and Feedback flag
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers from this conference, or all
     * of the papers from the conference with feedback, depending on the flag.
    */
    @WebMethod
    public Vector<Paper> listFromConference (@WebParam String conferenceName, @WebParam int feedbackFlag);

    /*
     * Method:  listAssignedToReviewer
     * Input:  A Reviewer name
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers assigned to this author.
    */
    @WebMethod
    public Vector<Paper> listAssignedToReviewer (@WebParam String reviewerName);

    /*
     * Method:  listFromAuthor
     * Input:  An Author name and Feedback flag
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers by an Author, or all
     * of the papers by an Author with feedback, depending on the flag
    */
    @WebMethod
    public Vector<Paper> listFromAuthor (@WebParam String authorName, @WebParam int feedbackFlag);
}
