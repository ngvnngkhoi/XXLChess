package XXLChess;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Camel extends chessPiece {
    public Camel(int[] position, boolean isWhite, PImage sprite) {
        super(position, "Camel", isWhite, sprite);
    }

    public ArrayList<int[]> getMoves(boardPiece[][] board)
    {
        moves.clear();
        potentialMoves.clear();

        if (position[1] + 3 <= 13)
        {
            if (position[0] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1] + 3].getPiece() == null || board[position[0] - 1][position[1] + 3].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] + 3].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] + 3].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] + 3});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] + 3].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] + 3});

                }
            }

            if (position[0] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1] + 3].getPiece() == null || board[position[0] + 1][position[1] + 3].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] + 3].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] + 3].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] + 3});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] + 3].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] + 3});
                }
            }
        }


        if (position[1] - 3 >= 0)
        {
            if (position[0] - 1 >= 0)
            {
                if (board[position[0] - 1][position[1] - 3].getPiece() == null || board[position[0] - 1][position[1] - 3].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 1][position[1] - 3].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 1][position[1] - 3].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 1, position[1] - 3});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 1][position[1] - 3].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 1, position[1] - 3});
                }
            }

            if (position[0] + 1 <= 13)
            {
                if (board[position[0] + 1][position[1] - 3].getPiece() == null || board[position[0] + 1][position[1] - 3].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 1][position[1] - 3].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 1][position[1] - 3].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 1, position[1] - 3});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 1][position[1] - 3].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 1, position[1] - 3});
                }
            }
        }


        if (position[0] + 3 <= 13)
        {
            if (position[1] - 1 >= 0)
            {
                if (board[position[0] + 3][position[1] - 1].getPiece() == null || board[position[0] + 3][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 3][position[1] - 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 3][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 3, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 3][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 3, position[1] - 1});
                }
            }

            if (position[1] + 1 <= 13)
            {
                if (board[position[0] + 3][position[1] + 1].getPiece() == null || board[position[0] + 3][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] + 3][position[1] + 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + 3][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] + 3, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + 3][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] + 3, position[1] + 1});
                }
            }
        }

 

        if (position[0] - 3 >= 0)
        {
            if (position[1] - 1 >= 0)
            {
                if (board[position[0] - 3][position[1] - 1].getPiece() == null || board[position[0] - 3][position[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 3][position[1] - 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 3][position[1] - 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 3, position[1] - 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 3][position[1] - 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 3, position[1] - 1});
                }
            }

            if (position[1] + 1 <= 13)
            {
                if (board[position[0] - 3][position[1] + 1].getPiece() == null || board[position[0] - 3][position[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    chessPiece temp = board[position[0] - 3][position[1] + 1].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - 3][position[1] + 1].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[] {position[0] - 3, position[1] + 1});
                    }
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - 3][position[1] + 1].setPiece(temp);
                    potentialMoves.add(new int[] {position[0] - 3, position[1] + 1});
                }
            }
        }

        
        return moves;
    }
}
