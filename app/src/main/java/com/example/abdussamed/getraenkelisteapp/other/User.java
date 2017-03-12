package com.example.abdussamed.getraenkelisteapp.other;

/**
 * Created by Abdussamed on 1/20/2017.
 *
 *
 *
 * This class is used to make a model of users of the GetraenkelisteApp in a Database.
 */


public class User {
    private int id;  // do we need this?
    private String username;
    private String email;
    private String fname;   //for full name of user
    private String pass;
    private String cardserial;
    private int privilege;

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public User(String username, String email, String fname, String pass, String cardserial,
                int privilege) {
        this.username = username;
        this.email = email;
        this.fname = fname;
        this.pass = pass;
        this.cardserial = cardserial;
        this.privilege = privilege;
    }

    public User(String username, String email, String fname, String pass, int privilege) {
        this.username = username;
        this.email = email;
        this.fname = fname;
        this.pass = pass;
        this.privilege = privilege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String name) {
        this.fname = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCardserial() {
        return cardserial;
    }

    public void setCardserial(String cardserial) {
        this.cardserial = cardserial;
    }
}
