package cms.services;
import cms.entities.Conference;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * Project:  ConferenceSystem Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */
@WebService(targetNamespace = "http://localhost:8080")
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
    @WebMethod
    public boolean addConference (@WebParam Conference c);

    @WebMethod
    public ArrayList<Conference> getAvailableConferences();

    @WebMethod
    public Conference getConferenceByID(@WebParam int conferenceID);

    @WebMethod
    public ArrayList<Conference> getAllConferences();
}
