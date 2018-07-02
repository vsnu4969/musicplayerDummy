package com.example.quest.aidldemomusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.aidllibrary.IMusicService;
import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.SongImport.SongImport;

public class MainActivity extends AppCompatActivity implements RecyclerListInteractor {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private IMusicService musicService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = IMusicService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_id);
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
    }

    private void setupRecyclerView() {
        SongImport songImport = new SongImport(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerview_id);
        RecyclerAdapter adapter = new RecyclerAdapter(songImport.getMediaFileList(), this);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onListItemClick(SongGS songGS) {
        Toast.makeText(this, songGS.getSongTitle(), Toast.LENGTH_SHORT).show();
        try {
            musicService.play(songGS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
