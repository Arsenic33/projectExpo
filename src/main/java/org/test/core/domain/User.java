package org.test.core.domain;

public class User {

    private String userName;
    private String password;
    private Integer userType;
    public static final int ADMIN_USER_TYPE = 0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * Checks whether the user is admin user or not
     * @return
     */
    public boolean isAdmin(){
        boolean isAdmin = Boolean.FALSE;
        if(userType != null && userType.equals(ADMIN_USER_TYPE)){
            isAdmin = Boolean.TRUE;
        }
        return isAdmin;
    }

}
