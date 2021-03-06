package com.patel.mayank.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Jobseek extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView txt_nm,txt_email;
    String uid = "";

    Fragment fragment;

    private FirebaseAuth raut;
    private FirebaseAuth.AuthStateListener mautListner;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        raut = FirebaseAuth.getInstance();


        if (savedInstanceState == null) {
            Class fragmentClass;
            fragmentClass = Jobseekeractivity.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.jobseekcon, fragment).addToBackStack("fragBack").commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        txt_nm = (TextView)header.findViewById(R.id.txt_navname);
        txt_email = (TextView)header.findViewById(R.id.txt_navemail);

        uid = getUid();

       getFirebasedata();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.jobseekcon, fragment).addToBackStack("fragBack").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jobseek, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alljob) {

            Jobseekeractivity jobseekeractivity = new Jobseekeractivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.jobseekcon,jobseekeractivity).addToBackStack("fragBack").commit();

        }else if (id == R.id.nav_pro)
        {
            Editprofile editprofile = new Editprofile();
            getSupportFragmentManager().beginTransaction().replace(R.id.jobseekcon,editprofile).addToBackStack("fragBack").commit();


        }
        else if(id == R.id.nav_joblogout)
        {
            raut.signOut();

            Intent i = new Intent(Jobseek.this,LoginActivity.class);
            startActivity(i);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getFirebasedata()
    {

        System.out.println("USer ID "+uid);
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference db = fb.getReference("User");

        db.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    String nm = dataSnapshot.child("name").getValue().toString();
                    String em = dataSnapshot.child("email").getValue().toString();

                   txt_nm.setText(nm);
                   txt_email.setText(em);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public String getUid() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        System.out.println("From GetUid Method  "+id);

        return id;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
