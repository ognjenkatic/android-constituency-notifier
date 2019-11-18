package org.ccomp.data.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.R;

import java.util.List;

public class FeedEntryHolder extends RecyclerView.ViewHolder {

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setSummary(String summary){
        this.summary.setText(summary);
    }

    public void setLink(String link){
        this.link = link;
    }

    public void setCategories(List<FeedCategory> categories){
        this.categories = categories;
    }

    private TextView title;
    private TextView summary;
    private String link;
    private List<FeedCategory> categories;

    public FeedEntryHolder(final View view) {
        super(view);

        title =   view.findViewById(R.id.entry_title);
        summary = view.findViewById(R.id.entry_summary);

        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                view.getContext().startActivity(intent);

            }
        });
    }


}
