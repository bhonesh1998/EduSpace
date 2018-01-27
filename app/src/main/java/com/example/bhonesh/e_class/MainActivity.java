package com.example.bhonesh.e_class;
import android.app.ProgressDialog;
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

import java.util.Map;
public class MainActivity extends AppCompatActivity {


    String seditpass,myid;
    EditText ipass,iid;
    Button create,signin;
    ProgressDialog pd;
    DatabaseReference ref1,ref2;
    boolean a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        signin=(Button)findViewById(R.id.signin);
        ipass=(EditText)findViewById(R.id.sub);
        iid=(EditText)findViewById(R.id.editid);

        Firebase.setAndroidContext(this);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=true;
                myid= iid.getText().toString();
                seditpass = ipass.getText().toString();
                if(myid.equals("")){
                    iid.setHint("can't be empty");
                    iid.setBackgroundColor(Color.parseColor("#FFEF767C"));a=false;
                   // Toast.makeText(MainActivity.this,"You can't leave it empty",Toast.LENGTH_SHORT).show();
                }
                if(seditpass.equals("")){
                    ipass.setHint("can't be empty");
                    ipass.setBackgroundColor(Color.parseColor("#FFEF767C"));a=false;
                }
               if(a) {

                    pd = new ProgressDialog(MainActivity.this);

                    // pd.setTitle("dashboard");
                    pd.setMessage("logging in");
                    pd.show();

                    ref1 = FirebaseDatabase.getInstance().getReference("users");
                    // ref1.keepSynced(true);
                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(myid=="admin"){
                                ref2 = ref1.child(myid);
                                ref2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Map<String, String> m = (Map<String, String>) dataSnapshot.getValue();
                                        String oemail = m.get("email");

                                        String oimage = m.get("image");



                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            if (!dataSnapshot.hasChild(myid)) {
                                Toast.makeText(MainActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else {

                                ref2 = ref1.child(myid);
                                // ref2.keepSynced(true);

                                ref2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        Log.e("error", "data " + dataSnapshot.getValue());

                                        Map<String, String> m = (Map<String, String>) dataSnapshot.getValue();
                                        // dataSnapshot.getValue(Map.class);


                                        //String semail = m.get("email");
                                        String spass = m.get("password");

                                        String oname = m.get("name");

                                        String oid = m.get("id");

                                        String obatch = m.get("batch");

                                        String oyear = m.get("year");

                                        String odepart = m.get("department");

                                        String oemail = m.get("email");

                                        String oimage = m.get("image");

                                        String osubject = m.get("subject");

                                        String oq = m.get("q");

                                        String profession = m.get("pro");

                                        //  String seditemail = iemail.getText().toString();


                                        if (seditpass.equals(spass) && profession.equals("Student"))

                                        {
                                            pd.dismiss();


                                            Intent i1 = new Intent("com.example.bhonesh.e_class.student_dashboard");
                                            i1.putExtra("name_value", oname);
                                            i1.putExtra("id_value", oid);
                                            i1.putExtra("batch_value", obatch);
                                            i1.putExtra("email_value", oemail);
                                            i1.putExtra("year_value", oyear);
                                            i1.putExtra("depart_value", odepart);
                                            i1.putExtra("image_value", oimage);
                                            ipass.setText("");
                                            iid.setText("");
                                            startActivity(i1);
                                        } else if (seditpass.equals(spass) && profession.equals("Teacher"))

                                        {
                                            pd.dismiss();
                                            Intent i2 = new Intent("com.example.bhonesh.e_class.teacher_dashboard");
                                            i2.putExtra("name_value", oname);
                                            i2.putExtra("id_value", oid);
                                            i2.putExtra("subject_value", osubject);
                                            i2.putExtra("email_value", oemail);
                                            i2.putExtra("q_value", oq);
                                            i2.putExtra("depart_value", odepart);
                                            i2.putExtra("image_value", oimage);

                                            ipass.setText("");
                                            iid.setText("");

                                            startActivity(i2);

                                        } else {
                                            pd.dismiss();

                                            Toast.makeText(MainActivity.this, "Incorrect Combination", Toast.LENGTH_SHORT).show();
                                            ipass.setText("");
                                            iid.setText("");
                                            //Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }







            }
        });




        create=(Button)findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent("com.example.bhonesh.e_class.options");
                startActivity(i1);
            }
        });


    }





}