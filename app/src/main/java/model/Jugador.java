package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * COPIAR LA DESCRIPCIÓN DE LA DOCUMENTACIÓN
 */

public class Jugador implements Serializable{

    private String nombre;
    private String color;

    private ArrayList<Boton> botones_pulsados;
    private boolean turno = false;
    public Jugador(String nombre, String color){
        this.nombre = nombre;
        this.color = color;
        botones_pulsados = new ArrayList<Boton>();

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
