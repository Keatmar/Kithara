package com.example.ntinos.kithara;


public class Application extends android.app.Application {

    private boolean isLogin;
    private String username = null;
    private String password = null;
    private String email = null;
    byte[] image = null;


    public boolean returnLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean _isLogin) {
        this.isLogin = _isLogin;
    }

    //User session method
    public void setUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
