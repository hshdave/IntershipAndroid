package com.patel.mayank.internship;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplieduserList extends Fragment {

    ListView lstApplied;
    ArrayList<Users> arrayList;
    ArrayList<String> arrayName;
    String key;

    Appliedadapter appliedadapter;


    public ApplieduserList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_applieduser_list);

        Bundle arguments = getArguments();

        key = arguments.getString("keyId");

        setList();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_applieduser_list,container,false);

        lstApplied = (ListView) view.findViewById(R.id.applied_lst);

        return view;
    }


    public void setList()
    {
        arrayName = new ArrayList<>();
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference db = fd.getReference("Jobseekers");

        FirebaseDatabase fd2 = FirebaseDatabase.getInstance();
        final DatabaseReference db2 =  fd2.getReference("User");

        db.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    System.out.println("Applier  "+ds.getValue().toString());
                    String jid = ds.getValue().toString();

                    db2.child(jid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {



                            String nm = dataSnapshot.child("name").getValue().toString();

                            System.out.println("Applier Name  "+nm);

                            arrayName.add(nm);

                            System.out.println("Array Name Array "+arrayName.toString());
                            appliedadapter =  new Appliedadapter(arrayName,getActivity());
                            lstApplied.setAdapter(appliedadapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
