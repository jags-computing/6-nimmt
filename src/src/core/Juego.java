/**
 * Representa el juego Toma 6, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package src.core;

import src.iu.IU;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Juego {

    private final IU iu;
    // Atributo de tipo Baraja para definir la baraja del juego
    private Baraja baraja;
    // Creamos generico de jugadores+cartas para nuestro juego
    LinkedList<Par<Jugador, Carta>> jugadores;
    // Lista con los nombres de los ganadores.
    LinkedList<String> ganadores;
    // Atributo de tipo mesa que representa la mesa del juego
    private Mesa mesa;

    // Constructor de la clase juego
    public Juego(IU iu) {
        this.iu = iu;
        jugadores = new LinkedList<>();
        ganadores = new LinkedList<>();
        baraja = new Baraja();
        mesa = new Mesa();
    }

    public void jugar() {
        iu.titulo();
        crearJugadoresPartida();
        baraja.barajar();
        repartirCartas();
        mesa.inicializarMesa(baraja);
        iu.mostrarMesa(mesa);
        
        boolean primeraRonda = true;
        
        while (!finPartida()) {
            if (primeraRonda) {
                primeraRonda = false;
            } else {
                devolverCartasBaraja();
                baraja.barajar();
                repartirCartas();
            }
            for (int i = 0; i < 10; i++) {
                iu.mostrarJugadores(jugadores);
                seleccionarCartas();
                turno();
            }
            mostrarBueyesJugadores();
        }
        mostrarGanadores();
    }
    
    /*
    Se crea una colección de nombres de jugadores, los cuales se extraen del método pedirDatosJugadores() de la clase IU.
    Mediante un bucle for each, se recorre la colección de nombres creando, en cada iteración,
    un jugador con dicho nombre y una carta nula seleccionada. Por último se añade a la lista de jugadores.
     */
    private void crearJugadoresPartida() {
        Collection<String> nombresJugadores = iu.pedirDatosJugadores();

        for (String nombre : nombresJugadores) {
            Par<Jugador, Carta> jugador = new Par<>(new Jugador(nombre), new Carta(-1, -1));
            jugadores.add(jugador);
        }
    }

    /*
    Se reparten 10 cartas a cada jugador, en concreto, se usa un bucle for de 10 interaciones (una por carta),
    el cual, contiene un bucle for each que recorre la lista de jugadores quitando una carta de la baraja y
    añadiendola a la mano del jugador. Con esto se consigue repartir una carta a cada jugador en cada iteración del bucle for.
     */
    private void repartirCartas() {
        for (int i = 0; i < 10; i++) {
            for (Par<Jugador, Carta> j : jugadores) {
                j.getJugador().addCarta(baraja.sacarCarta());
            }
        }
    }

    /*
    Se crea una variable auxiliar (cartaSeleccionada) para favorecer la legibilidad del código.
    Mediante un bucle for each se recorre la lista de jugadores, se controla con un bucle while y un if que la carta elegida sea valida
    (mayor que 0 y menor al numero de cartas que tiene la mano).
    Se almacena en la variable cartaSeleccionada la carta elegida por el jugador.
        Nota: Dado que la lista de cartas empieza en 0 se decrementa en 1 la posición que se le pasa a devolverCarta,
        (al insertar un 5 accedemos a la 6ta posición por eso es necesario decrementar).
    Se sustituye la carta asignada al jugador por la carta seleccionada.
     */
    private void seleccionarCartas() {
        Carta cartaSeleccionada;

        for (Par<Jugador, Carta> j : jugadores) {
            boolean cartaValida = false;

            while (!cartaValida) {
                int posicionCarta = iu.leeNum("Jugador: " + j.getJugador().getNombreJugador() + "\n\tSelecciona que carta deseas jugar:  ");

                if (posicionCarta < 1 || posicionCarta > j.getJugador().getMano().size()) {
                    iu.mostrarMensaje("\nNo existe esa carta.");
                } else {
                    cartaSeleccionada = j.getJugador().getMano().remove(posicionCarta - 1);
                    j.setCarta(cartaSeleccionada);
                    cartaValida = true;
                }
            }
        }
        mostrarCartasSeleccionadas();
    }

    /*
    Muestra las cartas seleccionadas por cada jugador.
     */
    private void mostrarCartasSeleccionadas() {
        iu.mostrarMensaje("\nCartas seleccionadas: ");

        for (Par<Jugador, Carta> j : jugadores) {
            iu.mostrarMensaje("\n\t" + j.getJugador().getNombreJugador() + ": " + j.getCarta());
        }
    }

    /*
    En cada iteración devuelve las cartas del monton de bueyes a la baraja y
    actualiza la varibale de cada jugador encargada de almacenar el número de bueyes.
    Muestra el número de bueyes totales de cada jugador.
     */
    private void mostrarBueyesJugadores() {
        iu.mostrarMensaje("\nNúmero de bueyes: ");
        for (Par<Jugador, Carta> j : jugadores) {
            j.getJugador().vaciarMontonBueyes();
            iu.mostrarMensaje("\n\t" + j.getJugador().getNombreJugador() + ": " + j.getJugador().getTotalBueyes() + " bueyes");
        }
    }

    /*
    Se recorre la lista de jugadores comparando su número de bueyes con una variable auxiliar
    inicialmente inicializada al máximo valor que puede tomar un entero. Si el número de bueyes
    del jugador es menor a esta variable, se actualiza el valor de esta última.
    
    Se vuelve a recorrer la lista de jugadores, esta vez ya sabiendo el número mínimo de bueyes (numBueyesGanador).
    Se añaden los nombres de los jugadores cuyo número de bueyes sea igual al mínimo a la lista de ganadores.
    
    Se muestran los nombres de los ganadores junto a su número de bueyes.
     */
    private void mostrarGanadores() {
        int numBueyesGanador = Integer.MAX_VALUE;

        for (Par<Jugador, Carta> j : jugadores) {
            if (j.getJugador().getTotalBueyes() <= numBueyesGanador) {
                numBueyesGanador = j.getJugador().getTotalBueyes();
            }
        }

        for (Par<Jugador, Carta> jugador : jugadores) {
            if (jugador.getJugador().getTotalBueyes() == numBueyesGanador) {
                ganadores.add(jugador.getJugador().getNombreJugador());
            }
        }

        iu.mostrarMensaje("\nGanadores: ");
        for (String ganador : ganadores) {
            iu.mostrarMensaje("\n\t" + ganador);
        }
        iu.mostrarMensaje("\nNúmero de bueyes: " + numBueyesGanador);
        iu.mostrarMensaje("\nLa partido ha finalizado");
    }
    
    /*
    Recorre todos los montones de la mesa devolviendo las cartas a la baraja.
    (Se copian a la baraja y despues se vacia el monton)
    
    Tambioén vacia los montones de cartas de todos los jugadores
    siguiendo el mismo procedimiento.
    */
    
    private void devolverCartasBaraja(){
        
        for (int i = 0; i < mesa.getTAM_MESA(); i++) {
            baraja.insertarCartas(mesa.getMesa()[i]);
            mesa.getMesa()[i].clear();
        }
        
        for (Par<Jugador, Carta> jugadore : jugadores) {
            baraja.insertarCartas(jugadore.getJugador().getMontonBueyes());
            jugadore.getJugador().getMontonBueyes().clear();
        }
    }

    /*
    Primero se crea una lista auxiliar que almacenara una copia de la lista de jugadores,
    esta lista auxiliar se ordena de menor a mayor, según el número de las cartas seleccionadas:
    Se hace uso del método sort al cual se le pasa como parametros la lista y un comparador.
    
    El comparador se invoca mediante el método comparinInt, el cual crea una regla mediante una expresión lambda,
    la expresión utilizada indica que: Para cada objeto Par se accede a su número de carta a la hora de comparar.
    
    Se recorren todos los pares jugador-carta asignada, se crea una variable auxiliar
    para guardar la posición del monton mas adecuado para colocar la carta
    (esto se obtiene mediante el método esColocable(). Mediante ifs se comprueba que:
        -Si todos los montones tienen una última carta mayor que la carta a colocar (posicionMonton = -1):
            Se solicita al jugador que elija un montón, para recojer sus cartas y añadirlas a su montón.
            Se coloca la carta seleccionada en el móntón vacío.
        -Si el monton tiene 5 cartas (comprobado mediante el método montonLleno()):
            Se vacía el montón volcando las cartas en el móntón del jugador.
            Se coloca la carta seleccionada en el móntón vacío.
        -Si no se cumple lo anterior, se inserta en el montón (mediante el método colocarCarta().
    Por último se muestra el estado de la mesa.
     */
    private void turno() {
        
        LinkedList<Par<Jugador, Carta>> jugadoresAUX = new LinkedList<>(jugadores);
        Collections.sort(jugadoresAUX, Comparator.comparingInt(par -> par.getCarta().getNumCarta()));
        
        for (Par<Jugador, Carta> j : jugadoresAUX) {

            int posicionMonton = mesa.esColocable(j.getCarta());

            iu.mostrarMensaje("\nTurno de " + j.getJugador().getNombreJugador() + "(" + j.getCarta().toString() + ")");

            if (posicionMonton == -1) {
                boolean montonValido = false;

                while (!montonValido) {
                    iu.mostrarMesa(mesa);
                    posicionMonton = iu.leeNum("\nTodas las cartas son mayores, selecciona fila para colocar carta: ") - 1;

                    if (posicionMonton < 0 || posicionMonton > 3) {
                        iu.mostrarMensaje("\nNo existe esa fila.");
                    } else {
                        mesa.vaciarMonton(j.getJugador().getMontonBueyes(), posicionMonton);
                        montonValido = true;
                    }
                }
            } else {
                if (mesa.montonLleno(posicionMonton) == true) {

                    mesa.vaciarMonton(j.getJugador().getMontonBueyes(), posicionMonton);
                }
            }
            mesa.colocarCarta(j.getCarta(), posicionMonton);
            iu.mostrarMesa(mesa);
        }
    }
    
    /*
    Se recorre la lista de jugadores comprobando si alguno de ellos tiene un
    número de bueyes igual o superior a 66. Si se da el caso, devuelve true.
    */
    
    private boolean finPartida() {
        boolean toret = false;

        for (Par<Jugador, Carta> j : jugadores) {
            if (j.getJugador().getTotalBueyes() >= 66) {
                toret = true;
            }
        }
        return toret;
    }
}
