/**
 * Write a description of class MindedPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MindedPlayer extends AutoPlayer
{
    
    public MindedPlayer(String name, String imageFilename, Position position){
    super(name, imageFilename, position);}
    
    public ArrayList<Card> possibleCards(){return null;}
    
    public ArrayList<Card> getPossibleCards(){return possibleCards();}
    
    public boolean lessSteps(Card card1,Card card2){return true;}
  
}
