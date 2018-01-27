package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class teacher_dashboard extends AppCompatActivity {

    String department;
    Button create_quiz,attand,subj,assign,chatroom;
    TextView disname,disq,dissubject,disid,disdepart,disemail;
    ImageView vi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        chatroom=(Button)findViewById(R.id.chatroom);
        subj=(Button)findViewById(R.id.sub);
        assign=findViewById(R.id.assignment);
        create_quiz=(Button)findViewById(R.id.create_quiz);
        attand=(Button)findViewById(R.id.attand);
        disname=(TextView)findViewById(R.id.disname);
        dissubject=(TextView)findViewById(R.id.dissubject);
        disq=(TextView)findViewById(R.id.disq);
        disid=(TextView)findViewById(R.id.disid);
        disdepart=(TextView)findViewById(R.id.disdepart);
        disemail=(TextView)findViewById(R.id.disemail);


        //image view
        vi=(ImageView)findViewById(R.id.image);


        Intent iin= getIntent();

        String name = iin.getExtras().get("name_value").toString();

        String id = iin.getExtras().get("id_value").toString();

        String subject = iin.getExtras().get("subject_value").toString();

        String q= iin.getExtras().get("q_value").toString();

        String email = iin.getExtras().get("email_value").toString();

        department = iin.getExtras().get("depart_value").toString();

       final String image = iin.getExtras().get("image_value").toString();

        // String image=iin.getExtras().get("image_value").toString();


        disname.setText(name);
        disemail.setText(email);
        disq.setText(q);
        dissubject.setText(subject);
        disid.setText(id);
        disdepart.setText(department);
        Picasso.with(this).load(image).into(vi);


        create_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent("com.example.bhonesh.e_class.create_quiz_teacher");

               i.putExtra("department",department);


                startActivity(i);

            }
        });
        attand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent("com.example.bhonesh.e_class.attandance");

                i.putExtra("department",department);


                startActivity(i);

            }
        });
        subj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent("com.example.bhonesh.e_class.subject_create");

                i.putExtra("department",department);


                startActivity(i);

            }
        });
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent("com.example.bhonesh.e_class.upload_assignment");

               // i.putExtra("department",department);


                startActivity(i);



               /* Intent i=new Intent("com.example.bhonesh.e_class.create_assign");

                i.putExtra("department",department);


                startActivity(i);*/

            }
        });


        chatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent("com.example.bhonesh.e_class.login");

                startActivity(i);
            }
        });


    }
}