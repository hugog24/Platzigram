package com.example.android.platzigram.post.view;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.platzigram.R;
import com.example.android.platzigram.adapter.PictureAdapterRecyclerView;
import com.example.android.platzigram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp="";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        showToolbar(getResources().getString(R.string.home_tab),false,view);
        fabCamera=(FloatingActionButton)view.findViewById(R.id.fabCamera);

        RecyclerView picturesRecycler=(RecyclerView)view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView=new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture,getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture()
    {
        Intent intentTakePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentTakePicture.resolveActivity(getActivity().getPackageManager())!=null)
        {
            //Se crea un archivo temporal para guardar la foto
            File photoFile=null;

            try
            {
                photoFile=createImageFile();
            }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                if(photoFile!=null)
                {
                    //String packageName=
                    Uri photoUri= FileProvider.getUriForFile(getActivity(),"com.example.android.platzigram",photoFile);
                    intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);

                    //Abre la camara, TOMA LA FOTO y despues regresa al HomeFragment
                    startActivityForResult(intentTakePicture,REQUEST_CAMERA);
                }

        }
    }

    private File createImageFile() throws IOException
    {
        //Se obtiene la hora exacta que se toma la foto, para que sea unica
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName="JPEG_" +timeStamp+ "_";
        File storageDir=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo=File.createTempFile(imageFileName,".jpg",storageDir);
        photoPathTemp="file:"+photo.getAbsolutePath();
        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CAMERA && resultCode==getActivity().RESULT_OK)
        {
            Log.d("HomeFragment","CAMERA OK!!");
            Intent i=new Intent(getActivity(),NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TEMP",photoPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures() {
      ArrayList<Picture> pictures=new ArrayList<>();
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Hugo García","4 dias","10 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Sonia Garcia","5 dias","6 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Elon Musk","8 dias","25 Me Gusta"));

        return pictures;
    }


    public void showToolbar(String title,boolean upButton, View view)
    {
        Toolbar toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
