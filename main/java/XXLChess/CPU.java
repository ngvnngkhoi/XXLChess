package XXLChess;
import java.util.*;

import XXLChess.boardPiece;
/**
 * Used to outline the functionality of the CPU. 
 */
public class CPU extends Entity{
    public CPU(boolean white)
    {
        super(white);
    }


    
    public chessPiece returnMove(boardPiece[][] board)
    {

            Random rand = new Random();
            ArrayList<chessPiece> CPU_pieces = new ArrayList<chessPiece>();

            for (int i = 0; i < 14; i++)
            {
                for (int j = 0; j < 14; j++)
                {
                    if (board[i][j].getPiece() != null && board[i][j].getPiece().checkIfWhite() == isWhite)
                    {
                        CPU_pieces.add(board[i][j].getPiece());
                    }
                }
            }

            int max = CPU_pieces.size();
            System.out.println(max);
            int rand_id = rand.nextInt(max);
            chessPiece temp = CPU_pieces.get(rand_id);
            

            while (temp.getMoves(board).size() <= 0)
            {
                //System.out.println("No moves");
                rand_id = rand.nextInt(max);
                temp = CPU_pieces.get(rand_id);
            }

            return temp;
    }
    /**
     * Randomly select a piece, if the piece has no moves, keep selecting until a piece with moves is found
     * @param the 2D array of boardPieces.
     * @return the chessPiece that is selected.
     */
    

}
