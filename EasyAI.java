
public class EasyAI implements ChessPlayer{
	//my AI consists of using a random number generator that provides it's probability
	//of making the right move
	private displayGame game;
	private String[] coordinates;
	private List coords;
	private int size;

	public EasyAI(displayGame initGame){
		game = initGame;
		size = 16;
		coordinates = new String[size];
		coords = new List();
	}
	
	public void makeMove(int pieceRow, int pieceCol, int moveRow, int moveCol ){
		ChessPiece[][] board = game.getPolyBoard();
		boolean validMove = false;
		int numTries = 0;
		String[] result = null;
		//this provides our max value for our random location generator
		int totalLocs = scanBoard(board);
		
		//choosing a random pawn
		int randLoc = (int) ( Math.random() *(totalLocs - 0) + 0) ;
		
		//grabbing our pawn's location
		//String[] result = this.coordinates[randLoc].split(",");
		if(coords.isEmpty())
			System.out.println("AI is all out of pawns");
		else{
			//save random pawn's location's range and column
			result = this.coords.get(randLoc).split(",");
			int aiRow = Integer.parseInt(result[0]);
			int aiCol = Integer.parseInt(result[1]);
			System.out.println( (aiRow+1) +", "+(aiCol+1) );
			
			if( (aiRow <= 7 && aiRow >= 0) && (aiCol <= 7 && aiCol >= 0) ){
				if(board[aiRow][aiCol].getPiece() == 'p'){
					if( (aiRow < 7 && aiCol >= 1) && 
							(int)board[aiRow+1][aiCol-1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol-1].getPiece() <= (int)'Z'){
							//there is a user piece diagonal left to the AI pawn 
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol);
						numTries = 0;
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							boolean good = game.setAIPiece(aiRow+2, aiCol, 'q');
							game.displayBoard();
						}
						validMove = true;
					}else if( (aiRow < 7 && aiCol <= 6) && 
							(int)board[aiRow+1][aiCol+1].getPiece() >= (int)'A' && (int)board[aiRow+1][aiCol+1].getPiece() <= (int)'Z'){
							//if there is a user piece diagonal right to the AI pawn
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol+2);
						numTries = 0;
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							boolean good = game.setAIPiece(aiRow+2, aiCol+2, 'q');
							game.displayBoard();
						}
						validMove = true;
					}else if( (aiRow < 7) &&
							board[aiRow+1][aiCol].getPiece() == ' '){
						//if the square in front is empty, move the pawn forward
						game.summarizeAIMove(aiRow+1, aiCol+1, aiRow+2, aiCol+1);
						numTries = 0;
						if(aiRow+2 == 8){
							System.out.println("AI pawn has reached the end of board");
							boolean good = game.setAIPiece(aiRow+2, aiCol+1, 'q');
							game.displayBoard();
						}
						validMove = true;
					}else{
							//if the last pawn is not viable, re-run the method again
						numTries++;
						if(numTries < 5)
						makeMove(pieceRow, pieceCol, moveRow, moveCol);
						else
							System.out.println("AI has no more pawns");
					}//else
				}//if valid pawn
			}else{
				System.out.println("end of the board. please choose a new piece");
			}
		//return validMove;
		}
	}//makeMove
	
	private int scanBoard(ChessPiece[][] board){
		//this method scans the board for all locations of pawn pieces
		//saves the location as a string with the format
		// "row,col"
		this.coordinates = new String[size];
		this.coords = new List();
		int index = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j].getPiece() == 'p'){
					//System.out.println(i+", "+j);
					this.coordinates[index] = i+","+j;
					this.coords.insert(i+","+j);
					index++;
				}//if
			}//for j
		}//for i
		return index;
	}//scanBoard

}//EasyAI
