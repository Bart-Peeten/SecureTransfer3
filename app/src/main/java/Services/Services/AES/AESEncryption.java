package Services.services.AES;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Base64;

import javax.crypto.Cipher;

/**
 * Created by peetenbart on 20-03-17.
 */


public class AESEncryption extends Service {

    public AESEncryption() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*

    public static byte[] EncryptAES(byte[] original, Key key){
        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            encodedBytes = c.doFinal(original);
        } catch (Exception e) {
            Log.e(TAG, "AES encryption error");
        }

        return encodedBytes;
    }

    */

    public static String encrypt(String str, Cipher ecipher) {
        byte[] enc;
        String encStr = null;

        try {
            // encode the string into a sequence of bytes using the named charset
            // storing the result into a new byte array.
            byte[] utf8 = str.getBytes("UTF8");
            enc = ecipher.doFinal(utf8);

            // encode to base64
            enc = Base64.encode(enc, Base64.DEFAULT);

            encStr = new String(enc);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return encStr;
    }
}
