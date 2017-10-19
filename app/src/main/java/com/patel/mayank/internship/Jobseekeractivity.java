package com.patel.mayank.internship;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Jobseekeractivity extends AppCompatActivity {

    private FirebaseAuth raut;
    private FirebaseAuth.AuthStateListener mautListner;


    ArrayList<Jobs> arrayList;

    TextView edt_test;
        private String uid;

    ListAdapter lstadpt;
    ListView lstjob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseekeractivity);


        lstjob = (ListView) findViewById(R.id.lst_jobs);

        raut = FirebaseAuth.getInstance();

        mautListner = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    uid = user.getUid();
                }else
                {

                }
            }
        };

        getJobs();
    }

    @Override
    public void onStart() {
        super.onStart();
        raut.addAuthStateListener(mautListner);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mautListner != null) {
            raut.removeAuthStateListener(mautListner);
        }
    }

    public void getJobs()
    {
        arrayList = new ArrayList<>();
        FirebaseDatabase fd  = FirebaseDatabase.getInstance();
        DatabaseReference db = fd.getReference("Intership");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds  : dataSnapshot.getChildren())
                {
                    String tit = ds.child("Title").getValue().toString();
                    String loc = ds.child("Location").getValue().toString();

                    System.out.println("Jobs "+tit+" "+loc);
                    arrayList.add(new Jobs(tit,loc));
                }

                for (int i=0;i<arrayList.size();i++)
                {
                    System.out.println(arrayList.get(i).getTitle());
                }

              lstadpt = new ListAdapter(arrayList,Jobseekeractivity.this);
                lstjob.setAdapter(lstadpt);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
