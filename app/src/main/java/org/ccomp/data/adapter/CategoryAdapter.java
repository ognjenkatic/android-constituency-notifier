package org.ccomp.data.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.interfaces.CategoryImportanceChangeListener;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private List<FeedCategory> feedCategories;
    private CategoryImportanceChangeListener categoryImportanceChangeListener;

    public CategoryAdapter(List<FeedCategory> feedCategories, CategoryImportanceChangeListener categoryImportanceChangeListener) {
        this.feedCategories = feedCategories;
        this.categoryImportanceChangeListener = categoryImportanceChangeListener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.category_entry, parent, false);

        return new CategoryHolder(itemView, categoryImportanceChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        FeedCategory entry = feedCategories.get(position);

        holder.setName(entry.getName());
        holder.setImportance(entry.getFeedCategoryImportance());
    }

    @Override
    public int getItemCount() {
        return feedCategories.size();
    }

}
