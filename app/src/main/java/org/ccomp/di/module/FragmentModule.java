package org.ccomp.di.module;

import org.ccomp.ui.feed.FeedFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract FeedFragment contributeFeedFragment();
}
