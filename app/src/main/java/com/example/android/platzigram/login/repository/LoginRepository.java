package com.example.android.platzigram.login.repository;

import com.example.android.platzigram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Redhat on 13/07/2017.
 */

public interface LoginRepository {

    void signIn(String username, String password, LoginActivity loginActivity, FirebaseAuth firebaseAuth);
}
