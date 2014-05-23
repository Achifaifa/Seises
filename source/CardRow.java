import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;
import java.util.LinkedList;


/**
 * Write a description of class CardRow here.
 * 
 * @author Felipe I. Anfurrutia
 * @version 2014/3
 */
public class CardRow  extends Actor implements Accepter
{
    private List<Card> cards;
    private Card.Suit suit;
    
    public CardRow(){
        cards = new LinkedList<Card>();
    }
    
    /**
     * Adds the cardRow without cards
     */
    public void addedToWorld(World world){
        setImage("images/cards/empty.png");
        int expand = 440;
        int middle = (expand-getImage().getWidth()) / 2;
        GreenfootImage image = new GreenfootImage(getImage().getWidth()+expand, getImage().getHeight());
        image.drawImage(new GreenfootImage("images/cards/empty.png"), middle, 0);
        image.drawRect( 0, 0, expand, image.getHeight()-1);
        setImage(image);
    }

    /**
     * Determina si la fila esta vacia
     * @return True si la fila esta vacia y False, en caso contrario
     */
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    
    /**
     * Determina si la fila esta complenta
     * @return True si la fila esta completa y False, en caso contrario
     */
    public boolean isCompleted(){
        return cards.size() == Card.Value.values().length;
    }

    /**
     * Determina si aceptar la carta es legal, es decir, cumple las reglas del juego
     * @param la carta
     * @return true si el movimiento es legal, y flase, en caso contrario
     */
    public boolean canAddCard(Card card){
        if (card.getAccepter() == null)
            return false;
            
        if ( !((TableGame)getWorld()).areAllCardsDealed() )
            return false;
        
        if ((card.getAccepter() instanceof Player) && !( ((Player) card.getAccepter()).canSelect(card) && ((TableGame)getWorld()).isMyturn((Player) card.getAccepter())))    
            return false;
          
        if (((TableGame)getWorld()).areAllRowsEmpty() && !( (card.isSixHearts())))
            return false;
        
        if (isEmpty() && (card.getValue() == Card.Value.SIX)){
            return true;
        }
        
        if (card.getSuit() != suit)
            return false;
        
        if (card.getValue().ordinal() == cards.get(0).getValue().ordinal()-1){
            return true;
        }
        
        if (card.getValue().ordinal() == cards.get(cards.size()-1).getValue().ordinal()+1){
            return true;
        }
        return false;        
    }

   /**
     * Acepta la carta y lo almacena en su estructura de datos interna
     * @param la carta
     */ 
    public void addCard(Card card){
        if (canAddCard(card)){
            if (cards.isEmpty() && (card.getValue() == Card.Value.SIX))
                suit = card.getSuit();
            int pos = getCardPosition(card);
            add(card, pos);
        }
    }
        
    private int getCardPosition(Card card){
        int pos = 0;
        if (!isEmpty() && card.getValue().ordinal() == cards.get(cards.size()-1).getValue().ordinal()+1)
            pos = cards.size();
        
        return pos;
    }
    
    /**
    * Devuelve la lista de cartas
    * @return lista de cartas
    */

    public List<Card> getCards(){
        return cards;
    }
    /**
     * Acepta la carta y lo almacena en su estructura de datos interna
     * @param la carta
     */ 
   private void add(Card card, int pos){
   if (card.getAccepter() != null){
   ((Player) (card.getAccepter())).remove(card);
   card.setAccepter(this);}
   card.setAccepter(this);
   if (pos != 0)
   cards.add(card);
   else
   cards.add(0, card);
   card.setDraggable(false);
   card.setRotation(0);
   placeCard(card);}
    
    /**
     * Coloca la carta en la fila de la mesa
     */
    private void placeCard(Card card){
        int x = getX(); 
        int y = getY();
        int dif;
        if (card.getValue() == Card.Value.SIX)
            x = x - 35;
        else if (card.getValue().ordinal() >= Card.Value.SEVEN.ordinal()){
            dif = card.getValue().ordinal() - Card.Value.SEVEN.ordinal();
            x = x + 35 + dif*15;
        }
        else {
            dif = Card.Value.SEVEN.ordinal() - card.getValue().ordinal();
            x = x - 75 - dif*15;
        }
        card.setLocation(x, y);
        //card.reAdd();
    }
    
   
}
