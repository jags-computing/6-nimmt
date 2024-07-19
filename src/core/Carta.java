
/*
 * Representa una carta, formada por un número y la cantidad de bueyes correspondiente
 */
package src.core;

public class Carta {

    // Atributo que indica el numero de bueyes
    private int numBueyes;
    // Atributo que indica el numero (unico) de la carta
    private int numCarta;

    // Constructor de la carta que recibe como parametros el numero de bueyes y el numero de la carta
    public Carta(int numBueyes, int numCarta) {
        this.numBueyes = numBueyes;
        this.numCarta = numCarta;
    }

    // Metodo que devuelve un entero con el numero de la carta (unico)
    public int getNumCarta() {
        return numCarta;
    }

    // Metodo que devuelve un entero con el numero de bueyes de una carta
    public int getNumBueyes() {
        return numBueyes;
    }

    // Metodo toString de Carta
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ").append(numCarta).append(" | ").append(numBueyes).append(" ]");
        return sb.toString();
    }
}
