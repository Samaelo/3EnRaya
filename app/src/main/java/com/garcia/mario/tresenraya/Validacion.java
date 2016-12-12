package com.garcia.mario.tresenraya;

/**
 * Created by Samael on 11/12/2016.
 */

public class Validacion {

    public Validacion(){}

    private String listaExclusion = "ºª\\\"!|@·#€~~$%&¬/()='?¡¿`^[*+]}¨´{_*:.;,<>";
    private int codigoError = 0;

    public int validarNombreJugador(String nombre1, String nombre2){

        char charNombre;
        char charListaExclusion;
        String nombres[] = {nombre1, nombre2};

        for (int i = 0; i < nombres.length; i++){

            if(nombres[i].trim().equals("") )
                return 1; // Ningún nombre en blanco

            else if(nombre1.trim().toUpperCase().equals(nombre2.trim().toUpperCase()))
                return 2; // Nombres iguales

            else {
                for (int j = 0; j < listaExclusion.length(); j ++) {
                    charListaExclusion = listaExclusion.charAt(j);
                    if(nombres[i].contains(charListaExclusion+""))
                        return 3; // Algún nombre tiene un carácter no válido
                }
            }
        }
    return codigoError;
    }
}
