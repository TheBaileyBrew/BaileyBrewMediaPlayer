package com.thebaileybrew.baileybrewmediaplayer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ScrollerAdapter extends RecyclerView.Adapter<ScrollerAdapter.ViewHolder> {

    private RecyclerView parentRecycler;
    private List<songItem> SongItems;

    private String selectedSong;
    private String selectedArtist;



    public ScrollerAdapter(List<songItem> SongItems, String selectedArtist, String selectedSong) {
        this.SongItems = SongItems;
        this.selectedArtist = selectedArtist;
        this.selectedSong = selectedSong;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_card,parent,false);
        return new ViewHolder(view, selectedArtist, selectedSong);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(SongItems.get(position).getImage())
                .into(holder.image);
        //TODO: Figure out the onClick Selection Criteria

    }

    @Override
    public int getItemCount() {
        if (SongItems == null) {
            return 0;
        } else {
            return SongItems.size();
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;


        public ViewHolder (View itemView, String selectedArtist, String selectedSong) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.album_art);
            itemView.findViewById(R.id.container).setOnClickListener(this);

        }


        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Intent openPlayerIntent = new Intent(v.getContext(),songPlayer.class);;
            openPlayerIntent.putExtra("SongName",selectedSong);
            openPlayerIntent.putExtra("ArtistName",selectedArtist);
            v.getContext().startActivity(openPlayerIntent);
        }
    }
}
