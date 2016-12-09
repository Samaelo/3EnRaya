package com.garcia.mario.tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import model.Boton;
import model.Jugador;
import model.Partida;

public class JuegoActivity extends AppCompatActivity {
    Partida p = new Partida(new ArrayList<Jugador>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);



    }

    public void onBtnPulsado(View v){
        switch(v.getId()){
            case R.id.btn1:
                pulsar_boton(1);
                break;
            case R.id.btn2:
                pulsar_boton(2);
                break;
            case R.id.btn3:
                pulsar_boton(3);
                break;
            case R.id.btn4:
                pulsar_boton(4);
                break;
            case R.id.btn5:
                pulsar_boton(5);
                break;
            case R.id.btn6:
                pulsar_boton(6);
                break;
            case R.id.btn7:
                pulsar_boton(7);
                break;
            case R.id.btn8:
                pulsar_boton(8);
                break;
            case R.id.btn9:
                pulsar_boton(9);
                break;


        }
    }
    public void pasarTurno(){
        p.pasarTurno();
    }

    public int comprobarSolucion(){
        return p.comprobarSolucion();
    }

    public void pulsar_boton(int id){
        p.pulsar_boton(new Boton(getBaseContext(),id));
    }
}
