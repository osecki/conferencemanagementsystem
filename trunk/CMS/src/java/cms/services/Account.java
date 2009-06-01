package cms.services;

import cms.entities.Conference;
import cms.entities.User;
import java.util.ArrayList;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

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
    public boolean createAccount (String username, String userRole, String fullname, String emailaddress, String password);
    
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
    public String resetPassword (String userName, String fullName, String emailAddress);

    /*
     * Method:  assignEditor
     * Input:  A Conference name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean assignEditor (Conference conference, String editorName);

    /*
     * Method:  assignReviewer
     * Input:  A Paper name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean assignReviewer (String editorName, String paperName);

    /*
     * Method:  releaseToAuthor
     * Input:  A paper name and author name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean releaseToAuthor (String paperName, String authorName);

    public ArrayList<User> getAvailableEditors();
}
