package cms.services;
import cms.data.ConferenceDB;
import cms.entities.Conference;
import java.util.ArrayList;

/*
 * Project:  ConferenceSystem Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class ConferenceSystemService implements ConferenceSystem
{
    /*
     * Method:  addConference
     * Input:  A ConferenceSystem Object
     * Output:  Boolean true or false
     * Algorithm:  The method will take in this information and enter it into
     * the ConferenceSystem database table to create a ConferenceSystem. It will return a
     * boolean based on whether it is successful or not.
    */
    public boolean addConference (Conference c)
    {
        return ConferenceDB.addConference(c);
    }

    public ArrayList<Conference> getAvailableConferences()
    {
        return ConferenceDB.getAvailableConferences();
    }

    public ArrayList<Conference> getAllConferences()
    {
        return ConferenceDB.getAllConferences();
    }

    public Conference getConferenceByID(int conferenceID)
    {
        return ConferenceDB.getConference(conferenceID);
    }
}
