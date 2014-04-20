import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Describe un controlador de turnos
 * 
 * @author Felipe I. Anfurrutia
 * @version 2014/3
 */
public class TableTurn<T>  extends Actor implements Turn<T>
{
        private int numOfTurn;
        private int turn;
        private T[] players;
        
        /**
         * Constructor del controlador de turnos
         * @param los jugadores
         * @param un entero entre el rango de los jugadores
         */
        public TableTurn(T[] players){
            this.players = players;
            this.turn = 0;
            numOfTurn = 0;
        }

        /**
         * Constructor del controlador de turnos
         * @param los jugadores
         * @param un entero entre el rango de los jugadores
         */
        public TableTurn(T[] players, T p){
            this.players = players;
            this.turn = getTurn(players, p);
            numOfTurn = 0;
        }
        
        private int getTurn(T[] player, T p){
            boolean enc = false;
            int i = 0;
            while ((i < players.length) && !enc){
                if (players[i] == p)
                    enc = true;
                else 
                    i++;
            }
            return i;
        }
        
        public void clear(){
            turn = 0;
            numOfTurn = 0;
        }
        
        public int getNumOfTurn(){
            return numOfTurn;
        }
        
        public int getTurn(){
            return turn + 1;
        }
        
        public T next(){
            numOfTurn++;
            T player = players[turn];
            turn = (turn + 1) % players.length;
            return player;
        }
        
        public boolean isMyturn(T player){
            return players[turn] == player;
        }
        

   
}
