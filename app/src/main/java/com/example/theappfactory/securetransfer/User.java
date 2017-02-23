package com.example.theappfactory.securetransfer;

/**
 * Created by peetenbart on 01-02-17.
 */

public class User {
    private String name;
    private String firstName;
    private String email;
    private String userName;
    private int publicKey;
    private int privateKey;
    private String password;

    //Constructors
    public User() {
    }


    public User(String name, String firstName, String email, String userName, String password) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        setPrivateKey(1);
        setPublicKey(3);

    }

    // Getter and Setters.
    public int getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(int publicKey) {
        this.publicKey = publicKey;
    }

    public int getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(int privateKey) {
        this.privateKey = privateKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
