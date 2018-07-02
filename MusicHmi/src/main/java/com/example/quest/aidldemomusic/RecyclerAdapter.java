
/**
 * @file      GridAdapter.java
 * @brief     used to set up the view fo the first page of music player$
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *            ALL RIGHTS RESERVED
 * @author    Vishnu Muraleedharan
 */

package com.example.quest.aidldemomusic;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidllibrary.IMusicService;
import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.SongImport.BitmapBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerInnerClass> {
    ArrayList<SongGS> arrayList;
    LayoutInflater layoutInflater;
    Context context;

    public RecyclerAdapter(ArrayList<SongGS> arrayList, Context context) {
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerInnerClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.song_list, parent, false);
        RecyclerInnerClass recyclerInnerClassObj = new RecyclerInnerClass(view);
        return recyclerInnerClassObj;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerInnerClass holder, int position) {
        SongGS currentObj = arrayList.get(position);
        holder.setData(currentObj, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class RecyclerInnerClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView songCover;
        TextView songTitle;
        TextView songArtist;
        TextView songDuration;
        int newposition;
        SongGS object;
        CardView cardView;
        public RecyclerInnerClass(final View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.crd);
            songCover = itemView.findViewById(R.id.song_cover);
            songTitle = itemView.findViewById(R.id.song_title);
            songTitle.setSelected(true);
            songArtist = itemView.findViewById(R.id.song_artist);
            songArtist.setSelected(true);
            songDuration = itemView.findViewById(R.id.song_duration);
            itemView.setOnClickListener(this);

        }

        public void setData(final SongGS currentObj, int position) {
            //this.songCover.setImageBitmap(currentObj.getCoverArt(itemView.getContext()));
            this.songCover.setTag(currentObj.getSongId());
            BitmapBuilder bitmapBuilder = new BitmapBuilder(songCover, itemView.getContext());
             bitmapBuilder.execute(currentObj);
            this.songTitle.setText(currentObj.getSongTitle());
            this.songArtist.setText(currentObj.getSongArtist());
            this.songDuration.setText(currentObj.getTime());
            this.newposition = position;
            this.object = currentObj;

        }


        @Override
        public void onClick(View v) {
            // Toast.makeText(context, "dsgedsgf", Toast.LENGTH_SHORT).show();
        }
    }

}

