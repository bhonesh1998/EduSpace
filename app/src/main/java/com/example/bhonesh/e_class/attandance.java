package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class attandance extends AppCompatActivity {

    Button open,close;
    EditText subject,year,batch,pass;
    String department,sub,y,bat,pas;
    Firebase a7,mref;
    DatabaseReference ref1;
    int  x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandance);

        open=findViewById(R.id.open);
        close=findViewById(R.id.close);
        subject=findViewById(R.id.sub);
        year=findViewById(R.id.e2);
        batch=findViewById(R.id.e3);
        pass=findViewById(R.id.pass);
        close.setEnabled(false);


        Intent i=getIntent();
        department =i.getExtras().get("department").toString();

        Firebase.setAndroidContext(this);


        mref=new Firebase("https://e-class-9c419.firebaseio.com/Attandence")
                ;

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                y=year.getText().toString();
                bat=batch.getText().toString();
                sub=subject.getText().toString();
                pas=pass.getText().toString();
                increase();
                a7=mref.child(department).child(y).child(bat).child(sub).child("pass");
                a7.setValue(pas);
                open.setEnabled(false);
                open.setVisibility(View.INVISIBLE);
                close.setVisibility(View.VISIBLE);
                close.setEnabled(true);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a7.setValue(pas+"bat");
            }
        });


    }

    private void increase() {
        x=0;
        ref1 = FirebaseDatabase.getInstance().getReference("Attandence")
                .child(department).child(y).child(bat).child(sub).child("classNo");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long t=(long)dataSnapshot.getValue();
                if(x==0){
                    mref.child(department).child(y).child(bat).child(sub).child("classNo").setValue(t+1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
