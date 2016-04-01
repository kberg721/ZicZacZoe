/* Kyle Bergman
 * Zappos Technical Challenge
 * This is the TicTacToe class. It runs a game of ZicZacZoe moderated by the one and
 * only Zony Hzieh. Only the best can survive.
 */

import java.util.Scanner;


public class TicTacToe {
	private GameState state;
	private GameBoard board;
	
	public enum GameState {
		IN_PROGRESS,
		Z_WON,
		O_WON,
		DRAW
	}
	
	public TicTacToe() {
		state = GameState.IN_PROGRESS;
		board = new GameBoard();
	}
	
	/* MakeMove is a function that attempts to make a move on the game board.
	 * Should a move not be possible (it is taken), makeMove will return false.
	 */
	public boolean makeMove(char whoseTurn, int row, int col) {
		if(board.getMove(row, col) != 'm' && board.getMove(row, col) != 'k') {
			return false;
		} else {
			board.setMove(whoseTurn, row ,col);
			return true;
		}
	}
	
	//This function will make a move on behalf of Zony.
	public void makeRobotMove() {
		System.out.println("IT IS MY TURN, THIS IS MY MOVE");
		System.out.println();
		boolean madeMove = false;
		do {
			int randRow = (int)(Math.random() * 3);
			int randCol = (int)(Math.random() * 3);
			madeMove = makeMove('z', randRow, randCol);
		}while(!madeMove);
	}
	
	/* The checkForWinner function will check the game board for a winner
	 * or a draw. If none of those outcomes has happened, the game is still in
	 * progress.
	 */
	public GameState checkForWinner() {
		boolean inProgress = false;
		//check rows for winner. This will also do a sweep to see if there
		//are any open squares and that the game is in progress.
		for(int i = 0; i < 3; i++) {
			char toCheck = board.getMove(i, 0);
			if(toCheck != 'm') {
				boolean hasWon = true;
				for(int j = 0; j < 3; j++) {
					if(board.getMove(i, j) == 'm') {
						inProgress = true;
					}
					if(board.getMove(i, j) != toCheck) {
						hasWon = false;
						break;
					}
				}
				if(hasWon) {
					if(toCheck == 'z') {
						return GameState.Z_WON;
					} else {
						return GameState.O_WON;
					}
				}
			} else {
				inProgress = true;
			}
		}
		
		//check columns for winner
		for(int i = 0; i < 3; i++) {
			char toCheck = board.getMove(0, i);
			if(toCheck != 'm') {
				boolean hasWon = true;
				for(int j = 0; j < 3; j++) {
					if(board.getMove(j, i) != toCheck) {
						hasWon = false;
						break;
					}
				}
				if(hasWon) {
					if(toCheck == 'Z') {
						return GameState.Z_WON;
					} else {
						return GameState.O_WON;
					}
				}
			}
		}
		
		//check main diagonal
		char diagToCheck = board.getMove(0, 0);
		if(diagToCheck != 'm') {
			boolean hasWon = true;
			for(int j = 0; j < 3; j++) {
				if(board.getMove(j, j) != diagToCheck) {
					hasWon = false;
					break;
				}
			}
			if(hasWon) {
				if(diagToCheck == 'z') {
					return GameState.Z_WON;
				} else {
					return GameState.O_WON;
				}
			}
		
		}
		
		//check anti-diagonal
		char antiDiagonalToCheck = board.getMove(0, 2);
		if(antiDiagonalToCheck != 'm') {
			boolean hasWon = true;
			if(board.getMove(1, 1) != antiDiagonalToCheck || board.getMove(2, 0) != antiDiagonalToCheck) {
				hasWon = false;
			}
			if(hasWon) {
				if(antiDiagonalToCheck == 'z') {
					return GameState.Z_WON;
				} else {
					return GameState.O_WON;
				}
			}
		}
		
		if(inProgress) {
			return GameState.IN_PROGRESS;
		}		
		
		return GameState.DRAW;
	}
	
	//prints the current state of the board.
	public void printBoard() {
		System.out.println("  0 1 2");
		for(int i = 0; i < 3; i++) {
			System.out.print(i + " ");
			for(int j = 0; j < 3; j++) {
				char toPrint = board.getMove(i, j);
				if(toPrint == 'm') {
					System.out.print("_ ");
				} else {
					System.out.print(toPrint + " ");
				}
			}
			System.out.println();
		}
	}
	
	//Generates commentary for Zony based on how many turns have passed.
	public void printThreat(int playCount) {
		switch(playCount) {
			case 5: System.out.println("HOW HAS NO ONE WON YET, THIS IS PITIFUL");
					System.out.println();
					break;
			case 7: System.out.println("STILL NO CHAMPION, ZONY IS NOT PLEASED");
					System.out.println();
					break;
			case 8: System.out.println("LOOKS LIKE THERE IS ONE MOVE LEFT, ABSOLUTELY NO PRESSURE");
					System.out.println();
					break;
		}
	}
	
	public void playGame(char first) {
		System.out.println("Z IS THE ZUPERIOR LETTER, SO IT WILL GO FIRZT");
		System.out.println();
		char whoseTurn = first;
		Scanner scan = new Scanner(System.in);
		
		int playCount = 0;
		/*undergo the game, printing the current state of the game to the users
		 * and asking for a row and column of where they would like to place their
		 * tile. If there is a winner, it will be announced.
		 */
		do {
			printThreat(playCount);
			System.out.println("THIS IS THE CURRENT GAME BOARD");
			printBoard();
			System.out.println();
			System.out.println("IT IS " + whoseTurn + "'s TURN, PLACE YOUR TILE AND BE RUTHLESS.");
			int row = scan.nextInt();
			int col = scan.nextInt();
			System.out.println();
			if(makeMove(whoseTurn, row, col) == false) {
				System.out.println("THIS SQUARE IS TAKEN, CAN'T YOU SEE THE GAME BOARD?");
				System.out.println("ENTER SOMETHING MORE PALATABLE, OR WE WILL BE HERE ALL DAY");
				System.out.println();
			} else {
				if(whoseTurn == 'z') {
					whoseTurn = 'o';
				} else {
					whoseTurn = 'z';
				}
			}
			state = checkForWinner();
			playCount++;
		} while(state == GameState.IN_PROGRESS);
		printBoard();
		
		//Print the results back to the users
		if(state == GameState.O_WON) {
			System.out.println("O IZ THE ULTIMATE CHAMPION");
		} else if(state == GameState.Z_WON) {
			System.out.println("Z IZ THE ULTIMATE CHAMPION");
		} else if(state == GameState.DRAW) {
			System.out.println("EVERYONE IZ EQUALLY BAD!!! IT'S A DRAW");		
		}
	}
	
	//plays the game with Zony as a player. I realize much of the code is copied but I went
	//with quick coding over modularization.
	public void playRobotGame(char first) {
		System.out.println("I AM Z, I WILL GO FIRST.");
		System.out.println();
		
		char whoseTurn = first;
		Scanner scan = new Scanner(System.in);
		
		int playCount = 0;
		/*undergo the game, printing the current state of the game to the users
		 * and asking for a row and column of where they would like to place their
		 * tile. If there is a winner, it will be announced.
		 */
		do {
			printThreat(playCount);
			System.out.println("THIS IS THE CURRENT GAME BOARD");
			printBoard();
			System.out.println();
			if(whoseTurn == 'z') {
				makeRobotMove();
				if(whoseTurn == 'z') {
					whoseTurn = 'o';
				} else {
					whoseTurn = 'z';
				}
			} else {
				System.out.println("IT IS YOUR TURN, PLACE YOUR TILE AND BE RUTHLESS.");
				int row = scan.nextInt();
				int col = scan.nextInt();
				System.out.println();
				if(makeMove(whoseTurn, row, col) == false) {
					System.out.println("THIS SQUARE IS TAKEN, CAN'T YOU SEE THE GAME BOARD?");
					System.out.println("ENTER SOMETHING MORE PALATABLE, OR WE WILL BE HERE ALL DAY");
					System.out.println();
				} else {
					if(whoseTurn == 'z') {
						whoseTurn = 'o';
					} else {
						whoseTurn = 'z';
					}
				}
			} 
			state = checkForWinner();
			playCount++;
		} while(state == GameState.IN_PROGRESS);
		printBoard();
		
		//Print the results back to the users
		if(state == GameState.O_WON) {
			System.out.println("O IZ THE ULTIMATE CHAMPION");
		} else if(state == GameState.Z_WON) {
			System.out.println("Z IZ THE ULTIMATE CHAMPION");
		} else if(state == GameState.DRAW) {
			System.out.println("EVERYONE IZ EQUALLY BAD!!! IT'S A DRAW");		
		}
	}
	
	
	public static void main(String args[]) {
		TicTacToe newGame = new TicTacToe();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("WELCOME TO THE GLORIOUS WORLD OF ZIC ZAC ZOE. IT IZ MUCH LIKE");
		System.out.println("TIC TAC TOE BUT ONLY PEOPLE AT ZAPPOS PLAY IT. IT IZ MUCH COOLER BECAUZE WE");
		System.out.println("USE Z'S IN THE TITLE.");
		System.out.println();
		System.out.println("I WILL BE YOUR GUIDE AND ZHINING LIGHT, THE MASTERFUL AND TALENTED ZONY");
		System.out.println(" HZIEH, THE ZICZACZOE ROBOT.");
		System.out.println("WOULD YOU LIKE TO PLAY MULTIPLAYER? Y OR N");
		String answer = scan.nextLine();
		if(answer.equals("Y")) {
			newGame.playGame('z');
		} else {
			newGame.playRobotGame('z');
		}
	}
}
























