package org.ccomp.data.adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItem;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private List<Feed> feeds;

    public FeedAdapter(List<Feed> feeds){
        this.feeds = feeds;
    }
    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.feed, parent, false);

        return new FeedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, int position) {

        Feed feed = feeds.get(position);

        holder.setFeedId((int)feed.getId());
        holder.setTitle(feed.getTitle());
        holder.setSubtitle(feed.getSubtitle());

    }

    @Override
    public int getItemCount() {
        if (feeds == null || feeds.isEmpty())
            return 0;
        return feeds.size();
    }
}
