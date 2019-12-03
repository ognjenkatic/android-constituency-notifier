package org.ccomp.utility;

import org.ccomp.data.domain.feed.FeedCategory;

import java.util.Comparator;

public class CategoryComparator implements Comparator<FeedCategory> {
    @Override
    public int compare(FeedCategory feedCategory, FeedCategory other) {
        return feedCategory.getName().compareTo(other.getName());
    }
}
