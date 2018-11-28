package com.michaelwheeler.networkinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import com.example.harshagoli.networkinggame.R;
import com.sarahansari.networkinggame.ContactDetails;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listView = (ListView) findViewById( R.id.listView );
        final ArrayList<String> arrayList= new ArrayList<>();

        arrayList.add("John"  );
        arrayList.add("Obama"  );
        arrayList.add("Teddy"  );
        arrayList.add("Beyonce"  );
        arrayList.add("Jack Frost"  );
        arrayList.add("Shrek"  );
        arrayList.add("Chris"  );
        arrayList.add("Jaws"  );
        arrayList.add("Will "  );
        arrayList.add("Kim"  );
        arrayList.add("George"  );
        arrayList.add("Washington"  );
        arrayList.add("Dad"  );
        arrayList.add("The Weird Guy"  );

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
