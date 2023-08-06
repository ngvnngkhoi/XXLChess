package XXLChess;
import java.util.*;
import processing.core.PImage;


public class King extends chessPiece {
    int moveCounter = 0;

    public King(int[] position, boolean isWhite, PImage sprite) {
        super(position, "King", isWhite, sprite);
    }
    
    public void setMoveCounter(int plus)
    {
        moveCounter += plus;
    }
    public int getMoveCounter()
    {
        return moveCounter;
    }

    public ArrayList<int[]> getMoves(boardPiece[][] board) 
    {
        moves.clear();
        attackingPieces.clear();
        potentialMoves.clear();
        
        int[] position = getPosition();

        //UP
        if (position[1] + 1 <= 13)
        {
            if (board[position[0]][position[1] + 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0]][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0], position[1] + 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0], position[1] + 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0]][position[1] + 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0], position[1] + 1});
            }

            if (board[position[0]][position[1] + 1].getPiece() != null && board[position[0]][position[1] + 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0]][position[1] + 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0]][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0], position[1] + 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0], position[1] + 1});
                }
                board[position[0]][position[1] + 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0], position[1] + 1});
            }
        }

        //DOWN
        if (position[1] - 1 >= 0)
        {
            if (board[position[0]][position[1] - 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0]][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0], position[1] - 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0], position[1] - 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0]][position[1] - 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0], position[1] - 1});
            }

            if (board[position[0]][position[1] - 1].getPiece() != null && board[position[0]][position[1] - 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0]][position[1] - 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0]][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0], position[1] - 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0], position[1] - 1});
                }
                board[position[0]][position[1] - 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0], position[1] - 1});
            }
        }

        //LEFT
        if (position[0] - 1 >= 0)
        {
            if (board[position[0] - 1][position[1]].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1]].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1]});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1]});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] - 1][position[1]].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1]});
            }

            if (board[position[0] - 1][position[1]].getPiece() != null && board[position[0] - 1][position[1]].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] - 1][position[1]].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1]].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1]});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1]});
                }
                board[position[0] - 1][position[1]].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1]});
            }
        }

        //RIGHT
        if (position[0] + 1 <= 13)
        {
            if (board[position[0] + 1][position[1]].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1]].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1]});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1]});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] + 1][position[1]].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1]});
            }

            if (board[position[0] + 1][position[1]].getPiece() != null && board[position[0] + 1][position[1]].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] + 1][position[1]].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1]].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1]});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1]});
                }
                board[position[0] + 1][position[1]].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1]});
            }
        } 
        
         //UP-LEFT
        if (position[0] - 1 >= 0 && position[1] + 1 <= 13)
        {
            if (board[position[0] - 1][position[1] + 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1] + 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1] + 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] - 1][position[1] + 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1] + 1});
            }

            if (board[position[0] - 1][position[1] + 1].getPiece() != null && board[position[0] - 1][position[1] + 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] - 1][position[1] + 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1] + 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1] + 1});
                }
                board[position[0] - 1][position[1] + 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1] + 1});
            }
        }

        //UP-RIGHT
        if (position[0] + 1 <= 13 && position[1] + 1 <= 13)
        {
            if (board[position[0] + 1][position[1] + 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1] + 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1] + 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] + 1][position[1] + 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1] + 1});
            }

            if (board[position[0] + 1][position[1] + 1].getPiece() != null && board[position[0] + 1][position[1] + 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] + 1][position[1] + 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1] + 1].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1] + 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1] + 1});
                }
                board[position[0] + 1][position[1] + 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1] + 1});
            }
        }

        //DOWN-LEFT
        if (position[0] - 1 >= 0 && position[1] - 1 >= 0)
        {
            if (board[position[0] - 1][position[1] - 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1] - 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1] - 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] - 1][position[1] - 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1] - 1});
            }

            if (board[position[0] - 1][position[1] - 1].getPiece() != null && board[position[0] - 1][position[1] - 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] - 1][position[1] - 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] - 1][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0] - 1, position[1] - 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] - 1, position[1] - 1});
                }
                board[position[0] - 1][position[1] - 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] - 1, position[1] - 1});
            }
        }

        //DOWN-RIGHT
        if (position[0] + 1 <= 13 && position[1] - 1 >= 0)
        {
            if (board[position[0] + 1][position[1] - 1].getPiece() == null)
            {
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1] - 1});

                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1] - 1});
                }

                board[position[0]][position[1]].setPiece(this);
                board[position[0] + 1][position[1] - 1].setPiece(null);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1] - 1});
            }

            if (board[position[0] + 1][position[1] - 1].getPiece() != null && board[position[0] + 1][position[1] - 1].getPiece().checkIfWhite() != isWhite)
            {
                chessPiece temp = board[position[0] + 1][position[1] - 1].getPiece();
                board[position[0]][position[1]].setPiece(null);
                board[position[0] + 1][position[1] - 1].setPiece(this);
                setPosition(new int[] {position[0] + 1, position[1] - 1});
                if (!KingAttacked(board))
                {
                    moves.add(new int[] {position[0] + 1, position[1] - 1});
                }
                board[position[0] + 1][position[1] - 1].setPiece(temp);
                board[position[0]][position[1]].setPiece(this);
                setPosition(position);
                potentialMoves.add(new int[] {position[0] + 1, position[1] - 1});
            }
        }

    return moves;
    }
}
