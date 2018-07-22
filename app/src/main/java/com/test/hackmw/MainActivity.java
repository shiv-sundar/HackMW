package com.test.hackmw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.linearLayout);
        Button starter = findViewById(R.id.startCamera);
        Button createTask = findViewById(R.id.createTask);
        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, getImage.class);
                startActivity(intent);
                finish();
            }
        });

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createTask.class);
                startActivity(intent);
                finish();
            }
        });

        final int size = ((global) this.getApplication()).tasks.size();
        final ArrayList<String[]> tasks = ((global) this.getApplication()).tasks;
        for(int i = 0; i < size; i++) {
            TextView text = new TextView(this);
            String test = "";
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            String[] params = ((global) this.getApplication()).tasks.get(i);
            for (int x = 0; x < params.length; x++) {
                test += params[x];
                if (x != params.length - 1) {
                    test += ", ";
                }
            }

            final String test2 = test;
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int x = 0; x < size; x++) {
                        if (tasks.get(x)[0].equals(test2.split(" ", 2)[0])) {
                            deleteTask(x);
                        }
                    }
                }
            });

            text.setText(test);
            mLayout.addView(text);
        }
    }

    private void deleteTask(int index) {
        ((global) this.getApplication()).tasks.remove(index);
    }
}
