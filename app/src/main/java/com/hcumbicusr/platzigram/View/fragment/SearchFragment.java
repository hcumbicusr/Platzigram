package com.hcumbicusr.platzigram.View.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.hcumbicusr.platzigram.Model.Picture;
import com.hcumbicusr.platzigram.R;
import com.hcumbicusr.platzigram.View.ContainerActivity;
import com.hcumbicusr.platzigram.adapter.PictureAdapterRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        showToolbar(getResources().getString(R.string.tab_search), true, view);
        setHasOptionsMenu(true); // IMPORTANTE para poder mostrar MenuOptions

        RecyclerView pictureRecycler = (RecyclerView) view.findViewById(R.id.searchRecycler);
        /*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        //

        pictureRecycler.setLayoutManager(gridLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView( buildPictures(), R.layout.cardview_picture,getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name1", "4 días", "3 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name2", "3 días", "7 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name3", "2 días", "6 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name4", "1 día", "5 Me gusta"));
        pictures.add(new Picture("http://lorempixel.com/400/200/", "Name5", "5 días", "3 Me gusta"));
        return  pictures;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "Menu search");
        menu.clear();
        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.search_fragment_menu, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        Log.d(TAG, "Menu search-> "+searchManager+" : "+searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        /*
        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(searchItem, searchView);
        */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(),"onQueryTextSubmit", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getContext(),"onQueryTextChange", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        /* // no sirve el evento click
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"setOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

}
