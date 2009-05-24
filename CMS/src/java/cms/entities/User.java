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
    private String username;
    //private String password;
    private String userType;

    public void User()
    {
        
    }
    
    public void User(String username, String userType)
    {
        this.username = username;
        //this.password = password;
        this.userType = userType;
    }
    
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }    
}
