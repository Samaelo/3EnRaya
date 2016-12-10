package model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


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


public class AccesoFicheros{

    final private static String  FICHERO_CUADRO_HONOR = "cuadrohonor.txt";

    ////pepeGUAY

    public AccesoFicheros(){

    }

    public void escribirFichero(Context contexto){

        BufferedWriter bufferedWriter = null;

        String str = "tu puta madre en vinagre";
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try{
            File fichero = new File(FICHERO_CUADRO_HONOR);
            fos = contexto.openFileOutput(fichero.getName(), contexto.MODE_APPEND | contexto.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);

            // Escribimos el String en el archivo
            osw.append("\n" + str);

            // Mostramos que se ha guardado
            Toast.makeText(contexto.getApplicationContext(), "Guardado " + fichero.getName(), Toast.LENGTH_SHORT).show();

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
            catch (IOException e) {Toast.makeText(contexto.getApplicationContext(), "RETOCAAAAAAAAAAAAAAAAAAR", Toast.LENGTH_SHORT).show();}
        }

        try {
            fis = contexto.openFileInput("cuadroHonor.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String texto;

            while((texto = br.readLine()) != null){

                Toast.makeText(contexto.getApplicationContext(),"Contenido del fichero " + texto, Toast.LENGTH_LONG).show();

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
            catch (IOException e) { Toast.makeText(contexto.getApplicationContext(), "RETOCAAAAAAAAAAAAAAAAAAR", Toast.LENGTH_SHORT).show();}
        }
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
