package com.example.android.platzigram.login.repository;

import android.support.annotation.NonNull;

import com.example.android.platzigram.login.presenter.LoginPresenter;
import com.example.android.platzigram.login.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Redhat on 13/07/2017.
 */
//Hace la conexion con firebase
public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String email, String password, LoginActivity loginActivity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    presenter.loginSucess();
                } else {
                    presenter.loginUnsucess("Usuario o contraseña incorrectos");
                }
            }

        });

    }
}
