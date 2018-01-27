package com.example.bhonesh.e_class;

import android.app.Application;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by bhonesh on 18/1/18.
 */

public class e_class extends Application{

    @Override
   public void onCreate() {

        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        /*Picasso.Builder bi=new Picasso.Builder(this);
        bi.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));

        Picasso built = bi.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);*/



    }


    }
