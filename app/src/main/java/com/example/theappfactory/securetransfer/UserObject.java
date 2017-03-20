package com.example.theappfactory.securetransfer;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by peetenbart on 21-01-17.
 */

public class UserObject{
    // This is a singleton class, so the data also can be used in other classes.
    private String userName;
    private static UserObject userObject = null;
    public static PrivateKey privateKey = null;
    public static PublicKey publicKey = null;

    public UserObject() {

    }

    public static UserObject getUserObject() {
        if (userObject == null) {
            userObject = new UserObject();
        }
        return userObject;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserName() {

        return userName;
    }
}
