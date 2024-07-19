
/*
* Representa la baraja del juego Toma 6, en total 104 cartas, enumeradas del 1 al 104 con el número de bueyes
* correspondiente en función del valor de la misma (revisar condiciones en el enunciado del juego). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package src.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Stack;

public class Baraja {

    // Constante con el numero de carta de la baraja
    private final int NUM_CARTAS = 104;

    // Creamos una pila, que será la estructura de nuestra baraja
    private Stack<Carta> baraja;

    /*
    Se crea la baraja como una pila vacía y se procede a agregar las cartas.
    Mediante un bucle for se recorren todos los números de la baraja. Dentro del for
    se controla, mediante Ifs anidados, el número de bueyes que se le asignan a cada carta. 
     */
 
    public Baraja() {

        this.baraja = new Stack<>();

        for (int i = 1; i <= NUM_CARTAS; i++) {
            if (i == 55) { // Numero de carta es 55 -> bueyes = 7
                baraja.push(new Carta(7, i));
            } else if (i % 5 == 0 && i % 10 != 0) {
                baraja.push(new Carta(2, i)); // Numero de carta terminado en 5 -> bueyes = 2
            } else if (i % 10 == 0) {// Numero de carta es multiplo de 10 -> bueyes = 3
                baraja.push(new Carta(3, i));
            } else if (i % 11 == 0) {// Numero de carta es multiplo de 11 -> bueyes = 5
                baraja.push(new Carta(5, i));
            } else { // Si llegamos a aqui es porque no cumple ninguna de las condiciones anteriores -> bueyes = 1
                baraja.push(new Carta(1, i));
            }

        }
    }

    /*
    El método "barajar" hace uso de una lista ArrayList auxiliar (aux), en la cual, se vuelcan todos los elementos de "baraja".
    
    Tras esto, se desordenan todos los elementos mediante un bucle for que en cada iteración crea un índice randomizado (randIndice).
    La carta que esté en ese índice en la baraja auxiliar se guarda en una variable Carta temporal (temp),
    a continuación se realiza un intercambio de cartas en aux:
    En la posición definida por el índice randomizado se incrusta el elemento de la posición actual del bucle for.
    En la posición actual del bucle for se incrusta la carta guardada en temp.
    
    Por último se vuelven a introducir los elementos en "baraja" mediante un blucle for.
     */
    public void barajar() {

        ArrayList<Carta> aux = new ArrayList<>();

        // Mientras que la baraja no este vacia, sacamos la carta de la cima (metodo pop) y la añadimos a la baraja auxiliar
        while (!baraja.empty()) {
            aux.add(baraja.pop());
        }

        Random rnd = new Random();

        for (int i = aux.size() - 1; i > 0; i--) {
            int randIndice = rnd.nextInt(i + 1);
            // Creamos una carta temporal, será una sacada aleatoriamente de la baraja auxiliar.
            Carta temp = aux.get(randIndice);
            // Intercambio de cartas 
            aux.set(randIndice, aux.get(i));
            aux.set(i, temp);
        }

        for (int i = 0; i < NUM_CARTAS; i++) {
            baraja.push(aux.get(i));
        }

    }

    /*
    Devuelve y elimina la carta que esta en la cima de la baraja
     */
    public Carta sacarCarta() {
        return baraja.pop();
    }
    
    /*
    Inserta una colección de cartas en la baraja
    */
    
    public void insertarCartas(Collection<Carta> cartas) {
        baraja.addAll(cartas);
    }
}
