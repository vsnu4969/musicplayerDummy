package com.example.musicservice.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidllibrary.IMusicService;
import com.example.aidllibrary.SongGS;

import java.net.URI;


public class BoundService extends Service {
    MediaPlayer mediaPlayer;
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iMusicService;

        }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    IMusicService.Stub iMusicService = new IMusicService.Stub() {

        @Override
        public void play(SongGS obj) throws RemoteException {
          String path=obj.getSongPath();
            Log.i("VAL","play works!!!");
            Log.i("VAL","the song path:"+path);
            mediaPlayer=MediaPlayer.create(getApplicationContext(), Uri.parse(path));
            Log.i("VAL","the song path:"+obj.getSongPath());
            mediaPlayer.start();

        }

        @Override
        public void next(SongGS obj) throws RemoteException {

        }

        @Override
        public void previous(SongGS obj) throws RemoteException {

        }
    };
}
