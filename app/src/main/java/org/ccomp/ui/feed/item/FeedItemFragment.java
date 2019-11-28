package org.ccomp.ui.feed.item;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ccomp.R;
import org.ccomp.data.application.NotificationData;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.service.notification.NotificationService;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedItemFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    NotificationService notificationService;

    private FeedItemViewModel mViewModel;

    private TextView descriptionText;


    public static FeedItemFragment newInstance() {
        return new FeedItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_item, container, false);

        descriptionText = view.findViewById(R.id.feed_item_description);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedItemViewModel.class);

        int id = getArguments().getInt("itemId");

        mViewModel.setFeedItem(id);

        mViewModel.getFeedItem().observe(this, data ->{
            descriptionText.setText(data.getDescription());


        });

        getView().findViewById(R.id.visit_feed_item).setOnClickListener((param)->{


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mViewModel.getFeedItem().getValue().getLink()));
            getView().getContext().startActivity(intent);
        });

    }

}
