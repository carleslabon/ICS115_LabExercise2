package com.eslabon.laboratoryexcercise2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button sharedPrefBtn, intStorBtn, nextBtn;
    FileOutputStream fos;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);

        sharedPrefBtn = (Button) findViewById(R.id.sharedPrefBtn);
        intStorBtn = (Button) findViewById(R.id.intStorBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void sharedPreferences(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sharedPrefUsername", username.getText().toString());
        editor.putString("sharedPrefPassword", password.getText().toString());
        editor.apply();
        Toast.makeText(this, "Saved to Shared Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void internalStorage(View view) {
        String message = "Username: " + username.getText().toString() + "\nPassword: " + password.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved to Internal Storage!", Toast.LENGTH_SHORT).show();
    }

    public void toSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
