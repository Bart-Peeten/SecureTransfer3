package Services.services.AES;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by peetenbart on 20-03-17.
 */


public class AESDecryption extends Service {


    public AESDecryption() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*
    public static byte[] DecryptAES(byte[] encryptedBytes, Key key){
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            decodedBytes = c.doFinal(encryptedBytes);
        } catch (Exception e) {
            Log.e(TAG, "AES encryption error");
        }

        return decodedBytes;
    }
    */

    public static void decrypt(String encryptedString, String desKey) {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.decode(desKey, Base64.DEFAULT);
        // rebuild key using SecretKeySpec
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        try {
            Cipher dcipher;
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, key);

            // decode with base64 to get bytes

            byte[] dec = Base64.decode(encryptedString.getBytes(), Base64.DEFAULT);

            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset

            String decStr = new String(utf8);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
