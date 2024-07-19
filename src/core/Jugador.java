/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano (añadir la carta de foma que queden 
 * ordenadas de menor a mayor por su número), convertir a String el objeto Jugador (toString)
 */
package src.core;

import java.util.LinkedList;
import java.util.Stack;

public class Jugador {

    // Creamos una variable de tipo cadena para el nombre del jugador
    private String nombreJugador;

    /*
    Creamos una lista enlazada de cartas para la  mano.
    "mano" es un TAD Lista de tipo Carta
    (se usan las implementaciones de Java en lugar de las de AEDI -> LinkedList);
     */
    private LinkedList<Carta> mano;

    /*
    Pila para almacenar las cartas que el jugador ha recogido de la mesa.
     */
    private Stack<Carta> montonBueyes;

    /*
    Variable para almacenar el total de bueyes
     */
    private int totalBueyes;

    /*
    Constructor: se crea el jugador asignandole un nombre (pasado como parametro)
    y creando su mano como una lista vacía
     */
    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        mano = new LinkedList<>();
        montonBueyes = new Stack<>();
        this.totalBueyes = totalBueyes;
    }

    /*
    Devuelve el nombre del jugador
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    public LinkedList<Carta> getMano() {
        return mano;
    }

    public Stack<Carta> getMontonBueyes() {
        return montonBueyes;
    }

    public int getTotalBueyes() {
        return totalBueyes;
    }

    /*
    Se recorre la mano mientras no se llegue al final de la lista y mientras el número de la carta a introducir sea inferior al número de la carta,
    en la posición apuntada por el iterador. Tras esto se comprueba si se ha llegado al final de la lista. En ese caso,
    se asume que la carta a introducir tiene un valor superior a todas las cartas en la lista, por lo que se introduce al final.
    En caso contrario, se introduce en la posición del iterador, desplazando todas las cartas posteriores a la derecha.
    
    Aclaraciones sobre LinkedList:
    .size() devuelve el numero de posiciones de la lista
    .get(i) devuelve el elemento de la posición "i" de la lista
    .add(carta) añade el elemento "carta" al final de la lista
    .add(i,carta) añade el elemento "carta" en la posición "i" y desplaza todos los elementos. No sobreescribe la posición "i"
     */
    public void addCarta(Carta carta) {
        int i = 0;
        while (i != mano.size() && carta.getNumCarta() < mano.get(i).getNumCarta()) {
            i++;
        }
        if (i == mano.size()) {
            mano.add(carta);
        } else {
            mano.add(i, carta);
        }
    }

    /*
    Devuelve el número total de bueyes del monton de bueyes.
     */
    public void vaciarMontonBueyes() {
        for (Carta bueyes : montonBueyes) {
            totalBueyes = totalBueyes + bueyes.getNumBueyes();
        }
    }

    /*
    Se crea un bucle para mostrar todas las cartas de la mano
        Nota: Dado que la lista de cartas empieza en 0 se incrementa en 1 el índice a mostrar.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNombre: ").append(nombreJugador).append("\n");

        // Dividir las cartas en dos filas
        int mitad = (mano.size() + 1) / 2;

        // Primera fila de cartas
        sb.append("\n");
        for (int i = 0; i < mitad; i++) {
            sb.append((i + 1)).append(". ").append(mano.get(i)).append("\t");
        }

        // Segunda fila de cartas
        sb.append("\n\n");
        for (int i = mitad; i < mano.size(); i++) {
            sb.append((i + 1)).append(". ").append(mano.get(i)).append("\t");
        }

        sb.append("\n");

        return sb.toString();
    }

}
