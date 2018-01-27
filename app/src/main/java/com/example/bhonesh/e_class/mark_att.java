package com.example.bhonesh.e_class;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class mark_att extends AppCompatActivity {

    int x;
    long tc,oc, p;
    Button mark;
    EditText pass,subt;
    String sid,year,department,batch,s,s2;
    DatabaseReference ref1,ref2,ref3,ref4;
    Firebase mref,f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_att);

        pass=findViewById(R.id.code);
       subt=(EditText)findViewById(R.id.code3);
        mark=findViewById(R.id.mark);

        Intent i=getIntent();
        sid=i.getStringExtra("id");
        year=i.getStringExtra("year");
        department=i.getStringExtra("dep");
        batch=i.getStringExtra("batch");
      //  s=pass.getText().toString().trim();
      //  s2=subt.getText().toString();

     //   Log.e("Sub",s2);


        ref1 = FirebaseDatabase.getInstance().getReference("Attandence");
      //  Log.e("ref",ref1.toString());
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 s=pass.getText().toString();
                 s2=subt.getText().toString();

                ref3=ref1.child(department).child(year).child(batch).child(s2).child("classNo");
                ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tc=(long)dataSnapshot.getValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                ref4=ref1.child(department).child(year).child(batch).child(s2).child("Student").child(sid);
                ref4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        oc=(long)dataSnapshot.getValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

if(tc==0){
    p=0;
}





                ref2=ref1.child(department).child(year).child(batch).child(s2).child("pass");
                Log.e("ref",ref2.toString());
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("pass",dataSnapshot.getValue()+" tpe "+s);

                        String x=dataSnapshot.getValue().toString();
                        if(!x.equals(s)){
                            pass.setBackgroundColor(Color.parseColor("#FFEF767C"));
                            Toast.makeText(mark_att.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                        }else{
                            markIt();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



    }

    private void markIt() {
        x=0;
        mref=new Firebase("https://e-class-9c419.firebaseio.com/Attandence");
        f1=mref.child(department).child(year).child(batch).child(s2).child("Student").child(sid);
        ref3=ref1.child(department).child(year).child(batch).child(s2).child("Student").child(sid);
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("value",ref3.toString()+"_"+dataSnapshot.getValue());

                long at=(long)dataSnapshot.getValue();
                if(x==0) {
                    f1.setValue(at + 1);
                    x++;
                }
                onBackPressed();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
