package com.example.nbateamsactivity.api;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MockClient implements Interceptor {

    Context context;

    public MockClient(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        HttpUrl url = chain.request().url();
        //here determine what to do base on url.
        //e.g.:

                    String response = loadJSONFromAsset(context);

                return new Response.Builder()
                        .code(200)
                        .message(response)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .body(ResponseBody.create(MediaType.parse("application/json"), response.getBytes()))
                        .addHeader("content-type", "application/json")
                        .build();
        }


    public String loadJSONFromAsset( Context context ) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("input.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
