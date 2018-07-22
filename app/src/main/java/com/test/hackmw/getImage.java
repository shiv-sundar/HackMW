package com.test.hackmw;

import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;
import android.os.StrictMode;
import android.widget.Toast;
import com.cloudinary.android.MediaManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class getImage extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    Uri uriProfile;
    public String pub_id = "file" + System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getPicture();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                makeRequest();
                String[] vals = getStrArray();
                ArrayList<ArrayList<String>> newVals = getALALS();
                startAction(vals, newVals);
                Intent intent = new Intent(getImage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 30000);
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

            Log.v("test", "file" + test);
        }
    }

    public void makeRequest() {
        try {
            String url = "https://wt-36d807a325bb4cdac069a2ebb8a9618f-0.sandbox.auth0-extend.com/auto_tag?public_id=android/" + pub_id;
            Log.v("test", url);
            URL githubEndpoint = new URL(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
            if (myConnection.getResponseCode() == 200) {
                int x = 0;
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    String newVal = jsonReader.nextString();
                    ((global) this.getApplication()).keywords[x] = newVal;
                    jsonReader.nextName();
                    jsonReader.nextDouble();
                    x++;
                    jsonReader.endObject();
                    if (x == 3) {
                        break;
                    }
                }

                jsonReader.close();
            }

            else {
                Log.v("test", myConnection.getResponseMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select your profile picture"), CHOOSE_IMAGE);
    }

    private void startAction(String[] keywords, ArrayList<ArrayList<String>> tasks) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < ((global) this.getApplication()).tasks.size(); y++) {
                String task = tasks.get(y).get(tasks.get(y).size() - 1);
                if (keywords[x].equals(task)) {
                    Toast.makeText(getBaseContext(), "Started the " + task + " task", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    public String[] getStrArray() {
        return ((global) this.getApplication()).keywords.clone();
    }

    public ArrayList<ArrayList<String>> getALALS() {
        return (ArrayList<ArrayList<String>>) ((global) this.getApplication()).tasks.clone();
    }
}