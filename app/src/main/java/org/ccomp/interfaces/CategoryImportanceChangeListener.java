package org.ccomp.interfaces;

import org.ccomp.data.domain.feed.FeedCategoryImportance;

public interface CategoryImportanceChangeListener {

    void onChange(int position, FeedCategoryImportance importance);
}
