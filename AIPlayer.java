
public class AIPlayer implements Player{
//do not change the line above	
	
	//Define your fields here
	private int playerID;
	private Board board;
	private int row;
	private int col;
	private int lastMove;
	private int[][] b;
	
	
	//constructor takes the player id for this player, and the number of 
	// rows and columns of the board we're playing on
	public AIPlayer(int playerID, int row, int col){
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
		this.b = new int[row][col];
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j< b[i].length; j++) {
				b[i][j] = 0;
			}
		}
	}
	
	//used to notify your AI player of the other players last move
	public void lastMove(int c) {

		

	
		this.lastMove = c;
		if (playerID == 1) {
			this.board.play(2, c);
			//set value in the board

			
				int i = b.length - 1;
				while (b[i][c] != 0) {
					i --;
					if (i < 0) {break;}
				}
				if (i>= 0) b[i][c] = 2;


			}
		
		else {
			this.board.play(1, c);

			//set value in the board


			int i = b.length - 1;
				while (b[i][c] != 0) {
					i --;
					if (i < 0) {break;}
				}
				if (i>= 0) b[i][c] = 1;



		}
		System.out.println("The other player drop in " + c);
	
	}
	
	//returns column of where to play a token
	public int playToken(){

		int a;
		System.out.println("lastMove" + lastMove);
		boolean isInBound = true;
		
			boolean isEmpty = false;
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[i].length; j++) {
					if (b[i][j] != 0) {
						isEmpty = true;
						break;
					}
					
				}
				
			}

		do {
			if (isEmpty == false ) {
				a = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
			} else {
				
				boolean is3 = false;
				int temp = -1;
				//if three discs connected(defense)
				//if in a horizontal line
				for (int i = 0; i< b.length; i++) {

					int j = 0;
					while ((j+2)<b[0].length) {
						if (b[i][j] != 0 && b[i][j] == b[i][j+1] && b[i][j+1] == b[i][j+2]) {
							
							if(playerID != b[i][j]){
								
								if ((j-1) != -1) {
									  if(b[i][j-1] == 0) {
										if ((i+1) == b.length) {
											is3 = true;
											temp = j-1;
											System.out.println("----------1" + temp + "         i: " + i);
										} else if (b[i+1][j-1] != 0) {
											is3 = true;
											temp = j-1;
											System.out.println("----------1" + temp + "         i: " + i);
										}
										
									}
								} 
								if ((j+3) != b[i].length) {
									 if(b[i][j+3] == 0) {
										if ((i+1) == b.length) {
											temp = j+3;
											is3 = true;
											System.out.println("----------1" + temp + "         i: " + i);
										} else if (b[i+1][j+3] != 0) {
											temp = j+3;
											is3 = true;
											System.out.println("----------1" + temp + "         i: " + i);
										}
										
									}
								}
							}

						
						}
						j++;
					}

				}

				//if in a vertical line
				for (int r = 0; r< b[0].length; r++) {
					int s = 0;
					while ((s+2)<b.length) {
						if (b[s][r] != 0 && b[s][r] == b[s+1][r] && b[s+1][r] == b[s+2][r]) {
							if(playerID != b[s][r]){
								if ((s-1) != -1) { 
										if(b[s-1][r] == 0 && b[0][r] == 0) {
										is3 = true;
										temp = r;
										System.out.println("----------1" + temp + "         s: " + s);
										}
								} 
							}

						}
						s++;
					}

				}

				//if in the diag \\\
				
				for (int e = 0; e < b.length-2;e++) {
					int f = 0;
					while ((f+2)<b[0].length) {
						if (b[e][f] != 0 && b[e][f] == b[e+1][f+1] && b[e+1][f+1] == b[e+2][f+2]) {
							if(playerID != b[e][f]) {
								if ((e-1) != -1){
									 	if((f-1) != -1 && b[e-1][f] != 0 && b[e-1][f-1] == 0) {
										is3 = true;
										temp = f-1;
										System.out.println("----------1" + temp + "         e: " + e);
										}
								} 
								if ((e+3) != b.length) {

									if((f+3) != b[0].length && b[e+3][f+3] == 0){
										if ((e+4) == b.length) {
											is3 = true;
											temp = f+3;
											System.out.println("----------1" + temp + "         e: " + e);
										} else if (b[e+4][f+3] != 0) {
											temp = f+3;
										is3 = true;
										System.out.println("----------1" + temp + "         e: " + e);
										}
									}	
								}
							}
							
						}
						f++;
					}
				}
				//if in the diag ///
				for (int p = 0; p < b.length - 2; p++) {
					int q = 2;
					while (q<b[0].length) {
						if (b[p][q] != 0 && b[p+1][q-1] == b[p][q] && b[p+1][q-1] == b[p+2][q-2]) {
							if (playerID != b[p][q]) {
								if ((p-1) != -1){
									if((q+1) != b[0].length && b[p][q+1] != 0 && b[p-1][q+1] == 0 ) {
										is3 = true;
										temp = q+1;
										System.out.println("----------1" + temp + "         p: " + p);
									}
								} 
								if ((q-3) != -1){

									if((p+3) != b.length && b[p+3][q-3] == 0) {
										if ((p+4) == b.length) {
											is3 = true;
											temp = q-3;
											System.out.println("----------1" + temp + "         p: " + p);
										} else if (b[p+4][q-3] != 0) {
												is3 = true;
												temp = q-3;
												System.out.println("----------1" + temp + "         p: " + p);
											
										}
									}
								}
							}
							
						}
						q++;
					
					}
				}

				//part2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				boolean is2 = false;
				int temp4 = -1;
				for (int i = 0; i< b.length; i++) {
					int j = 0;
					while ((j+1)<b[0].length) {
						if (b[i][j] != 0 && b[i][j] == b[i][j+1]) {
							if(playerID != b[i][j]){
								if ((j-1) != -1){
									if(b[i][j-1] == 0) {
											if ((i+1) == b.length) {
												is2 = true;
												temp4 = j-1;
												System.out.println("***********1" + temp4 + "         i: " + i);

											} else if (b[i+1][j-1] != 0) {
												is2 = true;
												temp4 = j-1;
												System.out.println("***********2" + temp4 + "         i: " + i);
											}
										
									}
								} 
								if ((j+2) != b[i].length){
									if(b[i][j+2] == 0) {
										if ((i+1) == b.length) {
											temp4 = j+2;
											is2 = true;
											System.out.println("***********3" + temp4 + "         i: " + i);
										} else if (b[i+1][j+2] != 0) {
											temp4 = j+2;
											is2 = true;
											System.out.println("***********4" + temp4 + "         i: " + i);
										}
										
									}
								}
							}

						}
						j++;
					}

				}
				//if in a vertical line
				for (int r = 0; r< b[0].length; r++) {
					int s = 0;
					while ((s+1)<b.length) {
						if (b[s][r] != 0 && b[s][r] == b[s+1][r]) {
							if(playerID != b[s][r]){
								if ((s-1) != -1){
									 	if (b[s-1][r] == 0 && b[0][r] == 0) {
										is2 = true;
										temp4 = r;
										System.out.println("***********5" + temp4 + "         s: " + s);
									}
								} 
							}

						}
						s++;
					}

				}

				//if in the diag \\\
				
				for (int e = 0; e < b.length-1;e++) {
					int f = 0;
					while ((f+1)<b[0].length) {
						if (b[e][f] != 0 && b[e][f] == b[e+1][f+1]) {
							if(playerID != b[e][f]) {
								if ((e-1) != -1){
									if((f-1) != -1 && b[e-1][f] != 0 && b[e-1][f-1] == 0) {
										is2 = true;
										temp4 = f-1;
										System.out.println("***********6" + temp4 + "         e: " + e);
									}
								} 
								if ((e+2) != b.length && (f+2) != b[0].length){
									if(b[e+2][f+2] == 0){
										if (e+3 == b.length) {
											temp4 = f+2;
											is2 = true;
											System.out.println("***********7" + temp4 + "         e: " + e);
										} else if (b[e+3][f+2] != 0) {
											temp4 = f+2;
											is2 = true;
										System.out.println("***********8" + temp4 + "         e: " + e);
										}
									}
							}
							
						}
						
					}
					f++;
				}
			}
				//if in the diag ///
				for (int p = 0; p < b.length - 1; p++) {
					int q = 1;
					while (q<b[0].length) {
						if (b[p][q] != 0 && b[p+1][q-1] == b[p][q]) {
							if (playerID != b[p][q]) {
								if ((p-1) != -1){
									if((q+1) != b[0].length && b[p][q+1] != 0 && b[p-1][q+1] == 0 ) {
										is2 = true;
										temp4 = q+1;
										System.out.println("***********9" + temp4 + "     p: " + p);
									}
								} 
								if ((q-2) != -1){
									if((p+2) != b.length && b[p+2][q-2] == 0) {
										if ((p+3) ==b.length) {
											is2 = true;
											temp4 = q-2;
											System.out.println("***********10" + temp4 + "       p: " + p);
										} else {
											if (b[p+3][q-2] != 0) {
												is2 = true;
												temp4 = q-2;
												System.out.println("***********11" + temp4 + "    p: " + p);
											}
										}
									}
								}
							}
							
						}
						q++;
					
					}
				}







				//offense part!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				boolean isme3 = false;
				int temp2 = -1;
				//if in a horizontal line
				for (int i = 0; i< b.length; i++) {
					int j = 0;
					while ((j+2)<b[0].length) {
						if (b[i][j] != 0 && b[i][j] == b[i][j+1] && b[i][j+1] == b[i][j+2]) {
							if(playerID == b[i][j]){
								if ((j-1) != -1){
								   if(b[i][j-1] == 0) {
									if ((i+1) == b.length) {
										isme3 = true;
										temp2 = j-1;
										System.out.println("|||||||||||||||||1" + temp2 + "         i: " + i);
									} else if (b[i+1][j-1] != 0) {
										isme3 = true;
										temp2 = j-1;
										System.out.println("|||||||||||||||||1" + temp2 + "         i: " + i);
									}
									}
								} 
								if ((j+3) != b[i].length){
								if(b[i][j+3] == 0) {
									if ((i+1) == b.length) {
										temp2 = j+3;
										isme3 = true;
										System.out.println("|||||||||||||||||1" + temp2 + "         i: " + i);
									} else if (b[i+1][j+3] != 0) {
										temp2 = j+3;
										isme3 = true;
										System.out.println("|||||||||||||||||1" + temp2 + "         i: " + i);
									}
									}
								}
							}

						}
						j++;
					}

				}
				//if in a vertical line
				for (int r = 0; r< b[0].length; r++) {
					int s = 0;
					while ((s+2)<b.length) {
						if (b[s][r] != 0 && b[s][r] == b[s+1][r] && b[s+1][r] == b[s+2][r]) {
							if(playerID == b[s][r]){
								if ((s-1) != -1){
								if(b[s-1][r] == 0 && b[0][r] == 0) {
									isme3 = true;
									temp2 = r;
									System.out.println("|||||||||||||||||1" + temp2 + "         s: " + s);
									}
								} 
							}

						}
						s++;
					}

				}

				//if in the diag \\\
				
				for (int e = 0; e < b.length-2;e++) {
					int f = 0;
					while ((f+2)<b[0].length) {
						if (b[e][f] != 0 && b[e][f] == b[e+1][f+1] && b[e+1][f+1] == b[e+2][f+2]) {
							if(playerID == b[e][f]) {
								if ((e-1) != -1){
								if((f-1) != -1 && b[e-1][f] != 0 && b[e-1][f-1] == 0) {
									isme3 = true;
									temp2 = f-1;
									System.out.println("|||||||||||||||||1" + temp2 + "         e: " + e);
								}
								} 
								if ((e+3) != b.length){
								if((f+3) != b[0].length && b[e+3][f+3] == 0){
									if ((e+4) == b.length) {
										temp2 = f+3;
										isme3 = true;
										System.out.println("|||||||||||||||||1" + temp2 + "         e: " + e);
									} else if (b[e+4][f+3] != 0) {
										temp2 = f+3;
									isme3 = true;
									System.out.println("|||||||||||||||||1" + temp2 + "         e: " + e);
									}
									}	
								}
							}
							
						}
						f++;
					}
				}
				//if in the diag ///
				for (int p = 0; p < b.length - 2; p++) {
					int q = 2;
					while (q<b[0].length) {
						if (b[p][q] != 0 && b[p+1][q-1] == b[p][q] && b[p+1][q-1] == b[p+2][q-2]) {
							if (playerID == b[p][q]) {
								if ((p-1) != -1){
								if((q+1) != b[0].length && b[p][q+1] != 0 && b[p-1][p+1] == 0 ) {
									isme3 = true;
									temp2 = q+1;
									System.out.println("|||||||||||||||||1" + temp2 + "         p: " + p);
								}
								} 
								if ((q-3) != -1){
								if((p+3) != b.length && b[p+3][q-3] == 0) {
									if ((p+4) ==b.length) {
										isme3 = true;
										temp2 = q-3;
										System.out.println("|||||||||||||||||1" + temp2 + "         p: " + p);
									} else {
										if (b[p+4][q-3] != 0) {
											isme3 = true;
											temp2 = q-3;
											System.out.println("|||||||||||||||||1" + temp2 + "         p: " + p);
										}
									}
								}
							}
							}
							
						}
						q++;
					
					}
				}




				//offence part 222222222222!!!!!!!!!!!!!!!!!!!!
				boolean isme2 = false;
				int temp3 = -1;
				for (int i = 0; i< b.length; i++) {
					int j = 0;
					while ((j+1)<b[0].length) {
						if (b[i][j] != 0 && b[i][j] == b[i][j+1]) {
							if(playerID == b[i][j]){
								if ((j-1) != -1){
								if(b[i][j-1] == 0) {
									if ((i+1) == b.length) {
										isme2 = true;
										temp3 = j-1;
										System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         i: " + i);
									} else if (b[i+1][j-1] != 0) {
										isme2 = true;
										temp3 = j-1;
									}
									}
								} 
								if ((j+2) != b[i].length){

									if(b[i][j+2] == 0) {
										if ((i+1) == b.length) {
											temp3 = j+2;
											isme2 = true;
											System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         i: " + i);
										} else if (b[i+1][j+2] != 0) {
											temp3 = j+2;
											isme2 = true;
											System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         i: " + i);
										}
									}
								}
							}

						}
						j++;
					}

				}
				//if in a vertical line
				for (int r = 0; r< b[0].length; r++) {
					int s = 0;
					while ((s+1)<b.length) {
						if (b[s][r] != 0){
						if(b[s][r] == b[s+1][r]) {
							if(playerID == b[s][r]){
								if ((s-1) != -1 && b[s-1][r] == 0  && b[0][r] == 0) {
									isme2 = true;
									temp3 = r;
									System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         s: " + s);
								}
								} 
							}

						}
						s++;
					}

				}

				//if in the diag \\\
				
				for (int e = 0; e < b.length-1;e++) {
					int f = 0;
					while ((f+1)<b[0].length) {
						if (b[e][f] != 0 && b[e][f] == b[e+1][f+1]) {
							if(playerID == b[e][f]) {
								if ((e-1) != -1){
								if((f-1) != -1 && b[e-1][f] != 0 && b[e-1][f-1] == 0) {
									isme2 = true;
									temp3 = f-1;
									System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         e: " + e);
								}
								} 
								if ((e+2) != b.length){
								if((f+2) != b[0].length && b[e+2][f+2] == 0){
									if (e+3 == b.length) {
										temp3 = f+2;
										isme2 = true;
										System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         e: " + e);
									} else if (b[e+3][f+2] != 0) {}
									temp3 = f+2;
									isme2 = true;
									System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         e: " + e);
								}
							}
							}
							
						}
						f++;
					}
				}
				//if in the diag ///
				for (int p = 0; p < b.length - 1; p++) {
					int q = 1;
					while (q<b[0].length) {
						if (b[p][q] != 0 && b[p+1][q-1] == b[p][q]) {
							if (playerID == b[p][q]) {
								if ((p-1) != -1){
								if((q+1) != b[0].length && b[p][q+1] != 0 && b[p-1][q+1] == 0 ) {
									isme2 = true;
									temp3 = q+1;
									System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         p: " + p);
								}
								} 
								if ((q-2) != -1){
								if((p+2) != b.length && b[p+2][q-2] == 0) {
									if ((p+3) ==b.length) {
										isme2 = true;
										temp3 = q-2;
										System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         p: " + p);
									} else {
										if (b[p+3][q-2] != 0) {
											isme2 = true;
											temp3 = q-2;
											System.out.println("\\\\\\\\\\\\\\\\\1" + temp3 + "         p: " + p);
										}
									}
								}
							}
							}
							
						}
						q++;
					
					}
				}
				if(temp < 0 || temp >= col) {is3 = false;}
					else if(b[0][temp] != 0) {
						is3 = false;
					}
					if(temp2 < 0 || temp2 >= col) {isme3 = false;}
					else if(b[0][temp2] != 0) {
						isme3 = false;
					}
					if(temp4 < 0 || temp4 >= col) {is2 = false;}
					else if(b[0][temp4] != 0) {
						is2 = false;
					}
					if(temp3 < 0 || temp3 >= col) {isme2 = false;}
					else if(b[0][temp3] != 0) {
						isme2 = false;
					}

				if (is3 == true) {
					System.out.println("**a");
					a = temp;
				} else if (isme3 == true) {
					System.out.println("**b");
					a = temp2;
				} else if (is2 == true) {
					System.out.println("**c");
					a = temp4;
				}else if (isme2 == true) {
					System.out.println("**d");
					a = temp3;
				} else {
					
					if (lastMove == 0) {
						System.out.println("**e");
						int kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
						//int kkk = (int)(Math.random() * 2) + 0;
						while (b[0][kkk] != 0) {
							//kkk = (int)(Math.random() * 2) + 0;
							kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
						}

						a = kkk;
						
					} else if (lastMove == b[0].length - 1) {
						System.out.println("**f");
						//int kkk = (int)(Math.random() * 2) + (lastMove - 1);
						int kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
						
						while (b[0][kkk] != 0) {
							kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
							//kkk = (int)(Math.random() * 2) + (lastMove - 1);
						}
						
						a = kkk;
						

					} else {
						System.out.println("**g");
						int kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
						//int kkk = (int) (Math.random() * 3) + (lastMove - 1);
						while (b[0][kkk] != 0) {
							kkk = (int) (Math.random() * (col - 1 - 0 + 1)) + 0;
							//kkk = (int) (Math.random() * 3) + (lastMove - 1);
						}
						
						a = kkk;
						
					}
				}
			
			}
			
			if (a < 0 || a >= col) isInBound = false;
			if (b[0][a] != 0) isInBound = false;
		} while (board.play(playerID,a) == true);
		//this.board.play(playerID, a);
		System.out.println("player2****************");
		//board.printBoard();
		int i = b.length - 1;
				while (b[i][a] != 0) {
					i --;
					if (i < 0) {break;}
				}
				if (i>= 0) b[i][a] = playerID;
		for (int abc = 0; abc < b.length; abc++) {
			for (int j = 0; j < b[abc].length; j++) {

				System.out.print("{" + b[abc][j] + "}" + '\t');
			}
			System.out.println();
		}
	
	return a;
}
	/*//if can drop
	public boolean canDrop(int c) {
		boolean a = true;
		int i = b.length - 1;
		while (b[i][c] != 0) {
			i--;
			if (i < 0) {a = false; break;}
		}
		return a;

	}*/
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
		this.b = new int[row][col];
	}
}