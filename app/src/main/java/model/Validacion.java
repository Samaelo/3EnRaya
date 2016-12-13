package model;

/**
 * Esta clase valida los datos de los jugadores
 */

public class Validacion {

    /**
     * Constructor de la clase Validacion
     */
    public Validacion(){}

    private String listaExclusion = "ºª\\\"!|@·#€~~$%&¬/()='?¡¿`^[*+]}¨´{_*:.;,<>"; // Lista que contiene los caracteres no válidos para el nombre de los jugadores

    /**
     * Este método valida los nombres introducidos por los jugadores. Para ello, se crea un Array de String denominado nombres[]. Se recorre dicho Array, y para cada uno de los nombres se comprueba:
     * primero que los nombres no estén vacíos, si no están vacíos que los nombres no sean iguales, y si no están vacíos y no están iguales, que ninguno de los dos contengan caracteres no válidos
     * que están definidos en la variable de tipo String definida como 'listaExclusion' que contiene el valor de todos los caracteres que no puede contener ninguno de los jugadores.
     *
     * @param nombre1 Variable de tipo String que hace referencia al nombre introducido por el jugador 1
     * @param nombre2 Variable de tipo String que hace referencia al nombre introducido por el jugador 2
     * @return Retorna un código de error que hace referencia al tipo de error que se ha producido a la hora de validar los datos de los nombres de los jugadores
     */
    public int validarNombreJugador(String nombre1, String nombre2){

        char charListaExclusion; // Variable de tipo char que hace referencia al caracter de la lista de exclusión
        String nombres[] = {nombre1, nombre2}; // Array donde almacenamos los 2 nombres de los jugadores

        for (int i = 0; i < nombres.length; i++){ // De 0 a la longitud del Array de jugadores (en este caso 2)

            if(nombres[i].trim().equals("")) // Si alguno de los nombres está en blanco
                return 1; // Retornamos 1

            else if(nombre1.trim().toUpperCase().equals(nombre2.trim().toUpperCase())) // Si los nombres son iguales
                return 2; // Retornamos 2

            else {
                for (int j = 0; j < listaExclusion.length(); j ++) {
                    charListaExclusion = listaExclusion.charAt(j); // Volcamos en la variable charListaExclusión, el valor del caracter actual de la lista de exclusión
                    if(nombres[i].contains(charListaExclusion+"")) // Si alguno de los nombres contiene el caracter de exclusión
                        return 3; // Retornamos 3
                }
            }
        }
    return 0; // Retornamos 0 en caso de que la validación haya sido correcta
    }
}
