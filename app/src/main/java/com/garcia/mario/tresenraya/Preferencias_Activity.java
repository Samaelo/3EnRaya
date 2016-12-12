package com.garcia.mario.tresenraya;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Preferencias_Activity extends AppCompatActivity {

    SharedPreferences preferencias = getSharedPreferences("preferencias", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias_);

    }
}
