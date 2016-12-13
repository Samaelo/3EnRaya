package com.islasf.MarioSamael.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garcia.mario.Activities.R;

/**
 * Esta clase hace referencia al menú principal. En esta Actividad, se podrá acceder a jugar una partida o a ver la lista de ganadores.
 */
public class MenuPrincipal_Activity extends AppCompatActivity {

    /**
     * Este método crea la Actividad
     * @param savedInstanceState Objeto de tipo Bundle donde guardamos el estado de nuestras variables su estado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    /**
     * Este método finaliza la actividad actual (MenuPrincipal_Activity) y llama a la Activity CuadroHonor_Activity
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void mostrarGanadores(View v){
        finish();
        Intent intent = new Intent(this, CuadroHonor_Activity.class);
        startActivity(intent);
    }

    /**
     * Este método finaliza la actividad actual (MenuPrincipal_Activity) y llama a la Activity Configuracion_Activity
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void jugar(View v){
        finish();
        Intent intent = new Intent(this, Configuracion_Activity.class);
        startActivity(intent);
    }
}
