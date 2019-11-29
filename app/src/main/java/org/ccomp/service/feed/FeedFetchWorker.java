package org.ccomp.service.feed;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.squareup.inject.assisted.Assisted;
import com.squareup.inject.assisted.AssistedInject;

import org.ccomp.MainActivity;
import org.ccomp.R;
import org.ccomp.data.application.NotificationData;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.interfaces.ChildWorkerFactory;
import org.ccomp.service.notification.NotificationService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

public class FeedFetchWorker extends Worker {


    public FeedParserService feedParserService;


    @AssistedInject
    public FeedFetchWorker(@Assisted Context context, @Assisted WorkerParameters parameters, FeedParserService feedParserService){
        super(context, parameters);
        this.feedParserService = feedParserService;
    }

    @NonNull
    @Override
    public Result doWork() {

        try {

            List<FeedItem> feeds = new FeedParserService().parseStream(new URL("https://certrs.org/feed/atom"));



            NotificationData nd = new NotificationData();
            nd.setTitle("New Feed Alert");
            nd.setText(feeds.get(0).getTitle());
            nd.setIcon(R.drawable.ic_menu_camera);
            nd.setDestination(R.id.nav_feed_item);
            nd.setGraph(R.navigation.mobile_navigation);
            nd.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Bundle args = new Bundle();
            args.putInt("itemId", 4);
            nd.setArguments(args);

            new NotificationService(getApplicationContext()).createNotification(nd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @AssistedInject.Factory
    public interface Factory extends ChildWorkerFactory {

    }


}


