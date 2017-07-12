package com.example.android.platzigram;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.platzigram.views.ContainerActivity;
import com.example.android.platzigram.views.CreateAccountActivity;

import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view)
    {

       Intent intent=new Intent(this,CreateAccountActivity.class);
        startActivity(intent); //intent explicito
    }

    public void goContainer(View view)
    {
        Intent container=new Intent(this, ContainerActivity.class);
        startActivity(container);
    }

    public void goLogoUrl(View view)
    {
        //Intent implicito
        Intent logo=new Intent(Intent.ACTION_WEB_SEARCH);
        logo.setData(Uri.parse("http://www.platzigram.com"));
        startActivity(logo);

    }

}
