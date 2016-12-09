package com.hcumbicusr.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcumbicusr.platzigram.Model.Picture;
import com.hcumbicusr.platzigram.R;
import com.hcumbicusr.platzigram.View.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 07/12/2016.
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private ArrayList<Picture> pictures;
    private int resource; // cardview
    private Activity activity; // desde donde se esta llamando esta clase

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inicializar la clase , trabaja sobre cada view del card
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        // se trabaja con toda la lista de elementos, para el paso de datos de cada obj de la lista de picture
        Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());
        Picasso.with(activity).load(picture.getPicture()).into(holder.imageCard);

        holder.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                //transicion
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v,activity.getString(R.string.transitionname_picture))
                                    .toBundle());
                } else {
                    activity.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size(); // solo retorna la cantidad de elementos de la list
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public PictureViewHolder(View itemView) {
            super(itemView);
            imageCard = (ImageView) itemView.findViewById(R.id.imageCard);
            usernameCard = (TextView) itemView.findViewById(R.id.userNameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
        }
    }
}
