package org.ccomp.ui.feed.index;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import org.ccomp.R;
import org.ccomp.data.adapter.FeedAdapter;
import org.ccomp.data.adapter.FeedEntryAdapter;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.feed.FeedViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedIndexFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FeedIndexViewModel mViewModel;

    private View view;
    public static FeedIndexFragment newInstance() {
        return new FeedIndexFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed_index, container, false);

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedIndexViewModel.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedIndexViewModel.class);

        Button button = (Button)view.findViewById(R.id.add_feed_button);

        TextInputEditText textInput = (TextInputEditText)view.findViewById(R.id.feed_url);

        button.setOnClickListener((data)->{

            String url = textInput.getText().toString();

            Feed feed = new Feed();
            feed.setLink(url);
            feed.setTitle("Title");
            feed.setSubtitle("Exciting subtitle");
            mViewModel.addFeed(feed);
        });

        mViewModel.getFeeds().observe(this, listResource -> {



            FeedAdapter fa = new FeedAdapter(listResource);
            RecyclerView lv = view.findViewById(R.id.feed_list);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            lv.setLayoutManager(llm);
            lv.setAdapter(fa);
            fa.notifyDataSetChanged();
        });
    }

}
