package com.example.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.assignment.R;

/**
 * Created by sujata on 02/04/17.
 */

public class LoginScreen extends AppCompatActivity {

    public static boolean active;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_screen);

        Button loginButton =  (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(LoginScreen.this,MainActivity.class);
            startActivity(intent);
        });

        active = true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }
}
