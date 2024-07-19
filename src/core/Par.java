/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.core;

/*
 Clase auxiliar para almacenar un par de jugador y carta seleccionada por el jugador.
 */

public class Par<K, V> {
    private K jugador;
    private V carta;

    public Par(K jugador, V carta) {
        this.jugador = jugador;
        this.carta = carta;
    }

    public K getJugador() {
        return jugador;
    }

    public V getCarta() {
        return carta;
    }

    public void setCarta(V carta) {
        this.carta = carta;
    }
}
