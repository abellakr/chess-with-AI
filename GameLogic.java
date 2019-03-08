import java.util.Scanner;
public class GameLogic implements ChessController{
	private displayGame board;
	private boolean gameDone;
	EasyAI easy;
	hardAI hard;
	
	public GameLogic(displayGame newBoard){
		gameDone = false;
		board = newBoard;
		board.displayBoard();
		
		easy = new EasyAI(newBoard);
		hard = new hardAI(newBoard);
		
	}
	
	public void simulation() throws Exception{
		displayGame yes = board;
		
		System.out.println("COMP 2150 Assignment 3: Chess game");
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
	
		int welp = 0;
		boolean gameDone = false;
		String input = "";
		int pieceRow, pieceCol, moveRow, moveCol;
		while(welp < 100000){
			System.out.println("enter coordinates or q for quit");
			System.out.println("enter item row: ");
			input = reader.next();
			if(input.charAt(0) == 'q'){
				System.out.println("User has chosen to quit the game.");
				break;
			}else
				pieceRow = Integer.parseInt(input);
			System.out.println("enter item col: ");
			
			input = reader.next();
			if(input.charAt(0) == 'q'){
				System.out.println("User has chosen to quit the game.");
				break;
			}else
				pieceCol = Integer.parseInt(input);
			System.out.println("enter move row: ");
			
			input = reader.next();
			if(input.charAt(0) == 'q'){
				System.out.println("User has chosen to quit the game.");
				break;
			}else
				moveRow =  Integer.parseInt(input);
			System.out.println("enter move col: ");
			
			input = reader.next();
			if(input.charAt(0) == 'q'){
				System.out.println("User has chosen to quit the game.");
				break;
			}else
				moveCol =  Integer.parseInt(input);
			
			boolean validMove = this.movePiece(pieceRow, pieceCol, moveRow, moveCol);
			ChessPiece[][] board = yes.getPolyBoard();
			
			if(validMove == true && yes.getWin() == false){
				System.out.println("AI is currently thinking");
				//TimeUnit.SECONDS.sleep(1);
				//System.out.print(".");
				//TimeUnit.SECONDS.sleep(1);
				//System.out.print(".");
				//TimeUnit.SECONDS.sleep(1);
				//System.out.println(".");
				//TimeUnit.SECONDS.sleep(1);
					//frick.makeMove(0,0,0,0);
				hard.makeMove(0, 0, 0, 0);
			}
			
			if( yes.getWin() == true){
				System.out.print("Game done! do you wish to play again?\nType y for Yes or n for No\n");
				char res = reader.next().charAt(0);
				if(res == 'y'){
					System.out.println("Starting new game");
					this.reset();
				}else if (res == 'n'){
					break;
				}//else
			}//if
			welp++;
		}//while

			System.out.println("Simulation done");
	}//simulation
	
	public boolean movePiece(int pieceRow, int pieceCol, int moveRow, int moveCol){
		boolean validMove = false;
		char[][] checkBoard = board.getBoard();
		ChessPiece[][] checkPolyBoard = board.getPolyBoard();
		Scanner keyboard = new Scanner(System.in);
		
		//handling parameter error checking here
		if( (pieceRow >= 9 || pieceRow < 0) || (pieceCol >= 9 || pieceCol < 0) 
				|| (moveRow >= 9 || moveRow < 0) ||(moveCol >= 9 || moveCol < 0) ){
			System.out.println("That is an invalid position! coordinates must be between 0 and 8.");
		}else if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == ' '){
			System.out.println("There is no Chess piece in that position! please try again");
		}else{
			if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'P'){
				//valid moves are:
				//1 square at a time in the forward direciton. square must be empty
				//no double moves
				//Captures by moving ahead one square on a diagonal to a square occupied by an opponent’s
				//piece
				//If a pawn reaches the far side of the board, the player can choose
				//the type of piece the pawn will convert to (rook, knight, bishop, or queen).
					if( (pieceRow - 1) == moveRow && pieceCol == moveCol){
							//the move is forward once
						if(checkPolyBoard[moveRow-1][moveCol-1].getPiece() == ' '){
							//make sure forward item is empty
							//System.out.println("user is moving pawn forward");
							board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
							validMove = true;
						}else{
							System.out.println("invalid move. there is an item infront.");
							board.displayBoard();
						}
					}else if((pieceRow - 1) == moveRow && ( (pieceCol-1) == moveCol ||
							(pieceCol+1) == moveCol)) {
						//we are eating an item. 
						//only valid if the piece to eat is diagonal to the user piece
						if(checkPolyBoard[moveRow-1][moveCol-1].getPiece() != ' ' &&
								//also make sure that the piece we're trying to eat is the opponent's piece
							 ( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= (int)'a' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'z')	){
							//System.out.println("user pawn is eating an opponents piece. valid move");
							board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
							if(moveRow == 1){
								System.out.println("your pawn reached the end of the board");
								System.out.println("please choose piece to replace your pawn");
								System.out.println("R for Rook, N for Knight, B for Bishop and Q for Queen");
								char piece = keyboard.next().charAt(0);
								
								boolean good = board.setPiece(moveRow, moveCol, piece);
								if(good){
									System.out.println("Pawn has been converted.");
								}else
									System.out.println("not a valid piece!");
							}
							validMove = true;
						}else{
							System.out.println("invalid move. your pawn can't make a diagonal move into an empty square or eat your own piece.");
							board.displayBoard();
						}//else if eating an item
					}else{
						System.out.println("invalid move. your pawn can't move more than one square at a time");
					}//else not a single square move
	
			}if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'R'){
				boolean hasPieceinPath = false;
				int blockPos = -1;
				if(pieceRow == moveRow){
					//the Rook is going sideways
					if(pieceCol > moveCol){
						//System.out.println("going left ");
						for(int i = pieceCol-2; i >= moveCol-1; i--){
							//check if there's anything in it's path
							if(checkPolyBoard[pieceRow-1][i].getPiece() != ' '){
								hasPieceinPath = true;
								blockPos = i;
								break;
							}//if there is, break
						}//loop that checks if there's anything in it's path
					}else if (pieceCol < moveCol){
						//System.out.println("going right ");
						for(int i = pieceCol; i < moveCol; i++){
							//check if there's anything in it's path
							if(checkPolyBoard[pieceRow-1][i].getPiece() != ' '){
								hasPieceinPath = true;
								blockPos = i;
								break;
							}//if there is, break
						}//loop that checks if there's anything in it's path
					}
					if(hasPieceinPath){
						//check if that char blocking it is an opponent piece or a team mate piece
						if( ( (int)checkPolyBoard[moveRow-1][blockPos].getPiece() >= 'a' && (int)checkPolyBoard[moveRow-1][blockPos].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							board.summarizeMove(pieceRow, pieceCol, moveRow, blockPos+1);
							validMove = true;
						}else if(( (int)checkPolyBoard[moveRow-1][blockPos].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][blockPos].getPiece() <= (int)'Z')){
							//if there's a team mate piece in it's path
							System.out.println("invalid move. there is a team mate piece in its path.");
							board.displayBoard();
						}//else
					}else{
						//nothing in it's path
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceCol == moveCol){
					//the Rook is going forward or backward
					if(pieceRow > moveRow){
						//System.out.println("going forward ");
						for(int i = pieceRow-2; i >= moveRow-1; i--){
							//check if there's anything in it's path
							if(checkPolyBoard[i][pieceCol-1].getPiece() != ' '){
								hasPieceinPath = true;
								blockPos = i;
								//System.out.println(checkBoard[i][pieceCol-1]+" is blocking the rook");
								break;
							}//if there is, break
						}//loop that checks if there's anything in it's path
					}else if (pieceRow < moveRow){
						//System.out.println("going backward ");
						for(int i = pieceRow+1; i < moveRow; i++){
							//check if there's anything in it's path
							if(checkPolyBoard[i][pieceCol-1].getPiece() != ' '){
								hasPieceinPath = true;
								blockPos = i;
								break;
							}//if there is, break
						}//loop that checks if there's anything in it's path
					}
					if(hasPieceinPath){
						//check if that char blocking it is an opponent piece or a team mate piece
						if( ( (int)checkPolyBoard[blockPos][moveCol-1].getPiece() >= 'a' && (int)checkPolyBoard[blockPos][moveCol-1].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							board.summarizeMove(pieceRow, pieceCol, blockPos+1, moveCol);
							validMove = true;
						}else if(( (int)checkPolyBoard[blockPos][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[blockPos][moveCol-1].getPiece() <= (int)'Z')){
							//if there's an item piece in it's path
							System.out.println("invalid move. there is a team mate piece in its path.");
							board.displayBoard();
						}//else 
					}else{
						//nothing in it's path
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else{
					System.out.println("invalid move! please try again");
				}
				
			}if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'N'){
				//char[][] checkBoardN = board.getBoard();
				if(pieceRow+2 == moveRow && pieceCol+1 == moveCol){
					//2 right 1 up
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow+2 == moveRow && pieceCol-1 == moveCol){
					//2 right 1 down
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow-2 == moveRow && pieceCol+1 == moveCol){
					//2 left 1 up 
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow-2 == moveRow && pieceCol-1 == moveCol){
					//2 left 1 down
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow+1 == moveRow && pieceCol+2 == moveCol){
					//2 up 1 right
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow-1 == moveRow && pieceCol+2 == moveCol){
					//2 up 1 left
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow+1 == moveRow && pieceCol-2 == moveCol){
					//2 down 1 right
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else if(pieceRow-1 == moveRow && pieceCol-2 == moveCol){
					//2 down 1 left
					if(( (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'Z'))
						System.out.println("invalid move. there is a team mate piece the movePiece location");
					else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else{
					System.out.println("invalid move. not an L pattern.");
				}//else not a valid pattern move
				
			}if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'B'){
				System.out.println("moving Bishop");
				boolean hasBlock = false;
				int blockR = 0;
				int blockC = 0;
				
				int checkR = 0;
				int checkC = 0;
				
				if(Math.abs( (pieceRow-moveRow) ) - Math.abs( (pieceCol-moveCol) ) == 0 ) {
				if(pieceRow > moveRow && pieceCol < moveCol){
				//diagonal up-right
					checkR = pieceRow-1;
					checkC = pieceCol-1;
					
					while(checkR >= moveRow && checkC <= moveCol){
						if(checkPolyBoard[checkR-1][checkC+1].getPiece() != ' '){
							hasBlock = true;
							blockR = checkR-1;
							blockC = checkC+1;
							break;
						}
						checkR--;
						checkC++;
					}//loop that checks if there something in it's path
					if(hasBlock){
						if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
							validMove = true;
						}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
							//if there's an item piece in it's path
							
							System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
							//board.displayBoard();
						}//else
					}else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}if(pieceRow < moveRow && pieceCol < moveCol){
				//diagonal down-right
					checkR = pieceRow-1;
					checkC = pieceCol-1;
					
					System.out.println("@ down-right move");
					while(checkR < moveRow-1 && checkC < moveCol-1){
						if(checkPolyBoard[checkR+1][checkC+1].getPiece() != ' '){
							hasBlock = true;
							blockR = checkR+1;
							blockC = checkC+1;
							break;
						}
						checkR++;
						checkC++;
					}//loop that checks if there something in it's path
					if(hasBlock){
						if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							System.out.println("eating opponent");
							board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
							validMove = true;
						}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
							//if there's an item piece in it's path
							System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
							//board.displayBoard();
						}//else
					}else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}if(pieceRow > moveRow && pieceCol > moveCol){
				//diagonal up-left
					checkR = pieceRow-1;
					checkC = pieceCol-1;
					
					while(checkR >= moveRow && checkC >= moveCol){
						if(checkPolyBoard[checkR-1][checkC-1].getPiece() != ' '){
							hasBlock = true;
							blockR = checkR-1;
							blockC = checkC-1;
							break;
						}
						checkR--;
						checkC--;
					}//loop that checks if there something in it's path
					if(hasBlock){
						if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							System.out.println("eating opponent");
							board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
							validMove = true;
						}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
							//if there's an item piece in it's path
							
							System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
							//board.displayBoard();
						}//else
					}else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}if(pieceRow < moveRow && pieceCol > moveCol){
				//diagonal down-left
					checkR = pieceRow-1;
					checkC = pieceCol-1;
					
					System.out.println("loop @ down left");
					while(checkR <= moveRow && checkC >= moveCol){
						if(checkPolyBoard[checkR+1][checkC-1].getPiece() != ' '){
							hasBlock = true;
							blockR = checkR+1;
							blockC = checkC-1;
							break;
						}
						checkR++;
						checkC--;
					}//loop that checks if there something in it's path
					if(hasBlock){
						if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
							//if there's an opponent piece in it's path
							System.out.println("eating opponent");
							board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
							validMove = true;
						}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
							//if there's an item piece in it's path
							
							System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
							//board.displayBoard();
						}//else
					}else{
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}//diagonal down-left
				
				}else{
					System.out.println("invalid move. That is not a diagonal move.");
				}
			}if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'K'){
					//this condition makes sure that the King is only eating it's opponent pieces or only going into empty squares
				if( (checkPolyBoard[moveRow-1][moveCol-1].getPiece() == ' ') || 
						(int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() >= (int)'a' && (int)checkPolyBoard[moveRow-1][moveCol-1].getPiece() <= (int)'z' ){
					if( (pieceRow - 1) == moveRow && pieceCol == moveCol){
						//the move is forward once
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}if( (pieceRow + 1) == moveRow && pieceCol == moveCol){
						//the move is back once
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}if( pieceRow == moveRow && (pieceCol+1) == moveCol){
						//the move is right
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}if( pieceRow == moveRow && (pieceCol-1) == moveCol){
						//the move is left
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}if((pieceRow - 1) == moveRow && ( (pieceCol-1) == moveCol ||
							(pieceCol+1) == moveCol)) {
						//the move is diagonal front left or diagonal front right
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}if((pieceRow + 1) == moveRow && ( (pieceCol-1) == moveCol ||
							(pieceCol+1) == moveCol)) {
						//the move is diagonal back left or diagonal back right
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}
				}else{
					System.out.println("invalid move. your King can't eat your own piece.");
					board.displayBoard();
				}
			}if(checkPolyBoard[pieceRow-1][pieceCol-1].getPiece() == 'Q'){
				System.out.println("@Queen");
				if(pieceRow == moveRow || pieceCol == moveCol){
					boolean hasPieceinPath = false;
					int blockPos = -1;
					if(pieceRow == moveRow){
						//the Rook is going sideways
						if(pieceCol > moveCol){
							//System.out.println("going left ");
							for(int i = pieceCol-2; i >= moveCol-1; i--){
								//check if there's anything in it's path
								if(checkPolyBoard[pieceRow-1][i].getPiece() != ' '){
									hasPieceinPath = true;
									blockPos = i;
									break;
								}//if there is, break
							}//loop that checks if there's anything in it's path
						}else if (pieceCol < moveCol){
							//System.out.println("going right ");
							for(int i = pieceCol; i < moveCol; i++){
								//check if there's anything in it's path
								if(checkPolyBoard[pieceRow-1][i].getPiece() != ' '){
									hasPieceinPath = true;
									blockPos = i;
									break;
								}//if there is, break
							}//loop that checks if there's anything in it's path
						}
						if(hasPieceinPath){
							//check if that char blocking it is an opponent piece or a team mate piece
							if( ( (int)checkPolyBoard[moveRow-1][blockPos].getPiece() >= 'a' && (int)checkPolyBoard[moveRow-1][blockPos].getPiece() <= (int)'z') ){
								//if there's an opponent piece in it's path
								board.summarizeMove(pieceRow, pieceCol, moveRow, blockPos+1);
								validMove = true;
							}else if(( (int)checkPolyBoard[moveRow-1][blockPos].getPiece() >= 'A' && (int)checkPolyBoard[moveRow-1][blockPos].getPiece() <= (int)'Z')){
								//if there's a team mate piece in it's path
								System.out.println("invalid move. there is a team mate piece in its path.");
								board.displayBoard();
							}//else
						}else{
							//nothing in it's path
							board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
							validMove = true;
						}
					}else if(pieceCol == moveCol){
						//the Rook is going forward or backward
						if(pieceRow > moveRow){
							//System.out.println("going forward ");
							for(int i = pieceRow-2; i >= moveRow-1; i--){
								//check if there's anything in it's path
								if(checkPolyBoard[i][pieceCol-1].getPiece() != ' '){
									hasPieceinPath = true;
									blockPos = i;
									//System.out.println(checkBoard[i][pieceCol-1]+" is blocking the rook");
									break;
								}//if there is, break
							}//loop that checks if there's anything in it's path
						}else if (pieceRow < moveRow){
							//System.out.println("going backward ");
							for(int i = pieceRow+1; i < moveRow; i++){
								//check if there's anything in it's path
								if(checkPolyBoard[i][pieceCol-1].getPiece() != ' '){
									hasPieceinPath = true;
									blockPos = i;
									break;
								}//if there is, break
							}//loop that checks if there's anything in it's path
						}
						if(hasPieceinPath){
							//check if that char blocking it is an opponent piece or a team mate piece
							if( ( (int)checkPolyBoard[blockPos][moveCol-1].getPiece() >= 'a' && (int)checkPolyBoard[blockPos][moveCol-1].getPiece() <= (int)'z') ){
								//if there's an opponent piece in it's path
								board.summarizeMove(pieceRow, pieceCol, blockPos+1, moveCol);
								validMove = true;
							}else if(( (int)checkPolyBoard[blockPos][moveCol-1].getPiece() >= 'A' && (int)checkPolyBoard[blockPos][moveCol-1].getPiece() <= (int)'Z')){
								//if there's an item piece in it's path
								System.out.println("invalid move. there is a team mate piece in its path.");
								board.displayBoard();
							}//else 
						}else{
							//nothing in it's path
							board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
							validMove = true;
						}
					}//if move is forward
				}//straight line moves
				
				//diagonal moves
				else{
					if(Math.abs( (pieceRow-moveRow) ) - Math.abs( (pieceCol-moveCol) ) == 0 ) {
						boolean hasBlock = false;
						int blockR = 0;
						int blockC = 0;
						
						int checkR = 0;
						int checkC = 0;
						
						if(pieceRow > moveRow && pieceCol < moveCol){
						//diagonal up-right
							checkR = pieceRow-1;
							checkC = pieceCol-1;
							
							while(checkR >= moveRow && checkC <= moveCol){
								if(checkPolyBoard[checkR-1][checkC+1].getPiece() != ' '){
									hasBlock = true;
									blockR = checkR-1;
									blockC = checkC+1;
									break;
								}
								checkR--;
								checkC++;
							}//loop that checks if there something in it's path
							if(hasBlock){
								if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
									//if there's an opponent piece in it's path
									board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
									validMove = true;
								}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
									//if there's an item piece in it's path
									
									System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
									//board.displayBoard();
								}//else
							}else{
								board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
								validMove = true;
							}
						}if(pieceRow < moveRow && pieceCol < moveCol){
						//diagonal down-right
							checkR = pieceRow-1;
							checkC = pieceCol-1;
							
							System.out.println("@ down-right move");
							while(checkR < moveRow-1 && checkC < moveCol-1){
								if(checkPolyBoard[checkR+1][checkC+1].getPiece() != ' '){
									hasBlock = true;
									blockR = checkR+1;
									blockC = checkC+1;
									break;
								}
								checkR++;
								checkC++;
							}//loop that checks if there something in it's path
							if(hasBlock){
								if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
									//if there's an opponent piece in it's path
									System.out.println("eating opponent");
									board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
									validMove = true;
								}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
									//if there's an item piece in it's path
									System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
									//board.displayBoard();
								}//else
							}else{
								board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
								validMove = true;
							}
						}if(pieceRow > moveRow && pieceCol > moveCol){
						//diagonal up-left
							checkR = pieceRow-1;
							checkC = pieceCol-1;
							
							while(checkR >= moveRow && checkC >= moveCol){
								if(checkPolyBoard[checkR-1][checkC-1].getPiece() != ' '){
									hasBlock = true;
									blockR = checkR-1;
									blockC = checkC-1;
									break;
								}
								checkR--;
								checkC--;
							}//loop that checks if there something in it's path
							if(hasBlock){
								if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
									//if there's an opponent piece in it's path
									System.out.println("eating opponent");
									board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
									validMove = true;
								}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
									//if there's an item piece in it's path
									
									System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
									//board.displayBoard();
								}//else
							}else{
								board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
								validMove = true;
							}
						}if(pieceRow < moveRow && pieceCol > moveCol){
						//diagonal down-left
							checkR = pieceRow-1;
							checkC = pieceCol-1;
							
							System.out.println("loop @ down left");
							while(checkR <= moveRow && checkC >= moveCol){
								if(checkPolyBoard[checkR+1][checkC-1].getPiece() != ' '){
									hasBlock = true;
									blockR = checkR+1;
									blockC = checkC-1;
									break;
								}
								checkR++;
								checkC--;
							}//loop that checks if there something in it's path
							if(hasBlock){
								if( ( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'a' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'z') ){
									//if there's an opponent piece in it's path
									System.out.println("eating opponent");
									board.summarizeMove(pieceRow, pieceCol, blockR+1, blockC+1);
									validMove = true;
								}else if(( (int)checkPolyBoard[blockR][blockC].getPiece() >= 'A' && (int)checkPolyBoard[blockR][blockC].getPiece() <= (int)'Z')){
									//if there's an item piece in it's path
									
									System.out.println("invalid move. there is a team mate piece in its path.: "+checkPolyBoard[blockR][blockC].getPiece());
									//board.displayBoard();
								}//else
							}else{
								board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
								validMove = true;
							}
						}//diagonal down-left
					}else{
							System.out.println("invalid move. That is not a diagonal move.");
					}//else its not a valid diagonal move
					
				}//if diagonal move
				
			}//if Q
		}
		
		return validMove;
	}//movePiece
	
	public void reset(){
		displayGame newGame = new displayGame();
		
		this.board = newGame;
		hard = new hardAI(newGame);
		this.board.displayBoard();
		
		System.out.println("Starting new game");
	}
	
	private boolean checkQuit(char input){
		boolean valid = false;
		
		return input == 'q';
	}
	
}//class



//extra code
//char board
		/*if(checkBoard[pieceRow-1][pieceCol-1] == 'P'){
			//valid moves are:
			//1 square at a time in the forward direciton. square must be empty
			//no double moves
			//Captures by moving ahead one square on a diagonal to a square occupied by an opponent’s
			//piece
			//If a pawn reaches the far side of the board, the player can choose
			//the type of piece the pawn will convert to (rook, knight, bishop, or queen).
				if( (pieceRow - 1) == moveRow && pieceCol == moveCol){
						//the move is forward once
					if(checkBoard[moveRow-1][moveCol-1] == ' '){
						//make sure forward item is empty
						System.out.println("user is moving pawn forward");
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}else{
						System.out.println("there is an item infront. invalid move");
						board.displayBoard();
					}
				}else if((pieceRow - 1) == moveRow && ( (pieceCol-1) == moveCol ||
						(pieceCol+1) == moveCol)) {
					//we are eating an item. 
					//only valid if the piece to eat is diagonal to the user piece
					if(checkBoard[moveRow-1][moveCol-1] != ' ' &&
							//also make sure that the piece we're trying to eat is the opponent's piece
						 ( (int)checkBoard[moveRow-1][moveCol-1] >= (int)'a' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'z')	){
						System.out.println("user pawn is eating an opponents piece. valid move");
						board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
						validMove = true;
					}else{
						System.out.println("invalid move. your pawn can't make a diagonal move into an empty square or eat your own piece.");
						board.displayBoard();
					}//invali
				}//else if eating an item

		}if(checkBoard[pieceRow-1][pieceCol-1] == 'R'){
			boolean hasPieceinPath = false;
			int blockPos = -1;
			if(pieceRow == moveRow){
				//the Rook is going sideways
				if(pieceCol > moveCol){
					System.out.println("going left ");
					for(int i = pieceCol-2; i >= moveCol-1; i--){
						//check if there's anything in it's path
						if(checkBoard[pieceRow-1][i] != ' '){
							hasPieceinPath = true;
							blockPos = i;
							break;
						}//if there is, break
					}//loop that checks if there's anything in it's path
				}else if (pieceCol < moveCol){
					System.out.println("going right ");
					for(int i = pieceCol; i < moveCol; i++){
						//check if there's anything in it's path
						if(checkBoard[pieceRow-1][i] != ' '){
							hasPieceinPath = true;
							blockPos = i;
							break;
						}//if there is, break
					}//loop that checks if there's anything in it's path
				}
				if(hasPieceinPath){
					//check if that char blocking it is an opponent piece or a team mate piece
					if( ( (int)checkBoard[moveRow-1][blockPos] >= 'a' && (int)checkBoard[moveRow-1][blockPos] <= (int)'z') ){
						//if there's an opponent piece in it's path
						board.summarizeMove(pieceRow, pieceCol, moveRow, blockPos+1);
						validMove = true;
					}else if(( (int)checkBoard[moveRow-1][blockPos] >= 'A' && (int)checkBoard[moveRow-1][blockPos] <= (int)'Z')){
						//if there's an item piece in it's path
						System.out.println("invalid move. there is a team mate piece in its path.");
						board.displayBoard();
					}//else
				}else{
					//nothing in it's path
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceCol == moveCol){
				//the Rook is going forward or backward
				if(pieceRow > moveRow){
					System.out.println("going forward ");
					for(int i = pieceRow-2; i >= moveRow-1; i--){
						//check if there's anything in it's path
						if(checkBoard[i][pieceCol-1] != ' '){
							hasPieceinPath = true;
							blockPos = i;
							//System.out.println(checkBoard[i][pieceCol-1]+" is blocking the rook");
							break;
						}//if there is, break
					}//loop that checks if there's anything in it's path
				}else if (pieceRow < moveRow){
					System.out.println("going backward ");
					for(int i = pieceRow+1; i < moveRow; i++){
						//check if there's anything in it's path
						if(checkBoard[i][pieceCol-1] != ' '){
							hasPieceinPath = true;
							blockPos = i;
							break;
						}//if there is, break
					}//loop that checks if there's anything in it's path
				}
				if(hasPieceinPath){
					//check if that char blocking it is an opponent piece or a team mate piece
					if( ( (int)checkBoard[blockPos][moveCol-1] >= 'a' && (int)checkBoard[blockPos][moveCol-1] <= (int)'z') ){
						//if there's an opponent piece in it's path
						board.summarizeMove(pieceRow, pieceCol, blockPos+1, moveCol);
						validMove = true;
					}else if(( (int)checkBoard[blockPos][moveCol-1] >= 'A' && (int)checkBoard[blockPos][moveCol-1]<= (int)'Z')){
						//if there's an item piece in it's path
						System.out.println("invalid move. there is a team mate piece in its path.");
						board.displayBoard();
					}//else 
				}else{
					//nothing in it's path
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}//if move is forward
			
		}if(checkBoard[pieceRow-1][pieceCol-1] == 'N'){
			//char[][] checkBoardN = board.getBoard();
			if(pieceRow+2 == moveRow && pieceCol+1 == moveCol){
				//2 right 1 up
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow+2 == moveRow && pieceCol-1 == moveCol){
				//2 right 1 down
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow-2 == moveRow && pieceCol+1 == moveCol){
				//2 left 1 up 
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow-2 == moveRow && pieceCol-1 == moveCol){
				//2 left 1 down
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow+1 == moveRow && pieceCol+2 == moveCol){
				//2 up 1 right
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow-1 == moveRow && pieceCol+2 == moveCol){
				//2 up 1 left
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow+1 == moveRow && pieceCol-2 == moveCol){
				//2 down 1 right
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else if(pieceRow-1 == moveRow && pieceCol-2 == moveCol){
				//2 down 1 left
				if(( (int)checkBoard[moveRow-1][moveCol-1] >= 'A' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'Z'))
					System.out.println("invalid move. there is a team mate piece the movePiece location");
				else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else{
				System.out.println("invalid move. not an L pattern.");
			}//else not a valid pattern move
			
		}if(checkBoard[pieceRow-1][pieceCol-1] == 'B'){
			System.out.println("moving Bishop");
			boolean hasBlock = false;
			int blockR = 0;
			int blockC = 0;
			
			int checkR = 0;
			int checkC = 0;
			
			if(pieceRow > moveRow && pieceCol < moveCol){
			//diagonal up-right
				checkR = pieceRow-1;
				checkC = pieceCol-1;
				
				System.out.println("@ up-right move");
				while(checkR >= moveRow && checkC <= moveCol){
					if(checkBoard[checkR-1][checkC+1] != ' '){
						hasBlock = true;
						blockR = checkR;
						blockC = checkC;
						break;
					}
					checkR--;
					checkC++;
				}//loop that checks if there something in it's path
				if(hasBlock){
					if( ( (int)checkBoard[blockR][blockC] >= 'a' && (int)checkBoard[blockR][blockC] <= (int)'z') ){
						//if there's an opponent piece in it's path
						System.out.println("eating opponent");
						board.summarizeMove(pieceRow, pieceCol, blockR, blockC);
						validMove = true;
					}else if(( (int)checkBoard[blockR][blockC] >= 'A' && (int)checkBoard[blockR][blockC] <= (int)'Z')){
						//if there's an item piece in it's path
						
						System.out.println("invalid move. there is a team mate piece in its path.: "+checkBoard[blockR][blockC]);
						//board.displayBoard();
					}//else
				}else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}if(pieceRow < moveRow && pieceCol < moveCol){
			//diagonal down-right
				checkR = pieceRow-1;
				checkC = pieceCol-1;
				
				System.out.println("@ down-right move");
				while(checkR < moveRow-1 && checkC < moveCol-1){
					if(checkBoard[checkR+1][checkC+1] != ' '){
						hasBlock = true;
						blockR = checkR+1;
						blockC = checkC+1;
						break;
					}
					checkR++;
					checkC++;
				}//loop that checks if there something in it's path
				if(hasBlock){
					if( ( (int)checkBoard[blockR][blockC] >= 'a' && (int)checkBoard[blockR][blockC] <= (int)'z') ){
						//if there's an opponent piece in it's path
						System.out.println("eating opponent");
						board.summarizeMove(pieceRow, pieceCol, blockR, blockC);
						validMove = true;
					}else if(( (int)checkBoard[blockR][blockC] >= 'A' && (int)checkBoard[blockR][blockC] <= (int)'Z')){
						//if there's an item piece in it's path
						System.out.println("invalid move. there is a team mate piece in its path.: "+checkBoard[blockR][blockC]);
						//board.displayBoard();
					}//else
				}else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}if(pieceRow > moveRow && pieceCol > moveCol){
			//diagonal up-left
				System.out.println("@ up-left move");
				while(checkR >= moveRow && checkC >= moveCol){
					if(checkBoard[checkR-1][checkC-1] != ' '){
						hasBlock = true;
						blockR = checkR;
						blockC = checkC;
						break;
					}
					checkR--;
					checkC--;
				}//loop that checks if there something in it's path
				if(hasBlock){
					if( ( (int)checkBoard[blockR][blockC] >= 'a' && (int)checkBoard[blockR][blockC] <= (int)'z') ){
						//if there's an opponent piece in it's path
						System.out.println("eating opponent");
						board.summarizeMove(pieceRow, pieceCol, blockR, blockC);
						validMove = true;
					}else if(( (int)checkBoard[blockR][blockC] >= 'A' && (int)checkBoard[blockR][blockC] <= (int)'Z')){
						//if there's an item piece in it's path
						
						System.out.println("invalid move. there is a team mate piece in its path.: "+checkBoard[blockR][blockC]);
						//board.displayBoard();
					}//else
				}else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}if(pieceRow < moveRow && pieceCol > moveCol){
			//diagonal down-left
				checkR = pieceRow-1;
				checkC = pieceCol-1;
				
				System.out.println("loop @ down left");
				while(checkR <= moveRow && checkC >= moveCol){
					System.out.println("fuck: "+checkR+", "+checkC );
					System.out.println("fuck: "+checkBoard[checkR][checkC]);
					if(checkBoard[checkR+1][checkC-1] != ' '){
						hasBlock = true;
						blockR = checkR+1;
						blockC = checkC-1;
						break;
					}
					checkR++;
					checkC--;
				}//loop that checks if there something in it's path
				if(hasBlock){
					if( ( (int)checkBoard[blockR][blockC] >= 'a' && (int)checkBoard[blockR][blockC] <= (int)'z') ){
						//if there's an opponent piece in it's path
						System.out.println("eating opponent");
						board.summarizeMove(pieceRow, pieceCol, blockR, blockC);
						validMove = true;
					}else if(( (int)checkBoard[blockR][blockC] >= 'A' && (int)checkBoard[blockR][blockC] <= (int)'Z')){
						//if there's an item piece in it's path
						
						System.out.println("invalid move. there is a team mate piece in its path.: "+checkBoard[blockR][blockC]);
						//board.displayBoard();
					}//else
				}else{
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}
		}if(checkBoard[pieceRow-1][pieceCol-1] == 'K'){
				//this condition makes sure that the King is only eating it's opponent pieces or only going into empty squares
			if( (checkBoard[moveRow-1][moveCol-1] == ' ') || 
					(int)checkBoard[moveRow-1][moveCol-1] >= (int)'a' && (int)checkBoard[moveRow-1][moveCol-1] <= (int)'z' ){
				if( (pieceRow - 1) == moveRow && pieceCol == moveCol){
					//the move is forward once
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}if( (pieceRow + 1) == moveRow && pieceCol == moveCol){
					//the move is back once
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}if( pieceRow == moveRow && (pieceCol+1) == moveCol){
					//the move is right
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}if( pieceRow == moveRow && (pieceCol-1) == moveCol){
					//the move is left
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}if((pieceRow - 1) == moveRow && ( (pieceCol-1) == moveCol ||
						(pieceCol+1) == moveCol)) {
					//the move is diagonal front left or diagonal front right
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}if((pieceRow + 1) == moveRow && ( (pieceCol-1) == moveCol ||
						(pieceCol+1) == moveCol)) {
					//the move is diagonal back left or diagonal back right
					board.summarizeMove(pieceRow, pieceCol, moveRow, moveCol);
					validMove = true;
				}
			}else{
				System.out.println("invalid move. your King can't eat your own piece.");
				board.displayBoard();
			}
			
		}*/
