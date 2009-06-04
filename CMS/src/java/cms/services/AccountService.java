package cms.services;

import cms.data.ConferenceDB;
import cms.data.UserDB;
import cms.data.UserPassDB;
import cms.data.FeedbackDB;
import cms.entities.Conference;
import cms.entities.User;
import java.util.ArrayList;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class AccountService implements Account
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
    public boolean createAccount (String username, String userRole, String fullName, String emailAddress, String password)
    {
        User user = new User(username, userRole, fullName, emailAddress);
        boolean a = UserDB.addUser(user);
        boolean b = UserPassDB.setUserPass(user, password);
        return (a && b);
    }

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
//    public String login (String username, String password)
//    {
//        // TODO
//        return "";
//    }

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
    public String resetPassword (String userName, String fullName, String emailAddress)
    {
        User user = null;
        try
        {
            user = UserDB.checkUser(userName, fullName, emailAddress);
            String newPass = UserPassDB.setRandomPassword(user);

            if ( newPass.equals("Error"))
                return "Error";
            else
                return newPass;
        }
        catch(NullPointerException e)
        {
            return "Error";
        }
    }

    /*
     * Method:  assignEditor
     * Input:  A Conference name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean assignEditor (Conference conference, String editorName)
    {
        return ConferenceDB.assignEditorToConference(conference, UserDB.getUser(editorName));
    }

    /*
     * Method:  assignReviewer
     * Input:  A Paper name and Editor name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean assignReviewer (String reviewerName, String paperName)
    {
        return FeedbackDB.addFeedbackAssignment(FeedbackDB.getFeedback(paperName, reviewerName));
    }

    /*
     * Method:  releaseToAuthor
     * Input:  A paper name and author name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean releaseToAuthor (String paperName, String authorName)
    {
        return FeedbackDB.releaseToAuthor(FeedbackDB.getFeedback(paperName, authorName));
    }

    /*
     * Method:  getAvailableEditors
     * Input:  None
     * Output:  Arraylist of Users
     * Algorithm:  The method will return a list of available Editors to match up to conferences
    */
    public ArrayList<User> getAvailableEditors()
    {
        return UserDB.getAvailableEditors();
    }

    /*
     * Method:  getReviewers
     * Input:  None
     * Output:  Arraylist of Users
     * Algorithm:  The method will return a list of Reviewers to match up to papers
    */
    public ArrayList<User> getReviewers()
    {
        return UserDB.getReviewers();
    }
}
