package com.example.android.platzigram.login.view;

/**
 * Created by Redhat on 12/07/2017.
 */

public interface LoginView {

    void goCreateAccount();
    void goHome();
    void goUrlLogo();
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginError(String error);
}
