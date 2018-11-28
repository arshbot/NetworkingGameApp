package com.sarahansari.networkinggame;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.harshagoli.networkinggame.R;

public class ContactDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_contact_details);

        TextView userData = (TextView)findViewById(R.id.textViewShowContactData);

        String userId= getIntent().getExtras().getString("ContactID");
        String userName = getIntent().getExtras().getString("ContactName");

        userData.setText("The contact ID clicked is " +userId + " And the name is " +userName);


    }
}
