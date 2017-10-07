package com.example.android.platzigram.login.interactor;

import com.example.android.platzigram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Redhat on 12/07/2017.
 */

public interface LoginInteractor {

    void signIn(String username, String password, LoginActivity loginActivity, FirebaseAuth firebaseAuth);
}
