package model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class AccesoFicheros{

    final private static String  FICHERO_CUADRO_HONOR = "cuadrohonor.txt";

    ////pepeGUAY

    public AccesoFicheros(){

    }

    public void escribirFichero(){

        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(FICHERO_CUADRO_HONOR));

            bufferedWriter.write("Joputaaaaaaaaaa");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        finally{

            if(bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.println("Se ha producido un error en la escritura del fichero " + FICHERO_CUADRO_HONOR);
                }
            }
        }
    }

    public void leerFichero(){

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(FICHERO_CUADRO_HONOR));
            String linea;

            linea = bufferedReader.readLine();
            System.out.println("Contenido : " + linea);
        }
        catch (IOException e) {
            System.out.println("Se ha producido un error de E/S");
        }

        finally{

            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println(" No se ha podido leer el fichero.");
                }
            }
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
    public static void verificarPermisosDeAlmacenamiento(Activity activity) {

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
