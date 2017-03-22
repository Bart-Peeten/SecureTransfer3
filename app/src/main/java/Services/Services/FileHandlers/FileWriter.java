package Services.services.FileHandlers;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.ContentValues.TAG;

/**
 * Created by peetenbart on 20-03-17.
 */

public class FileWriter {

    @SuppressLint("LongLogTag")
    public static void writeToFile(String data, String filePath) {
        try {
            String encryptedFileOutputFile = filePath;
            Log.d("///////DE OUTPUT FILE STAAT IN: ", encryptedFileOutputFile);
            File encryptedOutputFile = new File(encryptedFileOutputFile);

            encryptedOutputFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(encryptedOutputFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(data);

            outputStreamWriter.close();

            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static void writeBytesToFile(String fileName, byte[] text ){
        try{
            FileOutputStream outputStream = new FileOutputStream(fileName);
            //FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text);
            outputStream.close();
        } catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

}
