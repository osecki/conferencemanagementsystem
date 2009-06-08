/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.data;
import cms.entities.Paper;
import cms.entities.Feedback;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaperDB
{
    public static boolean addPaper (Paper paper)
    {
        // First check to see if it is already in there
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Paper WHERE PaperName = ? and ConferenceID = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, paper.getPaperName());
            ps.setInt(2, paper.getConference().getConferenceID());
            rs = ps.executeQuery();

            int count = 0;
            if(rs.next())
            {
                count++;
            }

            if ( count > 0 )
                return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        // Now do actual insert, since it's not in there
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        ps = null;
        String preparedQuery = "INSERT INTO paper (PaperName, ConferenceID, Abstract, Keywords, PaperBLOB, PaperBLOBSize, AuthorUserName, FileName, seeFeedback) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, paper.getPaperName());
            ps.setInt(2, paper.getConference().getConferenceID());
            ps.setString(3, paper.getPaperAbstract());
            ps.setString(4, paper.getPaperKeywords());

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
            ps.setInt(9, 0);

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

        String query = "SELECT * FROM Paper WHERE PaperName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, paperName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
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

    public static boolean releaseToAuthor (int paperID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String preparedQuery;

        preparedQuery = "UPDATE paper SET seeFeedback = ? WHERE PaperID = ?";

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, 1);
            ps.setInt(2, paperID);
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

    public static Paper downloadPaper(int paperID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paper paper = new Paper();

        String query = "SELECT * FROM Paper WHERE PaperID = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, paperID);
            rs = ps.executeQuery();

            if(rs.next())
            {
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
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

    public static HashMap<Paper, Vector<Feedback>> getListForConference(String conferenceName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID WHERE p.ConferenceID = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ConferenceDB.getConference(conferenceName).getConferenceID());
            rs = ps.executeQuery();
            HashMap<Paper, Vector<Feedback>> map = new HashMap<Paper, Vector<Feedback>>();

            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(11));
                feedback.setPaperID(rs.getInt(12));
                feedback.setReviewerName(rs.getString(13));
                feedback.setContentRate(rs.getInt(14));
                feedback.setInnovativeRate(rs.getInt(15));
                feedback.setQualityRate(rs.getInt(16));
                feedback.setDepthRate(rs.getInt(17));
                feedback.setCommentsBox(rs.getString(18));

                if ( map.containsKey(paper) )
                {
                    Vector<Feedback> tempValue = map.get(paper);
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
                else
                {
                    Vector<Feedback> tempValue = new Vector<Feedback> ();
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
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

    public static HashMap<Paper, Vector<Feedback>> getListForConferenceToRelease(String conferenceName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID WHERE p.ConferenceID = ? and p.seeFeedback = 0";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ConferenceDB.getConference(conferenceName).getConferenceID());
            rs = ps.executeQuery();
            HashMap<Paper, Vector<Feedback>> map = new HashMap<Paper, Vector<Feedback>>();

            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(11));
                feedback.setPaperID(rs.getInt(12));
                feedback.setReviewerName(rs.getString(13));
                feedback.setContentRate(rs.getInt(14));
                feedback.setInnovativeRate(rs.getInt(15));
                feedback.setQualityRate(rs.getInt(16));
                feedback.setDepthRate(rs.getInt(17));
                feedback.setCommentsBox(rs.getString(18));

                if ( map.containsKey(paper) )
                {
                    Vector<Feedback> tempValue = map.get(paper);
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
                else
                {
                    Vector<Feedback> tempValue = new Vector<Feedback> ();
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
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

    public static HashMap<Paper, Vector<Feedback>> getListForReviewer(String reviewerName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<Paper, Vector<Feedback>> map = new HashMap<Paper, Vector<Feedback>>();

        try
        {
            String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID WHERE f.ReviewerUserName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, reviewerName);
            rs = ps.executeQuery();
            

            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));
                
                map.put(paper, null);
            }
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
        
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        ps = null;
        rs = null;

        try
        {
            String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(11));
                feedback.setPaperID(rs.getInt(12));
                feedback.setReviewerName(rs.getString(13));
                feedback.setContentRate(rs.getInt(14));
                feedback.setInnovativeRate(rs.getInt(15));
                feedback.setQualityRate(rs.getInt(16));
                feedback.setDepthRate(rs.getInt(17));
                feedback.setCommentsBox(rs.getString(18));

                if ( map.containsKey(paper) )
                {
                    Vector<Feedback> tempValue = map.get(paper);

                    if ( tempValue == null)
                        tempValue = new Vector<Feedback>();

                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
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

    public static HashMap<Paper, Vector<Feedback>> getListForReviewerNoFeedback (String reviewerName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID WHERE f.ReviewerUserName = ? and contentRate = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, reviewerName);
            ps.setInt(2, 0);
            rs = ps.executeQuery();
            HashMap<Paper, Vector<Feedback>> map = new HashMap<Paper, Vector<Feedback>>();

            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(11));
                feedback.setPaperID(rs.getInt(12));
                feedback.setReviewerName(rs.getString(13));
                feedback.setContentRate(rs.getInt(14));
                feedback.setInnovativeRate(rs.getInt(15));
                feedback.setQualityRate(rs.getInt(16));
                feedback.setDepthRate(rs.getInt(17));
                feedback.setCommentsBox(rs.getString(18));

                if ( map.containsKey(paper) )
                {
                    Vector<Feedback> tempValue = map.get(paper);
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
                else
                {
                    Vector<Feedback> tempValue = new Vector<Feedback> ();
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
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

    public static HashMap<Paper, Vector<Feedback>> getListForAuthor (String authorName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            String query = "SELECT * FROM Paper p left join Feedback f on p.PaperID = f.PaperID WHERE p.AuthorUserName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, authorName);
            rs = ps.executeQuery();

            HashMap<Paper, Vector<Feedback>> map = new HashMap<Paper, Vector<Feedback>>();
            while (rs.next())
            {
                Paper paper = new Paper();
                paper.setPaperID(rs.getInt(1));
                paper.setPaperName(rs.getString(2));
                paper.setConference(ConferenceDB.getConference(rs.getInt(3)));
                paper.setPaperAbstract(rs.getString(4));
                paper.setPaperKeywords(rs.getString(5));
                paper.setInputStream(rs.getBinaryStream(6));
                paper.setSizeInBytes(rs.getInt(7));
                paper.setAuthorName(rs.getString(8));
                paper.setFileName(rs.getString(9));

                Feedback feedback = null;

                // Add real feedbakc only if it has been released to the author
                if ( rs.getInt(10) == 1)
                {
                    feedback = new Feedback();
                    feedback.setFeedbackID(rs.getInt(11));
                    feedback.setPaperID(rs.getInt(12));
                    feedback.setReviewerName(rs.getString(13));
                    feedback.setContentRate(rs.getInt(14));
                    feedback.setInnovativeRate(rs.getInt(15));
                    feedback.setQualityRate(rs.getInt(16));
                    feedback.setDepthRate(rs.getInt(17));
                    feedback.setCommentsBox(rs.getString(18));
                }

                // Determine if this paper is already in the map
                if ( map.containsKey(paper) )
                {
                    Vector<Feedback> tempValue = map.get(paper);
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
                else
                {
                    Vector<Feedback> tempValue = new Vector<Feedback> ();
                    tempValue.add(feedback);
                    map.put(paper, tempValue);
                }
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
