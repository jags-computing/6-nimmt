/**
 * Representa la interfaz del juego Toma 6, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package src.iu;

import src.core.Carta;
import src.core.Jugador;
import src.core.Mesa;
import src.core.Par;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class IU {

    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    public static final String PURPLE = "\033[0;35m";

    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in);
    }

    /**
     * Lee un número de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El numero como entero
     */
    public int leeNum(String msg) {
        boolean repite;
        int toret = 0;

        do {
            repite = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee un texto de teclado
     *
     * @param msg El mensaje a utilizar
     * @return El texto como String
     */
    public String leeString(String msg) {
        String toret;
        System.out.print(msg);
        toret = teclado.nextLine();
        return toret;
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    
    
    /*
    Se crea una colección de nombres de jugadores, que será lo que devuelva el método.
    También se crea una variable auxiliar para almacenar el número de jugadores.
    
    Se recorre con un for el número de jugadores, en cada iteración se solicita un nombre.
    Si algún nombre se repite se le asigna el nombre en cuestión seguido de un número.
    
    Por último se añade el nombre a la colección y se devuelve esta última.
     */
    
    public Collection<String> pedirDatosJugadores() {
        Collection<String> nombresJugadores = new ArrayList<>();
        int numJugadores = -1;
        
        while (numJugadores < 2 || numJugadores > 10) {
            numJugadores = leeNum("Introduce el número de jugadores (2 / 10): \n");
        }

        for (int i = 1; i <= numJugadores; i++) {
            String nombre = leeString("Introduce el nombre del jugador " + i + ": \n");
            if (nombresJugadores.contains(nombre)) {
                int count = 1;
                while (nombresJugadores.contains(nombre + "_" + count)) {
                    count++;
                }
                nombre += "_" + count;
            }
            nombresJugadores.add(nombre);
        }
        return nombresJugadores;
    }

    /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param jugador Jugador para el cual se mostrarán los datos por pantalla
     */
    private void mostrarJugador(Jugador jugador) {
        mostrarMensaje(jugador.toString());
    }

    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param jugadores Jugadores cuyos datos se mostrarán por pantalla
     */
    public void mostrarJugadores(LinkedList<Par<Jugador, Carta>> jugadores) {
        for (Par<Jugador, Carta> j : jugadores) {
            mostrarJugador(j.getJugador());
        }
    }

    /*Muestra el estado de la mesa y sus montones*/
    public void mostrarMesa(Mesa mesa) {
        mostrarMensaje(mesa.toString());
    }

    public void titulo() {
        mostrarMensaje("\n");
        mostrarMensaje(" /$$$$$$$$                                       /$$$$$$ \n"
                + "|__  $$__/                                      /$$__  $$\n"
                + "   | $$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$       | $$  \\__/\n"
                + "   | $$ /$$__  $$| $$_  $$_  $$ |____  $$      | $$$$$$$ \n"
                + "   | $$| $$  \\ $$| $$ \\ $$ \\ $$  /$$$$$$$      | $$__  $$\n"
                + "   | $$| $$  | $$| $$ | $$ | $$ /$$__  $$      | $$  \\ $$\n"
                + "   | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$      |  $$$$$$/\n"
                + "   |__/ \\______/ |__/ |__/ |__/ \\_______/       \\______/ \n"
                + "                                                         \n"
                + "                                                         \n"
                + "                                                         ");
        mostrarMensaje("                      Creado por: Adrián Carballo, Marta Barros, José Antonio González y Xoán Rodríguez.");
        mostrarMensaje("\n");
    }

}
