package sova.ws.randomjoke;

/**
 * Created by ololo on 12/11/14.
 */
import android.content.Context;
import android.util.Log;

import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;


public class NetApi {

    private String host;
    final String TAG = "MainActivity";

    public void getJson( String url, AsyncHttpResponseHandler handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, handler);

    }



    public String getJoke(String json)
    {
        //Joke joke;
        Log.d(TAG, "Try to parse");
        if( json.equals("")) {
            return "Нет соединения с интернетом";
        }
        json = json.replace("\"content\"", "content");

        Log.d(TAG, json);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //final Joke joke = new Gson().fromJson(json, Joke.class);

        //return joke.content.toString();
        return "";
    }
}
