/**
 * Write a description of class AutoPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AutoPlayer extends Player{   
   public AutoPlayer(String name, String imageFilename, Position position){super(name, imageFilename, position);}
    
   public void act(){
    //Comprobar que todas las cartas estan repartidas
    //Intentar poner una carta
    //Pasar turno
    TableGame game = (TableGame)getWorld();
    if (game.areAllCardsDealed()){
    super.addCard(selectCard());
    Turn turn=(Turn)getWorld();
    turn.next();}}
 
   public Card selectCard(){return null;}
   
   public Card getSixHearts(){
    for (int i=0; i<super.cards.size(); i++){
     if (super.cards.get(i).suit==Card.Suit.HEARTS &&  super.cards.get(i).value==Card.Value.SIX){
        return cards.get(i);}}
    return null;}}
