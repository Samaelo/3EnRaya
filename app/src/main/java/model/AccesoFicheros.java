package model;

import android.content.Context;
import android.util.Log;



import com.garcia.mario.Activities.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Esta clase permite el acceso a los ficheros
 */
public class AccesoFicheros {

    // Constante de tipo String que hace referencia al nombre del fichero donde se guardan los datos de las partidas y los ganadores que han ganado dicha partida
    final private static String FICHERO_CUADRO_HONOR = "cuadrohonor.txt";

    Context contexto; // Contexto de la Actividad
    Partida partida; // Objeto Partida

    /**
     * Constructor de la clase AccesoFicheros que recibe por parámetros el Contexto de la actividad y una Partida
     *
     * @param contexto Contexto de la Actividad
     * @param partida Objeto Partida
     */
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
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        String jugador1 = partida.getJugadores().get(0).getNombre();
        String jugador2 = partida.getJugadores().get(1).getNombre();
        String ganador = partida.getJugador_actual().getNombre();

        String resultadoPartida = String.format(contexto.getResources().getString(R.string.partidaGuardada), jugador1, jugador2, ganador);

        try {
            File fichero = new File(FICHERO_CUADRO_HONOR); // Creamos un fichero con el nombre de la constante
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

    /**
     * Este método accede al fichero definido en la constante FICHERO_CUADRO_HONOR. Como el almacenamiento de los datos en la partida y el ganador, está separado por una coma (','), necesitamos
     * obtener los valores tanto de la partida como del ganador. Para ello, utilizamos el método StringTokenizer, especificando que el separador es la coma (','). En primer lugar, el primer String
     * que obtenemos con el método nextToken(), hace referencia al String de la partida(Jugador1 vs Jugador2), que lo almacenamos en el String definido como 'versus', y el segundo String que obtenemos
     * volviendo a usar el método nextToken(), lo volcamos en la variable 'ganador' y en ella recogemos el nombre del ganador.
     *
     * Una vez que tenemos los dos datos de interés que queremos recoger(partida y ganador), volcamos los datos en un String que hemos definido como 'resultado_partida', que posteriormente añadimos
     * al ArrayList de String denominado 'partidas_ganadas', para finalmente devolver dicho ArrayList.
     * El objetivo de este método, es devolver el contenido del fichero en forma de ArrayList de tipo String, para poder darle forma con un Adaptador.
     *
     * @return Retorna un ArrayList que contiene las partidas almacenadas en el fichero
     */
    public ArrayList<String> leerDatosDeFichero() {

        ArrayList<String> partidas_ganadas = new ArrayList<String>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String versus = "";
        String ganador = "";

        try {
            fis = contexto.openFileInput(FICHERO_CUADRO_HONOR);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String texto;

            while ((texto = br.readLine()) != null) {
                StringTokenizer st1 = new StringTokenizer(texto,",");

                while(st1.hasMoreTokens()){
                    versus = st1.nextToken();
                    ganador = st1.nextToken();
                }

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

            }
        }
        return partidas_ganadas;
    }
}
