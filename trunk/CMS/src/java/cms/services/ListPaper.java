package cms.services;
import java.util.Vector;
import cms.entities.*;
import java.util.HashMap;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

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
    public HashMap<Paper, Vector<Feedback>> listFromConference (String conferenceName, int feedbackFlag);

    /*
     * Method:  listAssignedToReviewer
     * Input:  A Reviewer name
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers assigned to this author.
    */
    public HashMap<Paper, Vector<Feedback>> listAssignedToReviewer (String reviewerUserName);

    /*
     * Method:  listFromAuthor
     * Input:  An Author name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a HashMap
     * of paper and feedback objects containing all of the papers by an Author, with
     * appropriate feedback if the editor has released it to them
    */
    public HashMap<Paper, Vector<Feedback>> listFromAuthor (String authorUserName);
}
