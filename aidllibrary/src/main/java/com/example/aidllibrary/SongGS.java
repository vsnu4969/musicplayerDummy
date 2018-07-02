
/**
 * @file      SongGS.java
 * @brief     used to store and retreive song $
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *            ALL RIGHTS RESERVED
 * @author    Vishnu Muraleedharan
 */

package com.example.aidllibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Parcel;
import android.os.Parcelable;

public class SongGS implements Parcelable {


   protected SongGS(Parcel in) {
       songId = in.readInt();
       songDuration = in.readInt();
       songPath = in.readString();
       songTitle = in.readString();
       songArtist = in.readString();
       songAlbum = in.readString();
   }


int songId;
int songDuration;
String songPath;
String songTitle;
String songArtist;
String songAlbum;

    public static final Creator<SongGS> CREATOR = new Creator<SongGS>() {
        @Override
        public SongGS createFromParcel(Parcel in) {
            return new SongGS(in);
        }

        @Override
        public SongGS[] newArray(int size) {
            return new SongGS[size];
        }
    };

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public SongGS() {
    }
    public Bitmap getCoverArt(Context context) {
        MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(getSongPath());
        byte[] art = metaRetriver.getEmbeddedPicture();
        return art != null
                ? BitmapFactory.decodeByteArray(art, 0, art.length)
                : BitmapFactory.decodeResource(context.getResources(),R.drawable.no_cover);
    }

    public SongGS(int songId, int songDuration, String songPath, String songTitle, String songArtist, String songAlbum) {
        this.songId = songId;
        this.songDuration = songDuration;
        this.songPath = songPath;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
    }
    @SuppressLint("DefaultLocale")
    public  String getTime() {
        long duration=getSongDuration();
        long minutes = (duration / 1000) / 60;
        long seconds = (duration / 1000) % 60;
        return minutes + ":" + String.format("%02d", seconds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(songId);
        dest.writeInt(songDuration);
        dest.writeString(songPath);
        dest.writeString(songTitle);
        dest.writeString(songArtist);
        dest.writeString(songAlbum);
    }
}

