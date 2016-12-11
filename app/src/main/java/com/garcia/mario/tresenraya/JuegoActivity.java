package com.garcia.mario.tresenraya;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Boton;
import model.Jugador;
import model.Partida;

public class JuegoActivity extends AppCompatActivity {

    Partida partida;

    final int id_btn1 = R.id.btn1, id_btn2 = R.id.btn2, id_btn3 = R.id.btn3, id_btn4 = R.id.btn4,
            id_btn5 = R.id.btn5, id_btn6 = R.id.btn6, id_btn7 = R.id.btn7, id_btn8 = R.id.btn8,
            id_btn9 = R.id.btn9;

    TextView txtTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Intent intent = getIntent();
        partida = (Partida) intent.getExtras().getSerializable("PARTIDA");
        cargar_componentes();
        actualizar_txtTurno();

    }

    public void onBtnPulsado(View v){

        Button btn = (Button) v;

        switch(btn.getId()){

            case id_btn1:
                pulsar_boton(1, btn);
                break;
            case id_btn2:
                pulsar_boton(2, btn);
                break;
            case id_btn3:
                pulsar_boton(3, btn);
                break;
            case id_btn4:
                pulsar_boton(4, btn);
                break;
            case id_btn5:
                pulsar_boton(5, btn);
                break;
            case id_btn6:
                pulsar_boton(6, btn);
                break;
            case id_btn7:
                pulsar_boton(7, btn);
                break;
            case id_btn8:
                pulsar_boton(8, btn);
                break;
            case id_btn9:
                pulsar_boton(9, btn);
                break;
        }

    }

    public void pasarTurno(){
        partida.pasarTurno();
        actualizar_txtTurno();

    }

    public void ponerNombreJugador(Button boton){

        boton.setText(partida.getJugador_actual().getNombre());
        boton.setTextColor(Color.parseColor(partida.getJugador_actual().getColor()));

    }

    public void cargar_componentes(){
        txtTurno = (TextView) findViewById(R.id.txtTurno);
    }

    public void actualizar_txtTurno(){

        String textoTurno = String.format(getResources().getString(R.string.txtTurno), partida.getJugador_actual().getNombre());
        txtTurno.setText(textoTurno);
        txtTurno.setTextColor(Color.parseColor(partida.getJugador_actual().getColor()));
    }

    public int comprobarSolucion(){
        return partida.comprobarSolucion();
    }

    public void finalizar_partida(int resultado){
        partida.finalizar(resultado);

      //  Intent intent = new Intent();
        //intent.putExtra("PARTIDA",partida);
        setResult(resultado);
        super.finish();


    }
    @Override
    public void onBackPressed() {

    }
    public void pulsar_boton(int idBoton, Button boton){

        String mensaje_trampa = String.format(getResources().getString(R.string.toastTrampa));
        if( boton.getText().toString().trim().equals("")){
            ponerNombreJugador(boton);
            int resultado = partida.pulsar_boton(new Boton(getBaseContext(),idBoton));
            if(resultado ==1){
                pasarTurno();
            }else{
                finalizar_partida(resultado);
            }
        }else{
            Toast.makeText(getApplicationContext(), mensaje_trampa, Toast.LENGTH_SHORT).show();
        }




    }
}
