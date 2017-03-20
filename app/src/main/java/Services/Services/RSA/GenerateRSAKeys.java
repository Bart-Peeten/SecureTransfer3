package Services.services.RSA;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateRSAKeys extends Service {

    static final String TAG = "RSAKeys";

    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void CreateRSAKeys(){
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
        } catch (Exception e) {
            Log.e(TAG, "RSA key pair error");
        }
    }


    public static PrivateKey getPrivateKey(){
        return privateKey;
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }
}
