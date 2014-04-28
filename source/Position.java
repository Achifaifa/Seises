import java.*;

   public enum Position{
       NORTH(180), 
       SOUTH(0), 
       EAST(270),
       WEST(90);
       
       private int deg;
       
       private Position(int deg) {
            this.deg=deg;
       }
       
       public int getRotation(){
        return deg;
       }
    }

      