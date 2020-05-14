package com.example.multigame2020.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multigame2020.R;
import com.example.multigame2020.activity.CreatePlayerActivity;
import com.example.multigame2020.activity.DisplayPlayersActivity;
import com.example.multigame2020.activity.MainActivity;
import com.example.multigame2020.manager.ProfileManager;
import com.example.multigame2020.model.Player;
import com.example.multigame2020.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private DisplayPlayersActivity activity;
    private ArrayList<Player> players;

    public PlayerAdapter(DisplayPlayersActivity activity, ArrayList<Player> players) {
        this.activity = activity;
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.show_player_row, viewGroup, false);
        return new PlayerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
       final Player player = players.get(position);
        Picasso.get().load(player.getPicture()).into(viewHolder.image);
        viewHolder.name.setText(player.getName());
        viewHolder.firstName.setText(player.getFirstName());
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileManager.getInstance().setPlayer(player);
                ActivityUtils.launchActivity(activity, MainActivity.class, ActivityUtils.TYPE_SLIDE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout container;
        private ImageView image;
        private TextView name;
        private TextView firstName;

        ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.players_row_container);
            image = itemView.findViewById(R.id.players_row_image);
            name = itemView.findViewById(R.id.players_row_name);
            firstName = itemView.findViewById(R.id.players_row_firstname);
        }
    }
}
