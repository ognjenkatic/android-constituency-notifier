package org.ccomp.task;

import android.os.AsyncTask;

import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.service.feed.FeedParserService;

import java.net.URL;
import java.util.List;

import javax.inject.Inject;

public class FeedFetchTask extends AsyncTask<URL,Void, List<FeedItem>> {

    @Inject
    public FeedParserService feedParserService;

    private Exception exception;

    @Override
    protected List<FeedItem> doInBackground(URL... urls) {
        try {
            List<FeedItem> feedItems = new FeedParserService().parseStream(urls[0]);

            return feedItems;
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }
}
