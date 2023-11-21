package com.example.myapplication.database;

public class users {

    private String Usersname, UsersEmail, UsersPic, id;

    public users() {

    }
    public users(String id, String Usersname, String UsersEmail, String UsersPic) {
        this.id = id;
        this.Usersname = Usersname;
        this.UsersEmail = UsersEmail;
        this.UsersPic = UsersPic;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
