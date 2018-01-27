package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class create_quiz_teacher extends AppCompatActivity {


    Button Submit;
   private EditText quiznameforq,noforq,batchforq,yearforq,codeforq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz_teacher);

        quiznameforq=(EditText)findViewById(R.id.sub);
        yearforq=(EditText)findViewById(R.id.e2);
        batchforq=(EditText)findViewById(R.id.e3);
        noforq=(EditText)findViewById(R.id.pass);
        codeforq=(EditText)findViewById(R.id.e5);

        Submit=(Button)findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=getIntent();

                String department =i.getExtras().get("department").toString();



                String s1=quiznameforq.getText().toString();

                String s2=batchforq.getText().toString();

                String s3=yearforq.getText().toString();

                String s4=noforq.getText().toString();

                String s5=codeforq.getText().toString();


                Intent i1=new Intent("com.example.bhonesh.e_class.quiz_submit_teacher");

                i1.putExtra("no_of_q",s4);
                i1.putExtra("department",department);
                i1.putExtra("batch",s2);
                i1.putExtra("year",s3);
                i1.putExtra("quizname",s1);
                i1.putExtra("code",s5);
                quiznameforq.setText("");
                noforq.setText("");
                batchforq.setText("");
                yearforq.setText("");
                codeforq.setText("");
                startActivity(i1);


            }
        });







    }
}
