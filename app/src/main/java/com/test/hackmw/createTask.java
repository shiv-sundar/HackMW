package com.test.hackmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class createTask extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        mEditText = findViewById(R.id.tag);
        mButton = findViewById(R.id.button);
        TextView textView = new TextView(this);
        textView.setHint("tag to look for");
    }
}
