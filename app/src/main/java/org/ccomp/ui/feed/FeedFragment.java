package org.ccomp.ui.feed;

import androidx.lifecycle.Observer;
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

import org.ccomp.R;

import org.ccomp.data.adapter.FeedEntryAdapter;
import org.ccomp.factory.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;


    private FeedViewModel mViewModel;

    private int feedId;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        this.view = view;
        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialiseViewModel();
        initialiseView();

    }

    private void initialiseView(){

        mViewModel.getFeedItems(feedId).observe(this, listResource -> {

            FeedEntryAdapter faa = new FeedEntryAdapter(listResource);
            RecyclerView lv = view.findViewById(R.id.entry_list);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            lv.setLayoutManager(llm);
            lv.setAdapter(faa);
            faa.notifyDataSetChanged();
        });
    }

    private void initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel.class);

        feedId = getArguments().getInt("feedId");


    }

}
