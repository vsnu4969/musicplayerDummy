package com.example.quest.aidldemomusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.SongImport.SongImport;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_id);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        SongImport songImport = new SongImport(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerview_id);
        RecyclerAdapter adapter = new RecyclerAdapter(songImport.getMediaFileList(), getApplicationContext());
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(adapter);
    }

}
