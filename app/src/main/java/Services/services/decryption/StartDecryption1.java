package Services.services.decryption;

/**
 * Created by peetenbart on 22-03-17.
 */

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;

import Services.services.FileHandlers.FileReader1;

public class StartDecryption1 {

    public PrivateKey getPrivate(String filename, String algorithm) throws Exception {

        byte[] keyBytes = FileReader1.ReadBytesFromFile(filename);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(spec);

    }

    public PublicKey getPublic(String filename, String algorithm) throws Exception {

        byte[] keyBytes = FileReader1.ReadBytesFromFile(filename);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePublic(spec);

    }

    public SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException {

        byte[] keyBytes = FileReader1.ReadBytesFromFile(filename);
        return new SecretKeySpec(keyBytes, algorithm);

    }
}
