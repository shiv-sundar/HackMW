package com.test.hackmw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.os.StrictMode;
import com.cloudinary.android.MediaManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class getImage extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    Uri uriProfile;
    public String pub_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getPicture();
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        makeRequest();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfile = data.getData();
            Map config = new HashMap();
            config.put("cloud_name", "yotabites-llc");
            MediaManager.init(this, config);
            pub_id = "file" + System.currentTimeMillis();
            String test = MediaManager.get().upload(uriProfile)
                    .unsigned("r9ejzsni")
                    .option("public_id", pub_id)
                    .dispatch();
        }
    }

    public void makeRequest() {
        try {
            String url = "https://wt-36d807a325bb4cdac069a2ebb8a9618f-0.sandbox.auth0-extend.com/auto_tag?public_id=soccer_man";
            URL githubEndpoint = null;
            githubEndpoint = new URL(url);
            HttpsURLConnection myConnection = null;
            myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
            if (myConnection.getResponseCode() == 200) {
                int x = 0;
                Log.v("test", "Success");
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = null;
                responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    ((global) this.getApplication()).keywords[x] = jsonReader.nextString();
                    jsonReader.nextName();
                    jsonReader.nextDouble();
                    x++;
                    jsonReader.endObject();
                    if (x == ((global) this.getApplication()).keywords.length) {
                        break;
                    }
                }

                jsonReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //            RequestQueue mRequestQueue;
        //
        //    // Instantiate the cache
        //            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        //
        //    // Set up the network to use HttpURLConnection as the HTTP client.
        //            Network network = new BasicNetwork(new HurlStack());
        //
        //    // Instantiate the RequestQueue with the cache and network.
        //            mRequestQueue = new RequestQueue(cache, network);
        //
        //    // Start the queue
        //            mRequestQueue.start();
        //
        //        String url ="https://wt-36d807a325bb4cdac069a2ebb8a9618f-0.sandbox.auth0-extend.com/auto_tag?public_id=android/" + pub_id;
        //
        //
        //        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        //                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        //
        //                    @Override
        //                    public void onResponse(JSONObject response) {
        //
        //                    }
        //                }, new Response.ErrorListener() {
        //
        //                    @Override
        //                    public void onErrorResponse(VolleyError error) {
        //                        // TODO: Handle error
        //
        //                    }
        //                });
        //
        //        mRequestQueue.add(jsonObjectRequest);
    }

    private void getPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select your profile picture"), CHOOSE_IMAGE);
    }
}
