package model;

import java.util.ArrayList;

/**
 *
 * COPIAR LA DESCRIPCIÓN DE LA DOCUMENTACIÓN
 */

public class Jugador {

    private String nombre;
    private String color;

    private ArrayList<Boton> botones_pulsados;
    private boolean turno;
    public Jugador(String nombre, String color){
        this.nombre = nombre;
        this.color = color;

    }

    public void asignarTurno(boolean turno){
        this.turno = turno;
    }

    public boolean esTurno(){
        return this.turno;
    }

    public ArrayList<Boton> obtenerBotonesPulsados(){
        return this.botones_pulsados;
    }

    public void agregarBotonPulsado(Boton b){
        this.botones_pulsados.add(b);
    }
}
