package com.garcia.mario.tresenraya;

import android.content.Intent;
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
    Partida p;
    final int id_btn1 = R.id.btn1, id_btn2 = R.id.btn2, id_btn3 = R.id.btn3, id_btn4 = R.id.btn4,
            id_btn5 = R.id.btn5, id_btn6 = R.id.btn6, id_btn7 = R.id.btn7, id_btn8 = R.id.btn8,
            id_btn9 = R.id.btn9;
    TextView txtTurno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Intent intent = getIntent();
        p = (Partida) intent.getExtras().getSerializable("PARTIDA");
        cargar_componentes();
        actualizar_txtTurno();

    }



    public void onBtnPulsado(View v){
        Button btn = (Button) v;
        switch(btn.getId()){
            case id_btn1:
                pulsar_boton(1);

                break;
            case id_btn2:
                pulsar_boton(2);
                break;
            case id_btn3:
                pulsar_boton(3);
                break;
            case id_btn4:
                pulsar_boton(4);
                break;
            case id_btn5:
                pulsar_boton(5);
                break;
            case id_btn6:
                pulsar_boton(6);
                break;
            case id_btn7:
                pulsar_boton(7);
                break;
            case id_btn8:
                pulsar_boton(8);
                break;
            case id_btn9:
                pulsar_boton(9);
                break;
        }
        btn.setEnabled(false);
    }
    public void pasarTurno(){
        p.pasarTurno();
        actualizar_txtTurno();

    }

    public void cargar_componentes(){
        txtTurno = (TextView) findViewById(R.id.txtTurno);
    }

    public void actualizar_txtTurno(){
        String textoTurno = String.format(getResources().getString(R.string.txtTurno), p.getJugador_actual().getNombre());

        txtTurno.setText(textoTurno);
    }
    public int comprobarSolucion(){
        return p.comprobarSolucion();
    }

    public void pulsar_boton(int id){
        int resultado = p.pulsar_boton(new Boton(getBaseContext(),id));

        if(resultado ==1){
            pasarTurno();
        }else if(resultado == 0){
            Toast.makeText(this,"Victoria!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Empate!",Toast.LENGTH_LONG).show();
        }


    }
}
