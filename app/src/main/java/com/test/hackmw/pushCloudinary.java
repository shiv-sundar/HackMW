package com.test.hackmw;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class pushCloudinary extends AppCompatActivity {
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_cloudinary);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("test")) {
            image = Uri.parse(extras.getString("test"));
        }

        Map config = new HashMap();
        config.put("cloud_name", "yotabites-llc");
        MediaManager.init(this, config);
        String requestId = MediaManager.get().upload(image)
                .unsigned("r9ejzsni")
                .dispatch();

        Toast.makeText(getApplicationContext(), requestId, Toast.LENGTH_SHORT).show();
    }
}
