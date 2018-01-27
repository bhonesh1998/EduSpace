package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

public class quiz_submit_student extends AppCompatActivity {


    Button bo1,bo2,bo3,bo4,next,submit;
    TextView questext;
    String sid,year,department,batch,qname,wan;
    DatabaseReference ref2,noq,ans;
    int quno_int,total=2;
    char an[],wn[];
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submit_student);


        bo1=(Button)findViewById(R.id.bo1);
        bo2=(Button)findViewById(R.id.bo2);
        bo3=(Button)findViewById(R.id.bo3);
        bo4=(Button)findViewById(R.id.bo4);
        next=(Button)findViewById(R.id.next);
        submit=(Button)findViewById(R.id.sub);

        questext=(TextView) findViewById(R.id.questext);


        Intent i=getIntent();
        sid=i.getStringExtra("id");
        year=i.getStringExtra("year");
        department=i.getStringExtra("dep");
        batch=i.getStringExtra("batch");
        qname=i.getStringExtra("qname");

        Firebase.setAndroidContext(this);


        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Student");
        // ref1.keepSynced(true);

        ref2=ref1.child(department).child(year).child(batch).child("quiz")
                .child(qname);

        noq=ref2.child("nq");

        noq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total=Integer.valueOf((String) dataSnapshot.getValue());
                an=new char[(int)Integer.valueOf(total)];

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ans=ref2.child("answer");
        ans.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               wan=(String)dataSnapshot.getValue();
                //an=new char[(int)Integer.valueOf(total)];

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        quno_int=1;
        putque("1");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quno_int++;
                if(quno_int==total){
                    next.setVisibility(View.INVISIBLE);
                    submit.setEnabled(true);
                    next.setEnabled(false);
                    submit.setVisibility(View.VISIBLE);
                }
                putque(String.valueOf(quno_int));

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to do on submit
                int mark=0;wn=wan.toCharArray();
                for(int x=0;x<an.length;x++){
                    if(wn[x]==an[x])
                        mark++;
                }
                Intent i=new Intent("com.example.bhonesh.e_class.result");
                i.putExtra("total",an.length);
                i.putExtra("marks",mark);
                startActivity(i);

            }
        });

        bo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                an[quno_int-1]='1';
            }
        });
        bo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                an[quno_int-1]='2';
            }
        });
        bo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                an[quno_int-1]='3';
            }
        });
        bo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                an[quno_int-1]='4';
            }
        });


    }

    private void putque(String qno) {

        DatabaseReference qu=ref2.child(qno);
        // ref2.keepSynced(true);


        qu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                Log.e("error","data "+dataSnapshot.getValue());

                Map<String,String> m = (Map<String, String>) dataSnapshot.getValue();

                String answer=m.get("answer");
                String question=m.get("question");
                String o1=m.get("o1");
                String o2=m.get("o2");
                String o3=m.get("o3");
                String o4=m.get("o4");


                bo1.setText(o1);
                bo2.setText(o2);
                bo3.setText(o3);
                bo4.setText(o4);
                questext.setText(question);





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
