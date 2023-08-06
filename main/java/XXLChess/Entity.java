package XXLChess;
import java.util.*;

/**
 * Used to outline the functionality of a player and/or cpu. 
 */
public class Entity{
    boolean isWhite;
    ArrayList<chessPiece> attackingPieces = new ArrayList<chessPiece>();

    public Entity(boolean white)
    {
        this.isWhite = white;
    }
    /**
     * @param white true if the entity playing white, false if playing black.
     */

    public boolean checkIfWhite()
    {
        return isWhite;
    }

    public chessPiece getKing(boardPiece[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if (board[i][j].getPiece() != null && board[i][j].getPiece().checkIfWhite() == isWhite && board[i][j].getPiece().getName().equals("King"))
                {
                    return board[i][j].getPiece();
                }
            }
        }
        return null;
    }
    /**
     * fetches the king with the same color as the entity.
     * @param board the 2D array of boardPieces.
     * @return the king of the entity, if not found --> null (This should not occur ever).
     */

    public boolean KingAttacked(boardPiece[][] board)
    {
        attackingPieces.clear();
        int[] kingpos = getKing(board).getPosition();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                if (board[i][j].getPiece() != null)
                {
                    for (int[] move : board[i][j].getPiece().getMoves(board))
                    {
                        if (move[0] == kingpos[0] && move[1] == kingpos[1])
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    } 
    /**
     * Check if the king of this entity is attacked.
     * @param board the 2D array of boardPieces.
     * @return true if current opponent piece has a valid move that matches with the king's position.
     */

    public boolean CheckMated(boardPiece[][] board)
    {
        ArrayList<int[]> allmoves = new ArrayList<int[]>();

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j< board.length; j++)
            {
                if (board[i][j].getPiece() != null && board[i][j].getPiece().checkIfWhite() == isWhite)
                {
                    for (int[] moved : board[i][j].getPiece().getMoves(board))
                    {
                        allmoves.add(moved);
                    }
                }
            }
        }
        //if attacked and no moves
        if (!KingAttacked(board))
        {
            return false;
        }

        if (KingAttacked(board) && getKing(board).getMoves(board).size() == 0 && allmoves.size() == 0)
        {
            //System.out.println("1");
            return true;
        }

        
        if (KingAttacked(board) && getKing(board).getMoves(board).size() != 0)
        {
            //System.out.println("this condition is running");

            //set pos into null
            chessPiece king = getKing(board);
            int[] kingPos = getKing(board).getPosition();
            ArrayList<int[]> moves = getKing(board).getMoves(board);
            /* 
            if (kingPos == null)
            {
                System.out.println("King not found");
                return false;
            }

            */
            for (int i = 0; i < moves.size(); i++)
            {
                int[] move = moves.get(i);

                chessPiece temp = board[move[0]][move[1]].getPiece();

                board[move[0]][move[1]].setPiece(king);
                board[kingPos[0]][kingPos[1]].setPiece(null);

                if (!KingAttacked(board))
                {
                    board[move[0]][move[1]].setPiece(temp);
                    board[kingPos[0]][kingPos[1]].setPiece(king);
                    return false;
                }

                board[move[0]][move[1]].setPiece(temp);
                board[kingPos[0]][kingPos[1]].setPiece(king);
            }
        }
        return false;
    }
    /**
     * Check if the king of this entity is checkmated.
     * @param board the 2D array of boardPieces.
     * @return true if the king is attacked and has no valid moves. 
     * @return false either the king is not attacked or has valid moves.
     */

    public boolean StaleMated(boardPiece[][] board)
    {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        if (KingAttacked(board))
        {
            System.out.println("attacked");
            return false;
        }
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j< board.length; j++)
            {
                if (board[i][j].getPiece() != null && board[i][j].getPiece().checkIfWhite() == isWhite)
                {
                    for (int[] move : board[i][j].getPiece().getMoves(board))
                    {
                        moves.add(move);
                    }
                }
            }
        }

        if (moves.size() == 0 && !KingAttacked(board))
        {
            System.out.println("stalemate");
            return true;
        }
        System.out.println("else");
        return false;
    }
    /*
     * Check if the king of this entity is stalemated. Meaning that the king is not attacked and the entity hasa no moves to play for all pieces.
     * @param board the 2D array of boardPieces.
     * @return true if the king is not attacked and has no valid moves.
     * @return false either the king is attacked or has valid moves.
     */
}