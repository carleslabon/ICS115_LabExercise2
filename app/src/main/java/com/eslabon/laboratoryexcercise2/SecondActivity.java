package com.eslabon.laboratoryexcercise2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;

public class SecondActivity extends AppCompatActivity {

    Button loadSharedBtn, loadIntStoreBtn, backBtn, clearBtn;
    TextView textDisplay;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_page);

        loadSharedBtn = (Button) findViewById(R.id.loadSharedBtn);
        loadIntStoreBtn = (Button) findViewById(R.id.loadIntStorBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);

        textDisplay = (TextView) findViewById(R.id.textDisplay);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSharedPreferences(View view) {
        String sharedPrefUsername = preferences.getString("sharedPrefUsername", "NULL");
        String sharedPrefPassword = preferences.getString("sharedPrefPassword", "NULL");
        textDisplay.setText("Username: " + sharedPrefUsername + "\nPassword: " + sharedPrefPassword);
    }

    public void loadInternalStorage(View view) {
        StringBuilder buffer = new StringBuilder();
        int read;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void clearText (View view){
        textDisplay.setText("");
    }

}
