package com.patel.mayank.internship;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Editprofile extends Fragment {


    EditText edt_nm,edt_bod,edt_bio,edt_skill;
    Button btn_save;

    FirebaseDatabase fd = FirebaseDatabase.getInstance();
    DatabaseReference db = fd.getReference("User");

    String uid = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_editprofile);

        uid = getUid();
        setData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_editprofile,container,false);

        edt_nm = (EditText) view.findViewById(R.id.epro_name);
        edt_bod = (EditText) view.findViewById(R.id.epro_bod);
        edt_bio = (EditText) view.findViewById(R.id.epro_bio);
        edt_skill = (EditText) view.findViewById(R.id.epro_Skill);
        btn_save = (Button) view.findViewById(R.id.epro_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });

        return view;
    }



    public String getUid() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        return id;
    }

    public void setData()
    {


        db.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nm,bod,bio,skill;

                nm= dataSnapshot.child("name").getValue().toString();
                bod= dataSnapshot.child("bod").getValue().toString();


                try {
                        bio = dataSnapshot.child("bio").getValue().toString();
                        edt_bio.setText(bio);

                        skill = dataSnapshot.child("skill").getValue().toString();
                        edt_skill.setText(skill);

                    edt_nm.setText(nm);
                    edt_bod.setText(bod);

                }catch (NullPointerException e)
                {
                    edt_skill.setText("");
                    edt_bio.setText("");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

  public void saveData()
  {
      String nnam,nbday,nskills,nbio;

      nnam = edt_nm.getText().toString();
      nbday = edt_bod.getText().toString();
      nskills = edt_skill.getText().toString();
      nbio = edt_bio.getText().toString();

      Users users = new Users(nnam,nbday,nbio,nskills);

      Map<String, Object> usermap = users.toMap();

     db.child(uid).updateChildren(usermap);

  }

}
