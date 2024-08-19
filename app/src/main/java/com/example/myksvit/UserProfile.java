package com.example.myksvit;

public class UserProfile {

    public String userphoneno;
    public String userEmail;
    public String userName;
    public String userrollno;
    public String userbranch;


    public UserProfile(){
    }

    public UserProfile(String userphoneno, String userEmail, String userName, String userrollno,String userbranch) {
        this.userphoneno = userphoneno;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userrollno= userrollno;
        this.userbranch= userbranch;
    }

    public String getUserphoneno() {
        return userphoneno;
    }

    public void setUserphoneno(String userphoneno) {
        this.userphoneno = userphoneno;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserrollno() { return userrollno; }

    public void setUserrollno(String userrollno) { this.userrollno = userrollno; }

    public String getUserbranch() { return userbranch; }

    public void setUserbranch(String userbranch) { this.userbranch = userbranch; }
}
