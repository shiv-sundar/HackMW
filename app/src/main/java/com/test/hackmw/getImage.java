package com.test.hackmw;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class getImage extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    Uri uriProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
        chooseProfilePicture();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfile = data.getData();
            Map config = new HashMap();
            config.put("cloud_name", "yotabites-llc");
            MediaManager.init(this, config);
            String pub_id = "file" + System.currentTimeMillis();
            String requestId = MediaManager.get().upload(uriProfile)
                    .unsigned("r9ejzsni")
                    .option("public_id", pub_id)
                    .dispatch();
            Toast.makeText(getApplicationContext(), requestId, Toast.LENGTH_SHORT).show();
        }
    }

    private void chooseProfilePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select your profile picture"), CHOOSE_IMAGE);
    }
}
