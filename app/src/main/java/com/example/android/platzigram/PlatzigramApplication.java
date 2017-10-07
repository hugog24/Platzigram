package com.example.android.platzigram;

import android.app.Application;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Redhat on 09/08/2017.
 */

public class PlatzigramApplication extends Application {

    private FirebaseStorage firebaseStorage;
    @Override
    public void onCreate() {
        super.onCreate();

firebaseStorage=FirebaseStorage.getInstance();
    }

    public StorageReference getStorageReference()
    {
        return firebaseStorage.getReference();
    }
}
