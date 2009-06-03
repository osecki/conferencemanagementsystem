/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.data;
import cms.entities.Conference;
import cms.entities.Paper;
import cms.entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PaperDB
{
    public static boolean addPaper (Paper paper)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String preparedQuery;

        preparedQuery = "INSERT INTO paper (PaperName, ConferenceID, Abstract, Keywords, PaperBLOB, PaperBLOBSize, AuthorUserName, FileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, paper.getPaperName());
            ps.setInt(2, paper.getConferenceID());
            ps.setString(3, paper.getPaperAbstract());
            ps.setString(4, paper.getPaperKeywords());

            File file = new File(paper.getFileName());
            InputStream inputStream;

            try {
                inputStream = new FileInputStream(file);
                ps.setBinaryStream(5, inputStream, (int)(file.length()));
                ps.setInt(6, (int)file.length());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaperDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ps.setString(7, paper.getAuthorName());
            ps.setString(8, paper.getFileName());

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
                conference.setConferenceID(rs.getInt(1));
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
                Conference conference = new Conference();
                conference.setConferenceID(rs.getInt(1));
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

    public static ArrayList<Conference> getAllConferences()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //TODO Apply logic for Date here: Author can upload only til a certain date
        String query = "SELECT * FROM Conference";

        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Conference> conferences = new ArrayList<Conference>();
            while (rs.next())
            {
                Conference conference = new Conference();
                conference.setConferenceID(rs.getInt(1));
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
