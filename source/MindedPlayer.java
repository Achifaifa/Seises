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
    
    public ArrayList possibleCards(){return null;}
    
    public boolean lessStep(Card card,Card card2){return false;}
}
