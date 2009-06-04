package cms.services;

import cms.entities.Feedback;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

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
    public boolean send (Feedback f);

    /*
     * Method:  edit
     * Input:  Feedback Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and check it against
     * what is already stored for this feedback entry, and update any fields if
     * necessary. It will return a boolean reflecting the operation's success.
    */
    public boolean edit (Feedback Object);

    /*
     * Method:  receive
     * Input:  A paper name and reviewer name
     * Output:  Feedback Object
     * Algorithm:  The method will take in this information and if it matches an
     * entry in the database, it will grab the information from this entry, build
     * a Feedback object, and return it.
    */
    public Feedback receive (int paperID, String reviewerName);
}
