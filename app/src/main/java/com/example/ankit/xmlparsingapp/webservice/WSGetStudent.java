package com.example.ankit.xmlparsingapp.webservice;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ANKIT on 8/20/2016.
 */
public class WSGetStudent {
    public WSGetStudent(Context context) {
    }

    public String executeWebservice() {
        final String url = "http://queenskitchen.in/app/farali_app.php?dt=1471408989&l=e";
        return WebService.getMethod(url);
    }

}
