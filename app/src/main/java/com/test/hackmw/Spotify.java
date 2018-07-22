package com.test.hackmw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Spotify extends AppCompatActivity {

    public Spinner choosePlaylist;
    public Button addPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);
        addPlaylist = findViewById(R.id.addPlaylist);
        choosePlaylist = findViewById(R.id.choosePlaylist);
        addPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(choosePlaylist.getSelectedItem().toString());
                addTask("Spotify");
                finish();
            }
        });
    }

    private void addTask(String value) {
        ((global) this.getApplication()).temp.add(value);
    }
}
