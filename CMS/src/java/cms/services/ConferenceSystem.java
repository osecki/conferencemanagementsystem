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
     * Input:  A ConferenceSystem Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the ConferenceSystem database table to create a ConferenceSystem. It will return a
     * boolean based on whether it is successful or not.
    */
    public boolean addConference (Conference c);

    public ArrayList<Conference> getAvailableConferences();

    public Conference getConferenceByID(int conferenceID);

    public ArrayList<Conference> getAllConferences();
}
