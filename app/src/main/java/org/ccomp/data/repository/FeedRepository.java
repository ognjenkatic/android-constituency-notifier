package org.ccomp.data.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.feed.FeedItemCategory;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.feed.FeedParserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;


@Singleton
public class FeedRepository {

    private static final String TAG = "FeedRepository";

    private FeedItemDAO feedItemDAO;
    private FeedParserService feedParserService;
    private FeedCategoryDAO feedCategoryDAO;
    private FeedItemCategoryDAO feedItemCategoryDAO;
    private ExecutorService executorService;

    public FeedRepository(FeedItemDAO feedItemDAO, FeedParserService feedParserService, ExecutorService executorService, FeedItemCategoryDAO feedItemCategoryDAO
    ,FeedCategoryDAO feedCategoryDAO) {
        this.feedItemDAO = feedItemDAO;
        this.feedParserService = feedParserService;
        this.executorService = executorService;
        this.feedItemCategoryDAO = feedItemCategoryDAO;
        this.feedCategoryDAO = feedCategoryDAO;
    }


    public LiveData<FeedItem> loadFeedItem(int id){

        return feedItemDAO.selectById(id);
    }

    public void updateFeedItem(FeedItem feedItem){

        executorService.execute(()->{
            feedItemDAO.update(feedItem);
        });
    }

    public LiveData<Resource<List<FeedItem>>> loadFeedItems(String feedURL){

        return new NetworkBoundResource<List<FeedItem>, List<FeedItem>>() {

            @Override
            protected void saveCallResult(@NonNull List<FeedItem> items) {

                executorService.execute(()->{


                    if (items != null)
                        for(FeedItem item: items) {
                            if (feedItemDAO.selectCountByTitle(item.getTitle()) == 0){

                                item.setId(feedItemDAO.insert(item)[0]);
                                for(FeedCategory cat : item.getCategories()){
                                    FeedCategory fc = feedCategoryDAO.selectByName(cat.getName());
                                    if (fc == null){
                                        cat.setId(feedCategoryDAO.insert(cat)[0]);
                                    } else{
                                        cat.setId(fc.getId());
                                    }

                                    FeedItemCategory fic = new FeedItemCategory();

                                    fic.setCategoryId(cat.getId());
                                    fic.setFeedItemId(item.getId());

                                    feedItemCategoryDAO.insert(fic);
                                }


                            }

                        }
                });



            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<FeedItem>> loadFromDb() {
                return feedItemDAO.selectAllByCategoryName(FeedCategoryImportance.SHOW);


                //return feedItemDAO.selectAll();
            }

            @NonNull
            @Override
            protected Resource<LiveData<List<FeedItem>>> createCall() {
                try {


                    MutableLiveData<List<FeedItem>> ld = new MutableLiveData<>();

                    executorService.execute(()->{
                        try {
                            List<FeedItem> feedItems = feedParserService.parseStream(new URL(feedURL));
                            ld.postValue(feedItems);
                        } catch (MalformedURLException e) {
                            Log.d(TAG, "createCall: "+e.getMessage());
                            e.printStackTrace();
                        }
                    });

                    return Resource.success(ld);

                } catch(Exception mex){

                    Log.d(TAG, "createCall: "+mex.getMessage());
                    return null;
                }

            }


        }.getAsLiveData();
    }





}
