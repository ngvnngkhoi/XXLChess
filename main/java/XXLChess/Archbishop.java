package XXLChess;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Archbishop extends chessPiece {
    public Archbishop(int[] position, boolean isWhite, PImage sprite) {
        super(position, "Archbishop", isWhite, sprite);
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

        return moves;
    }
    
}
