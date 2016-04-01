/*
 * Kyle Bergman
 * Zappos Technical Challenge
 * The GameBoard Class represents a Tic-Tac-Toe board that may be used to play Tic-Tac-Toe
 */

public class GameBoard {
	private char[][] myBoard;
	private final int GAME_DIMENSION = 3;
	
	
	/*This is the default constructor for the game board. This constructor
	 * initializes a basic game board of dimension 3x3. 
	 */
	public GameBoard() {
		myBoard = new char[GAME_DIMENSION][GAME_DIMENSION];
		for(int i = 0; i < GAME_DIMENSION; i++) {
			for(int j = 0; j < GAME_DIMENSION; j++) {
				myBoard[i][j] = 'm';			
			}
		}
	}
	
	/* The getMove function will return what character is in that position
	 * on the game board. If incorrect parameters are passed, the character 'k'
	 * will be returned and handled by the TicTacToe class.
	 */
	public char getMove(int row, int col) {
		if(row < 0 || row > 2 || col > 2 || col < 0) {
			return 'k';
		} else {
			return myBoard[row][col];
		}
	}
	
	/* places the correct character in the correct position on the 
	 * game board.
	 */
	public void setMove(char whoseTurn, int row, int col) {
		myBoard[row][col] = whoseTurn;
	}
}
