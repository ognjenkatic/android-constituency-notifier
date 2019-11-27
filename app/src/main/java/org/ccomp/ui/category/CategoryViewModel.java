package org.ccomp.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.repository.CategoryRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class CategoryViewModel extends ViewModel {

    private CategoryRepository categoryRepository;

    private LiveData<List<FeedCategory>> feedCategories;

    public LiveData<List<FeedCategory>> getFeedCategories(){
        return feedCategories;
    }

    @Inject
    public CategoryViewModel(FeedCategoryDAO feedCategoryDAO, ExecutorService executorService){
        categoryRepository = new CategoryRepository(feedCategoryDAO,executorService);

        feedCategories = categoryRepository.getFeedCategories();

    }

    public void UpdateCategory(int position, FeedCategoryImportance importance){
        FeedCategory category = feedCategories.getValue().get(position);

        if (category.getFeedCategoryImportance() != importance) {
            category.setFeedCategoryImportance(importance);
            categoryRepository.updateCategory(category);
        }

    }

}
