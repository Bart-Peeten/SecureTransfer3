package Services.services.FileHandlers;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.FileOutputStream;

/**
 * Created by peetenbart on 20-03-17.
 */

public class FileHandler extends Service {
    private static final String TAG = "FileHandler";

    public FileHandler() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    public static void ClearFile(Context context, String fileName){
//        try{
//                context.deleteFile(fileName);
//            Toast.makeText(context, "File created", Toast.LENGTH_SHORT).show();
//            }
//            catch (Exception e){
//                Log.d(TAG, e.getMessage());
//            }
//
//    }




    public static void AppendText(Context context, String fileName, String text){
        try{

            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }




}
