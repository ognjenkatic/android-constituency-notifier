package org.ccomp.ui.category;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ccomp.R;
import org.ccomp.data.adapter.CategoryAdapter;
import org.ccomp.data.adapter.FeedEntryAdapter;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.interfaces.CategoryImportanceChangeListener;
import org.ccomp.ui.feed.FeedViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CategoryFragment extends DaggerFragment implements CategoryImportanceChangeListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private CategoryViewModel mViewModel;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        this.view = view;
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialiseViewModel();
        initialiseView();
    }

    private void initialiseView(){

        mViewModel.getFeedCategories().observe(this, categoryList -> {

            CategoryAdapter faa = new CategoryAdapter(categoryList, this);
            RecyclerView lv = view.findViewById(R.id.category_entry_list);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            lv.setLayoutManager(llm);
            lv.setAdapter(faa);

            faa.notifyDataSetChanged();
        });
    }

    private void initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoryViewModel.class);

    }


    @Override
    public void onChange(int position, FeedCategoryImportance importance) {
        mViewModel.UpdateCategory(position, importance);
    }
}
