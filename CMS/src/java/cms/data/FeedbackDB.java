/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

package cms.data;

import cms.entities.Feedback;
import java.sql.*;

public class FeedbackDB
{
    public static boolean addFeedbackAssignment(int paperID, String reviewerUserName)
    {
        // First check to see if it is already in there
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Feedback WHERE PaperID = ? and ReviewerUserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, paperID);
            ps.setString(2, reviewerUserName);
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
        String preparedQuery = "INSERT INTO feedback (PaperID, ReviewerUserName, contentRate, innovativeRate, qualityRate, depthRate, commentsBox) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, paperID);
            ps.setString(2, reviewerUserName);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.setString(7, "");
            
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

    public static boolean addActualFeedback (Feedback f)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String preparedQuery;

        preparedQuery = "UPDATE feedback SET contentRate = ?, innovativeRate = ?, qualityRate = ?, depthRate = ?, commentsBox = ? WHERE PaperID = ? and ReviewerUserName = ?";

        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, f.getContentRate());
            ps.setInt(2, f.getInnovativeRate());
            ps.setInt(3, f.getQualityRate());
            ps.setInt(4, f.getDepthRate());
            ps.setString(5, f.getCommentsBox());

            ps.setInt(6, f.getPaperID());
            ps.setString(7, f.getReviewerName());
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

    public static Feedback getFeedback(int paperID, String reviewerName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Feedback feedback = new Feedback();

        String query = "SELECT * FROM Feedback WHERE PaperID = ? and ReviewerUserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, paperID);
            ps.setString(2, reviewerName);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                feedback.setFeedbackID(rs.getInt(1));
                feedback.setPaperID(rs.getInt(2));
                feedback.setReviewerName(rs.getString(3));
                feedback.setContentRate(rs.getInt(4));
                feedback.setInnovativeRate(rs.getInt(5));
                feedback.setQualityRate(rs.getInt(6));
                feedback.setDepthRate(rs.getInt(7));
                feedback.setCommentsBox(rs.getString(8));
            }
            return feedback;
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
