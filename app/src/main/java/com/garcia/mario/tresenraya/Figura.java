package com.garcia.mario.tresenraya;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Samael on 10/12/2016.
 */

public class Figura extends View {


    public Figura(Context contexto) {
        super(contexto);
    }

    public void dibujar(Canvas canvas, int color){

        Paint miPincel = new Paint();
        miPincel.setColor(color);
        miPincel.setStrokeWidth(10);
        miPincel.setStyle(Paint.Style.STROKE);


    }

}
