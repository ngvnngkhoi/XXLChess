package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.core.PFont;
import processing.event.MouseEvent;
//import processing.sound.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.ElementScanner14;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import java.awt.Font;
import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;
    public boardPiece[][] board = new boardPiece[BOARD_WIDTH][BOARD_WIDTH];
    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;
    public char[][] board_read = new char[BOARD_WIDTH][BOARD_WIDTH];
    chessPiece clickedPiece = null;
    boardPiece clickedSquare = null;
    public static final int FPS = 60;
    int[] kingPos = new int[2];
    int moveCounter = 0;
    Player user;
    CPU bot;
    Timer playerClock;
    Timer cpuClock;
    int lastFrameTime = 0;
    int currentFrameTime = 0;
    int playerTime;
    int cpuTime;
    boolean playerResigned = false;  
    boolean justMoved = false;
    int[] before = new int[2];
    int[] after = new int[2];
    boolean castlingMode = false;
    boolean canCastleLeft = false;
    boolean canCastleRight = false;
    chessPiece rookLeft = null;
    chessPiece rookRight = null;
    boolean inCheck = false;
    int[] inCheckPos = new int[2];
    boolean draw = false;
    boolean playerWins = false;
    boolean cpuWins = false;
    chessPiece loserKing = null;
    boolean invalidMove = false;

    public String configPath;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        playerWins = false;
        cpuWins = false;
        File file = new File("level1.txt");

        try {
            Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    board_read[i][j] = line.charAt(j);
                }
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    


        // Load images during setup


        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);


        PImage b_amazon = loadImage("src/main/resources/XXLChess/b-amazon.png");
        PImage b_archbishop = loadImage("src/main/resources/XXLChess/b-archbishop.png");
        PImage b_bishop = loadImage("src/main/resources/XXLChess/b-bishop.png");
        PImage b_camel = loadImage("src/main/resources/XXLChess/b-camel.png");
        PImage b_chancellor = loadImage("src/main/resources/XXLChess/b-chancellor.png");
        PImage b_king = loadImage("src/main/resources/XXLChess/b-king.png");
        PImage b_knight = loadImage("src/main/resources/XXLChess/b-knight.png");
        PImage b_knight_king = loadImage("src/main/resources/XXLChess/b-knight-king.png");
        PImage b_pawn = loadImage("src/main/resources/XXLChess/b-pawn.png");
        PImage b_queen = loadImage("src/main/resources/XXLChess/b-queen.png");
        PImage b_rook = loadImage("src/main/resources/XXLChess/b-rook.png");

        PImage w_amazon = loadImage("src/main/resources/XXLChess/w-amazon.png");
        PImage w_archbishop = loadImage("src/main/resources/XXLChess/w-archbishop.png");
        PImage w_bishop = loadImage("src/main/resources/XXLChess/w-bishop.png");
        PImage w_camel = loadImage("src/main/resources/XXLChess/w-camel.png");
        PImage w_chancellor = loadImage("src/main/resources/XXLChess/w-chancellor.png");
        PImage w_king = loadImage("src/main/resources/XXLChess/w-king.png");
        PImage w_knight = loadImage("src/main/resources/XXLChess/w-knight.png");
        PImage w_knight_king = loadImage("src/main/resources/XXLChess/w-knight-king.png");
        PImage w_pawn = loadImage("src/main/resources/XXLChess/w-pawn.png");
        PImage w_queen = loadImage("src/main/resources/XXLChess/w-queen.png");
        PImage w_rook = loadImage("src/main/resources/XXLChess/w-rook.png");



		// load config
        JSONObject conf = loadJSONObject(new File(this.configPath));
        String player_colour = conf.getString("player_colour");
        boolean onWhite = player_colour.equals("white");

        JSONObject time_controls = conf.getJSONObject("time_controls");

        playerTime = time_controls.getJSONObject("player").getInt("seconds");
        int playerIncrement = time_controls.getJSONObject("player").getInt("increment");
        playerClock = new Timer(playerTime, playerIncrement);

        cpuTime = time_controls.getJSONObject("cpu").getInt("seconds");
        int cpuIncrement = time_controls.getJSONObject("cpu").getInt("increment");
        cpuClock = new Timer(cpuTime, cpuIncrement);

        
        user = new Player(onWhite);
        bot = new CPU(!onWhite);

    
        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                boolean isWhite = (i+j) % 2 == 0;
                board[i][j] = new boardPiece(isWhite, new int[] {i, j}, this);
            }
        }
        // init all pieces
        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j < 14; j++)
            {
                if (board_read[j][i] == 'P')
                {
                    board[i][j].setPiece(new Pawn(board[i][j].getPosition(), false, b_pawn, onWhite == true, b_queen));
                }
                if (board_read[j][i] == 'p')
                {
                    board[i][j].setPiece(new Pawn(board[i][j].getPosition(),true, w_pawn, onWhite == false, w_queen));
                }
                if (board_read[j][i] == 'R')
                {
                    board[i][j].setPiece(new Rook(board[i][j].getPosition(), false, b_rook));
                }
                if (board_read[j][i] == 'r')
                {
                    board[i][j].setPiece(new Rook(board[i][j].getPosition(), true, w_rook));
                }
                if (board_read[j][i] == 'N')
                {
                    board[i][j].setPiece(new Knight(board[i][j].getPosition(), false, b_knight));
                }
                if (board_read[j][i] == 'n')
                {
                    board[i][j].setPiece(new Knight(board[i][j].getPosition(), true, w_knight));
                }
                if (board_read[j][i] == 'B')
                {
                    board[i][j].setPiece(new Bishop(board[i][j].getPosition(), false, b_bishop));
                }
                if (board_read[j][i] == 'b')
                {
                    board[i][j].setPiece(new Bishop(board[i][j].getPosition(), true, w_bishop));
                }
                if (board_read[j][i] == 'Q')
                {
                    board[i][j].setPiece(new Queen(board[i][j].getPosition(), false, b_queen));
                }
                if (board_read[j][i] == 'q')
                {
                    board[i][j].setPiece(new Queen(board[i][j].getPosition(), true, w_queen));
                }
                if (board_read[j][i] == 'K')
                {
                    board[i][j].setPiece(new King(board[i][j].getPosition(), false, b_king));
                }
                if (board_read[j][i] == 'k')
                {
                    board[i][j].setPiece(new King(board[i][j].getPosition(), true, w_king));
                }
                if (board_read[j][i] == 'A')
                {
                    board[i][j].setPiece(new Amazon(board[i][j].getPosition(), false, b_amazon));
                }
                if (board_read[j][i] == 'a')
                {
                    board[i][j].setPiece(new Amazon(board[i][j].getPosition(), true, w_amazon));
                }
                if (board_read[j][i] == 'C')
                {
                    board[i][j].setPiece(new Camel(board[i][j].getPosition(), false, b_camel));
                }
                if (board_read[j][i] == 'c')
                {
                    board[i][j].setPiece(new Camel(board[i][j].getPosition(), true, w_camel));
                }
                if (board_read[j][i] == 'E')
                {
                    board[i][j].setPiece(new Chancellor(board[i][j].getPosition(), false, b_chancellor));
                }
                if (board_read[j][i] == 'e')
                {
                    board[i][j].setPiece(new Chancellor(board[i][j].getPosition(), true, w_chancellor));
                }
                if (board_read[j][i] == 'H')
                {
                    board[i][j].setPiece(new Archbishop(board[i][j].getPosition(), false, b_archbishop));
                }
                if (board_read[j][i] == 'h')
                {
                    board[i][j].setPiece(new Archbishop(board[i][j].getPosition(), true, w_archbishop));
                }
                if (board_read[j][i] == 'G')
                {
                    board[i][j].setPiece(new Guard(board[i][j].getPosition(), false, b_knight_king));
                }
                if (board_read[j][i] == 'g')
                {
                    board[i][j].setPiece(new Guard(board[i][j].getPosition(), true, w_knight_king));
                }
            }
        }
    }

    /**
     * Setup FPS, reads the config file for black/white start. 
     * Read the .txt file for initial board state.
     * Translate the board state into a 2D array of boardPieces.
     * Load all relevant resources including sprites
     * Initialize the player and cpu clocks.
     * Initialize the player and cpu entities.
     */



    public boolean exists(ArrayList<int[]> moves, int[] pos)
    {
        for (int i = 0; i < moves.size(); i++)
        {
            if (moves.get(i)[0] == pos[0] && moves.get(i)[1] == pos[1])
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Check if an element exists in a given arrayList.
     * @param moves the arrayList to check.
     * @param pos the element to check for.
     * @return true if exists, else false.
     */


    public void stopClock()
    {
        playerClock.setPaused();
        cpuClock.setPaused();
    }
    /**
     * Pause the both player and cpu clocks.
     */


    public void keyPressed(){
        if (key == 'r' || key == 'R')
        {
            justMoved = false;
            setup();
        }

        if (key == 'E' || key =='e')
        {
            playerResigned = true;
        }
    /**
     * Receive key pressed signal from the keyboard.
     * r/R is to restart the game.
     * e/E is to resign.
    */

    }
    
    /**
     * Receive key released signal from the keyboard.
     * Not used.
    */
    public void keyReleased(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = mouseX / CELLSIZE;
        int row = mouseY / CELLSIZE;
        boardPiece temp = board[col][row];
        if (col >= 0 && col < 14 && row >= 0 && row < 14 && user.isTurn(moveCounter) && (!cpuWins || !playerWins) && !playerResigned && !draw) {
            canCastleLeft = false;
            canCastleRight = false;
            //to unhighlight the board        
            if (user.KingAttacked(board) && clickedPiece != null && exists(clickedPiece.getPotentialMoves(), temp.getPosition()))
            {
                invalidMove = true;
            }

            //if piece is king, check if it can castle
            if (temp.getPiece() != null && temp.getPiece().getName() == "King")
            {
                int count = 1;
                int[] pos = temp.getPosition();

                //check left
                while (pos[0] - count >= 0)
                {
                    if (board[pos[0] - count][pos[1]].getPiece() != null && board[pos[0] - count][pos[1]].getPiece().getName() == "Rook")
                    {
                        canCastleLeft = true;
                        rookLeft = board[pos[0] - count][pos[1]].getPiece();
                    }

                    if (board[pos[0] - count][pos[1]].getPiece() != null && board[pos[0] - count][pos[1]].getPiece().getName() != "Rook")
                    {
                        break;
                    }

                    count++;
                }

                count = 1;

                //check right
                while (pos[0] + count < 14)
                {
                    if (board[pos[0] + count][pos[1]].getPiece() != null && board[pos[0] + count][pos[1]].getPiece().getName() == "Rook")
                    {
                        canCastleRight = true;
                        rookRight = board[pos[0] + count][pos[1]].getPiece();
                    }

                    if (board[pos[0] + count][pos[1]].getPiece() != null && board[pos[0] + count][pos[1]].getPiece().getName() != "Rook")
                    {
                        break;
                    }
                    count++;
                }
            }

            //if not valid move, reset clickedPiece and clickedSquare
            if (clickedPiece != null && !exists(clickedPiece.getMoves(board), temp.getPosition()) && temp.getPiece() == null)
            {
                clickedPiece = null;
                clickedSquare = null;
            }

            //if valid move, move the piece
            if (clickedPiece != null && exists(clickedPiece.getMoves(board), temp.getPosition()))
            {
                if (board[col][row].getPiece() == null)
                {
                    clickedSquare = temp;
                }

                if (temp.getPiece() != null && clickedPiece.checkIfWhite() != temp.getPiece().checkIfWhite())
                {
                    clickedSquare = temp;
                }

                if (board[col][row].getPiece() != null && clickedPiece.checkIfWhite() == temp.getPiece().checkIfWhite())
                {
                    clickedPiece = temp.getPiece();
                }

            }
            //if slected piece is king and click on rook
            if (clickedPiece != null && clickedPiece.getName() == "King" && temp.getPiece() != null && temp.getPiece().getName() == "Rook" && temp.getPiece().checkIfWhite() == clickedPiece.checkIfWhite())
            {
                if (clickedPiece.getMoveCounter() == 0 && temp.getPiece().getMoveCounter() == 0)
                {
                    int[] kingPos = clickedPiece.getPosition();
                    int[] rookPos = temp.getPiece().getPosition();
                    //if left castle
                    if (rookPos[0] < kingPos[0])
                    {
                        int count = 1;

                        while (kingPos[0] - count != rookPos[0])
                        {
                            if (board[kingPos[0] - count][kingPos[1]].getPiece() != null)
                            {
                                return;
                            }
                            count++;
                        }

                        //move king 2 squares to the left
                        board[kingPos[0] - 2][kingPos[1]].setPiece(clickedPiece);
                        clickedPiece.setPosition(new int[] {kingPos[0] - 2, kingPos[1]});
                        board[kingPos[0] - 1][kingPos[1]].setPiece(temp.getPiece());
                        temp.getPiece().setPosition(new int[] {kingPos[0] - 1, kingPos[1]});

                        board[kingPos[0]][kingPos[1]].setPiece(null);
                        board[rookPos[0] + 2][rookPos[1]].setPiece(null);
                        clickedPiece.setMoveCounter(1);

                    }

                    //if right castle

                    if (rookPos[0] > kingPos[0])
                    {
                        int count = 1;

                        while (kingPos[0] + count != rookPos[0])
                        {
                            if (board[kingPos[0] + count][kingPos[1]].getPiece() != null)
                            {
                                return;
                            }
                            count++;
                        }

                        //move king 2 squares to the right
                        castlingMode = true;
                        board[kingPos[0] + 2][kingPos[1]].setPiece(clickedPiece);
                        clickedPiece.setPosition(new int[] {kingPos[0] + 2, kingPos[1]});
                        board[kingPos[0] + 1][kingPos[1]].setPiece(temp.getPiece());
                        temp.getPiece().setPosition(new int[] {kingPos[0] + 1, kingPos[1]});

                        board[kingPos[0]][kingPos[1]].setPiece(null);
                        board[rookPos[0]][rookPos[1]].setPiece(null);
                        clickedPiece.setMoveCounter(1);
                    }
                }
            }


            //if clicked piece is not null, and clicked square is not null, then move the piece
            if (clickedPiece != null && temp.getPiece() != null && temp.getPiece().checkIfWhite() == clickedPiece.checkIfWhite() && castlingMode == false)
            {
                clickedPiece = temp.getPiece();
            }

            //to get the initial piece
            if (clickedPiece == null && clickedSquare == null && temp.getPiece() != null && temp.getPiece().checkIfWhite() == user.isWhite)
            {   
                clickedPiece = temp.getPiece();
            }

        } 
    }
    /**
     * Event to select pieces. first clicked save in ClickedPiece, 2nd in ClickedSquare
     * if ClickedPiece is not null, and ClickedSquare is not null, then move the piece to the square
     * if clickedPiece and clickedSquare is null, then set clickedPiece to the piece clicked
     * if clickedPiece is not null, and clickedSquare is null, then set clickedSquare to the square clicked
     * if piece clicked is king, check for valid castling, if next click is rook then castle
     * @param e mouse event
     */
    
    //draws the board and pieces
    public void draw_default()
    {
        for (int i = 0; i < 14; i++)
        {
            for (int j = 0; j <14; j++)
            {
                board[i][j].drawSquare();
                if (board[i][j].getPiece() != null)
                {
                    board[i][j].getPiece().drawSprite(this);
                }
            }
        }
    }
    /**
     * Draw all elements in the game by current frame.
     */



    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        background(125); 
        draw_default();

        int currentFrameTime = millis();
        int deltaTime = currentFrameTime - lastFrameTime;
        lastFrameTime = currentFrameTime;

        //unpauses the clock for whichever player's turn it is
        if (user.isTurn(moveCounter))
        {
            cpuClock.setPaused();
            playerClock.setUnpaused();
        }
        else
        {
            cpuClock.setPaused();
            playerClock.setUnpaused();
        }



        if (castlingMode)
        {
            draw_default();
            castlingMode = false;
        }


        if (canCastleLeft)
        {
            int[] pos =  rookLeft.getPosition();

            board[pos[0]][pos[1]].fillSquare(0);
            rookLeft.drawSprite(this);
        }


        if (canCastleRight)
        {
            int[] pos =  rookRight.getPosition();

            board[pos[0]][pos[1]].fillSquare(0);
            rookRight.drawSprite(this);
        }





        // handle draw possdible moves
        if (clickedPiece != null) {
            justMoved = false;
            int[] pos = clickedPiece.getPosition();
            board[pos[0]][pos[1]].fillSquare(1);
            clickedPiece.drawSprite(this);
            ArrayList<int[]> moves = clickedPiece.getMoves(board);
            for (int i = 0; i < moves.size(); i++) {
                
                int[] move = moves.get(i);
                boardPiece current = board[move[0]][move[1]];
                chessPiece current_piece = board[move[0]][move[1]].getPiece();
                if (current.getPiece() != null && current_piece.checkIfWhite() != clickedPiece.checkIfWhite()) {
                    current.fillSquare(2);
                    current_piece.drawSprite(this);
                }
                if (current.getPiece() != null && current_piece.checkIfWhite() == clickedPiece.checkIfWhite()) {
                    current.fillSquare(0);
                    current_piece.drawSprite(this);
                }
                if (current.getPiece() == null) {
                    current.fillSquare(0);
                }
            }
        }


        // event to move the pieces 
        if (clickedPiece != null && clickedSquare != null)
        {
            justMoved = false;
            inCheck = false;
            int[] pos = clickedPiece.getPosition();
            int[] new_pos = clickedSquare.getPosition();
            
            int[] before = pos;
            int[] after = new_pos;

            justMoved = true;
 
            chessPiece temp = clickedSquare.getPiece();

            board[new_pos[0]][new_pos[1]].setPiece(clickedPiece);
            board[pos[0]][pos[1]].setPiece(null);
            clickedPiece.setPosition(new int[] {new_pos[0], new_pos[1]}); 
            
            if (temp != null)
            {
                temp.setPosition(new int[] {-1, -1});
            }

            playerClock.addIncrement();

            clickedPiece = null;
            clickedSquare = null;
            boolean ok = true;
            if (bot.KingAttacked(board) && !bot.CheckMated(board) && !bot.StaleMated(board))
            {
                inCheck = true;
                inCheckPos = bot.getKing(board).getPosition();
            }

            if (bot.StaleMated(board))
            {
                justMoved = false;
                draw = true;
                ok = false;
            }

            if (bot.CheckMated(board))
            {
                justMoved = false;
                playerWins = true;
                loserKing = bot.getKing(board);
                ok = false;
            }

            if (ok)
            {
                moveCounter++;
            }


        }



        if (!user.isTurn(moveCounter))
        {
            justMoved = false;
            inCheck = false;   
            chessPiece botpiece = bot.returnMove(board);
            int[] pos = botpiece.getPosition();

            Random rand = new Random();
            int max = botpiece.getMoves(board).size();
            int rand_id = rand.nextInt(max);
            int[] new_move = botpiece.getMoves(board).get(rand_id);

            //set current pos to nul
            chessPiece temp = board[new_move[0]][new_move[1]].getPiece();
            board[pos[0]][pos[1]].setPiece(null);
            board[new_move[0]][new_move[1]].setPiece(botpiece);
            botpiece.setPosition(new int[] {new_move[0], new_move[1]});

            justMoved = true;
            before = pos;
            after = new_move;
            
            if (temp != null)
            {
                temp.setPosition(new int[] {-1, -1});
            }
            cpuClock.addIncrement();
            if (user.KingAttacked(board))
            {
                inCheck = true;
                inCheckPos = user.getKing(board).getPosition();
            }

            if (user.CheckMated(board))
            {
                cpuWins = true;
                loserKing = user.getKing(board);
            }
            
            if (user.StaleMated(board))
            {
                draw = true;
            }

            moveCounter++;
        }



        if (playerResigned)
        {
            justMoved = false;
            stopClock();
            fill(255);
            textSize(15);
            text("You resigned",680,350);   
            clickedPiece = null;
            clickedSquare = null;
        }

        if (playerClock.isOver())
        {
            cpuWins = true;
        }

        if (cpuClock.isOver())
        {
            playerWins = true;
        }

        if (draw)
        {
            stopClock();
            clickedPiece = null;
            clickedSquare = null;
            fill(255);
            textSize(20);
            text("Draw", 690, 300);
        }

        if (playerWins && !playerClock.isOver())
        {
            fill(255);
            textSize(10);
            text("You won by checkmate", 675, 250);
            text("Press 'r' to restart", 690, 350);
            stopClock();
            
            int[] pos = loserKing.getPosition();
            board[pos[0]][pos[1]].fillSquare(3);
            board[pos[0]][pos[1]].getPiece().drawSprite(this);

            ArrayList<chessPiece> attackingPieces = loserKing.getAttackingPieces();

            for (int i = 0; i < attackingPieces.size(); i++)
            {
                int[] pos2 = attackingPieces.get(i).getPosition();
                board[pos2[0]][pos2[1]].fillSquare(2);
                board[pos2[0]][pos2[1]].getPiece().drawSprite(this);
            }

        }

        if (playerWins && playerClock.isOver())
        {
            fill(255);
            textSize(10);
            text("You won on time", 690, 300);
            stopClock();
        }

        if (cpuWins && !playerClock.isOver())
        {
            fill(255);
            textSize(10);
            text("You lost by checkmate", 675, 250);
            text("Press 'r' to restart", 690, 350);
            stopClock();
            
            int[] pos = loserKing.getPosition();
            board[pos[0]][pos[1]].fillSquare(3);
            board[pos[0]][pos[1]].getPiece().drawSprite(this);

            ArrayList<chessPiece> attackingPieces = loserKing.getAttackingPieces();

            for (int i = 0; i < attackingPieces.size(); i++)
            {
                int[] pos2 = attackingPieces.get(i).getPosition();
                board[pos2[0]][pos2[1]].fillSquare(2);
                board[pos2[0]][pos2[1]].getPiece().drawSprite(this);
            }
        }

        if (cpuWins && cpuClock.isOver())
        {
            fill(255);
            textSize(10);
            stopClock();
            text("You lost on time", 690, 300);
        }

        if (inCheck)
        {
            board[inCheckPos[0]][inCheckPos[1]].fillSquare(3);
            board[inCheckPos[0]][inCheckPos[1]].getPiece().drawSprite(this);
            if (invalidMove)
            {
                fill(255);
                textSize(20);
                text("Invalid move", 690, 300);
            }
            else
            {
                fill(255);
                textSize(20);
                text("Check!", 690, 300);
            }

        }

        if (justMoved) {
            board[before[0]][before[1]].fillSquare(1);
            board[after[0]][after[1]].fillSquare(1);
            board[after[0]][after[1]].getPiece().drawSprite(this);
        }



        fill(255);
        textSize(20);
        text(playerClock.toString(), 700, 650);
        text(cpuClock.toString(), 700, 100);



    }
    /**
     * The draw method, called every frame
     * Used to update the state of the game
     * handles the execution of endgame states.
     */

    public static void main(String[] args) {
        PApplet.main("XXLChess.App");

    }

}
