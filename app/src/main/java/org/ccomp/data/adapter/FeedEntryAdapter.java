package org.ccomp.data.adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.FeedItem;

import java.util.List;

public class FeedEntryAdapter extends RecyclerView.Adapter<FeedEntryHolder> {

    private List<FeedItem> entries;

    public FeedEntryAdapter(List<FeedItem> entries){
        this.entries = entries;
    }

    @NonNull
    @Override
    public FeedEntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.feed_entry, parent, false);

        return new FeedEntryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedEntryHolder holder, int position) {

        FeedItem entry = entries.get(position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.setTitle(Html.fromHtml(entry.getTitle(), Html.FROM_HTML_MODE_COMPACT).toString());
            holder.setSummary(Html.fromHtml(entry.getTitle(), Html.FROM_HTML_MODE_LEGACY).toString());
        } else {
            holder.setTitle(entry.getTitle());
            holder.setSummary(Html.fromHtml(entry.getTitle()).toString());
        }

        holder.setLink(entry.getLink());
        //holder.setCategories(entry.getCategories());

    }

    @Override
    public int getItemCount() {
        if (entries == null || entries.isEmpty())
            return 0;
        return entries.size();
    }
}
