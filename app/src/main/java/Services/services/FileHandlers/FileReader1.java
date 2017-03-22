package Services.services.FileHandlers;

import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by peetenbart on 22-03-17.
 */

public class FileReader1 {

    public static byte[] ReadBytesFromFile(String fileName) {
        File file = new File(fileName);
        byte[] bytes = new byte[(int) file.length()];
        try {

            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);

            dis.readFully(bytes);
            dis.close();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            return bytes;
        }
    }
}
