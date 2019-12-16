package org.ccomp.di.module;

import org.ccomp.ui.feed.FeedFragment;
import org.ccomp.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract FeedFragment contributeFeedFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}