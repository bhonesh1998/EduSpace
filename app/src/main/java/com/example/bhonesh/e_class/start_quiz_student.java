package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class start_quiz_student extends AppCompatActivity {

    EditText code,name;
    Button start;
    String sid,year,department,batch,s,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz_student);

        Intent i=getIntent();
        sid=i.getStringExtra("id");
        year=i.getStringExtra("year");
        department=i.getStringExtra("dep");
        batch=i.getStringExtra("batch");

        start=(Button)findViewById(R.id.start);
        code=(EditText)findViewById(R.id.textView19);
        name=(EditText)findViewById(R.id.qname);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s=code.getText().toString();
                s2=name.getText().toString();
                s2=department+"_"+s2;
                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Student");

                DatabaseReference ref2=ref1.child(department).child(year).child(batch)
                        .child("quiz").child(s2).child("code");

                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        String datacode=dataSnapshot.getValue().toString();
                        if(datacode.equals(s)){
                            Intent ii=new Intent("com.example.bhonesh.e_class.quiz_submit_student");
                            ii.putExtra("qname",s2);
                            ii.putExtra("id",sid);
                            ii.putExtra("dep",department);
                            ii.putExtra("year",year);
                            ii.putExtra("batch",batch);
                            code.setText("");
                            name.setText("");
                            startActivity(ii);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });

    }
}
