package com.example.abdussamed.getraenkelisteapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abdussamed.getraenkelisteapp.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_display_success);

        String username = getIntent().getStringExtra("username");

        TextView textView = (TextView) findViewById(R.id.TVusername);
        textView.setText(username);
    }
}
