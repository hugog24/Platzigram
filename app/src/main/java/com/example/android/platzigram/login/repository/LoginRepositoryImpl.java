package com.example.android.platzigram.login.repository;

import com.example.android.platzigram.login.presenter.LoginPresenter;

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
    public void signIn(String username, String password) {
        boolean success=false;
        if(success)
        {
            presenter.loginSucess();
        }
        else
            {
                presenter.loginUnsucess("Ocurrio un error");
            }
    }
}
