package Services.services.SHA;

/**
 * Created by peetenbart on 22-03-17.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.security.MessageDigest;

import static android.content.ContentValues.TAG;

public class GenerateHash extends Service {
    public GenerateHash() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public static byte[] CreateHash(String message) {
        byte[] hashedBytes = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(message.getBytes());
            hashedBytes = digest.digest();


        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            return hashedBytes;
        }
    }
}

