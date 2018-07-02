
/**
 * @file      SongImport.java
 * @brief     import song from sd card$
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *            ALL RIGHTS RESERVED
 * @author    Vishnu Muraleedharan
 */

package com.example.quest.aidldemomusic.SongImport;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.aidllibrary.SongGS;

import java.util.ArrayList;

public class SongImport   {
   Context context;

    public SongImport(Context context) {
        this.context = context;
    }

    public ArrayList<SongGS> getMediaFileList() {

      ContentResolver contentResolver = context.getContentResolver();

      Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

      Cursor cursor = contentResolver.query(
              uri, // Uri
              null, // Projection
              null, // Selection
              null, // Selection args
              null // Sor order
      );
      return convertToArrayList(cursor);
   }

   public static ArrayList<SongGS> convertToArrayList(Cursor cursor) {
      ArrayList<SongGS> songList = new ArrayList<>();

      if (cursor == null) {
//            mResult.append("\n" +"Query failed, handle error.");
      } else if (!cursor.moveToFirst()) {
         // no media on the device
//            mResult.append("\n" +"Nno music found on the sd card.");
      } else {
         int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
         int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
         int album = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
         int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
         int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
         int songUrl = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
         int fileName = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
         int dateAdded = cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED);
         int composer = cursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER);
         // Loop through the musics
         do {
            SongGS song = new SongGS();
            song.setSongId(cursor.getInt(id));
            song.setSongTitle(cursor.getString(title));
            song.setSongAlbum(cursor.getString(album));
            song.setSongArtist(cursor.getString(artist));
            song.setSongDuration(cursor.getInt(duration));
            song.setSongPath(cursor.getString(songUrl));

            // Process current music here
            songList.add(song);
         } while (cursor.moveToNext());
      }
      return songList;
   }

   public ArrayList<String> getAlbumList() {
      ArrayList<SongGS> songs = getMediaFileList();
      ArrayList<String> albumList = new ArrayList<String>();
      for (SongGS song : songs)
         if (!albumList.contains(song.getSongAlbum()))
            albumList.add(song.getSongAlbum());
      return albumList;
   }

   public ArrayList<String> getArtistList() {
      ArrayList<SongGS> songs = getMediaFileList();
      ArrayList<String> artistList = new ArrayList<String>();
      for (SongGS song : songs)
         if (!artistList.contains(song.getSongArtist()))
            artistList.add(song.getSongArtist());
      return artistList;
   }
}





