/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.data;

import cms.entities.Conference;
import cms.entities.User;
import java.sql.*;

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

        preparedQuery = "UPDATE conference SET EditorUserName = ? WHERE ConferenceName = ?";
        
        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, editor.getUserName());
            ps.setString(2, conference.getName());
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

}
