package cms.services;

import cms.entities.Conference;
import java.util.ArrayList;

/*
 * Project:  ConferenceSystem Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public interface ConferenceSystem
{
    /*
     * Method:  addConference
     * Input:  A Conference Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the ConferenceSystem database table to create a ConferenceSystem. It will return a
     * boolean based on whether it is successful or not.
    */
    public boolean addConference (Conference c);

    /*
     * Method:  getAvailableConferences
     * Input:  None
     * Output:  Arraylist of conferences
     * Algorithm:  The method will return an arraylist of all conferences available for
     * an editor to be assigned to.
    */
    public ArrayList<Conference> getAvailableConferences();

    /*
     * Method:  getConferenceByID
     * Input:  An id
     * Output:  Conference object
     * Algorithm:  The method will take in the id and return a Conference object.
    */
    public Conference getConferenceByID(int conferenceID);

    /*
     * Method:  getAllConferences
     * Input:  None
     * Output:  Arraylist of conferences
     * Algorithm:  The method will return an arraylist of all conferences.
    */
    public ArrayList<Conference> getAllConferences();
}
