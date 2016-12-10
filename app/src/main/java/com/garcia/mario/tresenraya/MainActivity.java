package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import model.Jugador;
import model.Partida;

//quesito
public class MainActivity extends AppCompatActivity {

    RadioButton rbNaranjaJugador1, rbNaranjaJugador2, rbVerdeJugador1, rbVerdeJugador2,
            rbAzulJugador1, rbAzulJugador2;
    EditText eTxtJugador1,eTxtJugador2;

    final String COLOR_NARANJA = "#FF9900";
    final String COLOR_VERDE = "#097054";
    final String COLOR_AZUL = "#00628B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar_componentes();


    }
    public void on_btnJugar_pulsado(View v){
        ArrayList<Jugador> jugadores;
        Intent intent;
        String mensaje = validar_campos();
        if(mensaje.length()<3){
            jugadores = new ArrayList<Jugador>();
            Jugador j1 = new Jugador(eTxtJugador1.getText().toString(),obtener_color(1));
            j1.asignarTurno(true);
            jugadores.add(j1);
            jugadores.add(new Jugador(eTxtJugador2.getText().toString(),obtener_color(2)));

            Partida p = new Partida(jugadores);

            intent = new Intent(getApplicationContext(),JuegoActivity.class);
            intent.putExtra("PARTIDA",p);
            startActivity(intent);
        }else{
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
        }

    }


    public String validar_campos(){
        String mensaje="";
        if (eTxtJugador1.getText().toString().trim().equals("") || eTxtJugador2.getText().toString().trim().equals("")){
            mensaje = "Por favor, rellene los campos.";
        }else if(eTxtJugador1.getText().toString().equals(eTxtJugador2.getText().toString())){
            mensaje = "Los nombres no deben coincidir.";
        }
        return mensaje;
    }
    public String obtener_color(int jugador){
        String res;
        if (jugador == 1){
            if(rbNaranjaJugador1.isChecked()){
                res = COLOR_NARANJA;
            }else if(rbVerdeJugador1.isChecked()){
                res = COLOR_VERDE;
            }else{
                res = COLOR_AZUL;
            }
        }else{
            if(rbNaranjaJugador2.isChecked()){
                res = COLOR_NARANJA;
            }else if(rbVerdeJugador2.isChecked()){
                res = COLOR_VERDE;
            }else{
                res = COLOR_AZUL;
            }
        }
        return res;
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
        if(!rbNaranjaJugador1.isEnabled()) rbNaranjaJugador1.setEnabled(true);
        if(!rbVerdeJugador1.isEnabled()) rbVerdeJugador1.setEnabled(true);
        if(!rbAzulJugador1.isEnabled()) rbAzulJugador1.setEnabled(true);


    }
    public void activar_radiobuttons_jugador2(){
        if(!rbNaranjaJugador2.isEnabled()) rbNaranjaJugador2.setEnabled(true);
        if(!rbVerdeJugador2.isEnabled()) rbVerdeJugador2.setEnabled(true);
        if(!rbAzulJugador2.isEnabled()) rbAzulJugador2.setEnabled(true);
    }
}
