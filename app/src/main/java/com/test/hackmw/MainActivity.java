package com.test.hackmw;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import android.util.Log;


import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Find your Account Sid and Token at twilio.com/console
        String ACCOUNT_SID = "ACa976797952fc727414f3fc83775a418b";
        String AUTH_TOKEN = "374a83e5f16784e889d6a7627d02b261";

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
        for (int i = 0; i < size; i++) {
            TextView text = new TextView(this);
            text.setTextSize(20);
            String test = "";
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ArrayList<String> params = ((global) this.getApplication()).tasks.get(i);
            for (int x = 0; x < params.size(); x++) {
                test += params.get(x);
                if (x != params.size() - 1) {
                    test += ", ";
                }
            }

            text.setText(test);
            mLayout.addView(text);
        }

//        try {
//            URL githubEndpoint = new URL("\n" +
//                    "https://ACa976797952fc727414f3fc83775a418b:374a83e5f16784e889d6a7627d02b261@api.twilio.com/2010-04-01/Accounts/ACa976797952fc727414f3fc83775a418b/Messages");
//            HttpsURLConnection myConnection =
//                    (HttpsURLConnection) githubEndpoint.openConnection();
//
//            String encoded = Base64.getEncoder().encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(StandardCharsets.UTF_8));  //Java 8
//            myConnection.setRequestProperty("Authorization", "Basic " + encoded);
//
//
//            if (myConnection.getResponseCode() == 200) {
//                // Success
//                // Further processing here
//                Log.i("Success -----> ", myConnection.getResponseMessage());
//
//                InputStream responseBody = myConnection.getInputStream();
//
//
//                try {
//
//                    int currentMessageCount = 0;
//                    Document doc = readXml(responseBody);
//
//                    doc.getDocumentElement().normalize();
//
//                    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//
//                    NodeList nList = doc.getElementsByTagName("Messages");
//
//                    System.out.println("----------------------------");
//
//
//                    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                        Node nNode = nList.item(temp);
//
//                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                        NodeList nList2 = nNode.getChildNodes();
//
//                        System.out.println("\n Number of Messages :" + nList2.getLength());
//
//                        currentMessageCount = nList2.getLength();
//
//                    }
//
//                    Globals globalVariable = Globals.getInstance();
//                    int globalMessageCount = globalVariable.getData();
//                    System.out.println("currentMessageCount -->" + currentMessageCount);
//                    System.out.println("globalMessageCount -->" + globalMessageCount);
//
//                    if (currentMessageCount < globalMessageCount) {
//                        System.out.println("Status Unchanged! ");
//                    } else {
//                        globalMessageCount = globalMessageCount + 2;
//                        globalVariable.setData(globalMessageCount);
//                        Intent intent = new Intent(MainActivity.this, getImage.class);
//                        startActivity(intent);
//                    }
//
//                } catch (SAXException e) {
//                    e.printStackTrace();
//                } catch (ParserConfigurationException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                // Error handling code goes here
//                Log.i("Failed", myConnection.getResponseMessage());
//            }
//
//        } catch (IOException e) {
//            Log.i("Failed", e.getLocalizedMessage());
//            e.printStackTrace();
//        }

    }


    public static Document readXml(InputStream is) throws SAXException, IOException,
            ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        // dbf.setCoalescing(true);
        // dbf.setExpandEntityReferences(true);

        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        db.setEntityResolver(new NullResolver());

        // db.setErrorHandler( new MyErrorHandler());

        return db.parse(is);
    }


    static class NullResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
                IOException {
            return new InputSource(new StringReader(""));
        }
    }


    private void deleteTask(int index) {
        ((global) this.getApplication()).tasks.remove(index);
    }
}
