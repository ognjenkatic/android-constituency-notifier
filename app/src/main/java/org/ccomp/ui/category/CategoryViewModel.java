package org.ccomp.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoryViewModel extends ViewModel {

    private CategoryRepository categoryRepository;

    private LiveData<List<FeedCategory>> feedCategories;

    public LiveData<List<FeedCategory>> getFeedCategories(){
        return feedCategories;
    }

    @Inject
    public CategoryViewModel(FeedCategoryDAO feedCategoryDAO){
        categoryRepository = new CategoryRepository(feedCategoryDAO);

        feedCategories = categoryRepository.getFeedCategories();

    }

}
