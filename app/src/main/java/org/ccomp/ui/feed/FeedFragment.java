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
<<<<<<< Updated upstream

public class FeedFragment extends Fragment {
=======
import org.ccomp.data.adapter.FeedEntryAdapter;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.Resource;
import org.ccomp.factory.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;
>>>>>>> Stashed changes

    private FeedViewModel mViewModel;

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
<<<<<<< Updated upstream
        mViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
=======

        initialiseViewModel();
        initialiseView();

    }

    private void initialiseView(){

        mViewModel.getFeedItems().observe(this, listResource -> {
            FeedEntryAdapter faa = new FeedEntryAdapter(listResource.data);
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
>>>>>>> Stashed changes
    }

}
