
/**
 * @file GridAdapter.java
 * @brief used to set up the view fo the first page of music player$
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 * ALL RIGHTS RESERVED
 * @author Vishnu Muraleedharan
 */

package com.example.quest.aidldemomusic;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.SongImport.BitmapBuilder;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<SongGS> arrayList;
    private Context context;
    private RecyclerListInteractor mListener;

    public RecyclerAdapter(ArrayList<SongGS> arrayList, MainActivity mainActivity) {
        this.arrayList = arrayList;
        this.context = mainActivity;
        mListener = (RecyclerListInteractor) mainActivity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.song_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SongGS currentObj = arrayList.get(position);
        holder.setData(currentObj);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView songCover;
        TextView songTitle;
        TextView songArtist;
        TextView songDuration;
        CardView cardView;
        private SongGS song;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.crd);
            songCover = itemView.findViewById(R.id.song_cover);
            songTitle = itemView.findViewById(R.id.song_title);
            songArtist = itemView.findViewById(R.id.song_artist);
            songDuration = itemView.findViewById(R.id.song_duration);
            cardView.setOnClickListener(this);
        }

        public void setData(SongGS song) {
            this.song = song;
            //this.songCover.setImageBitmap(currentObj.getCoverArt(itemView.getContext()));
            this.songCover.setTag(song.getSongId());
            BitmapBuilder bitmapBuilder = new BitmapBuilder(songCover, context);
            bitmapBuilder.execute(song);
            this.songTitle.setText(song.getSongTitle());
            this.songArtist.setText(song.getSongArtist());
            this.songDuration.setText(song.getTime());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onListItemClick(song);
            }
        }
    }

}

