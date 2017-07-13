package com.example.android.platzigram.login.presenter;

/**
 * Created by Redhat on 12/07/2017.
 */

public interface LoginPresenter {

    void signIn(String username, String password);//Interactor
    void loginSucess();
    void loginUnsucess(String error);
}
