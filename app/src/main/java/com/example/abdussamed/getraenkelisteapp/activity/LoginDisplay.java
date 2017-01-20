package com.example.abdussamed.getraenkelisteapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.abdussamed.getraenkelisteapp.R;

public class LoginDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_display);


    }

    public void logIn(View view){

        if(view.getId() == R.id.Blogin){
            EditText editText = (EditText) findViewById(R.id.TFusername);
            String str = editText.getText().toString();
            Intent intent = new Intent(LoginDisplay.this, WelcomeScreen.class);
            intent.putExtra("username", str);
            startActivity(intent);
        }

    }

}

