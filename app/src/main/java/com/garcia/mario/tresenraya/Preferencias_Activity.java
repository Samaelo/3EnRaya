package com.garcia.mario.tresenraya;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import model.AccesoFicheros;

public class Preferencias_Activity extends AppCompatActivity {

    SharedPreferences preferencias;
    SharedPreferences.Editor editorPreferencias;
    RadioButton rb_Espanyol, rb_Aleman, rb_Swahili, rb_Ingles;
    TextView textoIdioma,textoAlmacenamiento;
    EditText et_NombreFichero;
    CheckBox checkInterno, checkExterno;
    File fichero;
    AccesoFicheros accesoFicheros;
    final String RUTA_SD = Environment.getExternalStorageDirectory().getAbsolutePath();

    final int id_Expanyol = R.id.rbSpanish, id_rb_Aleman = R.id.rbAleman, id_rb_Swahili = R.id.rbSwahili, id_rb_Ingles = R.id.rbEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias_);

        cargar_componentes();

    }

    public void guardarPreferencias(){

        editorPreferencias = preferencias.edit();
        guardarPreferenciaIdioma();
        guardarPreferenciaAlmacenamiento();
    }

    public void cargarPreferencias(){

        preferencias = getSharedPreferences("preferencias", MODE_PRIVATE);
    }

    public String recogerIdioma(){

        String idioma = "";

        if(rb_Espanyol.isChecked())
            idioma = "Español";
        else if(rb_Aleman.isChecked())
            idioma = "Aleman";
        else if(rb_Swahili.isChecked())
            idioma = "Swahili";
        else if (rb_Ingles.isChecked())
            idioma = "Ingles";

        return idioma;
    }

    public String recogerTipoAlmacenamiento(){

        String tipoAlmacenamiento = "Interno";
        String nombreFichero = et_NombreFichero.getText().toString().trim();
        boolean existeTarjeta = accesoFicheros.comprobarTarjetaSD();

        if(checkInterno.isChecked())
            tipoAlmacenamiento = "Interno";

        else if(checkExterno.isChecked())
            tipoAlmacenamiento = "Externo";

        return tipoAlmacenamiento;
    }

    public void guardarPreferenciaIdioma(){

        String idioma = recogerIdioma();

        switch (idioma){

             case "Español" :
                 editorPreferencias.putString("idioma", "Español");
                 break;
             case "Aleman" :
                 editorPreferencias.putString("idioma", "Aleman");
                 break;
             case "Swahili" :
                 editorPreferencias.putString("idioma", "Swahili");
                 break;
             case "Ingles" :
                 editorPreferencias.putString("idioma", "Ingles");
                 break;
        }
    }

    public void guardarPreferenciaAlmacenamiento() {

        String almacenamiento = recogerTipoAlmacenamiento();
        boolean existeTarjeta;
        Toast toast = null;

        switch (almacenamiento) {

            case "Interno":
                            editorPreferencias.putString("almacenamiento", "Interno");
                            break;

            case "Externo":
                            existeTarjeta = accesoFicheros.comprobarTarjetaSD();
                            if (existeTarjeta = true) {
                                editorPreferencias.putString("almacenamiento", "Externo");
                                break;
                            }
                            else {
                                toast = Toast.makeText(this, "No existe tarjeta SD", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.show();
                            }
        }
    }

    public void cargarPreferenciaAlmacenamiento(){

        String tipo_almacenamiento = preferencias.getString("almacenamiento","");


    }

    public void cambiarCheckBox(View v){

        if(checkExterno.isChecked())
            checkInterno.setChecked(false);
        if(checkInterno.isChecked())
            checkExterno.setChecked(false);
    }

    public void cargar_componentes(){
        rb_Espanyol = (RadioButton) findViewById(id_Expanyol);
        rb_Aleman = (RadioButton) findViewById(id_rb_Aleman);
        rb_Swahili = (RadioButton) findViewById(id_rb_Swahili);
        rb_Ingles = (RadioButton) findViewById(id_rb_Ingles);
        textoIdioma = (TextView) findViewById(R.id.texto_Idioma);
        textoAlmacenamiento = (TextView) findViewById(R.id.texto_Almacenamiento);
        checkInterno = (CheckBox)findViewById(R.id.cb_Interna);
        checkExterno = (CheckBox)findViewById(R.id.cb_Externa);
        et_NombreFichero = (EditText)findViewById(R.id.eT_nombreFichero);
    }
}
