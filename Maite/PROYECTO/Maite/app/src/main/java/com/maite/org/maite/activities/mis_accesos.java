package com.maite.org.maite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.maite.org.maite.R;

/**
 * Created by Stalyn on 10/07/2016.
 */
public class mis_accesos extends AppCompatActivity {


    Intent intent;
    EditText txtclave, txtconfirmaclave;
    boolean control = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitacora_details_layout);
    }
}