package cms.entities;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class Conference
{
    private String name;
    private String location;
    private int eventDate;
    private int dueDate;

    public Conference (String n, String l, int ed, int dd)
    {
        name = n;
        location = l;
        eventDate = ed;
        dueDate = dd;
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
    public int getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(int eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the dueDate
     */
    public int getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }
}
