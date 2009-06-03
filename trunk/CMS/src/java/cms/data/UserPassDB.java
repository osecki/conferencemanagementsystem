/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.data;

import cms.entities.User;
import cms.services.PwGen;
import java.sql.*;


/**
 *
 * @author Piyush
 */
public class UserPassDB
{

    public static boolean setUserPass(User user, String password)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "INSERT INTO UserPass VALUES (?, ?)";
        PreparedStatement ps = null;

        if(!passwordAlreadyExists(user.getUserName()))
        {
            try
            {
                ps = connection.prepareStatement(preparedQuery);
                ps.setString(1, user.getUserName());
                ps.setString(2, password);
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
        else
        {
            return false;
        }
    }

    public static boolean passwordAlreadyExists(String userName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int x=0;

        String query = "SELECT count(*) FROM UserPass WHERE UserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1,userName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                x = rs.getInt(1);
            }
            return (x!=0);
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
    }

    public static String setRandomPassword(User user)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "UPDATE UserPass SET Password = ? WHERE UserName = ?";
        PreparedStatement ps = null;
        String password = PwGen.getPassword(8);

        try
        {
            //System.out.println(user.getUserName() + "|" + password);
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, password);
            ps.setString(2, user.getUserName());

            if ( ps.executeUpdate()==1 )
                return password;
            else
                return "Error";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "Error";
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
