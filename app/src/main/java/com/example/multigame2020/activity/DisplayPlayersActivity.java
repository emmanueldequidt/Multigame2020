package com.example.multigame2020.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.multigame2020.R;
import com.example.multigame2020.adapter.PlayerAdapter;
import com.example.multigame2020.model.Player;

import java.util.ArrayList;

public class DisplayPlayersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_player_activity);
        recyclerView = findViewById(R.id.display_player_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            players.add(new Player("Vaudey " + i, "Baptiste", "12", "o", "loc"));
        }
        recyclerView.setAdapter(new PlayerAdapter(this, players));
    }
}