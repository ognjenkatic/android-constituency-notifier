package org.ccomp.ui.feed;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ccomp.R;
import org.ccomp.factory.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FeedViewModel mViewModel;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialiseViewModel();
    }

    private void initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel.class);
        /*
        movieListViewModel.getMoviesLiveData().observe(this, resource -> {
            if(resource.isLoading()) {
                displayLoader();

            } else if(!resource.data.isEmpty()) {
                updateMoviesList(resource.data);

            } else handleErrorResponse();
        });

        movieListViewModel.loadMoreMovies();
        */
    }

}
