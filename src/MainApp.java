import java.util.Scanner;

public class MainApp {

	static String board[][];
	static boolean player1 = true, player2 = false, newGame = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// declare variable
		int boardLength = 0;
		boolean stillContinue = true;
		String p1 = "X", p2 = "O", winnerHorizontal = "", winnerDiagonal = "";

		// declare board length
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter length of tictacgame : ");
		boardLength = scanner.nextInt();
		board = new String[boardLength][boardLength];
		
		if(newGame) {
			drawNewBoard(boardLength);
			newGame=false;
		}
		
		drawBoard(boardLength);

		// start game with max turn as long stillContinue is true
		while (stillContinue) {
			if (player1) {
				pickPosition(p1, boardLength);
			} else if (player2) {
				pickPosition(p2, boardLength);
			}
			drawBoard(boardLength);
			
			winnerHorizontal = checkWinner(boardLength);
			winnerDiagonal = checkWinnerDiagonal(boardLength);
			
			if (winnerHorizontal.equals("p1")) {
				System.out.println("P1 WINNER x");
				stillContinue = false;
			} else if (winnerHorizontal.equals("p2")) {
				System.out.println("P1 WINNER x");
				stillContinue = false;

			} else if (winnerHorizontal.equals("draw")) {
				System.out.println("DRAW x");
				stillContinue = false;
			}
			
			if(stillContinue) {
				if (winnerDiagonal.equals("p1")) {
					System.out.println("P1 WINNER");
					stillContinue = false;
				} else if (winnerDiagonal.equals("p2")) {
					System.out.println("P1 WINNER");
					stillContinue = false;

				} else if (winnerDiagonal.equals("draw")) {
					System.out.println("DRAW");
					stillContinue = false;
				}
			}
		}
		
		scanner.close();

	}

	// create position method
	public static String pickPosition(String player, int boardLength) {

		Scanner scanner = new Scanner(System.in);
		int x = 0, y = 0;
		String position = "";
		boolean valid = false;

		while (!valid) {
			// let user input their position
			if (player.equals("X")) {
				System.out.print("player 1 input x :");
				x = scanner.nextInt();
				System.out.print("player 1 input y :");
				y = scanner.nextInt();
				position = x + "," + y;
				valid = positionEmpty(position, boardLength);
				if (valid) updateBoard("X", position, boardLength);
				
				player1 = false;
				player2 = true;
			} else {
				System.out.print("player 2 input x :");
				x = scanner.nextInt();
				System.out.print("player 2 input y :");
				y = scanner.nextInt();
				position = x + "," + y; 
				valid = positionEmpty(position, boardLength);
				if (valid) updateBoard("O", position, boardLength);
				
				player1 = true;
				player2 = false;
			}

		}

//		scanner.close();
		return null;

	}

	public static boolean positionEmpty(String position, int boardLength) {
		// check if position is empty
		int x = Integer.valueOf(position.split(",")[0]);
		int y = Integer.valueOf(position.split(",")[1]);

		if (x > boardLength) {
			System.out.println("=================== !! invalid data : put x lower than : " + boardLength);
			return false; 
		}
		
		if (y > boardLength) {
			System.out.println("=================== !! invalid data : put y lower than : " + boardLength);
			return false; 
		}
		
		if (board[x][y] != "-") {
			System.out.println("=================== !! invalid data : data already signed ");
			return false;
		}

		return true;

	}

	public static void updateBoard(String player, String position, int boardLength) {
		int x = Integer.valueOf(position.split(",")[0]);
		int y = Integer.valueOf(position.split(",")[1]);
		
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				if(i==x && y==j) board[x][y] = player;
			}
		}

	}

	public static void drawBoard(int boardLength) {
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
	}

	public static String checkWinner(int boardLength) {
		int jumlahP1 = 0;
		int jumlahP2 = 0;
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				if (board[i][j] == "X") {
					jumlahP1 += 1;
				}
				else if (board[i][j] == "O") {
					jumlahP2 += 1;
					break;
				}
			}
		}

		if (jumlahP1 + jumlahP2 == (boardLength * boardLength)) {
			return "draw";
		} else if (jumlahP1 == boardLength) {
			return "p1";
		} else if (jumlahP1 == boardLength) {
			return "p2";
		}

		return "continue";
	}
	
	// create method for check winner on diagonal "-"
	public static String checkWinnerDiagonal(int boardLength) {
		int jumlahP11 = 0;
		int jumlahP22 = 0;
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				if (board[i][j] == "X") {
					jumlahP11 += 1;
				}
				else if (board[i][j] == "O") {
					jumlahP22 += 1;
					break;
				}
			}
		}

		if (jumlahP11 + jumlahP22 == (boardLength * boardLength)) {
			return "draw";
		} else if (jumlahP11 == boardLength) {
			return "p1";
		} else if (jumlahP11 == boardLength) {
			return "p2";
		}

		return "continue";
	}
	
	// create method for draw "-"
	public static void drawNewBoard(int boardLength) {
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				board[i][j] = "-";
			}
		}
	}

}
