import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.*;
/**
 * Write a description of class Player here.
 * 
 * @author Felipe I. Anfurrutia
 * @version 2014/3
 */
public class Player  extends Actor implements Accepter
{
    /** The player's name */
    private String name;
    /** The player's image */
    private GreenfootImage playerImage;
    /** The orientation of the player */
    private Position position;
    /** The cards in the hands */
    protected ArrayList<Card> cards;
    /** The counter to know how many times the player couldn't put a card on the table */
    private int failures;
    
    /**
     * Create a new player 
     */
    public Player(String name, String imageFilename, Position position){
        this.name = name;
        playerImage = new GreenfootImage(imageFilename);
        this.position = position;
        cards = new ArrayList<Card>();
        failures = 0;
    }
    /**Returns the player name
     @return this.name*/
    public String getName(){return name;}
    /**
     * Adds the player without cards
     */
    public void addedToWorld(World world){
        setImage("images/cards/empty.png");

        int expand = 200;
        int width = playerImage.getWidth() + getImage().getWidth() + expand;
        int height = getImage().getHeight();
        GreenfootImage image = new GreenfootImage(width, height);
        setImage(image);
        setRotation(position.getRotation());
        showPlayerInfo();
    }
    
    /**
    * Devuelve el numero de 'pasos' realizados por el jugador
    * @return los pasos
    */
    public int getFailures(){return failures;}
    /**
     * Devuelve las cartas de la mano
     * @return las cartas de la mano
     */
    public ArrayList<Card> getCards(){return cards;}
    
    /**
     * Visualiza el texto del jugador
     */
    private void showPlayerInfo(){
        GreenfootImage image = getImage();
        image.clear();
        image.drawImage(playerImage, 0, 0);
        image.drawImage(new GreenfootImage("images/cards/empty.png"), playerImage.getWidth(), 0);
        image.drawString(name, 0, playerImage.getHeight()+10);
        image.drawString("f:"+failures, 10, playerImage.getHeight()+30);
        image.drawRect( 0, 0, image.getWidth()-1, image.getHeight()-1);
    }
    
    /**
     * Determine whether the player has cards in hand or not
     * @return True if the player has cards on hand and False otherwise
     */
    public boolean hasCards(){
        //TO-DO
        //if len(cards)<1:return 0
        //else:return 1
        
        if (this.cards.size()<1){return false;}
        else{return true;}}
    
    /**
     * Determina si aceptar la carta es legal, es decir, cumple las reglas del juego
     * @param la carta
     * @return true si el movimiento es legal, y flase, en caso contrario
     */
    public boolean canAddCard(Card card)
    {
       //si el turno es igual al nombre
       //si viene de la baraja (accepter == null), es decir, no se admite de ningun otro accepter
       if ((card.getAccepter() == null)){// && ((TableGame)getWorld()).isMyturn(this)) {
            return true;}
        return false;}
    
    /**
     * Acepta la carta y lo almacena en su estructura de datos interna
     * @param la carta
     */
    public void addCard(Card card){
        if (canAddCard(card)){
            card.setAccepter(this);
            cards.add(card);
            card.setDraggable(true);
            placeCard(card);}}

    /**
     * Coloca la ultima carta en la mesa adecuando la posicion y rotacion de la carta
     */
    private void placeCard(Card card){
        int x = getX4card(cards.size());
        int y = getY4card(cards.size());
        card.setLocation(x, y);      
        card.setRotation(position.getRotation());}
    
    /**
     * Calcula y devuelve la coordenada x para las cartas del jugador
     * @return la coordenada x
     */
    private int getX4card(int i){
        int x = getX();
        int rotation = position.getRotation();
        if (rotation == 0 )       
            x = x - (getImage().getWidth() / 2) + playerImage.getWidth() + getOffset(i); //20 + 15*i;
        else if (rotation == 180)
            x = x + (getImage().getWidth() / 2) - playerImage.getWidth() - getOffset(i); // 20 - 15*i;
        return x;}
    
    /**
     * Calcula y devuelve la coordenada y para las cartas del jugador
     * @return la coordenada y
     */
    private int getY4card(int i){
        int y = getY();
        int rotation = position.getRotation();
        if (rotation == 90)
            y = y - (getImage().getWidth()/2) + playerImage.getWidth() + getOffset(i); //20 + 15*i;
        else if (rotation == 270)
            y = y + (getImage().getWidth()/2) - playerImage.getWidth() - getOffset(i); // 20 - 15*i;
        return y;}
    
    /**
     * Calcula la diferencia de una carta a otra
     */
    private int getOffset(int i){
        int offset = 20 + 15 * i;
        return offset;}
   
    /**
     * Calcula y devuelve el numero de cartas que tiene el jugador en la mano
     * @return el n?mero de cartas que tiene el jugador en la mano
     */
    public int numberOfCards(){return cards.size();}
    
    /**
     * Elimina la carta 'card'
     */
    public void remove(Card card){
        //TO-DO
        this.cards.remove(card);        
        placeAllCards();}

    /**
     * Determina si puede seleccionar la carta 'card' para ponerla encima de la mesa
     * @return True si puede selecionarla y False, en caso contrario
     */
    public boolean canSelect(Card card){
        TableGame game = (TableGame)getWorld();
        if (game.areAllCardsDealed() && game.isMyturn(this)){return cards.contains(card);}
        else{return false;}}
    
    /**
     * Analiza y devuelve la fila en la que se puede colocar la carta
     * @param la carta para colocar
     * @return la fila en la que se puede poner la carta
     */
    public CardRow whereCanPlace(Card card){
       //TO-DO
       //for i in TableGame.rows:
       //   if i.canaddcard(card): return i
       TableGame TableGame = (TableGame) getWorld();
       for (CardRow row:TableGame.getRows())
            {if (row.canAddCard(card)){return row;}}return null;}
    
    /**
     * Coloca automaticamente la carta en la fila correspondiente
     */
    public boolean placeOnArow(Card card){
       boolean placed = false;
       
       //TO-DO
       //
       whereCanPlace(card).addCard(card);
        
       if(placed) {
           Greenfoot.playSound("sounds/card.wav");
           card.reAdd();
       }
       else{
           //TO-DO
           //
       }
       return placed;
    }    
    
    
    /**
     * Calcula las cartas que estan encima de la carta 'card'
     * @return una lista con las cartas que estan encima de la carta 'card'
     */
    public List<Card> getCardsOn(Card card){
        if (!cards.contains(card)) return null;
        //si se visualizan como una pila, entonces las que se han anadido al final
        List<Card> cardsOn = new ArrayList<Card>();
        boolean encontrado = false;
        Iterator<Card> it = cards.iterator();
        while (it.hasNext() && !encontrado) {
            Card c = it.next();
            if (c.equals(card))
                encontrado = true;
        }
        while (it.hasNext())
            cardsOn.add(it.next());
        return cardsOn;}
    
    /**
     * Recoloca todas las cartas que tiene el jugador en la mano
     */
    public void placeAllCards(){
        int i = 1;
        for(Card card: cards){
            card.setLocation(getX4card(i), getY4card(i));
            i++;}}

    /**
     * Realiza paso y mueve la carta a otro sitio para el siguiente turno
     */
    public void incrementFailures(){
        failures++;
        showPlayerInfo();}
    
    
    /**
     * Coloca la primera carta al final de todas y la muestra completamente
     */
    private void moveFirst2Last(){
        Card card = cards.get(0);
        cards.remove(card);
        cards.add(card);
        placeAllCards();
        card.reAdd();}
    
}
