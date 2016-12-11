package model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


import com.garcia.mario.tresenraya.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class AccesoFicheros {

    final private static String FICHERO_CUADRO_HONOR = "cuadro_honor.txt";

    Context contexto;
    Partida partida;

    public AccesoFicheros(Context contexto, Partida partida){
        this.contexto = contexto;
        this.partida = partida;
    }

    /**
     * Método encargado de almacenar el resultado de la partida en el fichero "cuadrohonor.txt".
     *
     * @return - Devuelve 0 en caso de que se haya almacenado correctamente la información.
     * Devuelve 1 si ha ocurrido algún error.
     */
    public int guardarResultadoEnFichero() {
        int cod_resultado = 0;
        BufferedWriter bufferedWriter = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;


        String jugador1 = partida.getJugadores().get(0).getNombre();
        String jugador2 = partida.getJugadores().get(1).getNombre();
        String ganador = partida.getJugador_actual().getNombre();

        String resultadoPartida = String.format(contexto.getResources().getString(R.string.partidaGuardada), jugador1, jugador2, ganador);

        try {
            File fichero = new File(FICHERO_CUADRO_HONOR);
            fos = contexto.openFileOutput(fichero.getName(), contexto.MODE_APPEND | contexto.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);

            // Escribimos el String en el archivo
            osw.append("\n" + resultadoPartida);
        }
        catch (IOException ex) {
            cod_resultado = 1;
        }
        finally {

            try {
                if (osw != null) osw.close();
                if (fos!=null) fos.close();

            } catch (IOException e) {
                cod_resultado = 1;
            }
            return cod_resultado;
        }
    }

    public ArrayList<String> leerDatosDeFichero() {

        ArrayList<String> partidas_ganadas = new ArrayList<String>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;


        try {
            fis = contexto.openFileInput(FICHERO_CUADRO_HONOR);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String texto;

            while ((texto = br.readLine()) != null) {
                StringTokenizer st1 = new StringTokenizer(texto,",");
                String versus = st1.nextToken();
                String ganador = st1.nextToken();

                String resultado_partida = "Partida: " + versus + ". Ganador: " + ganador;
                partidas_ganadas.add(resultado_partida);


            }

        }
        catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        finally {
            try {
                if (fis != null && isr != null && br != null) {
                    br.close();
                    isr.close();
                    fis.close();
                }
            } catch (IOException e) {
                Toast.makeText(contexto.getApplicationContext(), "RETOCAAAAAAAAAAAAAAAAAAR", Toast.LENGTH_SHORT).show();
            }
        }
        return partidas_ganadas;
    }



    // -- PETICIÓN DE PERSMISOS -- //

    /**
     * Constante de tipo entero que es utilizada para verificar los permisos sobre la memoria externa.
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    /**
     * Array de constantes de tipo String que ayudan a conocer si los permisos garantizados son los deseados. Dentro del Array pedimos los permisos de lectura y escritura del fichero.
     */
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Verifica que los permisos de escritura en la memoria externa están garantizados.
     * @param activity - La actividad que desea verificar los
     */
    public void verificarPermisosDeAlmacenamientoExterno(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
