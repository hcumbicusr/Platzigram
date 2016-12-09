package com.hcumbicusr.platzigram.View.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcumbicusr.platzigram.Model.Picture;
import com.hcumbicusr.platzigram.R;
import com.hcumbicusr.platzigram.adapter.PictureAdapterRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView pictureRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pictureRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView( buildPictures(), R.layout.cardview_picture,getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name1", "4 días", "3 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name2", "3 días", "7 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name3", "2 días", "6 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name4", "1 días", "5 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name5", "5 días", "3 Me gusta"));
        return  pictures;
    }


    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
