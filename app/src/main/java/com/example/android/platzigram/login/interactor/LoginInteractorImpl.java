package com.example.android.platzigram.login.interactor;

import com.example.android.platzigram.login.presenter.LoginPresenter;
import com.example.android.platzigram.login.repository.LoginRepository;
import com.example.android.platzigram.login.repository.LoginRepositoryImpl;
import com.example.android.platzigram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Redhat on 12/07/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter=presenter;
        repository=new LoginRepositoryImpl(presenter);
    }

    @Override
    public void signIn(String username, String password, LoginActivity loginActivity, FirebaseAuth firebaseAuth) {
        repository.signIn(username, password,loginActivity,firebaseAuth);
    }
}
