package org.ccomp.data.repository;

import androidx.lifecycle.LiveData;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.domain.feed.FeedCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

@Singleton
public class CategoryRepository {

    private static final String TAG = "CategoryRepository";

    private FeedCategoryDAO feedCategoryDAO;
    private ExecutorService executorService;

    public CategoryRepository(FeedCategoryDAO feedCategoryDAO, ExecutorService executorService){
        this.feedCategoryDAO = feedCategoryDAO;
        this.executorService = executorService;
    }

    public LiveData<List<FeedCategory>> getFeedCategories(){
        return feedCategoryDAO.selectAll();
    }

    public void updateCategory(FeedCategory feedCategory){

        executorService.execute(()->{
            feedCategoryDAO.update(feedCategory);
        });

    }
}
