package org.ccomp.ui.feed;

import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedDAO;

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    @Inject
    public FeedViewModel(FeedDAO feedDAO) {

    }
}
