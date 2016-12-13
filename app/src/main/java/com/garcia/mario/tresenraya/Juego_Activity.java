package com.garcia.mario.tresenraya;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.AccesoFicheros;
import model.Boton;
import model.Partida;

/**
 * Esta clase pertenece a la Activity donde se produce el desarrollo del juego.
 */
public class Juego_Activity extends AppCompatActivity {

    Partida partida; // Objeto de tipo partida

    // Variables de tipos enteros que recogen los valores de las id's de los Recursos que hacen referencia a los botones
    final int id_btn1 = R.id.btn1, id_btn2 = R.id.btn2, id_btn3 = R.id.btn3, id_btn4 = R.id.btn4, id_btn5 = R.id.btn5, id_btn6 = R.id.btn6, id_btn7 = R.id.btn7, id_btn8 = R.id.btn8, id_btn9 = R.id.btn9;

    TextView txtTurno; // TextView

    AccesoFicheros accesoFichero; // Objeto de tipo AccesoFicheros

    /**
     * En este método se crea la Actividad. Instanciamos un Intent con el método getIntent(). Creamos una partida, cogiendo los datos del intent, mediante la clave "PARTIDA", que contiene como valor
     * una partida. Cargamos los componentes de la Activity, mediante el método cargar_componentes(), actualizamos el texto del turno mediante el método actualizar_txtTurno(), e instanciamos
     * un objeto de la clase AccesoFicheros, que recibe el contexto de la aplicación de la cual ha sido llamada, y una partida(cuyo valor lo hemos obtenido a partir del getIntent())
     * @param savedInstanceState Objeto de tipo Bundle donde guardamos el estado de nuestras variables su estado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        Intent intent = getIntent();
        partida = (Partida) intent.getExtras().getSerializable("PARTIDA");
        cargar_componentes();
        actualizar_txtTurno();
        accesoFichero = new AccesoFicheros(getApplicationContext(),partida);
    }

    /**
     * Este método recoge el id del botón que se pulsa. A partir de se id, se invoca al método pulsar_boton(), al cual se le pasan dos parámetros: el primero hace referencia al id del botón que se
     * ha pulsado, y en segundo lugar se le pasa un botón.
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void onBtnPulsado(View v){

        Button btn = (Button) v;

        switch(btn.getId()){

            case id_btn1:
                pulsar_boton(1, btn);
                break;
            case id_btn2:
                pulsar_boton(2, btn);
                break;
            case id_btn3:
                pulsar_boton(3, btn);
                break;
            case id_btn4:
                pulsar_boton(4, btn);
                break;
            case id_btn5:
                pulsar_boton(5, btn);
                break;
            case id_btn6:
                pulsar_boton(6, btn);
                break;
            case id_btn7:
                pulsar_boton(7, btn);
                break;
            case id_btn8:
                pulsar_boton(8, btn);
                break;
            case id_btn9:
                pulsar_boton(9, btn);
                break;
        }

    }

    /**
     * Este método invoca al método pasarTurno() de la clase Partida y al método actualizar_txtTurno()
     */
    public void pasarTurno(){
        partida.pasarTurno();
        actualizar_txtTurno();

    }

    /**
     * Este método asigna el nombre del jugador que tiene el turno al botón. Lo hace mediente el método getJugador_actual.getNombre() de la clase Partida. También le asigna el color del jugador
     * mediante el método getJugador_actual().getColor().
     * @param boton Objeto de la clase Button
     */
    public void ponerNombreJugador(Button boton){

        boton.setText(partida.getJugador_actual().getNombre()); // Asignamos un texto al botón
        boton.setTextColor(Color.parseColor(partida.getJugador_actual().getColor())); // Asignamos un color al texto del botón

    }

    /**
     * Este método carga los componentes de la Activity. En este caso el único componente es el texto donde se muestra el turno del jugador
     */
    public void cargar_componentes(){
        txtTurno = (TextView) findViewById(R.id.txtTurno);
    }

    /**
     * Este método asigna al TextView txtTurno donde se muestra el turno del jugador. Para ello se vuelca en una variable de tipo String denominada 'textoTurno', el String almacenado en los
     * recursos de la aplicación, mediante el método getResources().getString(R.string.txtTurno). La variable que se le asigna al String de los Recursos hará referencia al nombre del jugador actual, y para
     * recoger ese valor, lo hacemos mediante el método getJugador_actual().getNombre() de la clase Partida.
     */
    public void actualizar_txtTurno(){

        String textoTurno = String.format(getResources().getString(R.string.txtTurno), partida.getJugador_actual().getNombre()); // Recogemos el valor en la variable textoTurno
        txtTurno.setText(textoTurno); // En el TextView aplicamos el valor de la variable textoTurno
        txtTurno.setTextColor(Color.parseColor(partida.getJugador_actual().getColor())); // Aplicamos el color del jugador al texto del turno del jugador
    }

    // -- MÉTODO DONDE GUARDAR LOS DATOS DE LA PARTIDA EN EL FICHERO --  A la vez que devolvemos el resultado al Configuracion_Activity, lo guardamos en el fichero //

    /**
     * Este método asigna una acción u otra dependiendo del resultado que recibe por parámetro. Si el resultado es 0, se guardan los datos en el fichero mediante el método guardarResultadoEnFichero()
     * de la clase AccesoFicheros, a través de la variable accesoFicheros que hemos definido a nivel de clase. Con intent volvemos a la pantalla de configuración(Configuracion_Activity) y le pasamos
     * el nombre del ganador mediente el método putExtra() del Intent. Para definir este valor, asignamos una clave denominada "GANADOR" donde adjuntamos como valor, el nombre del jugador ganador
     * que obtenemos mediante el método getJugador_actual().getNombre() de la clase Partida.
     * Por último, mediante el método setResult() pasamos como primer parámetro el resultado que hemos recibido como parámetro en el método.
     * Si el resultado es distinto de 0, significa que la partida no ha finalizado con un ganador, es decir, se ha podido volver sin que la partida finalice, o la partida ha podido quedar en tablas (empate ),
     * por lo que la partida no se guarda en el fichero. Por último, independientemente del resultado, se finaliza la Actividad.
     * @param resultado Variable de tipo entero que hace referencia al resultado que se obtiene cuando se pulsa un botón.
     */
    public void finalizar_partida(int resultado){

        if(resultado == 0){

            accesoFichero.guardarResultadoEnFichero();

            Intent intent = new Intent(getApplicationContext(),Configuracion_Activity.class);
            intent.putExtra("GANADOR",partida.getJugador_actual().getNombre());
            setResult(resultado,intent);

        }else{
            setResult(resultado);
        }
        finish();
    }

    /**
     * Este método se produce cuando pulsamos el botón atrás del móvil
     */
    @Override
    public void onBackPressed() {
        setResult(3);
        super.finish();
    }

    /**
     * Este método detecta cuando un botón ha sido pulsado. Cuando se pulsa el botón, lo primero que se hace es comprobar si el botón ya ha sido pulsado. Si el contenido del texto del botón está en blanco,
     * significa que ese botón todavía no ha sido pulsado. Por ello, se procede a llamar al método ponerNombreJugador() al cual le pasamos el botón que hemos pulsado, y que procede a asignar el nombre
     * del jugador actual al botón. Una vez que el jugador ha pulsado el botón, es necesario saber en qué situación de la partida nos encontramos, es decir, saber si el jugador que ha pulsado el botón
     * debe pasar el turno, ha ganado, o ha provocado el empate. Para ello, utilizamos el método pulsar_boton() de la clase Partida, al cual le pasamos un botón con su id.
     * Si el resultado que obtenemos es 1, significa que el jugador debe pasar el turno, por lo que se llama al método pasarTurno(). Si el resultado es distinto de 1, es decir, el jugador no debe pasar
     * el turno, significa que se ha producido un evento en la partida, es decir, se ha llegado a empate o alguno de los jugadores finaliza la partida sin que ésta haya terminado con un ganador.
     * Por ello, se invoca al método finalizar_partida() al cual se le pasa por parámetro el código 'resultado' obtenido a partir del método pulsar_boton de la clase Partida obtenido anteriormente.
     * En contraposición, si el texto del botón no está vacío, significa que contiene el nombre d ealguno de los jugadores, por lo que se vuelca en la variable de tipo String 'mensaje_trampa'
     * el String almacenado en los Recursos definido como 'toastTrapa' y dicho mensaje se vuelca en un Toast, para informar al usuario de que no haga trampas, puesto que ha intentado pulsar un botón
     * que ya está pulsado.
     *
     * @param idBoton Variable de tipo entero que recoge el id del botón almacenado en la carpeta de Recursos
     * @param boton Objeto de tipo botón que hace referencia al botón pulsado
     */
    public void pulsar_boton(int idBoton, Button boton){

        String mensaje_trampa;

        if(boton.getText().toString().trim().equals("")){
            ponerNombreJugador(boton);
            int resultado = partida.pulsar_boton(new Boton(idBoton));

            if(resultado ==1){
                pasarTurno();
            }
            else{
                finalizar_partida(resultado);
            }
        }
        else{
            mensaje_trampa = String.format(getResources().getString(R.string.toastTrampa));
            Toast.makeText(getApplicationContext(), mensaje_trampa, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * Este método asigna valor 3 al setResult que esperaba la Actividad Configuracion_Activity y finaliza la actividad actual del juego(Juego_Activity)
     */
    public void volver(View v){
        setResult(3);
        finish();
    }

    /**
     * Este método se aplica a la acción de pulsar el botón salir (como Listener del boton 'Salir'). Cuando se pulsa el botón salir, aparece una ventana de alerta, en este caso definido con un
     * AlertDialog, en el cual se le da la oportunidad al usuario de confirmar su salida. Si se pulsa 'Si', se asigna el valor 4 al setResult() que espera la actividad de configuración
     * (Configuracion_Activity) y se finaliza la ejecución de la Actividad actual (Juego_Activity).
     * Si por el contrario el botón pulsado del AlertDialog es 'No', entonces no ocurre nada, el AlertDialog se cierra, y se sigue el curso de la Actividad con Normalidad.
     *
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void finalizarPrograma(View v){

        //Mostrar un mensaje de confirmación antes de realizar el test
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea abandonar la aplicación?");
        alertDialog.setTitle("Salir");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int which)
            {

                //Finalizamos la ejecución de la actividad y del resto de ellas de la aplicación.
                setResult(4);
                finish();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                //código java si se ha pulsado no
            }
        });
        alertDialog.show();
    }
}
