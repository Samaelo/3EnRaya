package com.garcia.mario.tresenraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class Adaptador extends ArrayAdapter<String> {

    List partidas_ganadas;

    public Adaptador(Context context, List partidas_ganadas) {
        super(context, 0, partidas_ganadas);
        this.partidas_ganadas = partidas_ganadas;

    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        View vista;
        ViewHolder view_holder;

        String partida_ganada = getItem(posicion);
        //Recibimos el item creado anteriormente. Si es null, significa que no se ha creado antes.
        View item = convertView;

        //Esto nos permitirá comprobar si ya se ha creado una cajita en esa posicion
        //Para no tener que volver a crear otra.
        if (item == null) {


            vista = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);


            view_holder = new ViewHolder();
            view_holder.setTxt((TextView) vista.findViewById(android.R.id.text1));


            vista.setTag(view_holder);


        }else{
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
