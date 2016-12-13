package com.islasf.MarioSamael.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.garcia.mario.Activities.R;

import java.util.ArrayList;

import model.AccesoFicheros;
import model.Adaptador;

/**
 * Esta clase pertenece a la Activity donde se muestra la lista de las partidas jugadas y los ganadores de las mismas.
 */
public class CuadroHonor_Activity extends AppCompatActivity {

    Adaptador adaptador; // Adaptador del ListView
    ListView listView; // ListView donde volcaremos la lista de las partidas y los ganadores

    /**
     * Este método crea la Actividad.
     * @param savedInstanceState Objeto de tipo Bundle donde guardamos el estado de nuestras variables su estado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadro_honor_);

        crearListView(); // Creamos el ListView cuando creamos la Activity
    }

    /**
     * Este método finaliza la actividad y pasa a la Activity del menú principal (MenuPrincipal_Activity)
     */
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }

    /**
     * Este método crea el ListView. En primer lugar, instanciamos un objeto de la clase AccesoFicheros para poder leer el contenido del fichero. Para ello, definimos una variable denominada 'accesoFicheros'
     * Mediate el método leerDatosDeFichero() que nos devuelve un ArrayList de Strings con el contenido del fichero, obtenemos el valor del ArrayList partidas. Este método recibe dos parámetros:
     * el contexto de la actividad, y una partida. El contexto será el propio de esta Activity(this), y como en este caso no nos hace falta una partida, su valor será 'null'.
     * Instanciamos un adaptador con el contexto de la propia Activity(this) y le pasamos la estructura de datos con la que va a trabajar ese adaptador, que en este caso es el ArrayList denominado
     * 'partidas'. Por último, creamos un ListView a partir del ListView definido en el xml 'activity_cuadro_honor_.xml' como 'listView_CuadroHonor' y le aplicamos el Adaptador que hemos creado.
     */
    public void crearListView(){

        AccesoFicheros accesoFicheros; // Objeto de la clase AccesoFicheros con el cual vamos a obtener las partidas
        ArrayList<String> partidas; // ArrayList de tipo String que contiene las líneas del fichero

        accesoFicheros = new AccesoFicheros(this,null);
        partidas  = accesoFicheros.leerDatosDeFichero();
        adaptador = new Adaptador(this, partidas);

        listView = (ListView) findViewById(R.id.listView_CuadroHonor);

        listView.setAdapter(adaptador);
    }

    /**
     * Este método finaliza la actividad y pasa a la Activity del menú principal (MenuPrincipal_Activity)
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void volver(View v){

        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }
}
