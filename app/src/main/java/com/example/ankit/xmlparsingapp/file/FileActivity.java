package com.example.ankit.xmlparsingapp.file;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ankit.xmlparsingapp.R;

import java.io.File;

/**
 * Created by ANKIT on 9/3/2016.
 */
public class FileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isExternalStorageWritable() && isExternalStorageReadable()) {
            createNewFolder();
        }

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(FileActivity.class.getSimpleName(), "Directory not created");
        }
        return file;
    }

    public void createNewFolder() {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name) + "/");

        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
