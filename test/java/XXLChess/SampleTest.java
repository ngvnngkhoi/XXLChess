package XXLChess;


import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
//check if white
    @Test
    public void check_if_white() {
        Player white = new Player(true);
        assertEquals(true, white.checkIfWhite());
    }
//check if not white
    @Test
    public void check_if_not_white() {
        Player black = new Player(false);
        assertEquals(false, black.checkIfWhite());
    }
//check if getmoves work
    @Test
    public void check_moves_invalid() {        
        Player white = new Player(true);
        assertEquals(false, white.isTurn(1));
    }
//check if getmoves work
    @Test
    public void check_moves_valid() {
        Player black = new Player(false);
        assertEquals(true, black.isTurn(1));
    }
//check if addpiece works
    @Test
    public void check_add_piece() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();
        chessPiece b_king = new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png"));
        chessPiece w_king = new King(new int[] {7,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png"));

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(b_king);
        test[0][0].setPiece(w_king);

        assertNotNull(test[0][0].getPiece());
    }
//check if checkmate

    @Test
    public void checkMate1() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();
        chessPiece b_king = new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png"));

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }


        test[0][1].setPiece(new Pawn(new int[] {1,0}, false, temp.loadImage("src/main/resources/XXLChess/b-pawn.png"), true, temp.loadImage("src/main/resources/XXLChess/b-queen.png")));
        test[1][1].setPiece(new Pawn(new int[] {1,1}, false, temp.loadImage("src/main/resources/XXLChess/b-pawn.png"), true, temp.loadImage("src/main/resources/XXLChess/b-queen.png")));
        test[2][1].setPiece(new Pawn(new int[] {1,2}, false, temp.loadImage("src/main/resources/XXLChess/b-pawn.png"), true, temp.loadImage("src/main/resources/XXLChess/b-queen.png")));

        test[0][0].setPiece(b_king);
        test[7][0].setPiece(new Rook(new int[] {7,0}, true, temp.loadImage("src/main/resources/XXLChess/w-rook.png")));

        test[7][7].setPiece(new King(new int[] {7,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));

        assertEquals(true, temp.bot.CheckMated(test));
    }
//check if checkmate

    @Test
    public void checkMate2() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();
        chessPiece b_king = new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png"));

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(b_king);
        test[7][0].setPiece(new Rook(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-rook.png")));
        test[7][1].setPiece(new Rook(new int[] {1,7}, true, temp.loadImage("src/main/resources/XXLChess/w-rook.png")));

        test[7][7].setPiece(new King(new int[] {7,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));

        boolean cm = temp.bot.CheckMated(test);

        assertEquals(true, cm);
    }
//check if checkmate
    @Test
    public void checkmate3() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();
        chessPiece b_king = new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png"));

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(b_king);

        test[0][1].setPiece(new Pawn(new int[] {0,1}, true, temp.loadImage("src/main/resources/XXLChess/w-pawn.png"), false, temp.loadImage("src/main/resources/XXLChess/w-queen.png")));
        test[0][2].setPiece(new Pawn(new int[] {0,2}, true, temp.loadImage("src/main/resources/XXLChess/w-pawn.png"), false, temp.loadImage("src/main/resources/XXLChess/w-queen.png")));
        test[1][1].setPiece(new Pawn(new int[] {1,1}, true, temp.loadImage("src/main/resources/XXLChess/w-pawn.png"), false, temp.loadImage("src/main/resources/XXLChess/w-queen.png")));
        test[1][2].setPiece(new Pawn(new int[] {1,2}, true, temp.loadImage("src/main/resources/XXLChess/w-pawn.png"), false, temp.loadImage("src/main/resources/XXLChess/w-queen.png")));

        
        test[7][7].setPiece(new King(new int[] {7,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));

        boolean cm = temp.bot.CheckMated(test);

        assertEquals(true, cm);
    }
//check if piece is pinned
    @Test
    public void pinned() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        chessPiece rook = new Rook(new int[] {1,6}, true, temp.loadImage("src/main/resources/XXLChess/b-rook.png"));

        test[0][7].setPiece(new King(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));
        test[1][6].setPiece(rook);
        test[2][5].setPiece(new Bishop(new int[] {2,5}, false, temp.loadImage("src/main/resources/XXLChess/b-bishop.png")));
        test[0][0].setPiece(new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png")));

        boolean valid = rook.getMoves(test).size() == 0;

        assertEquals(true, valid);
    }
//check if piece is not pinned
    @Test 
    public void notPinned() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        chessPiece rook = new Rook(new int[] {1,6}, true, temp.loadImage("src/main/resources/XXLChess/b-rook.png"));

        test[1][7].setPiece(new King(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));
        test[1][6].setPiece(rook);
        test[2][5].setPiece(new Bishop(new int[] {2,5}, false, temp.loadImage("src/main/resources/XXLChess/b-bishop.png")));
        test[0][0].setPiece(new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png")));

        boolean valid = rook.getMoves(test).size() == 0;

        assertEquals(true, valid);
    }
    //check if king is in check
    @Test
    public void inCheck1() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png")));
        test[0][5].setPiece(new Rook(new int[] {0,5}, true, temp.loadImage("src/main/resources/XXLChess/b-rook.png")));
        test[0][7].setPiece(new King(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));

        boolean valid = temp.bot.KingAttacked(test);

        assertEquals(true, valid);
    }

    //check if king is in check
    @Test
    public void inCheck2() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png")));
        test[0][5].setPiece(new Rook(new int[] {0,5}, true, temp.loadImage("src/main/resources/XXLChess/b-rook.png")));
        test[0][7].setPiece(new King(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));
        test[0][6].setPiece(new Rook(new int[] {0,6}, true, temp.loadImage("src/main/resources/XXLChess/b-rook.png")));

        boolean valid = temp.bot.KingAttacked(test);

        assertEquals(true, valid);
    }

    //check if king in check
    @Test
    public void notinCheck() {
        boardPiece[][] test = new boardPiece[14][14];
        //boardPiece(boolean isWhite, int[] position, PApplet sketch)
        //public Pawn(int[] position, boolean isWhite, PImage sprite, boolean onTop, PImage Queen) 
        //    public King(int[] position, boolean isWhite, PImage sprite) 
        App temp = new App();
        PApplet.runSketch(new String[]{"App"}, temp);
        temp.setup();

        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                test[i][j] = new boardPiece(i+j % 2 == 0, new int[]{i,j}, temp);
            }
        }

        test[0][0].setPiece(new King(new int[] {0,0}, false, temp.loadImage("src/main/resources/XXLChess/b-king.png")));
        test[0][7].setPiece(new King(new int[] {0,7}, true, temp.loadImage("src/main/resources/XXLChess/w-king.png")));

        boolean valid = temp.bot.KingAttacked(test);

        assertEquals(false, valid);
    }





}
