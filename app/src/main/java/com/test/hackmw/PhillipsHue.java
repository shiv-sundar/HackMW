package com.test.hackmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PhillipsHue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phillips_hue);
        final EditText red = findViewById(R.id.red);
        final EditText green = findViewById(R.id.green);
        final EditText blue = findViewById(R.id.blue);
        final Spinner lightvalues = findViewById(R.id.lightvalues);
        Button addSettings = findViewById(R.id.addsettings);
        addSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask("PHue");
                addTask("red");
                String redVal = red.getText().toString();
                addTask(redVal);
                addTask("blue");
                String blueVal = blue.getText().toString();
                addTask(blueVal);
                addTask("green");
                String greenVal = green.getText().toString();
                addTask(greenVal);
                addTask("lightvalues");
                addTask(lightvalues.getSelectedItem().toString());

                if (!getStr(8).equals("None") && !getStr(6).equals("") && !getStr(4).equals("") && !getStr(2).equals("")) {
                    Log.v("shit", getStr(8) + ", " + getStr(6) + ", " + getStr(4) + ", " + getStr(2));
                    Toast.makeText(getApplicationContext(), "Remove one set of values", Toast.LENGTH_SHORT).show();
                    clear();
                }

                else if (getStr(8) == "") {
                    if (getStr(6) == "" && getStr(4) == "" && getStr(2) == "") {
                        Toast.makeText(getApplicationContext(), "You didn't pick any values", Toast.LENGTH_SHORT).show();
                        clear();
                    }

                    else {
                        finish();
                    }
                }

                else {
                    finish();
                }
            }
        });
    }

    private void addTask(String value) {
        ((global) this.getApplication()).temp.add(value);
    }

    private String getStr(int index) {
        return ((global) this.getApplication()).temp.get(index);
    }

    private void clear() {
        ((global) this.getApplication()).temp.clear();
    }
}
