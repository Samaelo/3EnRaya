package model;



import java.util.ArrayList;

/**
 * Copiar de la doc.
 */

public class Partida {

    private final int[][] SOLUCIONES_POSIBLES = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

    private ArrayList<Jugador> jugadores;
    private ArrayList<Boton> botones_pulsados;
    private int total_botones_pulsados=0;
    private final String VICTORIA = "VICTORIA";
    private final String EMPATE = "EMPATE";
    private String resultado;


    /**
     * Método constructor de la clase Partida que recibe la lista de jugadores que participarán en
     * la partida.
     * @param jugadores - Lista de jugadores que participarán en la partida.
     */
    public Partida(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
        this.botones_pulsados = new ArrayList<Boton>();
    }

    /**
     * Método encargado de asignar al jugador un botón pulsado por el mismo, comprobando que se
     * añada al jugador que tenga el turno.
     * @param b - Botón pulsado.
     */
    public void pulsar_boton(Boton b){
        for(int i = 0;i<jugadores.size();i++) {//Iteramos la lista de jugadores
            if (jugadores.get(i).esTurno()) {//Si es el turno del jugador iterado
                jugadores.get(i).agregarBotonPulsado(b);
            }
        }
    }
    /**
     * Método que cambia el valor del turno de los jugadores. El jugador que tenga el turno
     * pasará a tener turno = false y el que esté parado obtendrá el valor true.
     */
    public void pasarTurno(){
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).esTurno()){
                jugadores.get(i).asignarTurno(false);

            }else{
                jugadores.get(i).asignarTurno(true);
            }
        }
    }

    /**
     * Método que comprueba si el jugador al que le pertenezca el turno actual, ha conseguido alinear
     * tres casillas (victoria) o pasa turno porque ha seleccionado una casilla sin alinear tres de ellas habiendo
     * disponibles almenos una casilla para el siguiente jugador, o esta última pero sin haber casillas
     * susceptibles de ser marcadas (empate).
     * Este método compara todas las posibles soluciones (almacenadas en el atributo <b>SOLUCIONES_POSIBLES</b>) con los botones pulsados de cada jugador.
     * @return - Devuelve 0 si el jugador ha ganado, 1 si pasa turno, 2 si la partida acaba por empate.
     */
    public int comprobarSolucion(){
        String solucion="";
        int resultado=1;
        ArrayList<Boton> botones_pulsados;
        boolean salir1=false, salir2 = false;

        total_botones_pulsados+=1; //Cada vez que se ejecute este método se habrá pulsado un botón.

        for(int i = 0;i<jugadores.size() && !salir2;i++){//Iteramos la lista de jugadores
            if (jugadores.get(i).esTurno()){//Si es el turno del jugador iterado
                botones_pulsados = jugadores.get(i).obtenerBotonesPulsados();

                if(!(botones_pulsados.size()<3)){
                    for(int j=0;j<SOLUCIONES_POSIBLES.length && !salir2;j++){//Iteramos la lista de las soluciones posibles
                        for(int k = 0; k<SOLUCIONES_POSIBLES[j].length && !salir1;k++){//Recorremos las casillas de la solución iterada
                            for(int l=0;l<botones_pulsados.size() && !salir1;l++){//Iteramos la lista de los botones pulsados por el jugador
                                if(SOLUCIONES_POSIBLES[j][k] == botones_pulsados.get(l).getID()){//Por cada botón vemos si la id de la casilla iterada coincide con su id
                                    solucion+=SOLUCIONES_POSIBLES[j][k];
                                }

                                if(l == botones_pulsados.size()-1 && solucion.length()<3){//Si hemos comparado un numero de una posible solución con todos los botones pulsados
                                    salir1 = true;                                          // y ninguno de ellos coincide, ya no serán 3 en línea, por lo que saltamos a la siguiente solución posible.
                                }

                                if(solucion.length()==3){//Victoria
                                    salir1 = true;
                                    salir2 = true;
                                    resultado=0;
                                }
                            }
                        }
                        salir1 = false;
                    }
                }

            }
        }
        if(total_botones_pulsados == 9 && solucion.length()<3){//Empate
            resultado = 2;
        }

        return resultado;
    }

    public void finalizar(int codResultado){

    }


}
