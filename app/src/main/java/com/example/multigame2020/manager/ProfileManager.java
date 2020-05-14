package com.example.multigame2020.manager;

import com.example.multigame2020.model.Player;

public class ProfileManager {

    private static final ProfileManager instance = new ProfileManager();

    private Player player;

    public static ProfileManager getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
