package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.ArrayList;

import model.AccesoFicheros;
import model.Jugador;
import model.Partida;


public class Configuracion_Activity extends AppCompatActivity {

    RadioButton rbNaranjaJugador1, rbNaranjaJugador2, rbVerdeJugador1, rbVerdeJugador2,
            rbAzulJugador1, rbAzulJugador2;
    EditText eTxtJugador1,eTxtJugador2;

    final int id_rbNaranjaJugador1 = R.id.rbNaranjaJugador1, id_rbNaranjaJugador2 = R.id.rbNaranjaJugador2, id_rbVerdeJugador1 = R.id.rbVerdeJugador1, id_rbVerdeJugador2 = R.id.rbVerdeJugador2, id_rbAzulJugador1 = R.id.rbAzulJugador1, id_rbAzulJugador2 = R.id.rbAzulJugador2;

    final String COLOR_NARANJA = "#FF9900";
    final String COLOR_VERDE = "#097054";
    final String COLOR_AZUL = "#00628B";

    String jugador1;
    String jugador2;
    Button btnCrear;
    AccesoFicheros af = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        cargar_componentes();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        jugador1 = eTxtJugador1.getText().toString();
        jugador2 = eTxtJugador2.getText().toString();
        super.onSaveInstanceState(outState);
        outState.putString("nombreJ1", jugador1);
        outState.putString("nombreJ2", jugador2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        jugador1 = eTxtJugador1.getText().toString();
        jugador2 = eTxtJugador2.getText().toString();
        super.onRestoreInstanceState(savedInstanceState);
        jugador1 = savedInstanceState.getString("nombreJ1");
        jugador2 = savedInstanceState.getString("nombreJ2");
        eTxtJugador1.setText(String.valueOf(jugador1));
        eTxtJugador2.setText(String.valueOf(jugador2));
    }


    public void on_btnJugar_pulsado(View v){

        ArrayList<Jugador> jugadores;
        Intent intent;
        String mensaje = validar_campos(eTxtJugador1.getText().toString(), eTxtJugador2.getText().toString());

        if(mensaje.length()<3){

            jugadores = new ArrayList<Jugador>();
            Jugador j1 = new Jugador(eTxtJugador1.getText().toString(),obtener_color(1));
            j1.asignarTurno(true);
            jugadores.add(j1);
            jugadores.add(new Jugador(eTxtJugador2.getText().toString(),obtener_color(2)));

            Partida p = new Partida(jugadores);

            intent = new Intent(getApplicationContext(),Juego_Activity.class);
            intent.putExtra("PARTIDA",p);
            startActivityForResult(intent,1);
        }
        else{
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        String ganador;
        String mensaje_victoria;
        String mensaje_empate;

        if(requestCode==1){
            if (resultCode == 0){
                ganador = data.getExtras().getString("GANADOR");
                mensaje_victoria = String.format(getResources().getString(R.string.toastVictoria), ganador);
                Toast.makeText(this,mensaje_victoria,Toast.LENGTH_LONG).show();

            }else if (resultCode == 2){

                mensaje_empate = String.format(getResources().getString(R.string.toastEmpate));
                Toast.makeText(this,mensaje_empate,Toast.LENGTH_LONG).show();

            }else if (resultCode == 3){
                finish();
                Toast.makeText(this,"Partida finalizada.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuPrincipal_Activity.class);
                startActivity(intent);
            }
            else if(resultCode == 4){
                finishAffinity();
                System.exit(0);
            }
        }
    }

    public String validar_campos(String nombreJ1, String nombreJ2){

        String mensaje="";
        Validacion validar = new Validacion();

        int codigoError = validar.validarNombreJugador(nombreJ1, nombreJ2);

        switch (codigoError){

            case 1:
                    mensaje = "Alguno de los nombres está en blanco";
                    break;
            case 2:
                    mensaje = "Los nombres son iguales";
                    break;
            case 3:
                    mensaje = "Alguno de los nombres contiene carácteres no válidos";
                    break;
        }
        return mensaje;
    }

    public String obtener_color(int idJugador){

        String res;

        if (idJugador == 1){
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
            case id_rbNaranjaJugador1:
                activar_radiobuttons_jugador2();
                rbNaranjaJugador2.setEnabled(false);
                rbNaranjaJugador2.setTextColor(Color.GRAY);
                rbNaranjaJugador1.setTextColor(Color.parseColor(COLOR_NARANJA));
                break;
            case id_rbNaranjaJugador2:
                activar_radiobuttons_jugador1();
                rbNaranjaJugador1.setEnabled(false);
                rbNaranjaJugador1.setTextColor(Color.GRAY);
                rbNaranjaJugador2.setTextColor(Color.parseColor(COLOR_NARANJA));
                break;
            case id_rbVerdeJugador1:
                activar_radiobuttons_jugador2();
                rbVerdeJugador2.setEnabled(false);
                rbVerdeJugador2.setTextColor(Color.GRAY);
                rbNaranjaJugador1.setTextColor(Color.parseColor(COLOR_VERDE));
                break;
            case id_rbVerdeJugador2:
                activar_radiobuttons_jugador1();
                rbVerdeJugador1.setEnabled(false);
                rbVerdeJugador1.setTextColor(Color.GRAY);
                rbNaranjaJugador2.setTextColor(Color.parseColor(COLOR_VERDE));
                break;
            case id_rbAzulJugador1:
                activar_radiobuttons_jugador2();
                rbAzulJugador2.setEnabled(false);
                rbAzulJugador2.setTextColor(Color.GRAY);
                rbNaranjaJugador1.setTextColor(Color.parseColor(COLOR_AZUL));
                break;
            case id_rbAzulJugador2:
                activar_radiobuttons_jugador1();
                rbAzulJugador1.setEnabled(false);
                rbAzulJugador1.setTextColor(Color.GRAY);
                rbNaranjaJugador2.setTextColor(Color.parseColor(COLOR_AZUL));
                break;
        }
    }

    public void cargar_componentes(){
        rbNaranjaJugador1 = (RadioButton) findViewById(id_rbNaranjaJugador1);
        rbNaranjaJugador2 = (RadioButton) findViewById(id_rbNaranjaJugador2);
        rbVerdeJugador1 = (RadioButton) findViewById(id_rbVerdeJugador1);
        rbVerdeJugador2 = (RadioButton) findViewById(id_rbVerdeJugador2);
        rbAzulJugador1 = (RadioButton) findViewById(id_rbAzulJugador1);
        rbAzulJugador2 = (RadioButton) findViewById(id_rbAzulJugador2);
        eTxtJugador1 = (EditText) findViewById(R.id.eTxtJugador1);
        eTxtJugador2 = (EditText) findViewById(R.id.eTxtJugador2);
        eTxtJugador1.requestFocus();


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

    public void volver(View v){
        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }
}
