package com.garcia.mario.tresenraya;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.ArrayList;

import model.AccesoFicheros;
import model.Jugador;
import model.Partida;

/**
 * Esta clase hace referencia a la pantalla de configuración, en la que los jugadores van a establecer sus nombres y los colores con los cuales desea jugar cada uno,
 * antes de comenzar a jugar una partida.
 */
public class Configuracion_Activity extends AppCompatActivity {

    // Elementos RadioButton que hace referencia a la elección de los colores de cada jugador
    RadioButton rbNaranjaJugador1,rbAzulJugador1,rbVerdeJugador1,rbNaranjaJugador2, rbVerdeJugador2, rbAzulJugador2;
    EditText eTxtJugador1,eTxtJugador2; // Textos en los cuales los jugadores introducen sus nombres

    // Variables de tipos enteros que recogen los valores de las id's de los Recursos que hacen referencia a los botones
    final int id_rbNaranjaJugador1 = R.id.rbNaranjaJugador1, id_rbNaranjaJugador2 = R.id.rbNaranjaJugador2, id_rbVerdeJugador1 = R.id.rbVerdeJugador1, id_rbVerdeJugador2 = R.id.rbVerdeJugador2,
              id_rbAzulJugador1 = R.id.rbAzulJugador1, id_rbAzulJugador2 = R.id.rbAzulJugador2;

    // Constantes que hacen referencia a los colores naranja, verde y azul. Cada uno recoge su valor en hexadecimal
    final String COLOR_NARANJA = "#FF9900";
    final String COLOR_VERDE = "#097054";
    final String COLOR_AZUL = "#00628B";

    String jugador1; // String donde recogemos el nombre del jugador 1
    String jugador2; // String donde recogemos el nombre del jugador 2

    /**
     * Este método crea la Actividad.
     *
     * @param savedInstanceState Objeto de tipo Bundle donde guardamos el estado de nuestras variables su estado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        cargar_componentes();
    }

    /**
     * Este método lanza la Actividad Juego_Activity. Para ello, primero comprueba la longitud del mensaje que se recibe a través del método validar_campos() (que recibe por parámetro el contenido de los
     * EditText que hacen referencia a los nombres de los jugadores). Si la longitud del mensaje es menor que 3 ( se ha estimado así, ya que un mensaje de error debe tener una longitud superior a 3,
     * pero podría haberse puesto otra condición, como por ejemplo que la longitud del mensaje fuese menor que 2 o 4...) significa que el método validar_campos() no ha devuelto ningún mensaje de error.
     * Primero instanciamos un ArrayList de tipo Jugador denominado 'jugadores'. Después instanciamos un jugador que recibe en su constructor el contenido del EditText denominado 'eTxtJugador1' y un color
     * que hace referencia al elemento RadioButton que haya elegido dicho jugador. Una vez creado, le asignamos el turno mediante el método asignarTurno() que recibe por parámetro un booleano, en este caso
     * será true, ya que queremos que la partida empiece con el turno en el jugador 1. Posteriormente instanciamos un jugador 2 que recibe en su constructor el contenido del EditText denominado
     * 'eTxtJugador2' y un color que hace referencia al elemento RadioButton que haya elegido al igual que sucedía con el jugador 1.
     * Una vez creados los dos jugadores, los añadimos al ArrayList que hemos creado denominado 'jugadores'. Instanciamos una partida a la cual pasamos dicho ArrayList y mediante un Intent
     * llamamos a la actividad 'Juego_Activity', en la cual pasamos la partida.
     *
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void on_btnJugar_pulsado(View v){

        ArrayList<Jugador> jugadores;
        Intent intent;
        String mensaje = validar_campos(eTxtJugador1.getText().toString(), eTxtJugador2.getText().toString());

        if(mensaje.length()<3){

            jugadores = new ArrayList<Jugador>();
            Jugador j1 = new Jugador(eTxtJugador1.getText().toString(),obtener_color(1));
            j1.asignarTurno(true);
            jugadores.add(j1);
            jugadores.add(new Jugador(eTxtJugador2.getText().toString(),obtener_color(2)));

            Partida p = new Partida(jugadores);

            intent = new Intent(getApplicationContext(),Juego_Activity.class);
            intent.putExtra("PARTIDA",p);
            startActivityForResult(intent,1);
        }
        else{
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Este método se produce cuando se pulsa el botón atrás del móvil. Primero se finaliza la Activity de la configuración (Configuracion_Activity) mediante el método finish() y posteriormente, mediante
     * un intent que instanciamos, pasamos a Activity del menú principal (MenuPrincipal_Activity)
     */
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }

    /**
     * Este método recoge los datos de la Actividad que ha sido llamada mediante el método startActivityForResult(). Dependiendo del código que se devuelva en la Activity que ha sido llamada,
     * mediante el método resultSet(), que asigna un valor al requestCode que recibe esta Actividad, realiza una cosa u otra. Si el código del requestCode es 0, significa que hay un ganador, por lo tanto
     * se recoge el valor del ganador mediante el método getExtras().getString("GANADOR") que contiene el nombre del ganador. Se crea una variable de tipo String denominada mensaje_victoria que hace
     * referencia al String que tenemos almacenado en R denominado 'toastVictoria'. Por último mostramos el mensaje por pantalla en un Toast.
     * Si el resultCode tiene valor 2, significa que se ha producido un empate. Se crea una variable de tipo String denominada mensaje_empate que hace referencia al String que tenemos almacenado
     * en R denominado 'toastEmpate'. Posteriormente se muestra el mensaje a través de un Toast. Si el código del resultCode es 3, significa que se ha producido una salida hacia atrás de la partida
     * sin que esta haya finalizado con un resultado(un ganador). Se procede a finalizar con la Actividad actual (Configuacion_Activity),
     * se muestra un Toast con el String "La partida ha finalizado. No ha habido ningún ganador". Si por el contrario el valor del resultCode es 4, se procede a finalizar con todas las Actividades que
     * estén sujetas a la Activity de configuración (Configuracion_Activity) y se sale del sistema.
     *
     * @param requestCode Variable de tipo int que hace referencia al código de la Actividad mediante la cual ha sido llamada la otra Actividad
     * @param resultCode Variable de tipo int que hace referencia al resultado que se quiere enviar desde otra Activity cuando se produce un suceso concreto
     * @param data Objeto de tipo Intent en el cual están almacenados los datos de la Activity que ha sido llamada mediante el método startActivityForResult()
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        String ganador;
        String mensaje_victoria;
        String mensaje_empate;

        if(requestCode==1){
            if (resultCode == 0){
                ganador = data.getExtras().getString("GANADOR");
                mensaje_victoria = String.format(getResources().getString(R.string.toastVictoria), ganador);
                Toast.makeText(this,mensaje_victoria,Toast.LENGTH_LONG).show();

            }else if (resultCode == 2){

                mensaje_empate = String.format(getResources().getString(R.string.toastEmpate));
                Toast.makeText(this,mensaje_empate,Toast.LENGTH_LONG).show();

            }else if (resultCode == 3){
                finish();
                Toast.makeText(this,"La partida ha finalizado. No ha habido ningún ganador",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuPrincipal_Activity.class);
                startActivity(intent);
            }
            else if(resultCode == 4){
                finishAffinity();
                System.exit(0);
            }
        }
    }

    /**
     * Este método valida el contenido de los EditText que hacen refencia al nombre del Jugador 1 y Jugador 2 antes de acceder a la partida. Primero instanciamos un objeto de la clase Validacion denominado
     * 'validacion'. Obtenemos un código de error, definido en la variable 'codigoError' que recoge el valor del método validarNombreJugador() de la clase Validacion, que recibe por parámetro los Strings
     * de los nombres de los jugadores. Dependiendo del código de error que obtengamos, se produce una secuencia de acciones u otras.
     * Si el valor del código de error es 1, significa que alguno de los EditText de los jugadores está en blanco (o los dos). Si el código de error es 1, significa que ambos nombres son iguales, es decir,
     * el contenido de los valores de cada EditText, es el mismo. Si por el contrario, el código de error es 3, signfica que alguno de los nombres(o los dos) contienen caracteres no válidos.
     *
     * @param nombreJ1 Variable de tipo String que hace referencia al EditText del Jugador 1, que contiene el nombre del jugador 1
     * @param nombreJ2 Variable de tipo String que hace referencia al EditText del Jugador 2, que contiene el nombre del jugador 2
     * @return Retorna una variable de tipo String denominada 'mensaje' que contene el mensaje de error correspondiente al código de error
     */
    public String validar_campos(String nombreJ1, String nombreJ2){

        String mensaje="";
        Validacion validacion = new Validacion();

        int codigoError = validacion.validarNombreJugador(nombreJ1, nombreJ2);

        switch (codigoError){

            case 1:
                    mensaje = "Alguno de los nombres está en blanco";
                    break;
            case 2:
                    mensaje = "Los nombres son iguales";
                    break;
            case 3:
                    mensaje = "Alguno de los nombres contiene carácteres no válidos";
                    break;
        }
        return mensaje;
    }

    /**
     * Este método asigna un color al jugador. Para ello, el método recibe un id del jugador. El jugador 1 tendrá el id 1 y el jugador 2 tendrá el id 2. Dependiendo del RadioButton que haya pulsado cada
     * jugador, obtendrá un color u otro.
     * @param idJugador Variable de tipo int que hace referencia al id del jugador.
     * @return Retorna un String denominado 'color' que recoge el valor de la constante definida a nivel de clase que hace refencia
     */
    public String obtener_color(int idJugador){

        String color;

        if (idJugador == 1){

            if(rbNaranjaJugador1.isChecked()){
                color = COLOR_NARANJA;
            }
            else if(rbVerdeJugador1.isChecked()){
                color = COLOR_VERDE;
            }
            else{
                color = COLOR_AZUL;
            }
        }
        else{
            if(rbNaranjaJugador2.isChecked()){
                color = COLOR_NARANJA;
            }
            else if(rbVerdeJugador2.isChecked()){
                color = COLOR_VERDE;
            }
            else{
                color = COLOR_AZUL;
            }
        }
        return color;
    }

    /**
     * Este método invoca al método bloquear_radiobutton y recibe por parámetro la Vista del método que lo invoca(el método invocador es on_radiobutton_pulsado)
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void on_radiobutton_pulsado(View v){

        bloquear_radiobutton(v);
    }

    /**
     * Este método recibe por parámetro la vista del método que lo invoca. Mediante un switch, y cogiendo la id de cada botón, se establece que cuando un botón de un jugador se pulsa(mientras no esté
     * pulsado en el otro jugador), se bloquee dicho botón en el RadioButton del otro jugador. De este modo se pierde la posibilidad de que los dos jugadores jueguen con el mismo color.
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void bloquear_radiobutton(View v){

        RadioButton rb = (RadioButton) v;

        switch(rb.getId()){

            case id_rbNaranjaJugador1:
                activar_radiobuttons_jugador2();
                rbNaranjaJugador2.setEnabled(false);
                break;

            case id_rbNaranjaJugador2:
                activar_radiobuttons_jugador1();
                rbNaranjaJugador1.setEnabled(false);
                break;

            case id_rbVerdeJugador1:
                activar_radiobuttons_jugador2();
                rbVerdeJugador2.setEnabled(false);
                break;

            case id_rbVerdeJugador2:
                activar_radiobuttons_jugador1();
                rbVerdeJugador1.setEnabled(false);
                break;
            case id_rbAzulJugador1:
                activar_radiobuttons_jugador2();
                rbAzulJugador2.setEnabled(false);
                break;

            case id_rbAzulJugador2:
                activar_radiobuttons_jugador1();
                rbAzulJugador1.setEnabled(false);
                break;
        }
    }

    /**
     * Este método carga los componentes de la aplicación. Carga 6 RadioButton que hace referencia a los 3 RadioButton de los colores de cada jugador, y 2 EditText, que hacen referencia a los elementos
     * en los cuales los jugadores van a introducir sus nombres.
     */
    public void cargar_componentes(){
        rbNaranjaJugador1 = (RadioButton) findViewById(id_rbNaranjaJugador1);
        rbNaranjaJugador2 = (RadioButton) findViewById(id_rbNaranjaJugador2);
        rbVerdeJugador1 = (RadioButton) findViewById(id_rbVerdeJugador1);
        rbVerdeJugador2 = (RadioButton) findViewById(id_rbVerdeJugador2);
        rbAzulJugador1 = (RadioButton) findViewById(id_rbAzulJugador1);
        rbAzulJugador2 = (RadioButton) findViewById(id_rbAzulJugador2);
        eTxtJugador1 = (EditText) findViewById(R.id.eTxtJugador1);
        eTxtJugador2 = (EditText) findViewById(R.id.eTxtJugador2);
        eTxtJugador1.requestFocus();


    }

    /**
     * Este método habilita los RadioButtons del jugador 1 cuando lo pulsa, en caso de que dicho RadioButton esté deshabilitado.
     */
    public void activar_radiobuttons_jugador1(){
        if(!rbNaranjaJugador1.isEnabled()) rbNaranjaJugador1.setEnabled(true);
        if(!rbVerdeJugador1.isEnabled()) rbVerdeJugador1.setEnabled(true);
        if(!rbAzulJugador1.isEnabled()) rbAzulJugador1.setEnabled(true);
    }

    /**
     * Este método habilita los RadioButtons del jugador 2 cuando lo pulsa, en caso de que dicho RadioButton esté deshabilitado.
     */
    public void activar_radiobuttons_jugador2(){
        if(!rbNaranjaJugador2.isEnabled()) rbNaranjaJugador2.setEnabled(true);
        if(!rbVerdeJugador2.isEnabled()) rbVerdeJugador2.setEnabled(true);
        if(!rbAzulJugador2.isEnabled()) rbAzulJugador2.setEnabled(true);
    }

    /**
     * Este método vuelve a la Activity del menú principal (MenuPrincipal_Activity)
     * @param v Objeto de la clase View que hace referencia a la vista donde se produce la llamada al método
     */
    public void volver(View v){
        finish();
        Intent intent = new Intent(this, MenuPrincipal_Activity.class);
        startActivity(intent);
    }
}
