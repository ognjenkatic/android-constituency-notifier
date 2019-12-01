package org.ccomp.di.module;

import org.ccomp.ui.category.CategoryFragment;
import org.ccomp.ui.feed.FeedFragment;
import org.ccomp.ui.feed.index.FeedIndexFragment;
import org.ccomp.ui.feed.item.FeedItemFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract FeedFragment contributeFeedFragment();

    @ContributesAndroidInjector
    abstract CategoryFragment contributeCategoryFragment();

    @ContributesAndroidInjector
    abstract FeedItemFragment contributeFeedItemFragment();

    @ContributesAndroidInjector
    abstract FeedIndexFragment contributeFeedIndexFragment();
}