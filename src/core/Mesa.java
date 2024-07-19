/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.core;

import java.util.LinkedList;
import java.util.Stack;

public class Mesa {

    private final int TAM_MESA = 4;
    private LinkedList<Carta>[] mesa;
    
    /*
    Se inicializa el array de listas (mesa) a 4 (TAM_MESA).
    Acto seguido, se recorre el array creando una lista vacía en cada posición.
    */

    @SuppressWarnings("unchecked")
    public Mesa() {
        mesa = new LinkedList[TAM_MESA];
        for (int i = 0; i < TAM_MESA; i++) {
            mesa[i] = new LinkedList<>();
        }
    }
    
    public int getTAM_MESA() {
        return TAM_MESA;
    }

    public LinkedList<Carta>[] getMesa() {
        return mesa;
    }
    
    //Añadir 4 cartas a la mesa inicial.
    public void inicializarMesa(Baraja baraja) {
        for (int i = 0; i < TAM_MESA; i++) {
            mesa[i].add(baraja.sacarCarta());
        }
    }
    
    //Devuelve true si el montón tiene 5 cartas
    
    public boolean montonLleno (int monton){
        boolean toret = false;
        if (mesa[monton].size() == 5) {
                toret = true;
        }
        return toret;
    }
    
    /*
    Se le pasa por parámetros una pila de cartas (el monton de bueyes de un jugador)
    y un entero que indica la posición de un montón en la mesa.
    
    Mueve las cartas del montón indicado a la pila de cartas.
    */
    
    public void vaciarMonton (Stack<Carta> montonBueyes, int monton){
        
        while (!mesa[monton].isEmpty()) {
            montonBueyes.add(mesa[monton].remove());
        }
    }
    
    /*
    Comprueba cual es el monton donde se debe colocar una carta:
    Se crean 4 variables auxiliares:
    -Un entero para almacenar la diferencia mínima entre la carta a posicionar y todos los montones (inicializada a Integer.MAX_VALUE)-->(diferenciaMinima).
    -Un entero para almacenar la diferencia entre la carta a posicionar y el montón de dicha iteración (inicializada a Integer.MAX_VALUE)-->(diferencia).
    -Un entero que almacenará la posición del monton en el array de la mesa (-1 si ningún monton es válido).
    -Una lista que almacenará la lista a estudiar en cada iteración.
    
    Se recorre el array de la mesa y, en cada iteración, se comprueba que la lista no esté vacía y que la última carta de la pila,
    sea menor que la carta a colocar, de cumplirse la condición se almacena la diferencia de estas en una variable auxiliar.
    Acto seguido se compara esta diferencia con el contenido de la variable auxiliar que guarda la diferencia mínima.
    Si esta última resulta mayor, se actualizan las variables auxiliares y se marca la posición del monton como candidato a devolver.
    
    En caso de que la lista estea vacía se devuelva esta directamente.
     */
    
    public int esColocable(Carta carta){
        int diferenciaMinima = Integer.MAX_VALUE;
        int diferencia = Integer.MAX_VALUE;
        int montonSeleccionado = -1;
        LinkedList<Carta> monton = new LinkedList<>();

        for (int i = 0; i < TAM_MESA; i++) {
            monton = mesa[i];

            if (!monton.isEmpty() && monton.getLast().getNumCarta() < carta.getNumCarta()) {
                diferencia = carta.getNumCarta() - monton.getLast().getNumCarta();

                if (diferencia < diferenciaMinima) {
                    diferenciaMinima = diferencia;
                    montonSeleccionado = i;
                }
            } else if (monton.isEmpty()){
                return i;
            }
        }
        return montonSeleccionado;
    }
    
    public void colocarCarta(Carta carta, int monton){
        mesa[monton].add(carta);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nMesa:");
        for (int i = 0; i < TAM_MESA; i++) {
            sb.append("\n\tMontón ").append((i + 1)).append(":");
            for (int j = 0; j < mesa[i].size(); j++) {
                sb.append("\n\t\t").append(mesa[i].get(j));
                
            }
        }
        return sb.toString();
    }
}
