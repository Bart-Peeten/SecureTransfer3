package com.example.theappfactory.securetransfer;

/**
 * Created by peetenbart on 09-02-17.
 */

public class Encrypt {

    public byte[] toSha(String string) throws Exception{
        java.security.MessageDigest dig = null;
        dig = java.security.MessageDigest.getInstance("SHA-1");
        dig.reset();
        dig.update(string.getBytes());

        return dig.digest();
    }
}
