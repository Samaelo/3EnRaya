package com.garcia.mario.tresenraya;

/**
 * Created by Samael on 11/12/2016.
 */

public class Validacion {

    public Validacion(){}

    private String listaExclusion = "ºª\\!|@·#€~~$%&¬/()='?¡¿`^[*+]}¨´{_*:.;,<>";
    private int codigoError = 0;

    public int validarNombreJugador(String nombre1, String nombre2){

        char charNombre;
        char charListaExclusion;
        String nombres[] = {nombre1, nombre2};

        for (int i = 0; i < nombres.length; i++){

            if(nombres[i].trim().equals("") )
                return 1; // Ningún nombre en blanco

            else if( nombre1.trim().equals(nombre2.trim()))
                return 2; // Nombres iguales

            else {
                for (int j = 1; j < nombres[i].length(); j++) {
                    charNombre = listaExclusion.charAt(i);

                    for (int k = 1; k < listaExclusion.length(); k ++) {

                        charListaExclusion = listaExclusion.charAt(k);
                        if(charNombre == charListaExclusion)
                            return 3; // Algún nombre tiene un carácter no válido
                    }
                }
            }
        }
    return codigoError;
    }
}
