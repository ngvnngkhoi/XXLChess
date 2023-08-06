package XXLChess;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Bishop extends chessPiece {
    public Bishop(int[] position, boolean isWhite, PImage sprite) {
        super(position, "Bishop", isWhite, sprite);
    }

    public ArrayList<int[]> getMoves(boardPiece[][] board)
    {
        moves.clear();
        potentialMoves.clear();
        int count = 1;

        while (position[0] + count <= 13 && position[1] + count <= 13)
        {
            if (board[position[0] + count][position[1] + count].getPiece() != null)
            {
                if (board[position[0] + count][position[1] + count].getPiece().isWhite == isWhite)
                {
                    break;
                }

                if (board[position[0] + count][position[1] + count].getPiece().isWhite != isWhite)
                {
                    chessPiece occupiedPiece = board[position[0] + count][position[1] + count].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + count][position[1] + count].setPiece(this);

                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] + count, position[1] + count});
                    }

                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + count][position[1] + count].setPiece(occupiedPiece);
                    potentialMoves.add(new int[]{position[0] + count, position[1] + count});
                    break;
                }
            }

            if (board[position[0] + count][position[1] + count].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + count][position[1] + count].setPiece(this);
                if (!KingAttacked(board))
                {
                    moves.add(new int[]{position[0] + count, position[1] + count});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] + count][position[1] + count].setPiece(null);
                potentialMoves.add(new int[]{position[0] + count, position[1] + count});
            }

            count++;
        }

        //up left

        count = 1;

        while (position[0] - count >= 0 && position[1] + count <= 13)
        {
            if (board[position[0] - count][position[1] + count].getPiece() != null)
            {
                if (board[position[0] - count][position[1] + count].getPiece().isWhite == isWhite)
                {
                    break;
                }

                if (board[position[0] - count][position[1] + count].getPiece().isWhite != isWhite)
                {
                    chessPiece occupiedPiece = board[position[0] - count][position[1] + count].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - count][position[1] + count].setPiece(this);

                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] - count, position[1] + count});
                    }

                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - count][position[1] + count].setPiece(occupiedPiece);
                    potentialMoves.add(new int[]{position[0] - count, position[1] + count});
                    break;
                }
            }

            if (board[position[0] - count][position[1] + count].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - count][position[1] + count].setPiece(this);
                if (!KingAttacked(board))
                {
                    moves.add(new int[]{position[0] - count, position[1] + count});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] - count][position[1] + count].setPiece(null);
                potentialMoves.add(new int[]{position[0] - count, position[1] + count});
            }

            count++;
        }

        //down right

        count = 1;

        while (position[0] + count <= 13 && position[1] - count >= 0)
        {
            if (board[position[0] + count][position[1] - count].getPiece() != null)
            {
                if (board[position[0] + count][position[1] - count].getPiece().isWhite == isWhite)
                {
                    break;
                }

                if (board[position[0] + count][position[1] - count].getPiece().isWhite != isWhite)
                {
                    chessPiece occupiedPiece = board[position[0] + count][position[1] - count].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + count][position[1] - count].setPiece(this);

                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] + count, position[1] - count});
                    }

                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + count][position[1] - count].setPiece(occupiedPiece);
                    potentialMoves.add(new int[]{position[0] + count, position[1] - count});
                    break;
                }
            }

            if (board[position[0] + count][position[1] - count].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + count][position[1] - count].setPiece(this);
                if (!KingAttacked(board))
                {
                    moves.add(new int[]{position[0] + count, position[1] - count});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] + count][position[1] - count].setPiece(null);
                potentialMoves.add(new int[]{position[0] + count, position[1] - count});
            }

            count++;
        }

        //down left

        count = 1;

        while (position[0] - count >= 0 && position[1] - count >= 0)
        {
            if (board[position[0] - count][position[1] - count].getPiece() != null)
            {
                if (board[position[0] - count][position[1] - count].getPiece().isWhite == isWhite)
                {
                    break;
                }

                if (board[position[0] - count][position[1] - count].getPiece().isWhite != isWhite)
                {
                    chessPiece occupiedPiece = board[position[0] - count][position[1] - count].getPiece();

                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - count][position[1] - count].setPiece(this);

                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] - count, position[1] - count});
                    }

                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - count][position[1] - count].setPiece(occupiedPiece);
                    potentialMoves.add(new int[]{position[0] - count, position[1] - count});
                    break;
                }
            }

            if (board[position[0] - count][position[1] - count].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - count][position[1] - count].setPiece(this);
                if (!KingAttacked(board))
                {
                    moves.add(new int[]{position[0] - count, position[1] - count});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] - count][position[1] - count].setPiece(null);
                potentialMoves.add(new int[]{position[0] - count, position[1] - count});
            }

            count++;
        }


        return moves;
    }
}
