package com.example.android.platzigram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.platzigram.R;
import com.example.android.platzigram.login.presenter.LoginPresenter;
import com.example.android.platzigram.login.presenter.LoginPresenterImpl;
import com.example.android.platzigram.view.ContainerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity implements LoginView{

    private TextInputEditText username,password;
    private Button login;
    ProgressBar progressBarLogin;
    private LoginPresenter presenter;
    private static final String TAG ="LoginActivity" ;
    public FirebaseAuth firebaseAuth;
    public FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //obtiene informacion del archivo google-services.json
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "Usuario logueado" + firebaseUser.getEmail());

                } else {
                    Log.w(TAG, "Usuario no logueado");
                }
            }
        };

        username=(TextInputEditText)findViewById(R.id.username);
        password=(TextInputEditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        progressBarLogin=(ProgressBar) findViewById(R.id.progressBarLogin);
        hideProgressBar();

        presenter=new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn(username.getText().toString(),password.getText().toString());


            }
        });

    }


    private void signIn(String username,String password)
    {
        presenter.signIn(username,password,this,firebaseAuth);
    }


    @Override
    public void goCreateAccount() {
        Intent intent=new Intent(this,CreateAccountActivity.class);
        startActivity(intent); //intent explicito
    }


    @Override
    public void goUrlLogo() {
        //Intent implicito
        Intent logo=new Intent(Intent.ACTION_WEB_SEARCH);
        logo.setData(Uri.parse("http://www.platzigram.com"));
        startActivity(logo);
    }

    public void UrlLogo(View view)
    {
        goUrlLogo();
    }

    public void goCreateAccount(View view)
    {
        goCreateAccount();
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar()    {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_error)+error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getApplicationContext(),("Inicio de sesion exitoso"), Toast.LENGTH_SHORT).show();
        Intent container=new Intent(this, ContainerActivity.class);
        startActivity(container);
    }


}
