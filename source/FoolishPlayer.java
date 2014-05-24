/**
 * Write a description of class FoolishPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FoolishPlayer extends AutoPlayer
{
    public FoolishPlayer(String name, String imageFilename, Position position){
     super(name, imageFilename, position);}
     
    public Card selectCard(){
    TableGame game=(TableGame)getWorld();
    for (int i=0;i<super.cards.size();i++){
    if (super.canSelect(super.cards.get(i))){return super.cards.get(i);}}
    return null;}
}
