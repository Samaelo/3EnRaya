package model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase hace referencia a la partida del juego. Implementa la interfaz Serializable para poder transferir objetos de esta clase entre las Activities
 */
public class Partida implements Serializable{

    // Conjunto de soluciones posibles que puede tener el juego 'Tres En Raya'. Cada número hace referencia a la casilla, teniendo en cuenta un tablero de 3x3 y que el número de los botones sigue la
    // nomenclatura de escritura Europea: de izquierda a derecha y de arriba a abajo.
    private final int[][] SOLUCIONES_POSIBLES = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

    private ArrayList<Jugador> jugadores; // ArrayList que contiene a los jugadores de la partida
    private int total_botones_pulsados = 0; // Variable de tipo entero que hace referencia al número de botones que ha pulsado el jugador
    private boolean resultado; // Variable de tipo entero que se asigna al resultado de la partida
    private Jugador jugador_actual; // Objeto de tipo Jugador

    /**
     * Método constructor de la clase Partida que recibe la lista de jugadores que participarán en la partida.
     *
     * @param jugadores - Lista de jugadores que participarán en la partida.
     */
    public Partida(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
        jugador_actual = jugadores.get(0); //Jugador que tiene el turno
    }

    /**
     * Método encargado de asignar al jugador un botón pulsado por el mismo, comprobando que se añada al jugador que tenga el turno.
     *
     * @param boton - Botón pulsado.
     */
    public int pulsar_boton(Boton boton){
        for(int i = 0; i < jugadores.size(); i++) {//Iteramos la lista de jugadores
            if (jugadores.get(i).esTurno()) {//Si es el turno del jugador iterado
                jugadores.get(i).agregarBotonPulsado(boton);
                total_botones_pulsados+=1; //Cada vez que se ejecute este método se habrá pulsado un botón.
            }
        }
        return comprobarSolucion();
    }

    /**
     * Método que cambia el valor del turno de los jugadores. El jugador que tenga el turno pasará a tener turno = false y el que esté parado obtendrá el valor true.
     */
    public void pasarTurno(){
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).esTurno()){
                jugadores.get(i).asignarTurno(false);
            }else{
                jugadores.get(i).asignarTurno(true);
                jugador_actual = jugadores.get(i);
            }
        }
    }

    /**
     * Método que comprueba si el jugador al que le pertenezca el turno actual, ha conseguido alinear tres casillas (victoria) o pasa turno porque ha seleccionado una casilla sin alinear tres de ellas
     * habiendo disponibles almenos una casilla para el siguiente jugador, o esta última pero sin haber casillas susceptibles de ser marcadas (empate).
     * Este método compara todas las posibles soluciones (almacenadas en el atributo <b>SOLUCIONES_POSIBLES</b>) con los botones pulsados de cada jugador.
     *
     * @return - Devuelve 0 si el jugador ha ganado, 1 si pasa turno, 2 si la partida acaba por empate.
     */
    public int comprobarSolucion(){

        String solucion="";
        int resultado=1;
        ArrayList<Boton> botones_pulsados;
        boolean salir1=false, salir3 = false,salir2 = false;

        for(int i = 0;i<jugadores.size() && salir3 == false;i++){//Iteramos la lista de jugadores
            if (jugadores.get(i).esTurno()){//Si es el turno del jugador iterado
                botones_pulsados = jugadores.get(i).obtenerBotonesPulsados();

                if(!(botones_pulsados.size()<3)){
                    for(int j=0;j<SOLUCIONES_POSIBLES.length && salir3 == false;j++){//Iteramos la lista de las soluciones posibles
                        solucion = "";
                        for(int k = 0; k<SOLUCIONES_POSIBLES[j].length;k++){//Recorremos las casillas de la solución iterada
                            for(int l=0;l<botones_pulsados.size() && salir1 == false;l++){//Iteramos la lista de los botones pulsados por el jugador
                                if(SOLUCIONES_POSIBLES[j][k] == botones_pulsados.get(l).getID()){//Por cada botón vemos si la id de la casilla iterada coincide con su id
                                    solucion += SOLUCIONES_POSIBLES[j][k];
                                    salir1 = true;//En cuanto vea que la posicion x de la solución, coincide con la id de un botón, pase a la siguiente posición de ésta.
                                }//if

                                if(solucion.length()==3){//Victoria
                                    salir1 = true;
                                    salir3 = true;
                                    resultado = 0;
                                }//if

                                if(l == botones_pulsados.size()-1 && solucion.length()<3){//Si hemos comparado un numero de una posible solución con todos los botones pulsados
                                    salir1 = true;                                        // y ninguno de ellos coincide, ya no serán 3 en línea, por lo que saltamos a la siguiente solución posible.
                                }//if
                            }//for
                            salir1 = false;
                        }//for
                    }//for
                }//if
            }//if
        }//for
        if(total_botones_pulsados == 9 && solucion.length()<3){ // Empate
            resultado = 2;
        }//if

        return resultado;
    }

    /**
     * Metodo getter que devuelve el valor del objeto que hace referencia al jugador actual
     * @return Retorna el objeto Jugador actual
     */
    public Jugador getJugador_actual() {
        return jugador_actual;
    }

    /**
     * Metodo getter que devuelve el valor del ArrayList de los jugadores que han accedido a la partidaq
     * @return Retorna la lista de jugadores de la partida
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}
