package cms.data;

import cms.entities.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDB
{
    public static User getUser(String userName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();

        String query = "SELECT * FROM User WHERE UserName = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1,userName);
            rs = ps.executeQuery();

            if(rs.next())
            {
                user.setUsername(rs.getString(1));
                user.setUserType(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setEmailAddress(rs.getString(4));
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

    public static ArrayList<User> getAvailableEditors()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User U WHERE UserTypeName='EDITOR' AND NOT EXISTS(SELECT * FROM Conference C WHERE C.EditorUserName = U.UserName)";

        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next())
            {
                //Create a User object
                User user = new User();

                user.setUsername(rs.getString(1));
                user.setUserType(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setEmailAddress(rs.getString(4));

                users.add(user);
            }
            return users;
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

    public static User checkUser(String userName, String fullName, String emailAddress)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();

        String query = "SELECT * FROM User WHERE UserName = ? AND fullName = ? AND emailAddress = ?";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1,userName);
            ps.setString(2, fullName);
            ps.setString(3, emailAddress);
            rs = ps.executeQuery();

            if(rs.next())
            {
                user.setUsername(rs.getString(1));
                user.setUserType(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setEmailAddress(rs.getString(4));
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

    public static boolean addUser(User user)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "INSERT INTO User VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try
        {            
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserType());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmailAddress());
            return ps.executeUpdate()==1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("addUser is throwing an exception");
            return false;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  
}