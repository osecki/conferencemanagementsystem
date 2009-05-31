package cms.services;
import cms.entities.Conference;
import cms.entities.User;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */
@WebService(targetNamespace = "http://localhost:8080")
public interface Account
{
    /*
     * Method:  createAccount
     * Input:  A user's username, password, full name, and e-mail address, and
     * a user role
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the correct User database table to create an author account. It will return a
     * boolean based on whether it is successful or not.
    */
    @WebMethod
    public boolean createAccount (@WebParam String username, @WebParam String userRole, @WebParam String fullname, @WebParam String emailaddress, @WebParam String password);
    
    /*
     * Method:  login
     * Input:  A user's username and password
     * Output:  String
     * Algorithm:  The method will take in this information and check to see if
     * it is in any of the user role databases. If it is a correct login, it will
     * return the name of user type for the system to handle it. It will return
     * "Error" if there was a problem and "Incorrect" if the credentials don't
     * exist.
    */
//    public String login (String username, String password);

    /*
     * Method:  resetPassword
     * Input:  A user's username and email address
     * Output:  String
     * Algorithm:  The method will take in this information and if it matches an
     * entry into the database, it will assign a new unique password for this
     * username using the ID in the database table to ensure its uniqueness. The
     * new password will be returned in this String. It will return "Error" if
     * there was a problem.
    */
    @WebMethod
    public String resetPassword (@WebParam String userName, @WebParam String fullName, @WebParam String emailAddress);

    /*
     * Method:  assignEditor
     * Input:  A Conference name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    @WebMethod
    public boolean assignEditor (@WebParam Conference conference, @WebParam String editorName);

    /*
     * Method:  assignReviewer
     * Input:  A Paper name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    @WebMethod
    public boolean assignReviewer (@WebParam String editorName, @WebParam String paperName);

    /*
     * Method:  releaseToAuthor
     * Input:  A paper name and author name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    @WebMethod
    public boolean releaseToAuthor (@WebParam String paperName, @WebParam String authorName);

    /*
     * Method:  getAvailableEditors()
     * Input:  None
     * Output:  Arraylist
     * Algorithm:  The method return an arraylist of editors that do not have a conference
    */
    @WebMethod
    public ArrayList<User> getAvailableEditors();
}
