public class HumanPlayer implements Player{
//do not change the line above	
	
	//Define your fields here
	private int playerID;
	private int row;
	private int col;
	private Board board;
	private int lastMove;

	
	
	//constructor takes the player id for this player, and the number of 
	// rows and columns of the board we're playing on
	public HumanPlayer(int playerID, int row, int col){
		while (playerID != 1 && playerID != 2) {
			System.out.println("Not enter the right ID, plaese enter again: ");
			playerID = IO.readInt();
		}
		this.playerID = playerID;
		while (row <= 0 || col <= 0) {
			System.out.println("Not enter the right number of row and column, please enter again: ");
			row = IO.readInt();
			col = IO.readInt();
		}
		this.row = row;
		this.col = col;
		this.board = new Board(row, col);
	}
	
	//used to notify your AI player of the other players last move
	public void lastMove(int c) {
		this.lastMove = c;
		if (playerID == 1) {
			this.board.play(2, c);
		}
		else {
			this.board.play(1, c);
		}
		System.out.println("The other player drop in " + c);

		
	}
	
	//returns column of where to play a token
	public int playToken(){
		System.out.println("Enter the column you want to input: ");
		int a = IO.readInt();

		
		while (this.board.play(playerID, a) == false) {
			System.out.println("Wrong column, please enter again: ");
			a = IO.readInt();
		}

		
		System.out.println("player1************");
		board.printBoard();
		return a;
	}
	
	//get this player's id
	public int getPlayerID(){
		return playerID;
	}
	
	//resets the state of the player in preparation for a new game
	public void reset(){
		this.row = row;
		this.col = col;
		Board newBoard = new Board(row, col);
		this.board = newBoard;

		
	}

	
}