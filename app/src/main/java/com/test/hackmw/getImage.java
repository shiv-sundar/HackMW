package com.test.hackmw;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.*;
import com.cloudinary.utils.*;
import com.cloudinary.android.MediaManager;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class getImage extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    Uri uriProfile;
    public String test = "";
    public String pub_id = "";
    Button btn1;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
        btn1 = (Button) findViewById(R.id.button2);
        txt = (TextView) findViewById(R.id.textView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
                Log.v("test", test + "file");
                Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
            }
        });
        getPicture();
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
            RequestQueue mRequestQueue;

    // Instantiate the cache
            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
            Network network = new BasicNetwork(new HurlStack());

    // Instantiate the RequestQueue with the cache and network.
            mRequestQueue = new RequestQueue(cache, network);

    // Start the queue
            mRequestQueue.start();

        String url ="https://wt-36d807a325bb4cdac069a2ebb8a9618f-0.sandbox.auth0-extend.com/auto_tag?public_id=android/" + pub_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

//                        try {
                            test = response.toString();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        mRequestQueue.add(jsonObjectRequest);
    }

    private void getPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select your profile picture"), CHOOSE_IMAGE);
    }
}
