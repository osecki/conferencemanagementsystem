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
    public HashMap<Paper, Feedback> listFromConference (String conferenceName, int feedbackFlag);

    /*
     * Method:  listAssignedToReviewer
     * Input:  A Reviewer name
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers assigned to this author.
    */
    public HashMap<Paper, Feedback> listAssignedToReviewer (String reviewerName);

    /*
     * Method:  listFromAuthor
     * Input:  An Author name and Feedback flag
     * Output:  Vector<Paper>
     * Algorithm:  The method will take in this information and return a vector
     * of paper objects containing all of the papers by an Author, or all
     * of the papers by an Author with feedback, depending on the flag
    */
    public HashMap<Paper, Feedback> listFromAuthor (String authorName, int feedbackFlag);
}
