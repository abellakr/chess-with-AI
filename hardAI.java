import java.util.Random;
public class hardAI implements ChessPlayer {
	private displayGame game;
	private List coords;
	private int difficulty;
	
	public hardAI(displayGame initGame){
		game = initGame;
		coords = new List();
		difficulty = 75;
	}//constructor
	
	public void makeMove(int pieceRow, int pieceCol, int moveRow, int moveCol ){
		//75% chance of happening?
		
		ChessPiece[][] board = game.getPolyBoard();
		boolean validMove = false;
		String[] result = null;
		
		//this provides our max value for our random location generator
		int totalLocs = scanBoard(board);
		
		//choosing a random piece
		int randLoc = (int) ( Math.random() *(totalLocs - 0) + 0) ;
		result = this.coords.get(randLoc).split(",");
				
		Random random = new Random();
		
		boolean happens = (random.nextInt(100)+1) < difficulty;
		
		int cR = Integer.parseInt(result[0]);
		int cC = Integer.parseInt(result[1]);
			
			//if( (aiRow <= 7 && aiRow >= 0) && (aiCol <= 7 && aiCol >= 0) ){
			while(!happens && board[cR][cC].getPiece() == 'p'){
				result = this.coords.get(randLoc).split(",");
				cR = Integer.parseInt(result[0]);
				cC = Integer.parseInt(result[1]);
				happens = (random.nextInt(100)+1) < 75;
			}
		
		//grabbing all of our AI piece locations
		//String[] result = this.coordinates[randLoc].split(",");
		if(coords.isEmpty())
			System.out.println("AI is all out of pawns or rooks");
		else{

			//save random pawn's location's range and column
			int aiRow = Integer.parseInt(result[0]);
			int aiCol = Integer.parseInt(result[1]);
			//System.out.println( (aiRow+1) +", "+(aiCol+1) );
			
			//if( (aiRow <= 7 && aiRow >= 0) && (aiCol <= 7 && aiCol >= 0) ){
				if(board[aiRow][aiCol].getPiece() == 'p'){
					if( (aiRow < 7 && aiCol >= 1) && 
							(int)board[aiRow+1][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol-1].getPiece() <= (int)'Z'){
							//there is a user piece diagonal left to the AI pawn 
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol);
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							game.setAIPiece(aiRow+2, aiCol, 'q');
							game.displayBoard();
						}
						validMove = true;
					}else if( (aiRow < 7 && aiCol <= 6) && 
							(int)board[aiRow+1][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol+1].getPiece() <= (int)'Z'){
							//if there is a user piece diagonal right to the AI pawn
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol+2);
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							game.setAIPiece(aiRow+2, aiCol+2, 'q');
							game.displayBoard();
						}
						validMove = true;
					}else if( (aiRow < 7) &&
							board[aiRow+1][aiCol].getPiece() == ' '){
						//if the square in front is empty, move the pawn forward
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol+1);
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							game.setAIPiece(aiRow+2, aiCol+1, 'q');
							game.displayBoard();
						}
						validMove = true;
					}//eating a piece
					
					if(validMove == false) //if no pieces were valid re-run method
						makeMove(pieceRow, pieceCol, moveRow, moveCol);
				}if(board[aiRow][aiCol].getPiece() == 'r'){
						//scan surrounding
						System.out.println("@ AI rook");
						List rookEats = new List();
						List emptySpace = new List();

						//checking down
						int checkDown = aiRow+1;
						while(checkDown < 8){
							if(board[checkDown][aiCol].getPiece() == ' '){
								emptySpace.insert(checkDown+","+aiCol);
							}else if(board[checkDown][aiCol].getPiece() != ' '){
								if((int)board[checkDown][aiCol].getPiece() >= (int)'A' && (int)board[checkDown][aiCol].getPiece() <= (int)'Z')
									rookEats.insert(checkDown+","+aiCol);
								break; //<-- cant jump pieces so might as well stop
							}
							checkDown++;
						}
						
						//checking up
						int checkUp = aiRow-1;
						while(checkUp >= 0){
							if(board[checkUp][aiCol].getPiece() == ' '){
								emptySpace.insert(checkUp+","+aiCol);
							}else if(board[checkUp][aiCol].getPiece() != ' '){
								if((int)board[checkUp][aiCol].getPiece() >= (int)'A' && (int)board[checkUp][aiCol].getPiece() <= (int)'Z')
									rookEats.insert(checkUp+","+aiCol);
								break; //<-- cant jump pieces so might as well stop
							}
							checkUp--;
						}
						//checking left
						int checkLeft = aiCol-1;
						while(checkLeft >= 0){
							if(board[aiRow][checkLeft].getPiece() == ' '){
								emptySpace.insert(aiRow+","+checkLeft);
							}else if(board[aiRow][checkLeft].getPiece() != ' '){
								if((int)board[aiRow][checkLeft].getPiece() >= (int)'A' && (int)board[aiRow][checkLeft].getPiece() <= (int)'Z')
									rookEats.insert(aiRow+","+checkLeft);
								break; //<-- cant jump pieces so might as well stop
							}
							checkLeft--;
						}
						//checking right
						int checkRight = aiCol+1;
						while(checkRight < 8){
							if(board[aiRow][checkRight].getPiece() == ' '){
								emptySpace.insert(aiRow+","+checkRight);
							}else if(board[aiRow][checkRight].getPiece() != ' '){
								if((int)board[aiRow][checkRight].getPiece() >= (int)'A' && (int)board[aiRow][checkRight].getPiece() <= (int)'Z')
									rookEats.insert(aiRow+","+checkRight);
								break; //<-- cant jump pieces so might as well stop
							}
							checkRight++;
						}
						
						//now choose a random valid move
						//aggressive or passive?
						random = new Random();

						happens = (random.nextInt(100)+1) < difficulty;
						
						//int coinFlip = (Math.random() <= 0.5) ? 1 : 2;
						
						if(!happens && !emptySpace.isEmpty()){
							//passive
							int rand = (int) ( Math.random() *(emptySpace.getSize() - 0) + 0) ;
							String[] rookRes = emptySpace.get(rand).split(",");
							int rookRow = Integer.parseInt(rookRes[0]);
							int rookCol = Integer.parseInt(rookRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, rookRow+1, rookCol+1);
						}else if (happens && !rookEats.isEmpty()){
							//aggressive
							int rand = (int) ( Math.random() *(rookEats.getSize() - 0) + 0) ;
							String[] rookRes = rookEats.get(rand).split(",");
							int rookRow = Integer.parseInt(rookRes[0]);
							int rookCol = Integer.parseInt(rookRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, rookRow+1, rookCol+1);
						}
						if(validMove == false) //if no pieces were valid re-run method
							makeMove(pieceRow, pieceCol, moveRow, moveCol);
					}else if(board[aiRow][aiCol].getPiece() == 'n'){
						List emptySpaces = new List();
						List knightEats = new List();
						System.out.println("@ AI knight");
						if(aiRow+2 < 8 && aiCol+1 < 8){
							//2 right 1 up
							if((int)board[aiRow+2][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow+2][aiCol+1].getPiece() <= (int)'Z')
								knightEats.insert((aiRow+2)+","+(aiCol+1));
							else if(board[aiRow+2][aiCol+1].getPiece() == ' ')
								emptySpaces.insert((aiRow+2)+","+(aiCol+1));
						}else if(aiRow+2 < 8 && aiCol-1 >= 0){
							//2 right 1 down
							if((int)board[aiRow+2][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow+2][aiCol-1].getPiece() <= (int)'Z')
								knightEats.insert(aiRow+2+","+(aiCol-1));
							else if(board[aiRow+2][aiCol-1].getPiece() == ' ')
								emptySpaces.insert(aiRow+2+","+(aiCol-1));
							
						}else if(aiRow-2 >= 0 && aiCol+1 < 8){
							//2 left 1 up 
							if((int)board[aiRow-2][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow-2][aiCol+1].getPiece() <= (int)'Z')
								knightEats.insert((aiRow-2)+","+(aiCol+1));
							else if(board[aiRow-2][aiCol+1].getPiece() == ' ')
								emptySpaces.insert((aiRow-2)+","+(aiCol+1));
						}else if(aiRow-2 >= 0 && aiCol-1 >= 0){
							//2 left 1 down
							if((int)board[aiRow-2][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow-2][aiCol-1].getPiece() <= (int)'Z')
								knightEats.insert((aiRow-2)+","+(aiCol-1));
							else if(board[aiRow-2][aiCol-1].getPiece() == ' ')
								emptySpaces.insert((aiRow-2)+","+(aiCol-1));
						}else if(aiRow+1 < 8 && aiCol+2 < 8){
							//2 up 1 right
							if((int)board[aiRow+1][aiCol+2].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol+2].getPiece() <= (int)'Z')
								knightEats.insert((aiRow+1)+","+(aiCol+2));
							else if(board[aiRow+1][aiCol+2].getPiece() == ' ')
								emptySpaces.insert((aiRow+1)+","+(aiCol+2));
						}else if(aiRow-1 >= 0 && aiCol+2 < 8){
							//2 up 1 left
							if((int)board[aiRow-1][aiCol+2].getPiece() >= (int)'A' && (int)board[aiRow-1][aiCol+2].getPiece() <= (int)'Z')
								knightEats.insert((aiRow-1)+","+(aiCol+2));
							else if(board[aiRow-1][aiCol+2].getPiece() == ' ')
								emptySpaces.insert((aiRow-1)+","+(aiCol+2));
						}else if(aiRow+1 < 8 && aiCol-2 >= 0){
							//2 down 1 right
							if((int)board[aiRow+1][aiCol-2].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol-2].getPiece() <= (int)'Z')
								knightEats.insert((aiRow+1)+","+(aiCol-2));
							else if(board[aiRow+1][aiCol-2].getPiece() == ' ')
								emptySpaces.insert((aiRow+1)+","+(aiCol-2));
						}else if(aiRow-1 >= 0 && aiCol-2 >= 0){
							//2 down 1 left
							if((int)board[aiRow-1][aiCol-2].getPiece() >= (int)'A' && (int)board[aiRow-1][aiCol-2].getPiece() <= (int)'Z')
								knightEats.insert((aiRow-1)+","+(aiCol-2));
							else if(board[aiRow-1][aiCol-2].getPiece() == ' ')
								emptySpaces.insert((aiRow-1)+","+(aiCol-2));
						}
						
						random = new Random();

						happens = (random.nextInt(100)+1) < difficulty;
						
						//int coinFlip = (Math.random() <= 0.5) ? 1 : 2;
						
						if(!happens && !emptySpaces.isEmpty()){
							//passive
							int rand = (int) ( Math.random() *(emptySpaces.getSize() - 0) + 0) ;
							String[] knightRes = emptySpaces.get(rand).split(",");
							int knightRow = Integer.parseInt(knightRes[0]);
							int knightCol = Integer.parseInt(knightRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, knightRow+1, knightCol+1);
						}else if(happens && !knightEats.isEmpty()){
							//aggressive
							int rand = (int) ( Math.random() *(knightEats.getSize() - 0) + 0) ;
							String[] knightRes = knightEats.get(rand).split(",");
							int knightRow = Integer.parseInt(knightRes[0]);
							int knightCol = Integer.parseInt(knightRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, knightRow+1, knightCol+1);
						}
						if(validMove == false) //if no pieces were valid re-run method
							makeMove(pieceRow, pieceCol, moveRow, moveCol);
					}else if(board[aiRow][aiCol].getPiece() == 'b'){
						System.out.println("@AI Bishop");
						List emptySpaces = new List();
						List bishopEats = new List();
						
						int checkRow = aiRow+1;
						int checkCol = aiCol+1;
						//diagonal down-left
						while(checkRow < 8 && checkCol < 8){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								bishopEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpaces.insert(checkRow+","+checkCol);
							
							checkRow++;
							checkCol++;
						}
						
						checkRow = aiRow+1;
						checkCol = aiCol-1;
						//diagonal down-right
						while(checkRow < 8 && checkCol >= 0){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								bishopEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpaces.insert(checkRow+","+checkCol);
							
							checkRow++;
							checkCol--;
						}
						
						checkRow = aiRow-1;
						checkCol = aiCol-1;
						//diagonal up-left
						while(checkRow >= 0 && checkCol >= 0){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								bishopEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpaces.insert(checkRow+","+checkCol);
							
							checkRow--;
							checkCol--;
						}
						
						checkRow = aiRow-1;
						checkCol = aiCol+1;
						//diagonal up-right
						while(checkRow >= 0 && checkCol < 8){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								bishopEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpaces.insert(checkRow+","+checkCol);
							
							checkRow--;
							checkCol++;
						}
						
						random = new Random();

						happens = (random.nextInt(100)+1) < difficulty;
						
						if(!happens && !emptySpaces.isEmpty()){
							//passive
							int rand = (int) ( Math.random() *(emptySpaces.getSize() - 0) + 0) ;
							String[] bishopRes = emptySpaces.get(rand).split(",");
							int bishopRow = Integer.parseInt(bishopRes[0]);
							int bishopCol = Integer.parseInt(bishopRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, bishopRow+1, bishopCol+1);
						}else if (happens && !bishopEats.isEmpty()){
							//aggressive
							int rand = (int) ( Math.random() *(bishopEats.getSize() - 0) + 0) ;
							String[] bishopRes = bishopEats.get(rand).split(",");
							int bishopRow = Integer.parseInt(bishopRes[0]);
							int bishopCol = Integer.parseInt(bishopRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, bishopRow+1, bishopCol+1);
						}
						if(validMove == false) //if no pieces were valid re-run method
							makeMove(pieceRow, pieceCol, moveRow, moveCol);
						
					}else if(board[aiRow][aiCol].getPiece() == 'k'){
						List emptySpaces = new List();
						List KingEats = new List();
						System.out.println("@ AI King");
						
							if( (aiRow - 1) >= 0){
								//the move is up
								if((int)board[aiRow-1][aiCol].getPiece() >= (int)'A' && (int)board[aiRow-1][aiCol].getPiece() <= (int)'Z')
									KingEats.insert((aiRow-1)+","+(aiCol));
								else if(board[aiRow-1][aiCol].getPiece() == ' ')
									emptySpaces.insert((aiRow-1)+","+(aiCol));
							}if( (aiRow + 1) < 8){
								//the move is down
								if((int)board[aiRow+1][aiCol].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol].getPiece() <= (int)'Z')
									KingEats.insert((aiRow+1)+","+(aiCol));
								else if(board[aiRow+1][aiCol].getPiece() == ' ')
									emptySpaces.insert((aiRow+1)+","+(aiCol));
							}if( (aiCol+1) < 8){
								//the move is right
								if((int)board[aiRow][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow][aiCol+1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow)+","+(aiCol+1));
								else if(board[aiRow][aiCol+1].getPiece() == ' ')
									emptySpaces.insert((aiRow)+","+(aiCol+1));
							}if( (aiRow-1) >= 0){
								//the move is left
								if((int)board[aiRow][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow][aiCol-1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow)+","+(aiCol-1));
								else if(board[aiRow][aiCol-1].getPiece() == ' ')
									emptySpaces.insert((aiRow)+","+(aiCol-1));
							}if( (aiRow - 1) >= 0 && ( (aiCol-1) >= 0) ){
									//diagonal up left
								if((int)board[aiRow-1][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow-1][aiCol-1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow-1)+","+(aiCol-1));
								else if(board[aiRow-1][aiCol-1].getPiece() == ' ')
									emptySpaces.insert((aiRow-1)+","+(aiCol-1));
							}if( (aiRow - 1) >= 0 && ( (aiCol+1) < 8) ) {
								//diagonal up right
								if((int)board[aiRow-1][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow-1][aiCol+1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow-1)+","+(aiCol+1));
								else if(board[aiRow-1][aiCol+1].getPiece() == ' ')
									emptySpaces.insert((aiRow-1)+","+(aiCol+1));
							}if(( aiRow + 1) < 8 && ( (aiCol-1) >= 0 ) ){
								//diagonal down left
								if((int)board[aiRow+1][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol-1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow+1)+","+(aiCol-1));
								else if(board[aiRow+1][aiCol-1].getPiece() == ' ')
									emptySpaces.insert((aiRow+1)+","+(aiCol-1));
							}if(( aiRow + 1) < 8 && ( (aiCol+1) < 8)) {
								//diagonal down right
								if((int)board[aiRow+1][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol+1].getPiece() <= (int)'Z')
									KingEats.insert((aiRow+1)+","+(aiCol+1));
								else if(board[aiRow+1][aiCol+1].getPiece() == ' ')
									emptySpaces.insert((aiRow+1)+","+(aiCol+1));
							}
							
							int coinFlip = (Math.random() <= 0.5) ? 1 : 2;
							
							if(coinFlip == 1 && !emptySpaces.isEmpty()){
								//passive
								int rand = (int) ( Math.random() *(emptySpaces.getSize() - 0) + 0) ;
								String[] kingRes = emptySpaces.get(rand).split(",");
								int kingRow = Integer.parseInt(kingRes[0]);
								int kingCol = Integer.parseInt(kingRes[1]);
								
								validMove = true;
								game.summarizeAIMove(aiRow+1, aiCol+1, kingRow+1, kingCol+1);
							}else if (coinFlip == 2 && !KingEats.isEmpty()){
								//aggressive
								int rand = (int) ( Math.random() *(KingEats.getSize() - 0) + 0) ;
								String[] kingRes = KingEats.get(rand).split(",");
								int kingRow = Integer.parseInt(kingRes[0]);
								int kingCol = Integer.parseInt(kingRes[1]);
								
								validMove = true;
								game.summarizeAIMove(aiRow+1, aiCol+1, kingRow+1, kingCol+1);
							}
							if(validMove == false) //if no pieces were valid re-run method
								makeMove(pieceRow, pieceCol, moveRow, moveCol);

					}else if(board[aiRow][aiCol].getPiece() == 'q'){
						List emptySpace = new List();
						List queenEats = new List();
						
						System.out.println("@ AI Queen");
						
						//checking down
						int checkDown = aiRow+1;
						while(checkDown < 8){
							if(board[checkDown][aiCol].getPiece() == ' '){
								emptySpace.insert(checkDown+","+aiCol);
							}else if(board[checkDown][aiCol].getPiece() != ' '){
								if((int)board[checkDown][aiCol].getPiece() >= (int)'A' && (int)board[checkDown][aiCol].getPiece() <= (int)'Z')
									queenEats.insert(checkDown+","+aiCol);
								break; //<-- cant jump pieces so might as well stop
							}
							checkDown++;
						}
						
						//checking up
						int checkUp = aiRow-1;
						while(checkUp >= 0){
							if(board[checkUp][aiCol].getPiece() == ' '){
								emptySpace.insert(checkUp+","+aiCol);
							}else if(board[checkUp][aiCol].getPiece() != ' '){
								if((int)board[checkUp][aiCol].getPiece() >= (int)'A' && (int)board[checkUp][aiCol].getPiece() <= (int)'Z')
									queenEats.insert(checkUp+","+aiCol);
								break; //<-- cant jump pieces so might as well stop
							}
							checkUp--;
						}
						//checking left
						int checkLeft = aiCol-1;
						while(checkLeft >= 0){
							if(board[aiRow][checkLeft].getPiece() == ' '){
								emptySpace.insert(aiRow+","+checkLeft);
							}else if(board[aiRow][checkLeft].getPiece() != ' '){
								if((int)board[aiRow][checkLeft].getPiece() >= (int)'A' && (int)board[aiRow][checkLeft].getPiece() <= (int)'Z')
									queenEats.insert(aiRow+","+checkLeft);
								break; //<-- cant jump pieces so might as well stop
							}
							checkLeft--;
						}
						//checking right
						int checkRight = aiCol+1;
						while(checkRight < 8){
							if(board[aiRow][checkRight].getPiece() == ' '){
								emptySpace.insert(aiRow+","+checkRight);
							}else if(board[aiRow][checkRight].getPiece() != ' '){
								if((int)board[aiRow][checkRight].getPiece() >= (int)'A' && (int)board[aiRow][checkRight].getPiece() <= (int)'Z')
									queenEats.insert(aiRow+","+checkRight);
								break; //<-- cant jump pieces so might as well stop
							}
							checkRight++;
						}
						
						int checkRow = aiRow+1;
						int checkCol = aiCol+1;
						//diagonal down-left
						while(checkRow < 8 && checkCol < 8){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								queenEats.insert(checkRow+","+checkCol);
							break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpace.insert(checkRow+","+checkCol);
							
							checkRow++;
							checkCol++;
						}
						
						checkRow = aiRow+1;
						checkCol = aiCol-1;
						//diagonal down-right
						while(checkRow < 8 && checkCol >= 0){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								queenEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpace.insert(checkRow+","+checkCol);
							
							checkRow++;
							checkCol--;
						}
						
						checkRow = aiRow-1;
						checkCol = aiCol-1;
						//diagonal up-left
						while(checkRow >= 0 && checkCol >= 0){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								queenEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpace.insert(checkRow+","+checkCol);
							
							checkRow--;
							checkCol--;
						}
						
						checkRow = aiRow-1;
						checkCol = aiCol+1;
						//diagonal up-right
						while(checkRow >= 0 && checkCol < 8){
							if((int)board[checkRow][checkCol].getPiece() >= (int)'A' && (int)board[checkRow][checkCol].getPiece() <= (int)'Z'){
								queenEats.insert(checkRow+","+checkCol);
								break;
							}
							else if(board[checkRow][checkCol].getPiece() == ' ')
								emptySpace.insert(checkRow+","+checkCol);
							
							checkRow--;
							checkCol++;
						}
						
						//now choose a random valid move
						//aggressive or passive?
						random = new Random();

						happens = (random.nextInt(100)+1) < difficulty;
						
						//int coinFlip = (Math.random() <= 0.5) ? 1 : 2;
						
						if(!happens && !emptySpace.isEmpty()){
							//passive
							int rand = (int) ( Math.random() *(emptySpace.getSize() - 0) + 0) ;
							String[] rookRes = emptySpace.get(rand).split(",");
							int rookRow = Integer.parseInt(rookRes[0]);
							int rookCol = Integer.parseInt(rookRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, rookRow+1, rookCol+1);
						}else if (happens && !queenEats.isEmpty()){
							//aggressive
							int rand = (int) ( Math.random() *(queenEats.getSize() - 0) + 0) ;
							String[] rookRes = queenEats.get(rand).split(",");
							int rookRow = Integer.parseInt(rookRes[0]);
							int rookCol = Integer.parseInt(rookRes[1]);
							
							validMove = true;
							game.summarizeAIMove(aiRow+1, aiCol+1, rookRow+1, rookCol+1);
						}
						if(validMove == false) //if no pieces were valid re-run method
							makeMove(pieceRow, pieceCol, moveRow, moveCol);
						
					}
				}//if valid move
				
	}//makeMove
	
	private int scanBoard(ChessPiece[][] board){
		//this method scans the board for all locations of pieces
		//saves the location as a string with the format
		// "row,col"
		this.coords = new List();
		int index = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j].getPiece() == 'p' || 
						board[i][j].getPiece() == 'r' || 
						board[i][j].getPiece() == 'n' || 
						board[i][j].getPiece() == 'b' || 
						board[i][j].getPiece() == 'k' ||
						board[i][j].getPiece() == 'q'
						){
					this.coords.insert(i+","+j);
					index++;
				}//if
			}//for j
		}//for i
		return index;
	}//scanBoard
}
