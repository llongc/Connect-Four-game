public class ConnectFour{
	
	
	public static void main(String[] args){
		
		//Create new board object
		//Board b = new Board();
		//Set player tokens for player 1 and player 2
		
		ChipColor red = ChipColor.RED;
		ChipColor blue = ChipColor.BLUE;
		//CFGUI cfgui = new CFGUI(b, red, blue);
		//Create Player objects
		//Note, the code below works because of the interface Player. All classes that "implement" Player can be
		// typed as Player. Makes switching from Human to AI Players really easy. For a challenge, you might
		// consider:
		//		1. asking the user whether he/she wants to play against a human or a computer
		//		2. implementing multiple AI players (easy, med, hard) that the user can choose to play against
		System.out.println("Please enter the row: ");
		int row = IO.readInt();
		System.out.println("Please enter the col: ");
		int col = IO.readInt();
		Board b = new Board();
		if (row > 0 && col > 0) b = new Board(row, col);
		b.setPlayerOne('o');
		b.setPlayerTwo('t');
		CFGUI cfgui = new CFGUI(b, red, blue);
		//Player p1 = new HumanPlayer(1,row,col);
		Player p2 = new HumanPlayer(2,row,col); //comment this line when testing AIPlayer
		
		Player p1 = new AIPlayer(1,row,col); //uncomment this line when testing AIPlayer
		
		 
		int i = 0;
		//Print your empty board
		do {b.printBoard();
		
		
		//WHILE the board is still playable
		flag:while (b.canPlay() == true && b.isFinished() != 0){
		while (b.canPlay() == true && b.isFinished() != 0) {
			int col1 = p1.playToken();
			p2.lastMove(col1);
			System.out.println("col1" + col1);			
			b.play(1,col1);
			cfgui.redraw();
			b.printBoard();
			if (b.isFinished() == 1 || b.isFinished() == 0) break flag;
			int col2 = p2.playToken();
			System.out.println("col2" + col2);
			p1.lastMove(col2);
			b.play(2,col2);
			cfgui.redraw();
			b.printBoard();
			if (b.isFinished() == 2 || b.isFinished() == 0) break flag;
			
		}
	}
		int end = b.isFinished();
		if ( end == 1) {
			System.out.println("Congratulation!!! Player 1 win!!!!!!!");
			cfgui.gameOver(1);
			cfgui.close();
		}
		else if (end == 2) {
			System.out.println("Congratulation!!! Player 2 win!!!!!!!");
			cfgui.gameOver(2);
			cfgui.close();
		}
		else if (end == 0) {
			System.out.println("OH NO!!!!!!! No one wins!!!!!!!");
			cfgui.gameOver(0);
			cfgui.close();
		}
		else {
			System.out.println("Game continues!");
		}
		System.out.println("do you want to play again? Enter 1 to play again, enter other will exit the game.");
		i = IO.readInt();
		if (i == 1) {
			p1.reset();
			p2.reset();
			if (row > 0 && col > 0) b = new Board(row, col);
			else b = new Board();
			cfgui = new CFGUI(b, red, blue);
		}
		
	} while (i == 1);
		
		//	get a column to play from player 1
		//	play that token on the board
		//  print the board
		//		has anyone won yet?
		// do the above for player 2
		
		// Get the status code from the board (isFinished())
		
		// Print out the results of the game
		
		
	}
	
}