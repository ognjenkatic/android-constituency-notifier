package org.ccomp.data.repository;

import androidx.lifecycle.LiveData;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.domain.feed.FeedCategory;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class CategoryRepository {

    private static final String TAG = "CategoryRepository";

    private FeedCategoryDAO feedCategoryDAO;

    public CategoryRepository(FeedCategoryDAO feedCategoryDAO){
        this.feedCategoryDAO = feedCategoryDAO;
    }

    public LiveData<List<FeedCategory>> getFeedCategories(){
        return feedCategoryDAO.selectAll();
    }
}
