package org.ccomp.data.adapter;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.FeedCategoryImportance;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public void setName(String name){
        this.name.setText(name);
    }

    public void setImportance(FeedCategoryImportance importance){
        this.importanceGroup.check(importance.ordinal());
    }

    private TextView name;
    private RadioGroup importanceGroup;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.category_entry_name);
    }
}
