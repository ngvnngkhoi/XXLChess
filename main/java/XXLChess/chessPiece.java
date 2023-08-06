package XXLChess;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;
/**
 * This class represents a standard chess piece.
 */
public class chessPiece{
    int[] position;
    String name;
    boolean isWhite;
    PImage sprite;
    ArrayList<int[]> moves = new ArrayList<int[]>();
    ArrayList<int[]> potentialMoves = new ArrayList<int[]>();
    int moveCounter = 0;
    boolean isOnTop;
    ArrayList<chessPiece> attackingPieces = new ArrayList<chessPiece>();

    public chessPiece(int[] position, String name, boolean isWhite ,PImage sprite) {
        this.position = position;
        this.name = name;
        this.isWhite = isWhite;
        this.sprite = sprite;
    }
    /** 
    * @param position Refers to the position of the piece on a 2D boardPiece array.
    * @param name Referes to the name of the piece (i.e "Pawn", "Rook"), mostly used for debugging.
    * @param isWhite Refers to the color of the piece, if !isWhite --> piece is black.
    * @param sprite Refers to the sprite of the piece, it is in a PImage format.
    */

    public int getMoveCounter()
    {
        return moveCounter;
    }
    /**
     * Used to determine how many times a piece has moved. Primarily utilzied for castling.
     * @return Returns the number of times a piece has moved.
     */

    public boolean isOnTop()
    {
        return isOnTop;
    }
    /**
     * Used to determine if a piece starts at top or bottom. Used for pawns.
     * @return True if starts on top, else false.
     */
    
    public void setMoveCounter(int plus)
    {
        moveCounter += plus;
    }
    /**
     * Used to set the move counter of a piece.
     * @param plus The number to add to the move counter.
     */
    
    public void setPosition(int[] position) {
        this.position = position;
    }
    /**
     * Used to set the position of a piece.
     * @param position The position to set the piece to.
     */

    public int[] getPosition() {
        return position;
    }
    /**
     * @return Returns the position of the piece on the 2d array.
     */

    public PImage getSprite() {
        return sprite;
    }
    /**
     * @return Returns the sprite of a piece in the form of a PImage.
     */

    public String getName() {
        return name;
    }
    /**
     * Used to get the name of a piece.
     * @return Returns the name of a piece, (i.e "Pawn", "Rook"). Mostly used for debugging.
     */

    public boolean checkIfWhite() {
        return isWhite;
    }
    /**
    * @return Returns true if the piece is white, else false.
    */

    public void drawSprite(PApplet applet) {
        applet.image(sprite, position[0] * 48, position[1] * 48, 48, 48);
    }
    /**
     * Use to render the sprite on the board
     * @param applet Essential to render the sprite. Refers to the PApplet running on App.java.
     * @see App
     */

    public ArrayList<int[]> getMoves(boardPiece[][] board) {   
        return moves;
    }
    /**
     * Used to get all VALID moves of a piece
     * @param board Refers to the 2D array of boardPieces.
     * @return Returns ArrayList containing all valid moves. This ArrayList is a subset of the potentialMoves ArrayList.
     */

    public ArrayList<int[]> getPotentialMoves() {
        return potentialMoves;
    }
    /**
     * Used to get all POTENTIAL moves of a piece. The difference is that this does not take into consideration whether this move will put the king in check.
     * @return Returns ArrayList containing all potential moves. This ArrayList contains all elements that exists in the moves ArrayList.
     */

    public ArrayList<chessPiece> getAttackingPieces()
    {
        return attackingPieces;
    }
    /**
     * Get all pieces that are threatening this current piece. Used to determine if king is in check.
     * @return Returns an ArrayList containing all chessPieces that are threatening this piece.S
     */

    public boolean isValidMove (int[] move, boardPiece[][] board) 
    {
        for (int[] tmp : getMoves(board))
        {
            if (tmp[0] == move[0] && tmp[1] == move[1])
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Used to determine if a move is valid to make. Meaning that it does not put the king in check and is an element of the moves ArrayList.
     * @param move The move to check.
     * @param board Refers to the 2D array of boardPieces.
     * @return Returns true --> valid move, else not.
     */

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
     * Used to fetch the king of the same color with the current piece. Used to determine if next move will put king in check.
     * @param board Refers to the 2D array of boardPieces.
     * @return Returns the king of the same color as the current piece. If no king (This case should never occur), returns null.
     */

    public boolean KingAttacked(boardPiece[][] board)
    {
        chessPiece king = getKing(board);
        int[] kingpos = king.getPosition();
        int count = 1;

        while (kingpos[1] + count < 14)
        {
            if (board[kingpos[0]][kingpos[1] + count].getPiece() != null)
            {
                if (board[kingpos[0]][kingpos[1] + count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0]][kingpos[1] + count].getPiece().getName().equals("Rook") || board[kingpos[0]][kingpos[1] + count].getPiece().getName().equals("Queen") || board[kingpos[0]][kingpos[1] + count].getPiece().getName().equals("Amazon") || board[kingpos[0]][kingpos[1] + count].getPiece().getName().equals("Chancellor"))
                    {
                        attackingPieces.add(board[kingpos[0]][kingpos[1] + count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Rook or Queen or Amazon or Chancellor on bottom of column
        count = 1;

        while (kingpos[1] - count >= 0)
        {
            if (board[kingpos[0]][kingpos[1] - count].getPiece() != null)
            {
                if (board[kingpos[0]][kingpos[1] - count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0]][kingpos[1] - count].getPiece().getName().equals("Rook") || board[kingpos[0]][kingpos[1] - count].getPiece().getName().equals("Queen") || board[kingpos[0]][kingpos[1] - count].getPiece().getName().equals("Amazon") || board[kingpos[0]][kingpos[1] - count].getPiece().getName().equals("Chancellor"))
                    {
                        attackingPieces.add(board[kingpos[0]][kingpos[1] - count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Rook or Queen or Amazon or Chancellor on right of row
        count = 1;

        while (kingpos[0] + count < 14)
        {
            if (board[kingpos[0] + count][kingpos[1]].getPiece() != null)
            {
                if (board[kingpos[0] + count][kingpos[1]].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] + count][kingpos[1]].getPiece().getName().equals("Rook") || board[kingpos[0] + count][kingpos[1]].getPiece().getName().equals("Queen") || board[kingpos[0] + count][kingpos[1]].getPiece().getName().equals("Amazon") || board[kingpos[0] + count][kingpos[1]].getPiece().getName().equals("Chancellor"))
                    {
                        attackingPieces.add(board[kingpos[0] + count][kingpos[1]].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Rook or Queen or Amazon or Chancellor on left of row
        count = 1;

        while (kingpos[0] - count >= 0)
        {
            if (board[kingpos[0] - count][kingpos[1]].getPiece() != null)
            {
                if (board[kingpos[0] - count][kingpos[1]].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] - count][kingpos[1]].getPiece().getName().equals("Rook") || board[kingpos[0] - count][kingpos[1]].getPiece().getName().equals("Queen") || board[kingpos[0] - count][kingpos[1]].getPiece().getName().equals("Amazon") || board[kingpos[0] - count][kingpos[1]].getPiece().getName().equals("Chancellor"))
                    {
                        attackingPieces.add(board[kingpos[0] - count][kingpos[1]].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Bishop or Queen or Amazon or Archbishop on top right diagonal

        count = 1;

        while (kingpos[0] + count < 14 && kingpos[1] + count < 14)
        {
            if (board[kingpos[0] + count][kingpos[1] + count].getPiece() != null)
            {
                if (board[kingpos[0] + count][kingpos[1] + count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] + count][kingpos[1] + count].getPiece().getName().equals("Bishop") || board[kingpos[0] + count][kingpos[1] + count].getPiece().getName().equals("Queen") || board[kingpos[0] + count][kingpos[1] + count].getPiece().getName().equals("Amazon") || board[kingpos[0] + count][kingpos[1] + count].getPiece().getName().equals("Archbishop"))
                    {
                        attackingPieces.add(board[kingpos[0] + count][kingpos[1] + count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Bishop or Queen or Amazon or Archbishop on top left diagonal

        count = 1;

        while (kingpos[0] - count >= 0 && kingpos[1] + count < 14)
        {
            if (board[kingpos[0] - count][kingpos[1] + count].getPiece() != null)
            {
                if (board[kingpos[0] - count][kingpos[1] + count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] - count][kingpos[1] + count].getPiece().getName().equals("Bishop") || board[kingpos[0] - count][kingpos[1] + count].getPiece().getName().equals("Queen") || board[kingpos[0] - count][kingpos[1] + count].getPiece().getName().equals("Amazon") || board[kingpos[0] - count][kingpos[1] + count].getPiece().getName().equals("Archbishop"))
                    {
                        attackingPieces.add(board[kingpos[0] - count][kingpos[1] + count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Bishop or Queen or Amazon or Archbishop on bottom right diagonal

        count = 1;

        while (kingpos[0] + count < 14 && kingpos[1] - count >= 0)
        {
            if (board[kingpos[0] + count][kingpos[1] - count].getPiece() != null)
            {
                if (board[kingpos[0] + count][kingpos[1] - count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] + count][kingpos[1] - count].getPiece().getName().equals("Bishop") || board[kingpos[0] + count][kingpos[1] - count].getPiece().getName().equals("Queen") || board[kingpos[0] + count][kingpos[1] - count].getPiece().getName().equals("Amazon") || board[kingpos[0] + count][kingpos[1] - count].getPiece().getName().equals("Archbishop"))
                    {
                        attackingPieces.add(board[kingpos[0] + count][kingpos[1] - count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Bishop or Queen or Amazon or Archbishop on bottom left diagonal

        count = 1;

        while (kingpos[0] - count >= 0 && kingpos[1] - count >= 0)
        {
            if (board[kingpos[0] - count][kingpos[1] - count].getPiece() != null)
            {
                if (board[kingpos[0] - count][kingpos[1] - count].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0] - count][kingpos[1] - count].getPiece().getName().equals("Bishop") || board[kingpos[0] - count][kingpos[1] - count].getPiece().getName().equals("Queen") || board[kingpos[0] - count][kingpos[1] - count].getPiece().getName().equals("Amazon") || board[kingpos[0] - count][kingpos[1] - count].getPiece().getName().equals("Archbishop"))
                    {
                        attackingPieces.add(board[kingpos[0] - count][kingpos[1] - count].getPiece());
                        return true;
                    }
                }
                break;
            }
            count++;
        }

        //check if king has Knight or ArchBishop or Guard or Amazon or Chancellor

        //check if king has Knight or ArchBishop or Guard or Amazon or Chancellor on top right L

        if (kingpos[1] + 2 <= 13)
        {
            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1] + 2].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] + 2].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 1][kingpos[1] + 2].getPiece().getName().equals("Knight") || board[kingpos[0] + 1][kingpos[1] + 2].getPiece().getName().equals("Archbishop") || board[kingpos[0] + 1][kingpos[1] + 2].getPiece().getName().equals("Guard") || board[kingpos[0] + 1][kingpos[1] + 2].getPiece().getName().equals("Amazon") || board[kingpos[0] + 1][kingpos[1] + 2].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] + 2].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1] + 2].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] + 2].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 1][kingpos[1] + 2].getPiece().getName().equals("Knight") || board[kingpos[0] - 1][kingpos[1] + 2].getPiece().getName().equals("Archbishop") || board[kingpos[0] - 1][kingpos[1] + 2].getPiece().getName().equals("Guard") || board[kingpos[0] - 1][kingpos[1] + 2].getPiece().getName().equals("Amazon") || board[kingpos[0] - 1][kingpos[1] + 2].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] + 2].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[1] - 2 >= 0 )
        {
            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1] - 2].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] - 2].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 1][kingpos[1] - 2].getPiece().getName().equals("Knight") || board[kingpos[0] + 1][kingpos[1] - 2].getPiece().getName().equals("Archbishop") || board[kingpos[0] + 1][kingpos[1] - 2].getPiece().getName().equals("Guard") || board[kingpos[0] + 1][kingpos[1] - 2].getPiece().getName().equals("Amazon") || board[kingpos[0] + 1][kingpos[1] - 2].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] - 2].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1] - 2].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] - 2].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 1][kingpos[1] - 2].getPiece().getName().equals("Knight") || board[kingpos[0] - 1][kingpos[1] - 2].getPiece().getName().equals("Archbishop") || board[kingpos[0] - 1][kingpos[1] - 2].getPiece().getName().equals("Guard") || board[kingpos[0] - 1][kingpos[1] - 2].getPiece().getName().equals("Amazon") || board[kingpos[0] - 1][kingpos[1] - 2].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] - 2].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[0] + 2 <= 13)
        {
            if (kingpos[1] + 1 <= 13)
            {
                if (board[kingpos[0] + 2][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 2][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 2][kingpos[1] + 1].getPiece().getName().equals("Knight") || board[kingpos[0] + 2][kingpos[1] + 1].getPiece().getName().equals("Archbishop") || board[kingpos[0] + 2][kingpos[1] + 1].getPiece().getName().equals("Guard") || board[kingpos[0] + 2][kingpos[1] + 1].getPiece().getName().equals("Amazon") || board[kingpos[0] + 2][kingpos[1] + 1].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] + 2][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[1] - 1 >= 0)
            {
                if (board[kingpos[0] + 2][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 2][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 2][kingpos[1] - 1].getPiece().getName().equals("Knight") || board[kingpos[0] + 2][kingpos[1] - 1].getPiece().getName().equals("Archbishop") || board[kingpos[0] + 2][kingpos[1] - 1].getPiece().getName().equals("Guard") || board[kingpos[0] + 2][kingpos[1] - 1].getPiece().getName().equals("Amazon") || board[kingpos[0] + 2][kingpos[1] - 1].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] + 2][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[0] - 2 >= 0)
        {
            if (kingpos[1] + 1 <= 13)
            {
                if (board[kingpos[0] - 2][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 2][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 2][kingpos[1] + 1].getPiece().getName().equals("Knight") || board[kingpos[0] - 2][kingpos[1] + 1].getPiece().getName().equals("Archbishop") || board[kingpos[0] - 2][kingpos[1] + 1].getPiece().getName().equals("Guard") || board[kingpos[0] - 2][kingpos[1] + 1].getPiece().getName().equals("Amazon") || board[kingpos[0] - 2][kingpos[1] + 1].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] - 2][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[1] - 1 >= 0)
            {
                if (board[kingpos[0] - 2][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 2][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 2][kingpos[1] - 1].getPiece().getName().equals("Knight") || board[kingpos[0] - 2][kingpos[1] - 1].getPiece().getName().equals("Archbishop") || board[kingpos[0] - 2][kingpos[1] - 1].getPiece().getName().equals("Guard") || board[kingpos[0] - 2][kingpos[1] - 1].getPiece().getName().equals("Amazon") || board[kingpos[0] - 2][kingpos[1] - 1].getPiece().getName().equals("Chancellor"))
                        {
                            attackingPieces.add(board[kingpos[0] - 2][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                }
            }
        }


        if (kingpos[0] + 3 <= 13)
        {
            if (kingpos[1] + 1 <= 13)
            {
                if (board[kingpos[0] + 3][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 3][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 3][kingpos[1] + 1].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] + 3][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[1] - 1 >= 0)
            {
                if (board[kingpos[0] + 3][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 3][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 3][kingpos[1] - 1].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] + 3][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[0] - 3 >= 0)
        {
            if (kingpos[1] + 1 <= 13)
            {
                if (board[kingpos[0] - 3][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 3][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 3][kingpos[1] + 1].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] - 3][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[1] - 1 >= 0)
            {
                if (board[kingpos[0] - 3][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 3][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 3][kingpos[1] - 1].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] - 3][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[1] + 3 <= 13)
        {
            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1] + 3].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] + 3].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 1][kingpos[1] + 3].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] + 3].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1] + 3].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] + 3].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 1][kingpos[1] + 3].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] + 3].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        if (kingpos[1] - 3 >= 0)
        {
            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1] - 3].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] - 3].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 1][kingpos[1] - 3].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] - 3].getPiece());
                            return true;
                        }
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1] - 3].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] - 3].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 1][kingpos[1] - 3].getPiece().getName().equals("Camel"))
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] - 3].getPiece());
                            return true;
                        }
                    }
                }
            }
        }

        //check if attacaked by pawn or king
        if (kingpos[1] - 1 >= 0)
        {
            if (board[kingpos[0]][kingpos[1] - 1].getPiece() != null)
            {
                if (board[kingpos[0]][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0]][kingpos[1] - 1].getPiece().getName().equals("King") || board[kingpos[0]][kingpos[1] - 1].getPiece().getName().equals("Guard"))
                    {
                        attackingPieces.add(board[kingpos[0]][kingpos[1] - 1].getPiece());
                        return true;
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1]].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1]].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] - 1][kingpos[1]].getPiece().getName().equals("King") || board[kingpos[0] - 1][kingpos[1]].getPiece().getName().equals("Guard"))
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1]].getPiece());
                            return true;
                        }
                    }
                }
                if (board[kingpos[0] - 1][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite && board[kingpos[0] - 1][kingpos[1] - 1].getPiece().getName().equals("Pawn"))
                    {
                        if (board[kingpos[0] - 1][kingpos[1] - 1].getPiece().isOnTop() == true)
                        {
                            //System.out.println("pawn attack1");
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                    if (board[kingpos[0] - 1][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite && (board[kingpos[0] - 1][kingpos[1] - 1].getPiece().getName().equals("King") || board[kingpos[0] - 1][kingpos[1] - 1].getPiece().getName().equals("Guard")))
                    {
                        //System.out.println("king attack1");
                        attackingPieces.add(board[kingpos[0] - 1][kingpos[1] - 1].getPiece());
                        return true;
                    }
                }
            }

            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1]].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1]].getPiece().checkIfWhite() != isWhite)
                    {
                        if (board[kingpos[0] + 1][kingpos[1]].getPiece().getName().equals("King") || board[kingpos[0] + 1][kingpos[1]].getPiece().getName().equals("Guard"))
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1]].getPiece());
                            return true;
                        }
                    }
                }
                if (board[kingpos[0] + 1][kingpos[1] - 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite && board[kingpos[0] + 1][kingpos[1] - 1].getPiece().getName().equals("Pawn"))
                    {
                        if (board[kingpos[0] + 1][kingpos[1] - 1].getPiece().isOnTop() == true)
                        {
                            //System.out.println("pawn attack2");
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] - 1].getPiece());
                            return true;
                        }
                    }
                    if (board[kingpos[0] + 1][kingpos[1] - 1].getPiece().checkIfWhite() != isWhite && (board[kingpos[0] + 1][kingpos[1] - 1].getPiece().getName().equals("King") || board[kingpos[0] + 1][kingpos[1] - 1].getPiece().getName().equals("Guard")))
                    {
                        //System.out.println("king attack2");
                        attackingPieces.add(board[kingpos[0] + 1][kingpos[1] - 1].getPiece());
                        return true;
                    }
                }
            }
        }

        if (kingpos[1] + 1 <=13)
        {
            if (board[kingpos[0]][kingpos[1] + 1].getPiece() != null)
            {
                if (board[kingpos[0]][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite)
                {
                    if (board[kingpos[0]][kingpos[1] + 1].getPiece().getName().equals("King") || board[kingpos[0]][kingpos[1] + 1].getPiece().getName().equals("Guard"))
                    {
                        attackingPieces.add(board[kingpos[0]][kingpos[1] + 1].getPiece());
                        return true;
                    }
                }
            }

            if (kingpos[0] - 1 >= 0)
            {
                if (board[kingpos[0] - 1][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] - 1][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite && board[kingpos[0] - 1][kingpos[1] + 1].getPiece().getName().equals("Pawn"))
                    {
                        if (board[kingpos[0] - 1][kingpos[1] + 1].getPiece().isOnTop() == false)
                        {
                            attackingPieces.add(board[kingpos[0] - 1][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }

                    if (board[kingpos[0] - 1][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite && (board[kingpos[0] - 1][kingpos[1] + 1].getPiece().getName().equals("King") || board[kingpos[0] - 1][kingpos[1] + 1].getPiece().getName().equals("Guard")))
                    {
                        attackingPieces.add(board[kingpos[0] - 1][kingpos[1] + 1].getPiece());
                        return true;
                    }
                }
            }

            if (kingpos[0] + 1 <= 13)
            {
                if (board[kingpos[0] + 1][kingpos[1] + 1].getPiece() != null)
                {
                    if (board[kingpos[0] + 1][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite && board[kingpos[0] + 1][kingpos[1] + 1].getPiece().getName().equals("Pawn"))
                    {
                        if (board[kingpos[0] + 1][kingpos[1] + 1].getPiece().isOnTop() == false)
                        {
                            attackingPieces.add(board[kingpos[0] + 1][kingpos[1] + 1].getPiece());
                            return true;
                        }
                    }
                    if (board[kingpos[0] + 1][kingpos[1] + 1].getPiece().checkIfWhite() != isWhite && (board[kingpos[0] + 1][kingpos[1] + 1].getPiece().getName().equals("King") || board[kingpos[0] + 1][kingpos[1] + 1].getPiece().getName().equals("Guard")))
                    {
                        attackingPieces.add(board[kingpos[0] + 1][kingpos[1] + 1].getPiece());
                        return true;
                    }
                }

            }
        }

        return false;
    } 
    /**
     * This method checks if the king is in checkmate.  
     * Determines the king's position and check if local squares have pieces that are threatening the king. 
     * Use this when checking for check and checkmate as calling getMoves() and comparing the king's position might cause a stack overflow.
     * @param board the boardPiece 2D array.
     */



    
}
