package com.example.android.platzigram.post.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.platzigram.PlatzigramApplication;
import com.example.android.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {

    private static final String TAG = "NewPostActivity";
    private ImageView imgPhoto;
    private Button btnCreatePost;
    private String photoPath;
    private PlatzigramApplication app;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        app=(PlatzigramApplication)getApplicationContext();
        storageReference=app.getStorageReference();

        btnCreatePost=(Button)findViewById(R.id.btnCreatePost);

        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        if(getIntent().getExtras()!=null)
        {
            photoPath=getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();

        }
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
    }

    private void uploadPhoto() {
        //pone en memoria la imagen
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] photoByte = baos.toByteArray();
        final String photoName = photoPath.substring(photoPath.lastIndexOf("/") + 1, photoPath.length());

        StorageReference photoref=storageReference.child("postImages/"+photoName);
        UploadTask uploadTask=photoref.putBytes(photoByte);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"Error al subir la foto > "+e.toString());
                e.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto=taskSnapshot.getDownloadUrl();
                String photoUrl=uriPhoto.toString();
                Log.w(TAG,"URL PHOTO > "+photoUrl);
                finish();//termina la actividad y regresa a la anterior
            }
        });
    }

    private void showPhoto()
    {
        Picasso.with(this).load(photoPath).into(imgPhoto);
    }
}
