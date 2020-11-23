package com.example.login.matches;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    MatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        RecyclerView recyclerView = findViewById(R.id.rvId3);
      /*  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Message> msgs = new ArrayList<>();//TODO - get messages from backend
/*
        adapter = new MessageAdapter(this, msgs, "Kerin Smith");
        recyclerView.setAdapter(adapter);*/
        // RecyclerView recyclerView = findViewById(R.id.rvId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<Match> msgs = new ArrayList<>();//TODO   -get messages from backend

        /*Stub fucntions*/
        msgs.add(new Match("Suraj Pariyar",25));

        msgs.add(new Match("Eric Marciano",27));
        msgs.add(new Match("Rishabh Bansal",23));

        /**/
        final MatchesActivity activity = this;
        adapter = new MatchAdapter(this, msgs);
        adapter.setClickListener(new MatchAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, "You click on match", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}