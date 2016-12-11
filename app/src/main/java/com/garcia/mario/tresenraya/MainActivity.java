package com.garcia.mario.tresenraya;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

import model.AccesoFicheros;
import model.Jugador;
import model.Partida;

//quesito
public class MainActivity extends AppCompatActivity {

    RadioButton rbNaranjaJugador1, rbNaranjaJugador2, rbVerdeJugador1, rbVerdeJugador2,
            rbAzulJugador1, rbAzulJugador2;
    EditText eTxtJugador1,eTxtJugador2;

    final int id_rbNaranjaJugador1 = R.id.rbNaranjaJugador1, id_rbNaranjaJugador2 = R.id.rbNaranjaJugador2, id_rbVerdeJugador1 = R.id.rbVerdeJugador1, id_rbVerdeJugador2 = R.id.rbVerdeJugador2, id_rbAzulJugador1 = R.id.rbAzulJugador1, id_rbAzulJugador2 = R.id.rbAzulJugador2;

    final String COLOR_NARANJA = "#FF9900";
    final String COLOR_VERDE = "#097054";
    final String COLOR_AZUL = "#00628B";

    Button btnCrear;
    AccesoFicheros af = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar_componentes();

        btnCrear = (Button)findViewById(R.id.btn_CrearFichero);

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
            startActivityForResult(intent,1);
        }
        else{
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
        }

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
                Toast.makeText(this,"Partida finalizada.",Toast.LENGTH_SHORT).show();
            }
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
                break;
            case id_rbNaranjaJugador2:
                activar_radiobuttons_jugador1();
                rbNaranjaJugador1.setEnabled(false);
                break;
            case id_rbVerdeJugador1:
                activar_radiobuttons_jugador2();
                rbVerdeJugador2.setEnabled(false);
                break;
            case id_rbVerdeJugador2:
                activar_radiobuttons_jugador1();
                rbVerdeJugador1.setEnabled(false);
                break;
            case id_rbAzulJugador1:
                activar_radiobuttons_jugador2();
                rbAzulJugador2.setEnabled(false);
                break;
            case id_rbAzulJugador2:
                activar_radiobuttons_jugador1();
                rbAzulJugador1.setEnabled(false);
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

    public void accionBoton(View v){

        String str = "tu puta madre en vinagre";
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try{
            File fichero = new File("cuadroHonor.txt");
            fos = openFileOutput(fichero.getName(), MODE_APPEND | MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);

            // Escribimos el String en el archivo
            osw.append("\n" + str);

            // Mostramos que se ha guardado
            Toast.makeText(getBaseContext(), "Guardado " + fichero.getName(), Toast.LENGTH_SHORT).show();

        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {

            try {
                if(osw != null && fos != null) {
                    osw.close();
                    fos.close();
                }
            }
            catch (IOException e) {Toast.makeText(getBaseContext(), "RETOCAAAAAAAAAAAAAAAAAAR", Toast.LENGTH_SHORT).show();}
        }

        try {
             fis = openFileInput("cuadroHonor.txt");
             isr = new InputStreamReader(fis);
             br = new BufferedReader(isr);

            String texto;

            while((texto = br.readLine()) != null){

                Toast.makeText(getBaseContext(),"Contenido del fichero " + texto, Toast.LENGTH_LONG).show();

            }

        }
        catch (Exception ex){  Log.e("Ficheros", "Error al leer fichero desde memoria interna");}

        finally {
            try {
                if(fis != null && isr != null && br != null) {
                    br.close();
                    isr.close();
                    osw.close();
                }
            }
            catch (IOException e) { Toast.makeText(getBaseContext(), "RETOCAAAAAAAAAAAAAAAAAAR", Toast.LENGTH_SHORT).show();}
        }
    }
}
