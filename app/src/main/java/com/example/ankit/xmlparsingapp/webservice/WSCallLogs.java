package com.example.ankit.xmlparsingapp.webservice;

import android.content.Context;
import android.text.TextUtils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

/**
 * Created by indianic on 12/08/16.
 */
public class WSCallLogs {

    private Context context;
    private String message = "";
    private int success = 0;

    public WSCallLogs(Context context) {
        this.context = context;
    }

    public String getMessage() {
        return message;
    }

    public int getSuccess() {
        return success;
    }

    public void executeWebservice(String batteryStatus, String connectiontype) {
        final String url = "http://volanze.com/api/generalinfologs/";
        WebService.callServiceHttpPost(url, generateRequest(batteryStatus, connectiontype, "1"));
    }


    private RequestBody generateRequest(String batteryStatus, String connectiontype, String device) {
        final FormEncodingBuilder requestBody = new FormEncodingBuilder();
        requestBody.add("battery_status", batteryStatus);
        requestBody.add("connection_type", connectiontype);
        requestBody.add("device", device);
        return requestBody.build();

    }
}
