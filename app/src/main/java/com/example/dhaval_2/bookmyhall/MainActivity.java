package com.example.dhaval_2.bookmyhall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener myAuthListener;
    private TextView tv,title;
    private ActionBarDrawerToggle toggle;
    private FirebaseUser user;


///////////////////////////ONCREATE////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listenerForCardViews();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ////////////////Click on Navigation Head/////////////////////////

    navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View hView =  navigationView.getHeaderView(0);
        hView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LogIn.class);
                startActivity(i);


            }
        });



        ////////////////////////////AUTHSTATELISTENER///////////////////////////////////////////////////////////

        tv=(TextView) hView.findViewById(R.id.nav_userName);
         title=(TextView) hView.findViewById(R.id.nav_title);




   myAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

              user=firebaseAuth.getCurrentUser();
                if(user!=null && user.isEmailVerified())
                {
                    tv.setText(user.getEmail());
                    title.setText("");

                }
                else
                {
                    tv.setText("LogIn");
                    title.setText("Get a Personilised Experiance");

                }

            }
        };



        ////////////////////////////////////////////////////////


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_image)
            Toast.makeText(this,"xyz",Toast.LENGTH_LONG).show();

        else if(id==R.id.nav_about)
        {

           /* SignUp signup=new SignUp();
            FragmentManager fm=getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.relaytivelayout_for_fragment,signup,signup.getTag()).commit();
*/
            FirebaseAuth auth=FirebaseAuth.getInstance();
            auth.signOut();
            Toast.makeText(MainActivity.this,"SignOut",Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.nav_conferenceHall) {
            Toast.makeText(MainActivity.this, "Conference Hall Selected", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, HallList.class);
            startActivity(i);
            HallList.c=1;
        }
        else if(id==R.id.nav_marriageHall) {
            Toast.makeText(MainActivity.this,"Marriage Hall Selected",Toast.LENGTH_LONG).show();
            Intent  i=new Intent(MainActivity.this,HallList.class);
            startActivity(i);
            HallList.c=2;

        }
        else if(id==R.id.nav_partyHall)
        {
            Toast.makeText(MainActivity.this,"Party Hall Selected",Toast.LENGTH_LONG).show();
            Intent  i=new Intent(MainActivity.this,HallList.class);
            startActivity(i);
            HallList.c=3;
        }
        else if (id==R.id.nav_garden)
        {
            Toast.makeText(MainActivity.this,"Garden Seleted",Toast.LENGTH_LONG).show();
            Intent  i=new Intent(MainActivity.this,HallList.class);
            startActivity(i);
            HallList.c=4;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        auth.addAuthStateListener(myAuthListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.action_LogOut)
        {
            Toast.makeText(MainActivity.this,"SignOut",Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void listenerForCardViews()
            {

                CardView c1=(CardView) findViewById(R.id.Card_View_Demo);
                c1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Conference Hall Selected",Toast.LENGTH_SHORT).show();
                        Intent  i=new Intent(MainActivity.this,HallList.class);
                        startActivity(i);
                        HallList.c=1;
                    }
                });

                CardView c2=(CardView) findViewById(R.id.Card_View_Demo_1);
                c2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Marriage Hall Selected",Toast.LENGTH_LONG).show();
                        Intent  i=new Intent(MainActivity.this,HallList.class);
                        startActivity(i);
                        HallList.c=2;
                    }
                });

                CardView c3=(CardView) findViewById(R.id.Card_View_Demo_2);
                c3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Party Hall Selected",Toast.LENGTH_LONG).show();
                        Intent  i=new Intent(MainActivity.this,HallList.class);
                        startActivity(i);
                        HallList.c=3;
                    }
                });

                CardView c4=(CardView) findViewById(R.id.Card_View_Demo_3);
                c4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this,"Garden Seleted",Toast.LENGTH_LONG).show();
                        Intent  i=new Intent(MainActivity.this,HallList.class);
                        startActivity(i);
                        HallList.c=4;
                    }
                });
            }
    }



