/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.data;
import cms.entities.Paper;
import cms.entities.Feedback;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

            //File file = paper.getInputFile();
            InputStream inputStream;

            try {
                inputStream = paper.getInputStream();
                ps.setBinaryStream(5, inputStream, (int)(paper.getSizeInBytes()));
                ps.setInt(6, (int)paper.getSizeInBytes());
            } catch (Exception ex) {
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

    public static Paper getPaper(String paperName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paper paper = new Paper();

        String query = "SELECT * FROM User WHERE PaperName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, paperName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConferenceID(rs.getInt(3));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));
            }
            return paper;
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

    public static HashMap<Paper, Feedback> getListForConference(String conferenceName, int feedbackFlag)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if ( feedbackFlag == 1)
        {
            String query = "SELECT * FROM Paper p inner join Feedback f on p.PaperID = f.PaperID WHERE p.ConferenceID = ?";

            try
            {
                ps = connection.prepareStatement(query);
                ps.setInt(1, ConferenceDB.getConference(conferenceName).getConferenceID());
                rs = ps.executeQuery();
                HashMap<Paper, Feedback> map = new HashMap<Paper, Feedback>();
                while (rs.next())
                {
                    Paper paper = new Paper();
                    paper.setPaperID(rs.getInt(1));
                    paper.setPaperName(rs.getString(2));
                    paper.setConferenceID(rs.getInt(3));
                    paper.setPaperAbstract(rs.getString(4));
                    paper.setPaperKeywords(rs.getString(5));
                    paper.setInputStream(rs.getBinaryStream(6));
                    paper.setSizeInBytes(rs.getInt(7));
                    paper.setAuthorName(rs.getString(8));
                    paper.setFileName(rs.getString(9));

                    Feedback feedback = new Feedback();
                    feedback.setFeedbackID(rs.getInt(1));
                    feedback.setPaperID(rs.getInt(2));
                    feedback.setReviewerName(rs.getString(3));
                    feedback.setContentRate(rs.getInt(4));
                    feedback.setInnovativeRate(rs.getInt(5));
                    feedback.setQualityRate(rs.getInt(6));
                    feedback.setDepthRate(rs.getInt(7));
                    feedback.setCommentsBox(rs.getString(8));

                    map.put(paper, feedback);
                }
                return map;
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
        else
        {
            String query = "SELECT * FROM Paper p inner join Feedback f on p.PaperID = f.PaperID WHERE p.ConferenceID = ?";

            try
            {
                ps = connection.prepareStatement(query);
                ps.setInt(1, ConferenceDB.getConference(conferenceName).getConferenceID());
                rs = ps.executeQuery();
                HashMap<Paper, Feedback> map = new HashMap<Paper, Feedback>();
                while (rs.next())
                {
                    Paper paper = new Paper();
                    paper.setPaperID(rs.getInt(1));
                    paper.setPaperName(rs.getString(2));
                    paper.setConferenceID(rs.getInt(3));
                    paper.setPaperAbstract(rs.getString(4));
                    paper.setPaperKeywords(rs.getString(5));
                    paper.setInputStream(rs.getBinaryStream(6));
                    paper.setSizeInBytes(rs.getInt(7));
                    paper.setAuthorName(rs.getString(8));
                    paper.setFileName(rs.getString(9));

                    map.put(paper, null);
                }
                return map;
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

    public static HashMap<Paper, Feedback> getListForReviewer(String reviewerName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Paper p inner join Feedback f on p.PaperID = f.PaperID WHERE f.ReviewerUserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, reviewerName);
            rs = ps.executeQuery();
            HashMap<Paper, Feedback> map = new HashMap<Paper, Feedback>();
            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConferenceID(rs.getInt(3));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(1));
                feedback.setPaperID(rs.getInt(2));
                feedback.setReviewerName(rs.getString(3));
                feedback.setContentRate(rs.getInt(4));
                feedback.setInnovativeRate(rs.getInt(5));
                feedback.setQualityRate(rs.getInt(6));
                feedback.setDepthRate(rs.getInt(7));
                feedback.setCommentsBox(rs.getString(8));

                map.put(paper, feedback);
            }
            return map;
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

    public static HashMap<Paper, Feedback> getListForAuthor (String authorName, int feedbackFlag)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if ( feedbackFlag == 1)
        {
            String query = "SELECT * FROM Paper p inner join Feedback f on p.PaperID = f.PaperID WHERE p.AuthorUserName = ?";

            try
            {
                ps = connection.prepareStatement(query);
                ps.setString(1, authorName);
                rs = ps.executeQuery();
                HashMap<Paper, Feedback> map = new HashMap<Paper, Feedback>();
                while (rs.next())
                {
                    Paper paper = new Paper();
                    paper.setPaperID(rs.getInt(1));
                    paper.setPaperName(rs.getString(2));
                    paper.setConferenceID(rs.getInt(3));
                    paper.setPaperAbstract(rs.getString(4));
                    paper.setPaperKeywords(rs.getString(5));
                    paper.setInputStream(rs.getBinaryStream(6));
                    paper.setSizeInBytes(rs.getInt(7));
                    paper.setAuthorName(rs.getString(8));
                    paper.setFileName(rs.getString(9));

                    Feedback feedback = new Feedback();
                    feedback.setFeedbackID(rs.getInt(1));
                    feedback.setPaperID(rs.getInt(2));
                    feedback.setReviewerName(rs.getString(3));
                    feedback.setContentRate(rs.getInt(4));
                    feedback.setInnovativeRate(rs.getInt(5));
                    feedback.setQualityRate(rs.getInt(6));
                    feedback.setDepthRate(rs.getInt(7));
                    feedback.setCommentsBox(rs.getString(8));

                    map.put(paper, feedback);
                }
                return map;
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
        else
        {
            String query = "SELECT * FROM Paper p inner join Feedback f on p.PaperID = f.PaperID WHERE p.AuthorUserName = ?";

            try
            {
                ps = connection.prepareStatement(query);
                ps.setString(1, authorName);
                rs = ps.executeQuery();
                HashMap<Paper, Feedback> map = new HashMap<Paper, Feedback>();
                while (rs.next())
                {
                    Paper paper = new Paper();
                    paper.setPaperID(rs.getInt(1));
                    paper.setPaperName(rs.getString(2));
                    paper.setConferenceID(rs.getInt(3));
                    paper.setPaperAbstract(rs.getString(4));
                    paper.setPaperKeywords(rs.getString(5));
                    paper.setInputStream(rs.getBinaryStream(6));
                    paper.setSizeInBytes(rs.getInt(7));
                    paper.setAuthorName(rs.getString(8));
                    paper.setFileName(rs.getString(9));

                    map.put(paper, null);
                }
                return map;
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
}
