package com.thebaileybrew.baileybrewmediaplayer;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DiscreteScrollView.OnItemChangedListener {

    private List<songItem> SongItems;
    private DiscreteScrollView songPicker;
    private TextView currentArtistName;
    private TextView currentSongName;
    private BottomNavigation mBottomNavigation;

    ScrollerAdapter adapterScroller;
    currentSongInterface songInterface;

    int currentId;
    String selectedSong;
    String selectedArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songPicker = findViewById(R.id.all_music_scroll_view);
        currentArtistName = findViewById(R.id.artist_name_display);
        currentSongName = findViewById(R.id.song_name_display);

        songInterface = new currentSongInterface() {
            @Override
            public void respond(String currentArtist, String currentSong) {
                Log.i("Interface Transfer", "respond: " + currentArtist);
                Log.i("Interface Transfer", "respond: " + currentSong);
            }
        };


        SongItems = new ArrayList<>();
        SongItems.add(new songItem(1,"Luca Stricagnoli","Thunderstruck",R.drawable.luca_image_300, "Instrumental"));
        SongItems.add(new songItem(2,"Pop Evil", "Monster You Made", R.drawable.pop_evil_700, "Rock"));
        SongItems.add(new songItem(3,"Disturbed", "Sound of Silence", R.drawable.disturbed_300, "Rock"));
        SongItems.add(new songItem(4,"Metallica","Fade to Black", R.drawable.metallica_400, "Rock"));
        SongItems.add(new songItem(5,"Pop Evil", "Waking Lions", R.drawable.pop_evil_700, "Rock"));
        SongItems.add(new songItem(6,"Pop Evil", "The Big House",R.drawable.pop_evil_700, "Rock"));
        SongItems.add(new songItem(7,"Metallica", "Enter The Sandman", R.drawable.metallica_400, "Rock"));
        SongItems.add(new songItem(8,"Two Cellos", "Smells Like Teen Spirit", R.drawable.twocellos_900, "Instrumental"));
        SongItems.add(new songItem(9,"Lindsey Stirling", "Radioactive",R.drawable.lindsey_stirling_1000, "Instrumental"));
        SongItems.add(new songItem(10, "Two Cellos", "Smooth Criminal", R.drawable.twocellos_900, "Instrumental"));
        SongItems.add(new songItem(11, "Andy McKee", "Art of Motion", R.drawable.andymckee_800,"Instrumental"));
        SongItems.add(new songItem(12, "The Chainsmokers", "Everybody Hates Me", R.drawable.chainsmokers_500, "Electronic"));
        SongItems.add(new songItem(13, "The Chainsmokers", "Sick Boy", R.drawable.chainsmokers_500, "Electronic"));
        SongItems.add(new songItem(14, "Ed Sheeran", "Shape of You", R.drawable.sheeran_700, "Pop"));
        SongItems.add(new songItem(15, "Fall Out Boy", "Hum Hallelujah", R.drawable.falloutboy_500, "Alt Rock"));
        SongItems.add(new songItem(16, "Green Day", "American Idiot", R.drawable.greenday_700, "Alt Rock"));
        SongItems.add(new songItem(17, "Fall Out Boy", "The Mighty Fall", R.drawable.falloutboy_500, "Alt Rock"));
        SongItems.add(new songItem(18, "Imagine Dragons", "Radioactive", R.drawable.imaginedragons_1000, "Pop"));
        SongItems.add(new songItem(19, "Imagine Dragons", "Thunder", R.drawable.imaginedragons_1000, "Pop"));
        SongItems.add(new songItem(20, "Marshmello", "Alone", R.drawable.marshmello_900, "Electronic"));

        Collections.shuffle(SongItems);

        //infinateAdapter = InfiniteScrollAdapter.wrap(new ScrollerAdapter(SongItems));
        songPicker.setOrientation(DSVOrientation.HORIZONTAL);
        songPicker.addOnItemChangedListener(this);
        songPicker.setSlideOnFling(true);
        songPicker.setSlideOnFlingThreshold(600);

        adapterScroller = new ScrollerAdapter(SongItems, selectedArtist, selectedSong);
        songPicker.setAdapter(adapterScroller);
        //songPicker.addItemDecoration(new RecyclerItemDecorator());
        songPicker.scrollToPosition(0);
        songPicker.setItemTransitionTimeMillis(1000);;

        songPicker.setItemTransformer( new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .setMaxScale(1.25f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build());




        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setSelectedIndex(2,true);
        mBottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int i, int i1, boolean b) {
                switch (i1) {
                    case 0:   //ARTISTS
                        break;
                    case 1:   //GENRES
                        break;
                    case 2:   //HOME PAGE
                        break;
                    case 3:   //ALPHABETICAL
                        break;
                    case 4:   //PLAYLISTS
                        break;
                }
            }

            @Override
            public void onMenuItemReselect(int i, int i1, boolean b) {

            }
        });

    }

    private void onItemChanged(songItem item) {
        //songPicker.getCurrentItem();
        currentArtistName.setText(item.getArtistName());
        selectedArtist = String.valueOf(currentArtistName);
        currentSongName.setText(item.getSongName());
        selectedSong = String.valueOf(currentSongName);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewText = v.getId();
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = songPicker.getCurrentItem();
        onItemChanged(SongItems.get(positionInDataSet));
    }


}
