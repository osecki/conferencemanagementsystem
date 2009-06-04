package cms.entities;

import java.io.Serializable;
import java.sql.Date;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class Conference implements Serializable
{
    private int conferenceID;
    private String name;
    private String location;
    private Date eventDate;
    private Date dueDate;
    private User editor;

    public Conference()
    {
        
    }

    public Conference (String n, String l, Date ed, Date dd)
    {
        name = n;
        location = l;
        eventDate = ed;
        dueDate = dd;
    }

    public int getConferenceID()
    {
        return conferenceID;
    }

    public void setConferenceID(int conferenceID)
    {
        this.conferenceID = conferenceID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

}
