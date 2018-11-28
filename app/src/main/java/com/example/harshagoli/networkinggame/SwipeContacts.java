package com.example.harshagoli.networkinggame;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.RealmQuery;


public class SwipeContacts extends AppCompatActivity  {

    private Realm realm = null;
    private RealmResults<Contact> listOfContacts;
    private ArrayAdapter<String> arrayAdapter;
    final ArrayList<String> listOfContactsArray = null;
    private int i;
    private int mYear, mMonth, mDay, mHour, mMinute;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.swipe_contacts);
//
//    }

//    private Realm realm = null;

    @Override
    public void onBackPressed() {
        //lol
    }

    SwipeFlingAdapterView flingContainer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_contacts);
        ButterKnife.inject(this);
        flingContainer = findViewById(R.id.frame);

        Realm.init(this);    //initialize to access database for this activity
        realm = Realm.getDefaultInstance();   //create a object for read and write database
        final ArrayList<String> listOfContactsArray = new ArrayList<String>();
        listOfContacts = realm.where(Contact.class)
                .equalTo("isIgnored", Boolean.FALSE)
//                .between("count", 0, 20)
                .findAll();

        for (Contact c: listOfContacts) {
            listOfContactsArray.add(c.getName());
        }


        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.contactName, listOfContactsArray);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                listOfContactsArray.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(SwipeContacts.this, "Left!");
            }

            @Override
            public void onRightCardExit(final Object dataObject) {
                makeToast(SwipeContacts.this, "Right!");

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                final TimerModel tm = new TimerModel();







                DatePickerDialog datePickerDialog = new DatePickerDialog(SwipeContacts.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tm.setYear(Integer.toString(year));
                                tm.setMonth(Integer.toString(monthOfYear));
                                tm.setDayOfMonth(Integer.toString(dayOfMonth));

                                //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                final Calendar c = Calendar.getInstance();
                                mHour = c.get(Calendar.HOUR_OF_DAY);
                                mMinute = c.get(Calendar.MINUTE);


                                // Launch Time Picker Dialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(SwipeContacts.this,
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                                  int minute) {

                                                //txtTime.setText(hourOfDay + ":" + minute);

                                                tm.setMinute(Integer.toString(minute));
                                                tm.setHourOfDay(Integer.toString(hourOfDay));



                                                tm.setContactName(dataObject.toString());
                                                realm.beginTransaction();
                                                realm.insertOrUpdate(tm);

                                                Log.d("loadSavedTimeHour",  realm.where(TimerModel.class).findFirst().getHourOfDay().toString());
                                                Log.d("loadSavedTimeMinute",  realm.where(TimerModel.class).findFirst().getMinute().toString());
                                                Log.d("loadSavedContactName",  realm.where(TimerModel.class).findFirst().getContactName().toString());
                                                Log.d("Dataobject", dataObject.toString());
                                            }

                                        }, mHour, mMinute, false);
                                timePickerDialog.show();



                            }





                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                listOfContactsArray.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(SwipeContacts.this, "Clicked!");
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }

    public void displayContactsPage(View view)
    {
        Intent intent = new Intent(SwipeContacts.this, com.michaelwheeler.networkinggame.Contact.class);
        startActivity(intent);
    }

}