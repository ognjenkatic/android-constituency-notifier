package org.ccomp.service.feed;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

public class FeedFetchWorker extends Worker {

    @Inject
    public FeedParserService feedParserService;

    public FeedFetchWorker(@NonNull  Context context,@NonNull WorkerParameters params){
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            feedParserService.parseStream(new URL("https://certrs.org/feed/atom"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
