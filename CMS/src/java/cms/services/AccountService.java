package cms.services;

import cms.data.ConferenceDB;
import cms.data.UserDB;
import cms.data.UserPassDB;
import cms.data.FeedbackDB;
import cms.data.PaperDB;
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

            // Make sure it is an author
            if ( ! user.getUserType().equals("AUTHOR") )
                return "Error";

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
    public boolean assignReviewer (String reviewerName, int paperID)
    {
        return FeedbackDB.addFeedbackAssignment(paperID, reviewerName);
    }

    /*
     * Method:  releaseToAuthor
     * Input:  A paper name and author name
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter into the
     * database the necessary information to link these two entities together.
     * It will return a boolean signifying how it turned out.
    */
    public boolean releaseToAuthor (int paperID)
    {
        return PaperDB.releaseToAuthor(paperID);
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
