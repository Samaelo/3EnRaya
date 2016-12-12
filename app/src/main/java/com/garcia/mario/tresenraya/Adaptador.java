package com.garcia.mario.tresenraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Esta clase es responsable de hacer una vista para cada item de un list view (que hace referencia a cada elemento de la estructura de datos que recibe el constructor por parámetro) en el conjunto
 * de datos.
 */

public class Adaptador extends ArrayAdapter<String> {

    List partidas_ganadas;

    /**
     * Constructor de la clase Adaptador. El constructor recibe un objeto Context denominado 'context', que hace referencia al contexto de la actividad en la cual se crea el adaptador, y una Lista
     * que hace referencia a la estructura de datos con la que queremos trabajar.
     * @param context Hace referencia al contexto de la actividad en la cual se crea el adaptador
     * @param partidas_ganadas Hace referencia a la estructura de datos con la que se va a trabajar
     */
    public Adaptador(Context context, List partidas_ganadas) {
        super(context, 0, partidas_ganadas);
        this.partidas_ganadas = partidas_ganadas;
    }

    /**
     *  Este método consiste en adaptar la estructura de datos que recibe por parámetro el constructor del adaptador para darle formato a un ListView
     * @param posicion Variable de tipo entero que hace referencia a la posición de la línea del ListView a inflar
     * @param convertView Objeto de tipo View que hace referencia a la vista de la cual partimos para reutilizarla e inflar el siguiente ítem (fila del ListView)
     * @param parent Objeto de tipo ViewGroup que hace referencia a la vista a la cual tiene que adaptarse la nueva vista, es decir, en este caso la vista de cada ítem del ListView
     * @return
     */
    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {

        View vista; // Creamos una vista
        ViewHolder view_holder; // Creamos un ViewHolder que será el responsable de recuperar los datos del tag de cada ítem

        String partida_ganada = getItem(posicion); // Recogemos la posición del ítem
        //Recibimos el item creado anteriormente. Si es null, significa que no se ha creado antes.
        View item = convertView;

        //Esto nos permitirá comprobar si ya se ha creado un ítem en esa posición
        // para no tener que volver a crear otra.
        if (item == null) {
            // Instanciamos la Vista que será el producto de inflar el ítem del ListView
            vista = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

            view_holder = new ViewHolder(); // Instanciamos el objeto ViewHolder
            view_holder.setTxt((TextView) vista.findViewById(android.R.id.text1)); //
            vista.setTag(view_holder);
        }
        else{
            //Si ya se ha creado este item, recogemos la referencia guardada para no inflar/crear
            // uno nuevo.
            vista = item;
            view_holder = (ViewHolder) vista.getTag();
        }
        view_holder.txt.setText(partida_ganada);


        return vista;
    }

    class ViewHolder{
        private TextView txt;

        public void setTxt(TextView txt) {
            this.txt = txt;
        }
    }

}
