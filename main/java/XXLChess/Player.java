package XXLChess;
import java.util.*;
/**
 * Used to outline the functionality of the player. 
 */

public class Player extends Entity{
    boolean isTurn;

    public Player(boolean isWhite)
    {
        super(isWhite);
    }        

    public boolean isTurn(int moveCounter)
    {
        if (isWhite)
        {
            if (moveCounter % 2 == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        if (!isWhite)
        {
            if (moveCounter % 2 != 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }
    /**
     * Determines if it is the player's turn. 
     * @param moveCounter @see App.java, total number of moves made.
     */

}
