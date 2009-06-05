package cms.services;
import java.util.Vector;
import cms.entities.*;
import cms.data.PaperDB;
import java.util.HashMap;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class ListPaperService implements ListPaper
{
    /*
     * Method:  listFromConference
     * Input:  A Conference name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a Hashmap
     * of paper and feedback objects containing all of the papers from this conference.
    */
    public HashMap<Paper, Vector<Feedback>> listFromConference (String conferenceName)
    {
        return PaperDB.getListForConference(conferenceName);
    }

    /*
     * Method:  listFromConferenceToRelease
     * Input:  A Conference name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a Hashmap
     * of paper and feedback objects containing all of the unreleased papers from this conference.
    */
    public HashMap<Paper, Vector<Feedback>> listFromConferenceToRelease (String conferenceName)
    {
        return PaperDB.getListForConferenceToRelease(conferenceName);
    }

    /*
     * Method:  listAssignedToReviewer
     * Input:  A Reviewer name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a Hashmap
     * of paper and feedback objects containing all of the papers assigned to this reviewer.
    */
    public HashMap<Paper, Vector<Feedback>> listAssignedToReviewer (String reviewerUserName)
    {
        return PaperDB.getListForReviewer(reviewerUserName);
    }

    /*
     * Method:  listAssignedToReviewerNoFeedback
     * Input:  A Reviewer name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a Hashmap
     * of paper and feedback objects containing all of the papers assigned to this reviewer
     * that do not yet have feedback.
    */
    public HashMap<Paper, Vector<Feedback>> listAssignedToReviewerNoFeedback (String reviewerUserName)
    {
        return PaperDB.getListForReviewerNoFeedback(reviewerUserName);
    }

    /*
     * Method:  listFromAuthor
     * Input:  An Author name
     * Output:  HashMap<Paper, Vector<Feedback>>
     * Algorithm:  The method will take in this information and return a HashMap
     * of paper and feedback objects containing all of the papers by an Author, with
     * appropriate feedback if the editor has released it to them
    */
    public HashMap<Paper, Vector<Feedback>> listFromAuthor (String authorUserName)
    {
        return PaperDB.getListForAuthor(authorUserName);
    }
}
