package com.michaelwheeler.networkinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import com.example.harshagoli.networkinggame.R;

import com.sarahansari.networkinggame.ContactDetails;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class Contact extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listView = (ListView) findViewById( R.id.listView );
        final ArrayList<String> arrayList= new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<com.example.harshagoli.networkinggame.Contact> contacts =
                realm.where(com.example.harshagoli.networkinggame.Contact.class).findAll();



        Log.d("Contact count", Integer.toString(contacts.size()));

        for(int i = 0 ; i < contacts.size(); i++)
        {
            arrayList.add(contacts.get(i).getName());
        }



        ArrayAdapter arrayAdapter=new ArrayAdapter( this,android.R.layout.simple_list_item_1,arrayList );
        listView.setAdapter( arrayAdapter );



        listView = (ListView) findViewById( R.id.listView );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,arrayList );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String myContact = (String) listView.getAdapter().getItem( position );
            }
        } );



        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText (Contact.this ,"clicked item" + i + " " + arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Contact.this, ContactDetails.class);
                intent.putExtra("ContactID", Integer.toString(i));
                intent.putExtra("ContactName", arrayList.get(i).toString());
                startActivity(intent);
            }
        } );

    }
}
