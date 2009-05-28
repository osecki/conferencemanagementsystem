/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.entities;

import java.io.Serializable;

/**
 *
 * @author Piyush
 */
public class User implements Serializable
{
    private String userName;
    private UserRole userType;
    private String fullName;
    private String emailAddress;

    public User()
    {
        
    }
    
    public User(String userName, String userType, String fullName, String emailAddress)
    {
        this.userName = userName;
        this.userType = UserRole.valueOf(userType.toUpperCase());
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public String getUserType() {
        return userType.toString();
    }

    public void setUserType(String userType) {
        this.userType = UserRole.valueOf(userType.toUpperCase());
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
