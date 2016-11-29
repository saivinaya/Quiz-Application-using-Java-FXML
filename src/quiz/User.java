
package quiz;

/**
 * User: This class contains all the fields and methods that would be used by 
 * User
 * @author Group
 */
public class User {
    private String loginName;
    private String userName;
    private String password;
    private String uniRole;

    /**
     * User: Constructor of this class
     * @param loginName
     * @param userName
     * @param password
     * @param uniRole
     */
    public User(String loginName, String userName, String password, String uniRole) {
        this.loginName = loginName;
        this.userName = userName;
        this.password = password;
        this.uniRole = uniRole;
    }//end of User

    /**
     * getLoginName: This method returns the login name
     * @return
     */
    public String getLoginName() {
        return loginName;
    }//end of getLoginName

    /**
     * getUserName: This methods returns the user name
     * @return
     */
    public String getUserName() {
        return userName;
    }//end of getUserName

    /**
     * getPassword: This method returns the password of the user
     * @return
     */
    public String getPassword() {
        return password;
    }//end of getPassword

    /**
     * getUniRole: THis method returns the university role
     * @return
     */
    public String getUniRole() {
        return uniRole;
    } //end of getUniRole
}
