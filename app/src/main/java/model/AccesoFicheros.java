package model;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class AccesoFicheros{

    final private static String  FICHERO_CUADRO_HONOR = "cuadrohonor.txt";

//////////ueeeeeeeeeeeeeeeeeeeeeeee          >//awegqwgqwegqwgqwge
    public AccesoFicheros(){

    }

    public void escribirFichero(){

        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(FICHERO_CUADRO_HONOR));

            bufferedWriter.write("Joputaaaaaaaaaa");
        }
        catch (IOException e) {
            System.out.println("Se ha producido un error de E/S");
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
}
