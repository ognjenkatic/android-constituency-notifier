package org.ccomp.data.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.ui.feed.FeedFragmentDirections;
import org.ccomp.ui.feed.index.FeedIndexFragmentDirections;

public class FeedHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView subtitle;

    private int feedId;

    public void setFeedId(int feedId){
        this.feedId = feedId;
    }
    public void setTitle(String title){
        this.title.setText(title);
    }

    public void  setSubtitle(String subtitle){
        this.subtitle.setText(subtitle);
    }

    public FeedHolder(@NonNull View itemView) {
        super(itemView);

        title =   itemView.findViewById(R.id.feed_title);
        subtitle = itemView.findViewById(R.id.feed_subtitle);





        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FeedIndexFragmentDirections.ActionNavFeedIndexToNavFeed act =  FeedIndexFragmentDirections.actionNavFeedIndexToNavFeed();
                act.setFeedId(feedId);
                Navigation.findNavController(v).navigate(act);
            }
        });
    }
}
