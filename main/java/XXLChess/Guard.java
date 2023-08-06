package XXLChess;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Guard extends chessPiece {
    public Guard(int[] position, boolean isWhite, PImage sprite) {
        super(position, "Guard", isWhite, sprite);
    }

    public ArrayList<int[]> getMoves(boardPiece[][] board)
    {
        moves.clear();
        potentialMoves.clear();
        
        if (position[1] + 2 <= 13)
        {
            if (position[0] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1] + 2].getPiece() == null || board[position[0] - 1][position[1] + 2].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] + 2].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] + 2].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] + 2});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] + 2].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] + 2});
                }
            }

            if (position[0] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1] + 2].getPiece() == null || board[position[0] + 1][position[1] + 2].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] + 2].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] + 2].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] + 2});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] + 2].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] + 2});
                }
            }
        }
    

        if (position[1] - 2 >= 0)
        {
            if (position[0] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1] - 2].getPiece() == null || board[position[0] - 1][position[1] - 2].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] - 2].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] - 2].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] - 2});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] - 2].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] - 2});
                }
            }

            if (position[0] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1] - 2].getPiece() == null || board[position[0] + 1][position[1] - 2].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] - 2].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] - 2].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] - 2});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] - 2].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] - 2});
                }
            }
        }
    

    
        if (position[0] + 2 <= 13)
        {
            if (position[1] - 1 >= 0)
            {
                if (board[position[0] + 2][position[1] - 1].getPiece() == null || board[position[0] + 2][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 2][position[1] - 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 2][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 2, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 2][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 2, position[1] - 1});
                }
            }

            if (position[1] + 1 <= 13)
            {
                if (board[position[0] + 2][position[1] + 1].getPiece() == null || board[position[0] + 2][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 2][position[1] + 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 2][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 2, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 2][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 2, position[1] + 1});
                }
            }
        }
    

        if (position[0] - 2 >= 0)
        {
            if (position[1] - 1 >= 0)
            {
                if (board[position[0] - 2][position[1] - 1].getPiece() == null || board[position[0] - 2][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 2][position[1] - 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 2][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 2, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 2][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 2, position[1] - 1});
                }
            }

            if (position[1] + 1 <= 13)
            {
                if (board[position[0] - 2][position[1] + 1].getPiece() == null || board[position[0] - 2][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 2][position[1] + 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 2][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 2, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 2][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 2, position[1] + 1});
                }
            }
        }
                //down
            if (position[1] + 1 <= 13)
            {
                if (board[position[0]][position[1] + 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0], position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] + 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0], position[1] + 1});
                }
    
                if (board[position[0]][position[1] + 1].getPiece() != null && board[position[0]][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0]][position[1] + 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0], position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0], position[1] + 1});
                }
            }
    
            //up
            if (position[1] - 1 >= 0)
            {
                if (board[position[0]][position[1] - 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0], position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] - 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0], position[1] - 1});
                }
    
                if (board[position[0]][position[1] - 1].getPiece() != null && board[position[0]][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0]][position[1] - 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0], position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0], position[1] - 1});
                }
            }
    
            //left
            if (position[0] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1]].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1]});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1]].setPiece(null);
                    potentialMoves.add(new int[] {position[0] - 1, position[1]});
                }
    
                if (board[position[0] - 1][position[1]].getPiece() != null && board[position[0] - 1][position[1]].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1]].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1]});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1]].setPiece(temp);
                }
            }
    
            //right
            if (position[0] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1]].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1]});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1]].setPiece(null);
                    potentialMoves.add(new int[] {position[0] + 1, position[1]});
                }
    
                if (board[position[0] + 1][position[1]].getPiece() != null && board[position[0] + 1][position[1]].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1]].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1]});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1]].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1]});
                }
            }
    
            //up left
            if (position[0] - 1 >= 0 && position[1] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1] - 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] - 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] - 1});
                }
    
                if (board[position[0] - 1][position[1] - 1].getPiece() != null && board[position[0] - 1][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] - 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] - 1});
                }
            }
    
            //up right
            if (position[0] + 1 <= 13 && position[1] - 1 >= 0)
            {
                if (board[position[0] + 1][position[1] - 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] - 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] - 1});
                }
    
                if (board[position[0] + 1][position[1] - 1].getPiece() != null && board[position[0] + 1][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] - 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] - 1});
                }
            }
    
            //down left
            if (position[0] - 1 >= 0 && position[1] + 1 <= 13)
            {
                if (board[position[0] - 1][position[1] + 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] + 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] + 1});
                }
    
                if (board[position[0] - 1][position[1] + 1].getPiece() != null && board[position[0] - 1][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] + 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] + 1});
                }
            }
    
            //down right
            if (position[0] + 1 <= 13 && position[1] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1] + 1].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] + 1].setPiece(null);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] + 1});
                }
    
                if (board[position[0] + 1][position[1] + 1].getPiece() != null && board[position[0] + 1][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] + 1].getPiece();
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] + 1});
                }
            }
        



        return moves;
    }
    
}
