package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class student_dashboard extends AppCompatActivity {

    Button start_quiz,mark_a,download,chatroom;
    TextView disname,disyear,disbatch,disid,disdepart,disemail;
    ImageView vi;
    String id,year,department,batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        download=(Button)findViewById(R.id.download);

        chatroom=(Button)findViewById(R.id.chatroom);

        disname=(TextView)findViewById(R.id.disname);
        disbatch=(TextView)findViewById(R.id.disbatch);
        disyear=(TextView)findViewById(R.id.disyear);
        disid=(TextView)findViewById(R.id.disid);
        disdepart=(TextView)findViewById(R.id.disdepart);
        disemail=(TextView)findViewById(R.id.disemail);

        mark_a=findViewById(R.id.mark);

        vi=(ImageView)findViewById(R.id.image);
        start_quiz=(Button)findViewById(R.id.start_quiz);


        Intent iin= getIntent();

        String name = iin.getExtras().get("name_value").toString();

        id= iin.getExtras().get("id_value").toString();

        batch = iin.getExtras().get("batch_value").toString();

        year= iin.getExtras().get("year_value").toString();

        String email = iin.getExtras().get("email_value").toString();

        department = iin.getExtras().get("depart_value").toString();

         String image = iin.getExtras().get("image_value").toString();

        // String image=iin.getExtras().get("image_value").toString();


        disname.setText(name);
        disemail.setText(email);
        disyear.setText(year);
        disbatch.setText(batch);
        disid.setText(id);
        disdepart.setText(department);

        Picasso.with(student_dashboard.this).load(image).into(vi);


        start_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent("com.example.bhonesh.e_class.start_quiz_student");
                i.putExtra("id",id);
                i.putExtra("dep",department);
                i.putExtra("year",year);
                i.putExtra("batch",batch);
                startActivity(i);




            }
        });
        mark_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent("com.example.bhonesh.e_class.mark_att");
                i.putExtra("id",id);
                i.putExtra("dep",department);
                i.putExtra("year",year);
                i.putExtra("batch",batch);
                startActivity(i);
            }
        });
       download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent("com.example.bhonesh.e_class.ViewUploadsActivity");

                startActivity(i);
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