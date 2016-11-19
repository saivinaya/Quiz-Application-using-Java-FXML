/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 *
 * @author Group
 */
public class User {
    private String loginName;
    private String userName;
    private String password;
    private String uniRole;

    public User(String loginName, String userName, String password, String uniRole) {
        this.loginName = loginName;
        this.userName = userName;
        this.password = password;
        this.uniRole = uniRole;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUniRole() {
        return uniRole;
    }  
}
