package com.example.bhonesh.e_class;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.core.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class create_assign extends AppCompatActivity {

    public final int PICK_PDF_CODE=403;//DATABASE_PATH_UPLOADS=23;
    Button Submit,select;
    private EditText quiznameforq,noforq,batchforq,yearforq;
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    File file ;
    Uri downloaduri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assign);

        mStorageReference = FirebaseStorage.getInstance().getReference();
     //   mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);


        quiznameforq=(EditText)findViewById(R.id.sub);
        yearforq=(EditText)findViewById(R.id.e2);
        batchforq=(EditText)findViewById(R.id.e3);

        select=findViewById(R.id.select);
        Submit=(Button)findViewById(R.id.Submit);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=getIntent();

                String department =i.getExtras().get("department").toString();



                String s1=quiznameforq.getText().toString();

                String s2=batchforq.getText().toString();

                String s3=yearforq.getText().toString();

                quiznameforq.setText("");
                noforq.setText("");
                batchforq.setText("");
                yearforq.setText("");
                //codeforq.setText("");


            }
        });
        select.setOnClickListener(new View.OnClickListener() {
@Override
            public void onClick(View v) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl("gs://e-class-9c419.appspot.com").child("test.pdf");

                InputStream stream = getResources().openRawResource(R.raw.test);

                UploadTask uploadTask = (UploadTask) storageReference.putStream(stream).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        downloaduri=taskSnapshot.getDownloadUrl();
                    }
                });
            }








          /*  @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
                        }*/
        });


    }
    public File getTempFile(Context context, String url) {
        File file=null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }




}
