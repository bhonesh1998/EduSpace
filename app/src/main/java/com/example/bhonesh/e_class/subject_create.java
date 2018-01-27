package com.example.bhonesh.e_class;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.Map;

public class subject_create extends AppCompatActivity {

    Button Submit;
    String s3,s2,s1,department;
    private EditText batchforq,subjectn,yearforq;
    DatabaseReference ref1,ref2;
    Map<String, String> m;
    Firebase mref,mref1,mref2;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_create);


        subjectn=(EditText)findViewById(R.id.sub);
        yearforq=(EditText)findViewById(R.id.e2);
        batchforq=(EditText)findViewById(R.id.e3);

        Submit=(Button)findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd = new ProgressDialog(subject_create.this);

                // pd.setTitle("dashboard");
                pd.setMessage("logging in");
                pd.show();

                Intent i=getIntent();

                department =i.getExtras().get("department").toString();

                s1=subjectn.getText().toString();

                s2=batchforq.getText().toString();

                s3=yearforq.getText().toString();


                ref1 = FirebaseDatabase.getInstance().getReference("Student")
                        .child(department).child(s3).child(s2);

                ref2 = ref1.child("Student");

                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.e("error", "data " + dataSnapshot.getValue());

                        m = (Map<String, String>) dataSnapshot.getValue();
                        // dataSnapshot.getValue(Map.class);
                        mref=new Firebase("https://e-class-9c419.firebaseio.com/Attandence");


                        mref1=mref.child(department).child(s3).child(s2).child(s1);
                        mref1.child("classNo").setValue(0);
                        mref2=mref1.child("Student");
                        for(Map.Entry ma:m.entrySet()){
                            mref2.child(ma.getKey().toString()).setValue(0);
                        }
                        pd.dismiss();

                        Toast.makeText(subject_create.this,"Completed",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });








                batchforq.setText("");
                yearforq.setText("");



            }
        });




    }
}
