/**
 * Actors implement this interface if they can have cards
 * placed on them.
 * 
 * @author Felipe I. Anfurrutia
 * @version 2014/3
 */
public interface Accepter  
{
    /**
     * Determina si aceptar la carta es legal, es decir, cumple las reglas del juego
     * @param la carta
     * @return true si el movimiento es legal, y flase, en caso contrario
     */
    public boolean canAddCard(Card card);
    
    /**
     * Acepta la carta y lo almacena en su estructura de datos interna
     * @param la carta
     */
    public void addCard(Card card);
}
