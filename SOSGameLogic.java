package sosGame_project;

import javax.swing.JOptionPane;

public class SOSGameLogic {
	
	private char[][] board;    // 2D array where each cell contains a token
    private int boardSize;     // size of game board
    private boolean isSimpleGame;   // references simple game mode
    private boolean playerTurn;  // references which player's turn
    private boolean gameOver;    // references when game has ended 

    // Constructor to initialize the state of the game
    public SOSGameLogic(int boardSize, boolean isSimpleGame) {
        this.boardSize = boardSize;
        this.isSimpleGame = isSimpleGame;  
        this.playerTurn = true; // blue player starts
        this.gameOver = false;  // must be false to begin game
        initializeBoard();  // call method to initialize the empty board
    }

    // method to initialize an empty board
    private void initializeBoard() {
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // make a move and return if an SOS sequence was created
    // check for a valid move
    public boolean makeMove(int row, int col, char token) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize || board[row][col] != ' ' || gameOver) {
            return false; // invalid move
        }
        // board stores the token "S" or "O' in the cell
        board[row][col] = token;
        
        playerTurn = !playerTurn;
        
        // check if board is full
        if (isBoardFull()) {
            gameOver = true;  // game is over 
        }
        
        return true;  // return updated sequence by player
    }

    // check if the board is full
    private boolean isBoardFull() {
        // returns false if there are empty cells
    	for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        // returns true if there are no empty cells
    	return true;
    }

    // get the "winner"
    public String getWinner() {
        return "Game Over!";  // for now, just returns "Game Over!"
    }

    // reset the game
    public void resetGame() {
        initializeBoard();   // initialize an empty board
        playerTurn = true;   // initiate blue player turn
        gameOver = false;   // the game is not over, it has just begun
    }

    // sets the game mode to either simple or general
    public void setGameMode(boolean isSimpleGame) {
        // simple game mode if true, otherwise sets to general game mode
    	this.isSimpleGame = isSimpleGame;
        resetGame();  // resets game when mode is chosen
    }

    // Getters
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }
    
    public boolean isSimpleGame() {
        return isSimpleGame;
    }
}

