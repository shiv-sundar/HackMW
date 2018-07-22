package com.test.hackmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class createTask extends AppCompatActivity {

    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        mLayout = findViewById(R.id.linearLayout);
        mEditText = findViewById(R.id.editText);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setHint("tag to look for");
        for(int i = 0; i < ((global) this.getApplication()).tasks.size();i++) {
            TextView text = new TextView(this);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            String[] params = ((global) this.getApplication()).tasks.get(i);
            String test = "";
            for (int x = 0; x < params.length; x++) {
                test += params[x];
                if (x != params.length - 1) {
                    test += ", ";
                }
            }

            text.setText(test);
            mLayout.addView(text);
        }
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView(mEditText.getText().toString()));
            }
        };
    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("New text: " + text);
        return textView;
    }
}
