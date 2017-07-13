package com.example.android.platzigram.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.platzigram.R;
import com.example.android.platzigram.adapter.PictureAdapterRecyclerView;
import com.example.android.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        showToolbar("Búsqueda",true,view);

        RecyclerView searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);

        GridLayoutManager gridLayoutManager=new GridLayoutManager
                (getContext(),2,GridLayoutManager.VERTICAL,false);

        searchRecycler.setLayoutManager(gridLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView=new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture,getActivity());
        searchRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;


    }

    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures=new ArrayList<>();
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Steve Jobs","4 dias","10 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Bill Gates","5 dias","58 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Mark Zuckerberg","8 dias","89 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Elon Musk","8 dias","150 Me Gusta"));
        pictures.add(new Picture("http://novalandtours.com/images/guide/guilin.jpg","Larry Page","8 dias","48 Me Gusta"));
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
