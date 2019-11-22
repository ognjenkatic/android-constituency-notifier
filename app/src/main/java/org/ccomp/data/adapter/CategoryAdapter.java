package org.ccomp.data.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.FeedCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private List<FeedCategory> feedCategories;

    public CategoryAdapter(List<FeedCategory> feedCategories){
        this.feedCategories = feedCategories;
    }
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.category_entry, parent, false);

        return new CategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        FeedCategory entry = feedCategories.get(position);

        holder.setName(entry.getName());
    }

    @Override
    public int getItemCount() {
        return feedCategories.size();
    }
}
