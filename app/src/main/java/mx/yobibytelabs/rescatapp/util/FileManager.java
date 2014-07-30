package mx.yobibytelabs.rescatapp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jagspage2013 on 30/07/14.
 */
public class FileManager {

    public static File getFileFromInput(InputStream inputStream) {
        File photo = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        OutputStream outputStream =null;

        try{
            outputStream  = new FileOutputStream("/Dogsom/tmp.jpg");

            while((read = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,read);
            }

        }catch (IOException E){
            E.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return photo;
    }
}
