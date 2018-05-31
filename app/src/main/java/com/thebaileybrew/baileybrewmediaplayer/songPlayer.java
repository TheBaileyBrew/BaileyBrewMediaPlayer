package com.thebaileybrew.baileybrewmediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class songPlayer extends AppCompatActivity {

    currentSongInterface songInterface;
    String currentSong;
    String currentArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_song);

        Intent getSongInfo = getIntent();
        currentSong = getSongInfo.getStringExtra("SongName");
        currentArtist = getSongInfo.getStringExtra("ArtistName");

        TextView songName = findViewById(R.id.song_name_display_player);
        TextView artistName = findViewById(R.id.artist_name_display_player);
        songName.setText(currentSong);
        artistName.setText(currentArtist);

    }
}
