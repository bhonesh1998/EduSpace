package com.example.bhonesh.e_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView obtain,totolm;
    Button back;
    int total,marks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent i=getIntent();
        total=i.getIntExtra("total",0);
        marks=i.getIntExtra("marks",0);
        obtain=findViewById(R.id.obtain);
        totolm=findViewById(R.id.total);
        back=findViewById(R.id.profile);
        obtain.setText(String.valueOf(marks));
        totolm.setText(String.valueOf(total));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i=new Intent("com.example.bhonesh.e_class.student_dashboard");
               // startActivity(i);
                onBackPressed();
            }
        });
    }
}
