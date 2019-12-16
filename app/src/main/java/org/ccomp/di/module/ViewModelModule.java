package org.ccomp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.ccomp.di.ViewModelKey;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.category.CategoryViewModel;
import org.ccomp.ui.feed.FeedViewModel;
import org.ccomp.ui.feed.index.FeedIndexFragment;
import org.ccomp.ui.feed.index.FeedIndexViewModel;
import org.ccomp.ui.feed.item.FeedItemViewModel;
import org.ccomp.ui.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel.class)
    protected abstract ViewModel feedViewModel(FeedViewModel feedViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    protected abstract ViewModel categoryViewModel(CategoryViewModel categoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FeedItemViewModel.class)
    protected abstract ViewModel feedItemViewModel(FeedItemViewModel feedItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FeedIndexViewModel.class)
    protected abstract ViewModel feedIndexViewModel(FeedIndexViewModel feedIndexViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    protected abstract ViewModel homeViewModel(HomeViewModel homeViewModel);


}