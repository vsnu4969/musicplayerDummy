package com.example.musicservice.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidllibrary.IMusicService;
import com.example.aidllibrary.SongGS;


public class BoundService extends Service {
    MediaPlayer mediaPlayer;

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iMusicService;

        }

    IMusicService.Stub iMusicService = new IMusicService.Stub() {


        @Override
        public void play(SongGS obj) throws RemoteException {

        }

        @Override
        public void next(SongGS obj) throws RemoteException {

        }

        @Override
        public void previous(SongGS obj) throws RemoteException {

        }
    };
}
