package com.example.android.platzigram.view;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.platzigram.R;
import com.example.android.platzigram.view.fragments.HomeFragment;
import com.example.android.platzigram.view.fragments.ProfileFragment;
import com.example.android.platzigram.view.fragments.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomBar bottomBar=(BottomBar) findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.home);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch(tabId)
                {
                    case R.id.home:
                       addFragment(new HomeFragment());
                        break;

                    case R.id.perfil:
                        addFragment(new ProfileFragment());
                        break;

                    case R.id.search:
                        addFragment(new SearchFragment());
                        break;
                }
            }
        });
    }

    /*
    Se crea un metodo que se encargara de realizar la gestion de colocar el fragment en el contenedor
    Por parametro, se va recibir un fragment; que se debe tener en cuenta de agregar el paquete de
    soporte de Fragment, mas no, el otro paquete(android.support.v4.app.Fragment)
    Tambien se valida que el fragment que se envia por parametro, no sea nulo.
    */

    private void addFragment(Fragment fragment)
    {
        if(null!=fragment)
        {
           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.container,fragment)
                   .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                   .addToBackStack(null)
                   .commit();

        }
    }


}
