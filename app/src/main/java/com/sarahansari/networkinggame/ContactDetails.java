package com.sarahansari.networkinggame;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.harshagoli.networkinggame.R;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class ContactDetails extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("YV2m1Sbmd6Ekv0eVonISZko18", "60NG42t1H2pAi8iVJLduJyQh81i8i6PmYVMSX6RpalCV0OKzzV"))
                .debug(true)
                .build();
        Twitter.initialize(config);

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("SarahAn93468032")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);


        setContentView(R.layout.activity_contact_details);
    }
}
