
package src.iu;

import src.core.Juego;

public class Main {
    public static void main(String[] args) {              
        IU iu = new IU();
        Juego toma6 = new Juego(iu);
        toma6.jugar();
    }   
}