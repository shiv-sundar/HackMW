package com.test.hackmw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;

public class createTask extends AppCompatActivity {

    public EditText trigger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        trigger = findViewById(R.id.trigger);
        final Button addTask = findViewById(R.id.addTask);
        ImageButton phue = findViewById(R.id.phue);
        ImageButton spotify = findViewById(R.id.spotify);
        ImageButton nest = findViewById(R.id.nest);
        ImageButton smart = findViewById(R.id.smart);
        phue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createTask.this, PhillipsHue.class);
                startActivity(intent);
            }
        });

        spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createTask.this, Spotify.class);
                startActivity(intent);
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
                Intent intent = new Intent(createTask.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addTask() {
        ((global) this.getApplication()).temp.add(trigger.getText().toString());
        ((global) this.getApplication()).tasks.add(((ArrayList<String>) ((global) this.getApplication()).temp.clone()));
        ((global) this.getApplication()).temp.clear();
    }
}
