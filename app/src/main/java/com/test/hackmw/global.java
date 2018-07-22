package com.test.hackmw;

import android.app.Application;
import java.util.ArrayList;

public class global extends Application {
    public ArrayList<ArrayList<String>> tasks = new ArrayList<>();
    public String[] keywords = new String[5];
    public ArrayList<String> temp = new ArrayList<>();
}
