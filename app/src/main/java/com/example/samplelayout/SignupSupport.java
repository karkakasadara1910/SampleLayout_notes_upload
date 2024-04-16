package com.example.samplelayout;

public class SignupSupport {

    String username,mailId,password,mobile_number,userId;

    public SignupSupport() {
    }


    public SignupSupport(String username,String mailId, String password, String mobile_number,String userId) {
        this.username = username;
        this.mailId = mailId;
        this.password = password;
        this.mobile_number = mobile_number;
        this.userId = userId;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
