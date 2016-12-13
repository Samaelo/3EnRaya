package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase hace referencia a un jugador de la partida
 */

public class Jugador implements Serializable{

    private String nombre; // Nombre del jugador
    private String color;  // Color del jugador

    private ArrayList<Boton> botones_pulsados; // Lista de botones que pulsa el jugador
    private boolean turno = false; // Booleano que comprueba el turno del jugador

    /**
     * Constructor de la clase Jugador que recibe un nombre y un color
     * @param nombre Hace referencia al nombre del jugador
     * @param color Hace referencia al color del jugador
     */
    public Jugador(String nombre, String color){
        this.nombre = nombre;
        this.color = color;
        botones_pulsados = new ArrayList<Boton>();
    }

    /**
     * Este método asigna el valor del turno
     */
    public void asignarTurno(boolean turno){
        this.turno = turno;
    }

    /**
     * Este método recoge el valor del turno
     * @return Retorna el valor del turno
     */
    public boolean esTurno(){
        return this.turno;
    }

    /**
     * Este método consiste en devolver la lista de botones que ha pulsado el jugador
     * @return Retorna un ArrayList de tipo Boton
     */
    public ArrayList<Boton> obtenerBotonesPulsados(){
        return this.botones_pulsados;
    }

    /**
     * Añade el botón que recibe por parámetro al  ArrayList de Botones pulsados del jugador
     * @param boton Objeto de tipo Boton
     */

    public void agregarBotonPulsado(Boton boton){
        this.botones_pulsados.add(boton);
    }

    /**
     * Este método retorna el color del jugador
     * @return Retorna el color del jugador
     */
    public String getColor() {
        return color;
    }

    /**
     * Este método retorna el nombre del jugador
     * @return Retorna el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
}
