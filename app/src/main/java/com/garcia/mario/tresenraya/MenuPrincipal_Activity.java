package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import model.AccesoFicheros;

public class MenuPrincipal_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


    }


    public void mostrarGanadores(View v){
        finish();
        Intent intent = new Intent(this, CuadroHonor_Activity.class);
        startActivity(intent);
    }

    public void jugar(View v){
        finish();
        Intent intent = new Intent(this, Configuracion_Activity.class);
        startActivity(intent);
    }
}
