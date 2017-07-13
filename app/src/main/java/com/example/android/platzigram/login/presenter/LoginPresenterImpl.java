package com.example.android.platzigram.login.presenter;

import com.example.android.platzigram.login.interactor.LoginInteractor;
import com.example.android.platzigram.login.interactor.LoginInteractorImpl;
import com.example.android.platzigram.login.view.LoginView;

/**
 * Created by Redhat on 12/07/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor=new LoginInteractorImpl(this);


    }

    @Override
    public void signIn(String username, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signIn(username,password);
    }

    @Override
    public void loginSucess() {
        loginView.goHome();
        loginView.hideProgressBar();
    }

    @Override
    public void loginUnsucess(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }

}
