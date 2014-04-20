import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;

/**
 * Describe el marcador de la mesa
 * 
 * @autor Felipe I. Anfurrutia
 * @version 2014/3 
 */
public class Board extends Actor
{
    private Font font;
    private int numOfTurns;
    private int turn;
    private String theWinner;
    
    /**
     * Constructor del marcador
     */
    public void Board(){
        numOfTurns = 0;
        turn = 0;
        theWinner = "";
        setText();
    }
    
    /**
      * Create some new text
      */
    public void addedToWorld(World world)
    {
        setImage(new GreenfootImage(150, 100));
        font = new Font("Comic Sans MS", Font.ITALIC, 24);
        setText();
    }
    
    /**
      * Changes the text on the display
      */
    public void setText()
    {
         String s1 = "num: " + numOfTurns;
         String s2 = "turno: " + turn;
         String s3 = "ganador: ";
         if (theWinner != null) s3 = s3 + theWinner;
         getImage().clear();
         getImage().setColor(java.awt.Color.RED);
         getImage().setFont(font);
         getImage().drawString(s1, 0, 25);
         getImage().drawString(s2, 0, 50);
         getImage().drawString(s3, 0, 75);
    }
    
    /**
     * Actualiza el marcador indicando el numero de turnos y el turno del jugador
     * @param el n?mero de turnos
     * @param el turno
     */
    public void update(int numOfTurns, int turn){
        this.numOfTurns = numOfTurns;
        this.turn = turn;
        setText();
    }
    
    /** 
     * Muestra el mensaje the fon de juego y el nombre del jugador
     * @param el nombre del jugador
     */
    public void showTheWinner(String theWinner){
        this.theWinner = theWinner;
        setText();
        BigText text = new BigText();
        getWorld().addObject(text, 200, 300);
        text.setText("Game complete! The winner is " + theWinner);
    }
        
       
}
