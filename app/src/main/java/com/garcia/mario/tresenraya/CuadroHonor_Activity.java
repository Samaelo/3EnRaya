package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import model.AccesoFicheros;

public class CuadroHonor_Activity extends AppCompatActivity {

    Adaptador adaptador;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadro_honor_);

        crearListView();

    }

    public void crearListView(){

        AccesoFicheros af;
        ArrayList<String>partidas;

        af = new AccesoFicheros(this,null);
        partidas  = af.leerDatosDeFichero();
        adaptador = new Adaptador(this, partidas);

        listView = (ListView)findViewById(R.id.listView_CuadroHonor);

        listView.setAdapter(adaptador);
    }

    public void volver(View v){

        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }
}
