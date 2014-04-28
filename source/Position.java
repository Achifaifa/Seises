import java.*;

public interface Position{
   public enum position{
       NORTH(180), 
       SOUTH(0), 
       EAST(270),
       WEST(90);
       
       private int deg;
       
       private position(int deg) {
            this.deg=deg;
       }
       
       public int getRotation(){
        return deg;
       }
    }
}
      