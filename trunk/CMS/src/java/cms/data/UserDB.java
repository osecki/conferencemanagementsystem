package cms.data;

import cms.entities.User;
import java.sql.*;

public class UserDB
{
//    public static boolean checkUserNamePassword(String userName, String passWord)
//    {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query =
//                "SELECT * FROM User " +
//                "WHERE UserName = ? " +
//                "AND Password = ? ";
//        try
//        {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, userName);
//            ps.setString(2, passWord);
//            rs = ps.executeQuery();
//            return rs.next();
//        }
//        catch(SQLException e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//        finally
//        {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
    
//    public static User getUser(String userName, String passWord)
//    {
//        if(checkUserNamePassword(userName,passWord))
//        {
//            ConnectionPool pool = ConnectionPool.getInstance();
//            Connection connection = pool.getConnection();
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//            User user = new User();
//
//            String query = "SELECT UserTypeID FROM User WHERE UserName = ?";
//
//            try
//            {
//                ps = connection.prepareStatement(query);
//                ps.setString(1,userName);
//                rs = ps.executeQuery();
//
//                if(rs.next())
//                {
//                    user.setUsername(userName);
//                    user.setPassword(passWord);
//                    user.setUserType(rs.getString(1));
//                }
//                return user;
//            }
//            catch(SQLException e)
//            {
//                e.printStackTrace();
//                return null;
//            }
//            finally
//            {
//                DBUtil.closeResultSet(rs);
//                DBUtil.closePreparedStatement(ps);
//                pool.freeConnection(connection);
//            }
//        }
//        else
//        {
//            return null;
//        }
//    }


    public static User getUser(String userName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();

        String query = "SELECT UserTypeName FROM User WHERE UserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1,userName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                user.setUsername(userName);
                user.setUserType(rs.getString(1));
            }
            return user;
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