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

public class quiz_submit_teacher extends AppCompatActivity {

    Firebase mref;
    EditText o1,o2,o3,o4,question,answer;
    private static AlertDialog dialog;
    TextView qno;
    int no_of_q,cq;
    String no;
    Button next,finish,home;
    String department,year,batch,quizname,code;
    char an[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submit_teacher);

        o1=(EditText)findViewById(R.id.bo1);
        o2=(EditText)findViewById(R.id.bo2);
        o3=(EditText)findViewById(R.id.bo3);
        o4=(EditText)findViewById(R.id.bo4);
        question=(EditText)findViewById(R.id.question);
        answer=(EditText)findViewById(R.id.answer);

        qno=(TextView)findViewById(R.id.qno);

        next=(Button)findViewById(R.id.next);
        finish=findViewById(R.id.fin);
        home=findViewById(R.id.home);
        finish.setEnabled(false);

        Intent ic=getIntent();
        no_of_q=
                Integer.parseInt(ic.getExtras().get("no_of_q").toString());
        an=new char[no_of_q];
        department=ic.getExtras().get("department").toString();
        year=ic.getExtras().get("year").toString();
        batch=ic.getExtras().get("batch").toString();
        quizname=ic.getExtras().get("quizname").toString();
        code=ic.getExtras().get("code").toString();

        Firebase.setAndroidContext(this);


        mref=new Firebase("https://e-class-9c419.firebaseio.com/Student");


        Firebase a7=mref.child(department).child(year).child(batch).child("quiz")
                .child(department+"_"+quizname).child("code");

        a7.setValue(code);
        a7=mref.child(department).child(year).child(batch).child("quiz")
                .child(department+"_"+quizname).child("nq");
        a7.setValue(String.valueOf(no_of_q));

      // for( i=1;i<=no_of_q;i++) {
        cq=1;
      //  showqu(cq);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showqu(cq);
                cq++;

                if(cq==no_of_q)
                {
                    next.setVisibility(View.INVISIBLE);
                    finish.setEnabled(true);
                    next.setEnabled(false);
                    finish.setVisibility(View.VISIBLE);
                }


            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showqu(cq);
                Firebase a2 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child("answer");
                Log.e("an",an.toString()+" ha "+new String(an));
                a2.setValue(new String(an));
                onBackPressed();

            }
        });
       /* home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent("com.example.bhonesh.e_class.teacher_dashboard");
                startActivity(i2);
            }
        });*/

    /*
*/


      // }


    }

    private void showqu(int i) {

        no = String.valueOf(i);


                Firebase a1 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("question");

                a1.setValue(question.getText().toString());


           /*    Firebase a2 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("answer");
*/
                an[i-1]=answer.getText().toString().charAt(0);

                Log.e("an",""+an[i-1]);

                Firebase a3 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("o1");

                a3.setValue(o1.getText().toString());


                Firebase a4 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("o2");

                a4.setValue(o2.getText().toString());


                Firebase a5 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("o3");

                a5.setValue(o3.getText().toString());


                Firebase a6 = mref.child(department).child(year).child(batch).child("quiz")
                        .child(department + "_" + quizname).child(no).child("o4");

                a6.setValue(o4.getText().toString());


        o4.setText("");
        o3.setText("");
        o2.setText("");
        o1.setText("");
        question.setText("");
        answer.setText("");
        qno.setText(String.valueOf(i+1));





    }

}
