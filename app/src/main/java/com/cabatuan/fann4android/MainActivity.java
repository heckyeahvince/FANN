package com.cabatuan.fann4android;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    static {
        try {
            System.loadLibrary("gnustl_shared");
            System.loadLibrary("fann-test");
        } catch (UnsatisfiedLinkError | Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call the native fann test
        testFann();
    }

    private File loadFile(File dir, int res, String filename) {
        try {
            InputStream is = this.getResources().openRawResource(res);
            File target = new File(dir, filename);
            FileOutputStream os = new FileOutputStream(target);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            return target;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static native void testFann();

}