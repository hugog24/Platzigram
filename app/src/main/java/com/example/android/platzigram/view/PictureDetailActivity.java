package com.example.android.platzigram.view;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.platzigram.PlatzigramApplication;
import com.example.android.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    private String PHOTO_NAME = "JPEG_20171003_20-07-16_1359532454.jpg";
    private ImageView imageHeader;
    private PlatzigramApplication app;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

       app=(PlatzigramApplication)getApplicationContext();
        storageReference=app.getStorageReference();

        imageHeader=(ImageView)findViewById(R.id.imageHeader);

        showToolbar("",true);

        //validacion de transicion de entrada
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
           getWindow().setEnterTransition(new Fade());
        }
        
        showData();
    }

    private void showData() {
        storageReference.child("postImages/"+PHOTO_NAME)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PictureDetailActivity.this).load(uri.toString()).into(imageHeader);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PictureDetailActivity.this,
                        "Ocurrio un error al descargar la foto",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    public void showToolbar(String title,boolean upButton)
    {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
    }
}
