package org.ccomp.data.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.R;
import org.ccomp.ui.feed.FeedFragmentDirections;

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

    private View view;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;

        if (isRead) {
            view.setBackgroundColor(view.getResources().getColor(R.color.primaryColor));
        }
        else {
            view.setBackgroundColor(view.getResources().getColor(R.color.secondaryColor));
        }
    }

    private boolean isRead;

    public FeedEntryHolder(final View view) {
        super(view);

        title =   view.findViewById(R.id.entry_title);
        summary = view.findViewById(R.id.entry_summary);

        this.view = view;


        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FeedFragmentDirections.ActionNavFeedToNavFeedItem act =  FeedFragmentDirections.actionNavFeedToNavFeedItem();
                act.setItemId((int)id);
                Navigation.findNavController(v).navigate(act);
            }
        });
    }


}
