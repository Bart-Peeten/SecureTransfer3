package Services.services.FileHandlers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by peetenbart on 20-03-17.
 */

public class FileReader {

    public static byte[] ReadBytesFromFile(Context context, String fileName){
        File file = new File(context.getFilesDir(),fileName);
        byte[] bytes = new byte[(int)file.length()];
        try{

            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);

            //    bytes = new byte[dis.available()];

            dis.readFully(bytes);
            dis.close();
        } catch (Exception e){
            Log.d("", e.getMessage());
        } finally {
            return bytes;
        }
    }


    public static String ReadFile(Context context, String fileName) {
        StringBuilder finalString = new StringBuilder();

        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String oneLine;

            while ((oneLine=bufferedReader.readLine())!=null){
                finalString.append(oneLine);
            }

            bufferedReader.close();
            inputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            return finalString.toString();
        }
    }
}
