package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal_Activity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        boton = (Button)findViewById(R.id.iniciar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_opciones:

                    Intent intent = new Intent(getApplicationContext(), CuadroHonor_Activity.class);
                    startActivity(intent);
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
