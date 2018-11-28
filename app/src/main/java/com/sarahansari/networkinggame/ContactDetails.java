package com.sarahansari.networkinggame;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.harshagoli.networkinggame.R;
import com.sarahansari.networkinggame.model.Timer;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.realm.Realm;

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

        TextView userData = (TextView)findViewById(R.id.textViewShowContactData);

        String userId= getIntent().getExtras().getString("ContactID");
        String userName = getIntent().getExtras().getString("ContactName");

        userData.setText("The contact ID clicked is " +userId + " And the name is " +userName);

        Timer timer = new Timer();

        timer.setDateStamp("11/28/2018 22:10:25");

        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        final Timer timer1 = realm.copyToRealm(timer);

        realm.commitTransaction();

    }
}
