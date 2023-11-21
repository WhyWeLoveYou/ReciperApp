package com.example.myapplication.database;

public class users {

    // variables for storing our data.
    private String Usersname, UsersEmail, UsersPic;

    public users() {

    }
    public users(String Usersname, String UsersEmail, String UsersPic) {
        this.Usersname = Usersname;
        this.UsersEmail = UsersEmail;
        this.UsersPic = UsersPic;
    }

    public String getUsersname() {
        return Usersname;
    }

    public void setUsersname(String Usersname) {
        this.Usersname = Usersname;
    }

    public String getUsersEmail() {
        return UsersEmail;
    }

    // setter method for all variables.
    public void setUsersEmail(String UsersEmail) {
        this.UsersEmail = UsersEmail;
    }

    public String getUsersPic() {
        return UsersPic;
    }

    public void setUsersPic(String UsersPic) {
        this.UsersPic = UsersPic;
    }
}
