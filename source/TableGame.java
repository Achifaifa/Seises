import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A table to play with cards.
 * 
 * @author Felipe I. Anfurrutia
 * @version 2014/3
 */
public class TableGame extends World
{
    /** Determines the max. number of players */
    private static int NUM_PLAYERS = 4;
    /** Determines the number of rows */
    private static int NUM_ROWS = 4;
    /** The players of the game */
    private Player[] players;
    /** The rows to be completed in the game */
    private CardRow[] rows;
    /** The deck of the game */
    private Deck deck;
    /** The card show to deal manually */
    private Card showCard;
    /** The controller of the player's turn */
    private TableTurn<Player> turn;
    /** The board */
    private Board board;
    /** Controls if the all cards are dealed */
    private boolean allCardsDealed;
    /** The actual player **/
    private Player currentPlayer;
    /** The winnwe player */
    private Player theWinner;

    /**
     * Create a new table game for 'seises'
     */
    public TableGame()
    {    
        super(750, 750, 1);
        Greenfoot.setSpeed(50);
       
        addBoard();
        addDeck();           
        addPlayers();
        addRows();
        setTurn();
        allCardsDealed = false;
 
       // Greenfoot.start();        
    }
    
     /**
     * Crea y anade un marcador a la mesa
     */   
    private void addBoard(){
        board = new Board();
        addObject(board, 650, 50);   
    }
    
    /**
     * Crea y anade una baraja a la mesa
     */
    private void addDeck(){
        deck = new Deck(Card.Colour.BLUE, false, 1, 1, 1, 1);
        deck.fill();
        deck.shuffle();
        addObject(deck, 40, 50);
    }
    
    /**
     * Crea y anade 4 jugadores a la mesa
     */
    private void addPlayers(){
        //TO-DO: Completa el codigo segun el 2.Ejercicio

        addObject(players[0], 50, 350);  //jugador WEST
        addObject(players[1], 375, 650); //jugador SOUTH
        addObject(players[2], 675, 350); // jugador EAST
        addObject(players[3], 375, 50);  //jugador NORTH
    }
    
    /**
     * Crea y anade 4 filas a la mesa
     */
    private void addRows(){
        rows = new CardRow[NUM_ROWS];        
        for(int i=0 ; i<NUM_ROWS ; i++) {
            CardRow row = new CardRow();
            rows[i]= row;
            addObject(row, 400, 200+i*100);
        }
    }
    
    /**
     * Devuelve el marcador
     * @return el marcador
     */
    public Board getBoard(){
        return board;
    }
    
    /**
     * Devuelve la baraja
     * @return la baraja
     */
    public Deck getDeck(){
        return deck;
    }
    
   /**
     * Devuelve las filas
     * @return las filas
     */
    public CardRow[] getRows(){
        return rows;
    }
    
    /**
     * Devuelve los jugadores
     * @return los jugadores
     */    
    public Player[] getPlayers(){
        return players;
    }
    
    /** 
     * Devuelve el turno
     * @return el turno
     */
    public TableTurn<Player> getTurn(){
        return turn;
    }
    
    /**
     * If the deck is clicked on, show a card o deal all cards
     */
    public void act()
    {
        if (isFinished() ) {
            showTheWinner();
            Greenfoot.stop();
        }

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(! (mouse!=null && (mouse.getActor()==deck))) return; //If the mouse isn't used on the deck, don't do anything
        
        if (Greenfoot.mouseClicked(deck)) {
            if(showCard != null && showCard.getAccepter()==null) {
                removeObject(showCard);
                deck.addCard(showCard);
            }
            if (mouse.getButton() == 1 && deck.getSize()>0) { //If the clicked button is the left
                showACard();
                if (deck.getSize()==0)
                    allCardsDealed = true;
            }
            else if (mouse.getButton() == 3){ //If the clicked button is the right
                dealAllCards();
            }
        }
    }
    
    /**
     * Muestra la carta que esta encima de la baraja y la coloca encima de la mesa boca arriba
     */
    private void showACard(){
        showCard = deck.drawCard();
        Greenfoot.playSound("sounds/card.wav");
        addObject(showCard, 120, 50);
        showCard.setDraggable(true);
    }
    
    /**
     * Reparte todas las cartas entre los jugadores, respetando el turno de cada uno de ellos
     */
    public void dealAllCards(){
       while (deck.getSize() > 0){
           showACard();
           currentPlayer = next();
           currentPlayer.addCard(showCard);
       }
        allCardsDealed = true;
        setTurn();
    }
    
    /**
     * Determina si todas las cartas estan repartidas
     * @return True si todas las cartas estan repartidas y False, en caso contrario
     */
    public boolean areAllCardsDealed(){
        return allCardsDealed;
    }    
    
    /**
     * Determina si todas las filas estan vacias
     * @return True si todas las filas estan vacias, y false, en caso contrario
     */
    public boolean areAllRowsEmpty() {
        //TO-DO: Implementar
        
        return false;
    }
 
    /** 
     * Analiza las cartas de los jugadores y devuelve el jugador que iniciar? la partida. 
     * @return El jugador que posee el 6 de corazones, es el que puede empezar la partida 
     */
    public Player whoIsFirst(){
        //TO-DO: Implementar

        return null;
    }
    
   /**
     * Determina si el juego ha terminado o no
     * @return True si el juego ha terminado y False, en caso contrario
     */
    private boolean isFinished(){
        //TO-DO:

        return false;
    }
    
    /**
     * Muestra en pantalla el ganador del juego
     */
    private void showTheWinner(){
        String name = getTheWinner();
        //TO-DO: show in the board
        
    }
    
    /**
     * Devuelve el ganador del juego
     * @return el nombre del jugador que ha ganado el juego. Formato: nombre (Tipo de jugador)
     */
    private String getTheWinner(){
        //TO-DO:Implementar 
        return "";
    }
    
    /**
     * Verifica si el jugador 'player' es el ganador
     * @return True si el jugador el es ganador y False, en caso contrario
     */
    public boolean isTheWinner(Player player){
        //TO-DO:
        return !player.hasCards();
    }
    
    /**
     * Establece quien es el ganador del juego, antes de que termine la partida.
     */
    public void setTheWinner(Player player){
        //To-Do:
        if (isTheWinner(player))
            theWinner = player;
    }
 
    
    /** 
     * Determina que jugador empieza la partida, establece el turno y lo refleja en el marcador
     */
    public void setTurn(){
        if (!allCardsDealed)
            turn = new TableTurn<Player>(players);
        else {
         //TO-DO:Implementar
         
        }
        //To-Do: mostrar en el marcador
       
    }

    /**
     * Determina si es el turno del jugador 'player' 
     * @param player un jugador
     * @return True si el turno es del jugador 'player' y False, en caso contrario
     */
    public boolean isMyturn(Player player){
        return turn.isMyturn(player);
    }
    
    /**
     * Pasa el turno al siguiente jugador
     */
    public Player next(){
        if (allCardsDealed && isTheWinner(currentPlayer))
            theWinner = currentPlayer;
        currentPlayer = turn.next();
        board.update(turn.getNumOfTurn(), turn.getTurn());
        return currentPlayer;
    }
}
