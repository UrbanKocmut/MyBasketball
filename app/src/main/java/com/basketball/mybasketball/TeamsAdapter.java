package com.basketball.mybasketball;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {


    private List<Team> teams;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView teamName;
        TextView teamScore;
        ImageView teamImage;

        public ViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.cv);

            teamName = (TextView) itemView.findViewById(R.id.tvTeamName);
            teamScore = (TextView) itemView.findViewById(R.id.tvTeamScore);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TeamsAdapter(List<Team> teams) {
        this.teams = teams;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return teams.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_team_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.teamName.setText(teams.get(position).getName());
        holder.teamScore.setText(teams.get(position).getStringScore());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}