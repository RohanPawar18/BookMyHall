package com.example.dhaval_2.bookmyhall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HallList extends AppCompatActivity {


    ProgressDialog pd;
static int c;
    ListView lv;
    DatabaseReference dref;
ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_list);


        pd=new ProgressDialog(this);
        pd.setMessage("Loading");
       // Toast.makeText(HallList.this, "Hello ", Toast.LENGTH_SHORT).show();






    //img=(ImageView)findViewById(R.id.img);
        lv=(ListView) findViewById(R.id.listView);
        //list= (ListView) findViewById(R.id.listView);
       // Toast.makeText(HallList.this, "Hello ", Toast.LENGTH_SHORT).show();







        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                //Toast.makeText(HallList.this, "Hello "+lv.getItemAtPosition(itemPosition).toString(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(HallList.this, Info.class);
                startActivity(i);

            }
        });


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        lv.setAdapter(adapter);

         pd.show();


        switch(c)
        {
            case 1:dref= FirebaseDatabase.getInstance().getReference().child("ListOfHalls").child("ConferenceHall");
                break;
            case 2:dref= FirebaseDatabase.getInstance().getReference().child("ListOfHalls").child("MarriageHall");break;
            case 3:dref= FirebaseDatabase.getInstance().getReference().child("ListOfHalls").child("PartyHall");break;
            case 4:dref= FirebaseDatabase.getInstance().getReference().child("ListOfHalls").child("Garden");break;

        }


       dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                 String value= dataSnapshot.getValue(String.class);

               list.add(value);

                adapter.notifyDataSetChanged();

                pd.dismiss();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
                list.remove(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










    }

public void Photo()
    {

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.itemsearch,menu);
        MenuItem searchItem =menu.findItem(R.id.item_search);

        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> templist = new ArrayList<String>();

                for (String temp : list)
                    if (temp.toLowerCase().contains(newText.toLowerCase())) {
                        templist.add(temp);
                    }
              ArrayAdapter<String>  adapter=new ArrayAdapter<String>(HallList.this,android.R.layout.simple_dropdown_item_1line,templist);
                lv.setAdapter(adapter);
                return  true;


            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}

class Display{
    String name;
    ImageView img;

    Display(String name,ImageView img){
        this.name=name;
        this.img=img;
    }
}
