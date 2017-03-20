package com.example.theappfactory.securetransfer;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

/* Java Imports */
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* Own Imports */
import Services.services.AES.AESDecryption;
import Services.services.AES.AESEncryption;
import Services.services.FileHandlers.FileHandler;
import Services.services.FileHandlers.FileWriter;
import Services.services.RSA.RSADecryption;
import Services.services.SHA.SHA;

import static Services.services.RSA.RSAEncryption.EncryptRSA;
import static java.security.AccessController.getContext;


/**
 * Created by peeten bart on 19-03-17.
 *
 *  There is code which we got by watching youtube video's
 *  Also we searched the web to get knowledge about DES encryption in Java.
 *  There is code from many sites, so I will not mention them all
 *  as there is not one whole specific part of one specific site.
 */


public class DES {

    private static String    fileInputPath = "";
    private static File      encryptedOutputFile;
    private static Cipher    ecipher;
    private static SecretKey key;
    private static String    keyString;

    public DES(){
    }

    public static void init(String filePath) {
        fileInputPath       = filePath.substring(0, filePath.length() - 4);
        encryptedOutputFile = new File(fileInputPath + "_encrypted.txt");

        /** Below code for reading a file is temp. commented out because there is
         *  a bug in this piece of code, it will be solved when there is time left.
         */

        /*BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            this.line = br.readLine();

            while (line != null) {
                sb.append(line);
                //sb.append(System.lineSeparator());
                this.line = br.readLine();
            }
            String textInput = sb.toString();
        } finally {
            br.close();
            }
        */
        try {
            // generate secret key using DES algorithm
            key     = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key

            ecipher.init(Cipher.ENCRYPT_MODE, key);

        }

        catch (NoSuchAlgorithmException e) {

            System.out.println("No Such Algorithm:" + e.getMessage());

            return;

        }

        catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        }
    }

    @SuppressLint("LongLogTag")
    public static void send(){

        //Get message Sender.
        String originalMessage = "Dit is een test voor Security Basic, voor het encrypteren met DES!!!!";

        //SHA message with AESKey
        String encryptedMessage = AESEncryption.encrypt(originalMessage,ecipher);

        //Save encrypted message to Test_encrypted1.txt
        FileWriter.writeToFile(encryptedMessage.toString(), "/storage/emulated/0/Android/data/Test_encrypted1.txt");

        //Get a string from secretkey and write it to a secretkey file.
        Log.d("///////DE DES KEY IS: ", Base64.encodeToString(key.getEncoded(), Base64.DEFAULT));
        keyString = Base64.encodeToString(key.getEncoded(), Base64.DEFAULT);
        FileWriter.writeToFile(keyString, "/storage/emulated/0/Android/data/DESKey.txt");

        //Get public key Receiver
        PublicKey publicKeyReceiver = UserObject.publicKey;

        //Encrypt DESKey with RSA and public key Reciever and save to EncryptedDESKey.txt
        byte[] encryptedAESKeyAsBytes = EncryptRSA(key.getEncoded(), publicKeyReceiver);
        FileWriter.writeToFile(byteToString(encryptedAESKeyAsBytes), "/storage/emulated/0/Android/data/EncryptedDESKey.txt");
        Log.d("/////////DE AES KEY IS: ", byteToString(encryptedAESKeyAsBytes));

        //Hash original message
        byte[] originalHash = new byte[0];
        try {
            originalHash = SHA.toSha(originalMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("//////DE HASH CODE IS: ", byteToString(originalHash));

        //SHA hash with RSA and private key Sender and save to HashCodeFile.txt
        byte[] encryptedHash = EncryptRSA(originalHash, UserObject.privateKey);
        FileWriter.writeToFile(byteToString(originalHash), "/storage/emulated/0/Android/data/HashCodeFile.txt");

    }


    public void Read(){
        File file_1 = new File(getContext().getFilesDir(), getString(R.string.encryptedText_B));
        if (file_1.exists()) {
            //Get private key Alice
            PrivateKey privateKeySender = UserObject.privateKey;

            //Decrypt public key Bob
            byte[] encryptedAESKeyAsBytes = FileHandler.ReadBytesFromFile(getContext(), getString(R.string.encryptedAESKey_B));
            byte[] decryptedAESKeyAsBytes = RSADecryption.DecryptRSA(encryptedAESKeyAsBytes, privateKeySender);

            //Get aesKey Bob with public key Bob
            SecretKeySpec aesKeyReceiver = new SecretKeySpec(decryptedAESKeyAsBytes, "AES");

            //Decrypt File_1 with aesKey Bob
            byte[] encryptedMessage = FileHandler.ReadBytesFromFile(getContext(), getString(R.string.encryptedText_B));
            byte[] originalMessage = (AESDecryption.DecryptAES(encryptedMessage, aesKeyReceiver));

            //bereken hash boodschap
            byte[] calculatedHash = GenerateHash.CreateHash(new String(originalMessage));

            //decrypteer file3 met publicB
            PublicKey publicKey_B = ReadPublicKey(getString(R.string.public_B), context);

            byte[] encryptedHashFromFile = FileHandler.ReadBytesFromFile(getContext(), getString(R.string.hash_B));
            byte[] decryptedHashFromFile = RSADecryption.DecryptRSA(encryptedHashFromFile, publicKey_B);

        }
    }



    @SuppressLint("LongLogTag")
    private static String byteToString(byte[] bytesData) {
        /**
         * This method will convert a byte[] to a string.
         * input:  byte[]
         * output: string
         */
        String decodedDataUsingUTF8 = "";

        decodedDataUsingUTF8 = Base64.encodeToString(bytesData, Base64.NO_WRAP);
        Log.d("Text Decryted using Base64 : ", decodedDataUsingUTF8);

        return decodedDataUsingUTF8;
    }
}
