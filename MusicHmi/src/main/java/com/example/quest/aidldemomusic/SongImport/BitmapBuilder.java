package com.example.quest.aidldemomusic.SongImport;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.R;

public class BitmapBuilder extends AsyncTask<SongGS, Void, Bitmap> {

    private ImageView imageView;
    private Context context;
    private SongGS song;

    public BitmapBuilder(ImageView imageView, Context context) {
        this.imageView = imageView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        imageView.setImageResource(R.drawable.no_cover);
    }

    @Override
    protected Bitmap doInBackground(SongGS... songs) {
        song = songs[0];
        return song.getCoverArt(context);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null && imageView.getTag() != null && song.getSongId() == (int) imageView.getTag())
            imageView.setImageBitmap(bitmap);
    }
}