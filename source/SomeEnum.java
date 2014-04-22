import java.*;
public class SomeEnum {
 
        private enum Position {
                NORTH(120),
                SOUTH(0),
                EAST(270),
                WEST(90);
               
                int value;
               
                Position(int value) {
                        this.value = value;
                }
               
            public int getValue() {
                return value;
            }
        }
       
        int position;
       
        public SomeEnum() {
                position = Position.NORTH.getValue();
        }
       
}