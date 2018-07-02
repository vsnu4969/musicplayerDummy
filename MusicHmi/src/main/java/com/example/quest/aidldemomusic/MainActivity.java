package com.example.quest.aidldemomusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.example.aidllibrary.SongGS;
import com.example.quest.aidldemomusic.SongImport.SongImport;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
Toolbar toolbar;
ArrayList<SongGS>newArrayList;
SongGS listImportObj;
    SongImport obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_id);
        listImportObj=new SongGS();
        newArrayList=new ArrayList<>();
        obj=new SongImport(getApplicationContext());
        setupRecyclerView();


    }
    private void setupRecyclerView() {
        recyclerView=findViewById(R.id.recyclerview_id);
        newArrayList=obj.getMediaFileList();
        RecyclerAdapter adapter=new RecyclerAdapter(newArrayList,getApplicationContext());
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(grid);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

}
