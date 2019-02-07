
public class Board{
	
	private char[][] board;
	private char player1 = 'o';
	private char player2 = 't';

	//creates a default board of size 7 columns*6 rows
	public Board() {
		this.board = new char[6][7];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j< board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	//creats a board of size row*col
	public Board(int row, int col){
		if (row <= 0 || col <= 0) {
			this.board = new char[6][7];
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j< board[i].length; j++) {
					board[i][j] = ' ';
				}
			}
		}
		else {
			this.board = new char[row][col];
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j< board[i].length; j++) {
					board[i][j] = ' ';
				}
			}
		}
		System.out.println(board.length);
	}

	
	public int getNumRows(){
		return board.length;
	} 

	
	public int getNumCols() {
		return board[0].length;
	};

	
	public char getPlayerOne() {
		return player1;
	}

	
	public char getPlayerTwo() {
		return player2;
	}

	
	public void setPlayerOne(char o) {
		if (o == player2) {
			player1 = 'o';
			player2 = 't';
		}
		this.player1 = o;
	}

	
	public void setPlayerTwo(char t) {
		if (t == player1) {
			player1 = 'o';
			player2 = 't';
		}
		else
		this.player2 = t;
	}

	//returns the char representing token at location row,col,returns'\0' if indices are invalid
	public char getToken(int row, int col) {
		
		if (row >board.length-1 || col > board[0].length-1 || row < 0 || col < 0) {
			return '\0';
		}
		else {return board[row][col];}
	}
	//returns true if a token is still able to placed onto the board, false otherwise
	public boolean canPlay() {
		boolean a = false;
		for (int i = 0; i<board.length;i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {a = true;}

			}
			
		}
		if (a == false) {return false;}
		else {return true;}
	}

	//print the board
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				System.out.print("[" + board[i][j] + "]" + '\t');
			}
			System.out.println();
		}
		
	}

	//places the appropriate token for player p in column c. returns true if successful, false otherwise
	public boolean play(int p, int c) {
		boolean b = true;

		if (c>board[0].length-1 || c < 0) {
			b = false;
			return b;
			
		}
		if (p<1 || (1<p && p < 2) || p > 2) {
			b = false;
			return b;
			
		}
		
			if (p == 1) {
				int i = board.length - 1;
				while (board[i][c] != ' ') {
					i --;
					if (i < 0) {b = false; break;}
				}
				if (i>= 0) this.board[i][c] = player1;
				
				
			}
			if (p == 2) {
				int i = board.length - 1;
				while (board[i][c] != ' ') {
					i --;
					if (i < 0) {b = false; break;}
				}
				if (i>= 0) board[i][c] = player2;
			}
			return b;
		
	}
	//returns either the ID of the player who has won (1or2) OR 0 if the game has ended in a tie OR -1 nobody has won yet
	public int isFinished() {
		boolean c = false;
		char pl = ' ';
		for (int i = 0; i< board.length; i++) {
			int j = 0;
			while ((j+3)<board[0].length) {
				if (board[i][j] != ' ' && board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2] && board[i][j+2] == board[i][j+3]) {
					c = true;
					if (board[i][j] == player1) pl = player1;
					else pl = player2;
				}
				j++;
			}

		}
		for (int a = 0; a < board[0].length; a ++) {
			int b = 0;
			while ((b+3)<board.length) {
				if (board[b][a] != ' ' && board[b][a] == board[b+1][a] && board[b+1][a] == board[b+2][a] && board[b+2][a] == board[b+3][a]) {
					c = true;
					if (board[b][a] == player1) pl = player1;
						else pl = player2;
					}
					b++;
			}
		}
		for (int e = 0; e < board.length-3;e++) {
			int f = 0;
			while ((f+3)<board[0].length) {
				if (board[e][f] != ' ' && board[e][f] == board[e+1][f+1] && board[e+1][f+1] == board[e+2][f+2] && board[e+2][f+2] == board[e+3][f+3]) {
					c = true;
					if (board[e][f] == player1) pl = player1;
					else pl = player2;
				}
				f++;
			}
		}
		for (int p = 0; p < board.length - 3; p++) {
			int q = 3;
			while (q<board[0].length) {
				if (board[p][q] != ' ' && board[p+1][q-1] == board[p][q] && board[p+1][q-1] == board[p+2][q-2] && board[p+2][q-2] == board[p+3][q-3]) {
					c = true;
					if (board[p][q] == player1) pl = player1;
					else pl = player2;
				}
				q++;
			}
		}
		if (c == true) {
			if (pl == player1) return 1;
			else return 2;
		}
		else if (this.canPlay() == false) { return 0;}
		else return -1;

	}

}
