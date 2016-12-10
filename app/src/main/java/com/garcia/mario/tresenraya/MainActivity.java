package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import model.AccesoFicheros;
import model.Jugador;
import model.Partida;


public class MainActivity extends AppCompatActivity {

    RadioButton rbNaranjaJugador1, rbNaranjaJugador2, rbVerdeJugador1, rbVerdeJugador2,
                rbAzulJugador1, rbAzulJugador2;
    EditText eTxtJugador1,eTxtJugador2;

    final String COLOR_NARANJA = "#FF9900";
    final String COLOR_VERDE = "#097054";
    final String COLOR_AZUL = "#00628B";
    AccesoFicheros af;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargar_componentes();
        af = new AccesoFicheros();

        af.escribirFichero();
        af.leerFichero();

    }

    public void on_btnJugar_pulsado(View v){
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(new Jugador(eTxtJugador1.getText().toString(),""));
        Intent intent = new Intent();
       // intent.putExtra("PARTIDA",new Partida(jugadores));
     //   startActivityForResult(intent,0);
    }

    public void on_radiobutton_pulsado(View v){
        bloquear_radiobutton(v);
    }

    public void bloquear_radiobutton(View v){
        RadioButton rb = (RadioButton) v;
        switch(rb.getId()){
            case R.id.rbNaranjaJugador1:
                activar_radiobuttons_jugador2();
                rbNaranjaJugador2.setEnabled(false);
                break;
            case R.id.rbNaranjaJugador2:
                activar_radiobuttons_jugador1();
                rbNaranjaJugador1.setEnabled(false);
                break;
            case R.id.rbVerdeJugador1:
                activar_radiobuttons_jugador2();
                rbVerdeJugador2.setEnabled(false);
                break;
            case R.id.rbVerdeJugador2:
                activar_radiobuttons_jugador1();
                rbVerdeJugador1.setEnabled(false);
                break;
            case R.id.rbAzulJugador1:
                activar_radiobuttons_jugador2();
                rbAzulJugador2.setEnabled(false);
                break;
            case R.id.rbAzulJugador2:
                activar_radiobuttons_jugador1();
                rbAzulJugador1.setEnabled(false);
                break;
        }
    }

    public void cargar_componentes(){
        rbNaranjaJugador1 = (RadioButton) findViewById(R.id.rbNaranjaJugador1);
        rbNaranjaJugador2 = (RadioButton) findViewById(R.id.rbNaranjaJugador2);
        rbVerdeJugador1 = (RadioButton) findViewById(R.id.rbVerdeJugador1);
        rbVerdeJugador2 = (RadioButton) findViewById(R.id.rbVerdeJugador2);
        rbAzulJugador1 = (RadioButton) findViewById(R.id.rbAzulJugador1);
        rbAzulJugador2 = (RadioButton) findViewById(R.id.rbAzulJugador2);
        eTxtJugador1 = (EditText) findViewById(R.id.eTxtJugador1);
        eTxtJugador2 = (EditText) findViewById(R.id.eTxtJugador2);

    }

    public void activar_radiobuttons_jugador1(){
        if(!rbNaranjaJugador1.isEnabled() && !rbNaranjaJugador2.isSelected()) rbNaranjaJugador1.setEnabled(true);
        if(!rbVerdeJugador1.isEnabled()&& !rbVerdeJugador2.isSelected()) rbVerdeJugador1.setEnabled(true);
        if(!rbAzulJugador1.isEnabled()&& !rbAzulJugador2.isSelected()) rbAzulJugador1.setEnabled(true);


    }
    public void activar_radiobuttons_jugador2(){
        if(!rbNaranjaJugador2.isEnabled()&& !rbNaranjaJugador1.isSelected()) rbNaranjaJugador2.setEnabled(true);
        if(!rbVerdeJugador2.isEnabled()&& !rbVerdeJugador1.isSelected()) rbVerdeJugador2.setEnabled(true);
        if(!rbAzulJugador2.isEnabled()&& !rbAzulJugador1.isSelected()) rbAzulJugador2.setEnabled(true);
    }
}
