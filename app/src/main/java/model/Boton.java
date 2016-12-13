package model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Esta clase hace referencia a un Boton
 */
public class Boton {

    private int id; // Variable de tipo int que hace referencia al id del botón

    /**
     * Constructor de la clase Botón
     * @param id Hace referencia a la id del botón
     */
    public Boton(int id){
        this.id = id;
    }

    /**
     * Este método realiza un getter sobre el id del botón
     * @return Retorna la id del botón
     */
    public int getID(){
        return this.id;
    }

}
