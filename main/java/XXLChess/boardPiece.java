package XXLChess;
import java.util.*;
import processing.core.PApplet;
/**
 * This class represents a standard board piece.
 */
public class boardPiece {
    chessPiece piece;
    boolean isWhite;
    int[] position;
    PApplet sketch;

    public boardPiece(boolean isWhite, int[] position, PApplet sketch) {
        this.isWhite = isWhite;
        this.position = position;
        this.sketch = sketch;
    }
    /** 
    * @param position Refers to the position of the piece on a 2D boardPiece array.
    * @param isWhite Refers to the color of the piece, if !isWhite --> piece is black.
    * @param sketch Required in order to render the piece, it refers to the PApplet that is ran on App.java.
    */

    public chessPiece getPiece() {
        return piece;
    }
    /**
     * Returns whichever chessPiece is assigned to this boardPiece.
     * @return chessPiece if there is one, else returns null.
     */

    public int[] getPosition() {
        return position;
    }
    /**
     * @return returns the position on the board array.
     */

    public void setPiece(chessPiece piece) {
        this.piece = piece;
    }
    /**
     * Assign a chessPiece to this boardPiece. Assign null if no piece or to remove a piece.
     * @param piece is the chesspiece to be assigned
     */

    public void drawSquare() {
        if (isWhite) {
            sketch.fill(165, 42, 42);
        } else {
            sketch.fill(255, 255, 153);
        }
        sketch.rect(position[0] * 48, position[1] * 48, 48, 48);
    }
    /**
     * Draw square depending on isWhite boolean.
     */

    public void fillSquare(int state) {
        if (state == 0) {
            sketch.fill(173, 216, 230);
        }  
        if (state == 1) {
            sketch.fill(0, 255, 0);
        }  
        if (state == 2) {
            sketch.fill(255, 165, 0);
        } 
         if (state == 3) {
            sketch.fill(255, 0, 0);
        }
        sketch.rect(position[0] * 48, position[1] * 48, 48, 48);
    }
    /**
     * Fill square depending on state, used for highlighting checks, checkmates or can-be-taken pieces.
     * @param state is the state of the square. 0 : blue, 1 : green, 2 : orange, 3 : red.
     */

}





