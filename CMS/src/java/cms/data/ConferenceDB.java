/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.data;

import cms.entities.Conference;
import cms.entities.User;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Piyush
 */
public class ConferenceDB
{

    public static Conference getConference(int conferenceID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conference conference = new Conference();

        String query = "SELECT * FROM Conference WHERE ConferenceID = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1,conferenceID);
            rs = ps.executeQuery();

            if(rs.next())
            {
                conference.setName(rs.getString(2));
                conference.setLocation(rs.getString(3));
                conference.setEventDate(rs.getDate(4));
                conference.setDueDate(rs.getDate(5));
                conference.setEditor(UserDB.getUser(rs.getString(6)));
            }
            return conference;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int getLastConferenceID()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 1;

        String query = "SELECT MAX(ConferenceID) FROM Conference";

        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if(rs.next())
            {
                i = rs.getInt(1);
            }
            return i;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


    public static Conference getConference(String conferenceName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conference conference = new Conference();

        String query = "SELECT * FROM Conference WHERE ConferenceName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1,conferenceName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                conference.setName(rs.getString(2));
                conference.setLocation(rs.getString(3));
                conference.setEventDate(rs.getDate(4));
                conference.setDueDate(rs.getDate(5));
                conference.setEditor(UserDB.getUser(rs.getString(6)));
            }
            return conference;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean addConference(Conference conference)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean isNullEditor = false;
        String preparedQuery;
        User editor = null;

        try
        {
            editor = conference.getEditor();
        }
        catch(NullPointerException e)
        {
            isNullEditor = true;
        }

        if(isNullEditor)
        {
            preparedQuery = "INSERT INTO conference (ConferenceName, ConferenceLocation, EventDate, DueDate) VALUES (?, ?, ?, ?)";
        }
        else
        {
            preparedQuery = "INSERT INTO conference (ConferenceName, ConferenceLocation, EventDate, DueDate, EditorUserName) VALUES (?, ?, ?, ?, ?)";
        }

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, conference.getName());
            ps.setString(2, conference.getLocation());
            ps.setDate(3, conference.getEventDate());
            ps.setDate(4, conference.getDueDate());
            if(!isNullEditor)
            {
                ps.setString(5, editor.getUserName());
            }
            return ps.executeUpdate()==1;
        }
         catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean assignEditorToConference(Conference conference, User editor)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String preparedQuery;        

        preparedQuery = "UPDATE conference SET EditorUserName = ? WHERE ConferenceID = ?";
        
        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, editor.getUserName());
            ps.setInt(2, conference.getConferenceID());
            return ps.executeUpdate()==1;
        }
         catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


    public static ArrayList<Conference> getAvailableConferences()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Conference WHERE EditorUserName is null";

        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Conference> conferences = new ArrayList<Conference>();
            while (rs.next())
            {
                //Create a User object
                Conference conference = new Conference();

                conference.setName(rs.getString(2));
                conference.setLocation(rs.getString(3));
                conference.setEventDate(rs.getDate(4));
                conference.setDueDate(rs.getDate(5));
//                conference.setEditor(UserDB.getUser(rs.getString(6)));

                conferences.add(conference);
            }
            return conferences;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

}
