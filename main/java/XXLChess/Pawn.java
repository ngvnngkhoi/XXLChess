package XXLChess;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

import XXLChess.chessPiece;
import processing.core.PImage;

public class Pawn extends chessPiece {
    boolean onTop;
    PImage QueenSprite;
    boolean promo = false;
    public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) {
        super(position, "Pawn", isWhite, sprite);
        this.promo = false;
        this.onTop = onTop;
        this.QueenSprite = Queen;
    }

    public void setPromo(boardPiece[][] board)
    {
        if (position[1] == 7)
        {
            promo = true;
            name = "Queen";
            sprite = QueenSprite;
        }
    }

    public boolean isOnTop()
    {
        return onTop;
    }


    public ArrayList<int[]> getMoves(boardPiece[][] board)
    {
        setPromo(board);
        //System.out.println("promo: " + promo);
        moves.clear();
        if (!promo)
        {
            if (onTop)
            {
                //System.out.println("onTop");
                if (position[1] == 1)
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
                        
                    if (position[1] + 2 <= 13)
                    {
                        if (board[position[0]][position[1] + 2].getPiece() == null)
                        {
                            board[position[0]][position[1]].setPiece(null);
                            board[position[0]][position[1] + 2].setPiece(this);
                            if (!KingAttacked(board))
                            {
                                moves.add(new int[] {position[0], position[1] + 2});
                            }
                            board[position[0]][position[1]].setPiece(this);
                            board[position[0]][position[1] + 2].setPiece(null);
                            potentialMoves.add(new int[] {position[0], position[1] + 2});
                        }
                    }
                    }
                }

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
                }

                //up right
                if (position[0] + 1 <= 13 && position[1] + 1 <= 13)
                {
                    if (board[position[0] + 1][position[1] + 1].getPiece() != null)
                    {
                        if (board[position[0] + 1][position[1] + 1].getPiece().isWhite != isWhite)
                        {
                            chessPiece occupiedPiece = board[position[0] + 1][position[1] + 1].getPiece();

                            board[position[0]][position[1]].setPiece(null);
                            board[position[0] + 1][position[1] + 1].setPiece(this);

                            if (!KingAttacked(board))
                            {
                                moves.add(new int[]{position[0] + 1, position[1] + 1});
                            }

                            board[position[0]][position[1]].setPiece(this);
                            board[position[0] + 1][position[1] + 1].setPiece(occupiedPiece);
                            potentialMoves.add(new int[] {position[0] + 1, position[1] + 1});
                        }
                    }
                }

                //up left
                if (position[0] - 1 >= 0 && position[1] + 1 <= 13)
                {
                    if (board[position[0] - 1][position[1] + 1].getPiece() != null)
                    {
                        if (board[position[0] - 1][position[1] + 1].getPiece().isWhite != isWhite)
                        {
                            chessPiece occupiedPiece = board[position[0] - 1][position[1] + 1].getPiece();

                            board[position[0]][position[1]].setPiece(null);
                            board[position[0] - 1][position[1] + 1].setPiece(this);

                            if (!KingAttacked(board))
                            {
                                moves.add(new int[]{position[0] - 1, position[1] + 1});
                            }

                            board[position[0]][position[1]].setPiece(this);
                            board[position[0] - 1][position[1] + 1].setPiece(occupiedPiece);
                            potentialMoves.add(new int[] {position[0] - 1, position[1] + 1});
                        }
                    }
                }
            }

            else

            {
                if (position[1] == 12)
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

                        
                    if (position[1] - 2 >= 0)
                    {
                        if (board[position[0]][position[1] - 2].getPiece() == null)
                        {
                            board[position[0]][position[1]].setPiece(null);
                            board[position[0]][position[1] - 2].setPiece(this);
                            if (!KingAttacked(board))
                            {
                                moves.add(new int[] {position[0], position[1] - 2});
                            }
                            board[position[0]][position[1]].setPiece(this);
                            board[position[0]][position[1] - 2].setPiece(null);
                            potentialMoves.add(new int[] {position[0], position[1] - 2});
                        }
                    }
                    }
                }

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
                }

                //down right
                if (position[0] + 1 <= 13 && position[1] - 1 >= 0)
                {
                    if (board[position[0] + 1][position[1] - 1].getPiece() != null)
                    {
                        if (board[position[0] + 1][position[1] - 1].getPiece().isWhite != isWhite)
                        {
                            chessPiece occupiedPiece = board[position[0] + 1][position[1] - 1].getPiece();

                            board[position[0]][position[1]].setPiece(null);
                            board[position[0] + 1][position[1] - 1].setPiece(this);

                            if (!KingAttacked(board))
                            {
                                moves.add(new int[]{position[0] + 1, position[1] - 1});
                            }

                            board[position[0]][position[1]].setPiece(this);
                            board[position[0] + 1][position[1] - 1].setPiece(occupiedPiece);
                            potentialMoves.add(new int[] {position[0] + 1, position[1] - 1});
                        }
                    }
                }

                //down left
                if (position[0] - 1 >= 0 && position[1] - 1 >= 0)
                {
                    if (board[position[0] - 1][position[1] - 1].getPiece() != null)
                    {
                        if (board[position[0] - 1][position[1] - 1].getPiece().isWhite != isWhite)
                        {
                            chessPiece occupiedPiece = board[position[0] - 1][position[1] - 1].getPiece();

                            board[position[0]][position[1]].setPiece(null);
                            board[position[0] - 1][position[1] - 1].setPiece(this);

                            if (!KingAttacked(board))
                            {
                                moves.add(new int[]{position[0] - 1, position[1] - 1});
                            }

                            board[position[0]][position[1]].setPiece(this);
                            board[position[0] - 1][position[1] - 1].setPiece(occupiedPiece);
                            potentialMoves.add(new int[] {position[0] - 1, position[1] - 1});
                        }
                    }
                }
            }
        }
        if (promo)
        {
            //System.out.println("shouldnt be running");
            int count = 1;

            while (position[1] + count <= 13)
            {
                if (board[position[0]][position[1] + count].getPiece() != null)
                {
                    if (board[position[0]][position[1] + count].getPiece().isWhite == isWhite)
                    {
                        break;
                    }
    
                    if (board[position[0]][position[1] + count].getPiece().isWhite != isWhite)
                    {
                        chessPiece occupiedPiece = board[position[0]][position[1] + count].getPiece();
    
                        board[position[0]][position[1]].setPiece(null);
                        board[position[0]][position[1] + count].setPiece(this);
    
                        if (!KingAttacked(board))
                        {
                            moves.add(new int[]{position[0], position[1] + count});
                        }
    
                        board[position[0]][position[1]].setPiece(this);
                        board[position[0]][position[1] + count].setPiece(occupiedPiece);
                        potentialMoves.add(new int[] {position[0], position[1] + count});
                        break;
                    }
                }
    
                if (board[position[0]][position[1] + count].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] + count].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0], position[1] + count});
                    }
    
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] + count].setPiece(null);
                    potentialMoves.add(new int[] {position[0], position[1] + count});
                }
    
                count++;
            }
    
            //down
    
            count = 1;
    
            while (position[1] - count >= 0)
            {
                if (board[position[0]][position[1] - count].getPiece() != null)
                {
                    if (board[position[0]][position[1] - count].getPiece().isWhite == isWhite)
                    {
                        break;
                    }
    
                    if (board[position[0]][position[1] - count].getPiece().isWhite != isWhite)
                    {
                        chessPiece occupiedPiece = board[position[0]][position[1] - count].getPiece();
    
                        board[position[0]][position[1]].setPiece(null);
                        board[position[0]][position[1] - count].setPiece(this);
    
                        if (!KingAttacked(board))
                        {
                            moves.add(new int[]{position[0], position[1] - count});
                        }
    
                        board[position[0]][position[1]].setPiece(this);
                        board[position[0]][position[1] - count].setPiece(occupiedPiece);
                        potentialMoves.add(new int[] {position[0], position[1] - count});
                        break;
                    }
                }
    
                if (board[position[0]][position[1] - count].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0]][position[1] - count].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0], position[1] - count});
                    }
    
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0]][position[1] - count].setPiece(null);
                    potentialMoves.add(new int[] {position[0], position[1] - count});
                }
    
                count++;
            }
    
            //right
    
            count = 1;
    
            while (position[0] + count <= 13)
            {
                if (board[position[0] + count][position[1]].getPiece() != null)
                {
                    if (board[position[0] + count][position[1]].getPiece().isWhite == isWhite)
                    {
                        break;
                    }
    
                    if (board[position[0] + count][position[1]].getPiece().isWhite != isWhite)
                    {
                        chessPiece occupiedPiece = board[position[0] + count][position[1]].getPiece();
    
                        board[position[0]][position[1]].setPiece(null);
                        board[position[0] + count][position[1]].setPiece(this);
    
                        if (!KingAttacked(board))
                        {
                            moves.add(new int[]{position[0] + count, position[1]});
                        }
    
                        board[position[0]][position[1]].setPiece(this);
                        board[position[0] + count][position[1]].setPiece(occupiedPiece);
                        potentialMoves.add(new int[] {position[0] + count, position[1]});
                        break;
                    }
                }
    
                if (board[position[0] + count][position[1]].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] + count][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] + count, position[1]});
                    }
    
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] + count][position[1]].setPiece(null);
                    potentialMoves.add(new int[] {position[0] + count, position[1]});
                }
    
                count++;
            }
    
            //left
    
            count = 1;
    
            while (position[0] - count >= 0)
            {
                if (board[position[0] - count][position[1]].getPiece() != null)
                {
                    if (board[position[0] - count][position[1]].getPiece().isWhite == isWhite)
                    {
                        break;
                    }
    
                    if (board[position[0] - count][position[1]].getPiece().isWhite != isWhite)
                    {
                        chessPiece occupiedPiece = board[position[0] - count][position[1]].getPiece();
    
                        board[position[0]][position[1]].setPiece(null);
                        board[position[0] - count][position[1]].setPiece(this);
    
                        if (!KingAttacked(board))
                        {
                            moves.add(new int[]{position[0] - count, position[1]});
                        }
    
                        board[position[0]][position[1]].setPiece(this);
                        board[position[0] - count][position[1]].setPiece(occupiedPiece);
                        potentialMoves.add(new int[] {position[0] - count, position[1]});
                        break;
                    }
                }
    
                if (board[position[0] - count][position[1]].getPiece() == null)
                {
                    board[position[0]][position[1]].setPiece(null);
                    board[position[0] - count][position[1]].setPiece(this);
                    if (!KingAttacked(board))
                    {
                        moves.add(new int[]{position[0] - count, position[1]});
                    }
    
                    board[position[0]][position[1]].setPiece(this);
                    board[position[0] - count][position[1]].setPiece(null);
                    potentialMoves.add(new int[] {position[0] - count, position[1]});
                }
    
                count++;
            }
    
            //up right
    
            count = 1;
    
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
                        potentialMoves.add(new int[] {position[0] + count, position[1] + count});
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
                    potentialMoves.add(new int[] {position[0] + count, position[1] + count});
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
                        potentialMoves.add(new int[] {position[0] - count, position[1] + count});
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
                    potentialMoves.add(new int[] {position[0] - count, position[1] + count});
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
                        potentialMoves.add(new int[] {position[0] + count, position[1] - count});
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
                    potentialMoves.add(new int[] {position[0] + count, position[1] - count});
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
                        potentialMoves.add(new int[] {position[0] - count, position[1] - count});
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
                    potentialMoves.add(new int[] {position[0] - count, position[1] - count});
                }
    
                count++;
            }
    
        }

        //System.out.println(moves.size());
        return moves;
    }

}


