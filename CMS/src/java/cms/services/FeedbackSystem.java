package cms.services;
import cms.entities.Feedback;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */
@WebService(targetNamespace = "http://localhost:8080")
public interface FeedbackSystem
{
    /*
     * Method:  send
     * Input:  Feedback Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the Feedback database table to create a Feedback entry. It will return a
     * boolean based on whether it is successful or not.
    */
    @WebMethod
    public boolean send (@WebParam Feedback f);

    /*
     * Method:  edit
     * Input:  Feedback Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and check it against
     * what is already stored for this feedback entry, and update any fields if
     * necessary. It will return a boolean reflecting the operation's success.
    */
    @WebMethod
    public boolean edit (@WebParam Feedback Object);

    /*
     * Method:  receive
     * Input:  A paper name and reviewer name
     * Output:  Feedback Object
     * Algorithm:  The method will take in this information and if it matches an
     * entry in the database, it will grab the information from this entry, build
     * a Feedback object, and return it.
    */
    @WebMethod
    public Feedback receive (@WebParam String paperName, @WebParam String reviewerName);
}
