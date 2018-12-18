package com.example.harshagoli.networkinggame;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sarahansari.networkinggame.ContactDetails;

import io.realm.Realm;
import io.realm.RealmResults;


public class OnboardingFlow extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Button importContacts;
    private Realm realm = null;
    private Config config = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_flow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        Realm.init(this);    //initialize to access database for this activity
        //Realm.deleteRealm(Realm.getDefaultConfiguration()); //do not do this it clears out entire thing
        realm = Realm.getDefaultInstance();   //create a object for read and write database

        // Create Config
        RealmResults<Config> configs =  realm.where(Config.class)
                .findAll();

//        if (configs.size() < 1) {
//            realm.beginTransaction();
//            Config conf = realm.createObject(Config.class);
//            this.config = conf;
//            realm.commitTransaction();
//        }


        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.WRITE_CONTACTS,

        };

        if(!hasPermissions(this, PERMISSIONS)){
//            for (String p: PERMISSIONS) {
//                ContextCompat.requestPermissions(this, p);
//            }
            ContextCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_onboarding_flow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_profiledetails) {
            displayProfileDetailsPage(this.findViewById(R.id.action_profiledetails));
        }

        if (id == R.id.action_contact) {
            displayContactsPage(this.findViewById(R.id.action_contact));
        }

        return super.onOptionsItemSelected(item);
    }

    public static class WelcomeFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public WelcomeFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WelcomeFragment newInstance(int sectionNumber) {
            WelcomeFragment fragment = new WelcomeFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding_flow, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a WelcomeFragment (defined as a static inner class below).
            Fragment f = null;
            switch(position) {
                case 0:
                    f = WelcomeFragment.newInstance(position);
                    break;
                case 1:
                    f = SetUpOAuthFragment.newInstance(position);
                    break;
                default:
                    //It's fucked
            }

            return f;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    public static class SetUpOAuthFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Button importContacts;
        private Realm realm = null;

        public SetUpOAuthFragment() {
            realm = Realm.getDefaultInstance();   //create a object for read and write database
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SetUpOAuthFragment newInstance(int sectionNumber) {
            SetUpOAuthFragment fragment = new SetUpOAuthFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_set_up_oauth_flow, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            importContacts = (Button) rootView.findViewById(R.id.ImportContactsButton);
            importContacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loadContacts(1);
                    Intent k = new Intent(getActivity(), SwipeContacts.class);
                    startActivity(k);
                }
            });
            return rootView;
        }


        private void loadContacts (int x ){

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Contact contact = new Contact();
                    StringBuilder builder = new StringBuilder();
                    ContentResolver resolver = getActivity().getContentResolver();
                    Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);
                    while(cursor.moveToNext()){
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = " ";
                        int hasNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                        if( hasNumber > 0)
                        {
                            Cursor cursor1 = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ", new String[]{id}, null);
                            while (cursor1.moveToNext())
                            {
                                phoneNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                builder.append("Contact : ").append(name).append(", PhoneNumber : ").append(phoneNumber).append("\n\n");
                            }
                            cursor1.close();
                        }
                        else
                        {
                            phoneNumber = " ";
                        }
                        contact.setID(id);
                        contact.setName(name);
                        contact.setPhoneNumber(phoneNumber);
                        contact.setIgnored(false);
                        realm.insertOrUpdate(contact);
                        builder.append("Contact : ").append(name).append(", PhoneNumber : ").append(phoneNumber).append("\n\n");

                    }
                    cursor.close();
                    Log.d("loadContacts",  realm.where(Contact.class).findFirst().getPhoneNumber().toString());

                }
            });



        }


    }
    public void displayProfileDetailsPage(View view)
    {
        Intent intent = new Intent(OnboardingFlow.this, ContactDetails.class);
        startActivity(intent);
    }

    public void displayContactsPage(View view)
    {
        Intent intent = new Intent(OnboardingFlow.this, com.michaelwheeler.networkinggame.Contact.class);
        startActivity(intent);
    }
}
