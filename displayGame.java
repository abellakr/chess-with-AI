public class displayGame implements GameDisplay {
	static int row;
	static int col;
	private char[][] board;
	private boolean gameDone;
	
	private ChessPiece[][] polyBoard;
	int minDifficulty;
	boolean win;
	//constructor
	public displayGame(){
		row = 8;
		col = 8;
		board = new char[row][col];
		
		polyBoard = new ChessPiece[row][col];
		gameDone = false;
		minDifficulty = 1;
		initBoard(board);
		initPolyBoard(polyBoard);
		win = false;
	}

	public int promptForOpponentDifficulty(int maxDifficulty){
		//difficulty = maxDifficulty;
		int range = (maxDifficulty - minDifficulty) + 1;
		return (int)(Math.random() * range) + minDifficulty;
	}//promptForOpponentDifficulty
	
	public void displayBoard(){
		System.out.println("  1 2 3 4 5 6 7 8");
		System.out.println("------------------");

		/*for(int i = 0; i < row; i++){
			System.out.print((i+1)+"|");
			for(int j = 0; j < col; j++){
				System.out.print(board[i][j]+"|");
			}
			System.out.println("\n------------------");
		}
		System.out.print("polymorphism\n");*/
		
		for(int i = 0; i < row; i++){
			System.out.print((i+1)+"|");
			for(int j = 0; j < col; j++){
				polyBoard[i][j].print();
				System.out.print("|");
			}
			System.out.println("\n------------------");
		}
		//printBoard(board);
	}//displayBoard
	
	public void summarizeMove(int pieceRow, int pieceCol, int moveRow, int moveCol){
		pieceRow = pieceRow-1;
		pieceCol = pieceCol-1;
		moveRow = moveRow-1;
		moveCol = moveCol-1;
		
		//polymorphism 
		if(polyBoard[pieceRow][pieceCol].getPiece() == 'P'){
			System.out.print("Pawn moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						win = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();

		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'R'){
			System.out.print("Rook moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'N'){
			System.out.print("Knight moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'B'){
			System.out.print("Bishop moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'K'){
			System.out.print("King moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'Q'){
			System.out.print("Queen moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'p'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'r'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'n'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'b'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'k'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(1);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}//Queen
		

		System.out.println("printing updated board");
		this.displayBoard(); //print the board after each move
	}//summarizeMove
	
	public void summarizeAIMove(int pieceRow, int pieceCol, int moveRow, int moveCol){
		pieceRow = pieceRow-1;
		pieceCol = pieceCol-1;
		moveRow = moveRow-1;
		moveCol = moveCol-1;
		
		//polymorphism 
		if(polyBoard[pieceRow][pieceCol].getPiece() == 'p'){
			System.out.print("AI Pawn moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();

		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'r'){
			System.out.print("AI Rook moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'n'){
			System.out.print("AI Knight moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'b'){
			System.out.print("AI Bishop moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'k'){
			System.out.print("AI King moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}if(polyBoard[pieceRow][pieceCol].getPiece() == 'q'){
			System.out.print("AI Queen moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
			System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
			
			if(polyBoard[moveRow][moveCol].getPiece() == ' '){
				System.out.println(" No capture made.");
			}else{
				if(polyBoard[moveRow][moveCol].getPiece() == 'P'){
						System.out.println(" Pawn captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'R'){
						System.out.println(" Rook captured.");	
				}if(polyBoard[moveRow][moveCol].getPiece() == 'N'){
						System.out.println(" Knight captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'B'){
						System.out.println(" Bishop captured.");
				}if(polyBoard[moveRow][moveCol].getPiece() == 'K'){
						System.out.println(" King captured.");
						gameDone = true;
						gameOver(2);
				}if(polyBoard[moveRow][moveCol].getPiece() == 'Q'){
						System.out.println(" Queen captured.");
				}
			}//else if theres a piece in baord[moveRow][moveCol]
			this.polyBoard[moveRow][moveCol] = this.polyBoard[pieceRow][pieceCol];
			this.polyBoard[pieceRow][pieceCol] = new Empty();
			
		}//Queen
		

		System.out.println("printing updated board");
		this.displayBoard(); //print the board after each move
	}//summarizeMove
	
	public void gameOver(int winner){
		if(winner == 1)
			System.out.println("User has won the game!");
		
		else if (winner == 2)
			System.out.println("AI has won the game! get rekt");
		
		else if (winner == 0)
			System.out.println("The User has forfeited");
			
	}
	
	public boolean getWin(){
		return gameDone;
	}
	
	public char[][] getBoard(){
		return board;
	}//returns the board
	
	public ChessPiece[][] getPolyBoard(){
		return polyBoard;
	}
	
	public int getRowSize(){
		return row;
	}
	
	public int getColSize(){
		return col;
	}
	
	public boolean setPiece(int row, int col, char piece){
		boolean valid = false;
		if(piece == 'R'){
			this.polyBoard[row-1][col-1] = new Rook(true);
			valid = true;
		}else if(piece == 'N'){
			this.polyBoard[row-1][col-1] = new Knight(true);
			valid = true;
		}else if(piece == 'B'){
			this.polyBoard[row-1][col-1] = new Bishop(true);
			valid = true;
		}else if(piece == 'Q'){
			this.polyBoard[row-1][col-1] = new Queen(true);
			valid = true;
		}
		return valid;
	}
	
	public boolean setAIPiece(int row, int col, char piece){
		boolean valid = false;
		if(piece == 'r'){
			this.polyBoard[row-1][col-1] = new Rook(false);
			valid = true;
		}else if(piece == 'n'){
			this.polyBoard[row-1][col-1] = new Knight(false);
			valid = true;
		}else if(piece == 'b'){
			this.polyBoard[row-1][col-1] = new Bishop(false);
			valid = true;
		}else if(piece == 'q'){
			this.polyBoard[row-1][col-1] = new Queen(false);
			valid = true;
		}
		return valid;
	}
	
	private static void initBoard(char[][] board){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				//if row 0
				if(i == 0 ){
					if(j == 0 || j == 7)
						board[i][j] = 'r';
					if(j == 1 || j == 6)
						board[i][j] = 'n';
					if(j == 2 || j == 5)
						board[i][j] = 'b';
					if(j == 3)
						board[i][j] = 'k';
					if(j == 4)
						board[i][j] = 'q';
				}else if(i == 1){
					board[i][j] = 'p';
				}else if(i == 6){
					board[i][j] = 'P';
				}else if(i == 7){
					if(j == 0 || j == 7)
						board[i][j] = 'R';
					if(j == 1 || j == 6)
						board[i][j] = 'N';
					if(j == 2 || j == 5)
						board[i][j] = 'B';
					if(j == 3)
						board[i][j] = 'K';
					if(j == 4)
						board[i][j] = 'Q';
				}else{
					board[i][j] = ' ';
				}
			}//for j
		}//for i
		
	}//initBoard
	
	private static void initPolyBoard(ChessPiece[][] board){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				//if row 0
				if(i == 0 ){
					if(j == 0 || j == 7)
						board[i][j] = new Rook(false);
					if(j == 1 || j == 6)
						board[i][j] = new Knight(false);
					if(j == 2 || j == 5)
						board[i][j] = new Bishop(false);
					if(j == 3)
						board[i][j] = new King(false);
					if(j == 4)
						board[i][j] = new Queen(false);
				}else if(i == 1 ){
					board[i][j] = new Pawn(false);
				}else if(i == 6){
					board[i][j] = new Pawn(true);
				}else if(i == 7){
					if(j == 0 || j == 7)
						board[i][j] = new Rook(true);
					if(j == 1 || j == 6)
						board[i][j] = new Knight(true);
					if(j == 2 || j == 5)
						board[i][j] = new Bishop(true);;
					if(j == 3)
						board[i][j] = new King(true);
					if(j == 4)
						board[i][j] = new Queen(true);
				}else{
					board[i][j] = new Empty();
				}
			}//for j
		}//for i
		
	}//initBoard
	
	private static void printBoard(char[][] board){
		System.out.println("  1 2 3 4 5 6 7 8");
		System.out.println("------------------");

		for(int i = 0; i < row; i++){
			System.out.print((i+1)+"|");
			for(int j = 0; j < col; j++){
				System.out.print(board[i][j]+"|");
			}
			System.out.println("\n------------------");
		}
	}//printBoard
	
}//class


//regular
/*if(board[pieceRow][pieceCol] == 'P'){
	System.out.print("Pawn moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[moveRow][moveCol] == 'p'){
				System.out.println(" Pawn captured.");
		}if(board[moveRow][moveCol] == 'r'){
				System.out.println(" Rook captured.");	
		}if(board[moveRow][moveCol] == 'n'){
				System.out.println(" Knight captured.");
		}if(board[moveRow][moveCol]== 'b'){
				System.out.println(" Bishop captured.");
		}if(board[moveRow][moveCol] == 'k'){
				System.out.println(" King captured.");
				win = true;
		}if(board[moveRow][moveCol] == 'q'){
				System.out.println(" Queen captured.");
		}
	}//else if theres a piece in baord[moveRow][moveCol]
	this.board[moveRow][moveCol] = this.board[pieceRow][pieceCol];
	this.board[pieceRow][pieceCol] = ' ';

}if(board[pieceRow][pieceCol] == 'R'){
	System.out.print("Rook moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[moveRow][moveCol] == 'p'){
				System.out.println(" Pawn captured.");
		}if(board[moveRow][moveCol] == 'r'){
				System.out.println(" Rook captured.");	
		}if(board[moveRow][moveCol] == 'n'){
				System.out.println(" Knight captured.");
		}if(board[moveRow][moveCol]== 'b'){
				System.out.println(" Bishop captured.");
		}if(board[moveRow][moveCol] == 'k'){
				System.out.println(" King captured.");
				win = true;
		}if(board[moveRow][moveCol] == 'q'){
				System.out.println(" Queen captured.");
		}
	}//else if theres a piece in baord[moveRow][moveCol]
	board[moveRow][moveCol] = board[pieceRow][pieceCol];
	board[pieceRow][pieceCol] = ' ';
	
}if(board[pieceRow][pieceCol] == 'N'){
	System.out.print("Knight moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[moveRow][moveCol] == 'p'){
				System.out.println(" Pawn captured.");
		}if(board[moveRow][moveCol] == 'r'){
				System.out.println(" Rook captured.");	
		}if(board[moveRow][moveCol] == 'n'){
				System.out.println(" Knight captured.");
		}if(board[moveRow][moveCol]== 'b'){
				System.out.println(" Bishop captured.");
		}if(board[moveRow][moveCol] == 'k'){
				System.out.println(" King captured.");
				win = true;
		}if(board[moveRow][moveCol] == 'q'){
				System.out.println(" Queen captured.");
		}
	}//else if theres a piece in baord[moveRow][moveCol]
	board[moveRow][moveCol] = board[pieceRow][pieceCol];
	board[pieceRow][pieceCol] = ' ';
	
}if(board[pieceRow][pieceCol] == 'B'){
	System.out.print("Bishop moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[moveRow][moveCol] == 'p'){
				System.out.println(" Pawn captured.");
		}if(board[moveRow][moveCol] == 'r'){
				System.out.println(" Rook captured.");	
		}if(board[moveRow][moveCol] == 'n'){
				System.out.println(" Knight captured.");
		}if(board[moveRow][moveCol]== 'b'){
				System.out.println(" Bishop captured.");
		}if(board[moveRow][moveCol] == 'k'){
				System.out.println(" King captured.");
				win = true;
		}if(board[moveRow][moveCol] == 'q'){
				System.out.println(" Queen captured.");
		}
	}//else if theres a piece in baord[moveRow][moveCol]
	board[moveRow][moveCol] = board[pieceRow][pieceCol];
	board[pieceRow][pieceCol] = ' ';
	
}if(board[pieceRow][pieceCol] == 'K'){
	System.out.print("King moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[moveRow][moveCol] == 'p'){
				System.out.println(" Pawn captured.");
		}if(board[moveRow][moveCol] == 'r'){
				System.out.println(" Rook captured.");	
		}if(board[moveRow][moveCol] == 'n'){
				System.out.println(" Knight captured.");
		}if(board[moveRow][moveCol]== 'b'){
				System.out.println(" Bishop captured.");
		}if(board[moveRow][moveCol] == 'k'){
				System.out.println(" King captured.");
				win = true;
		}if(board[moveRow][moveCol] == 'q'){
				System.out.println(" Queen captured.");
		}
		
	}//else if theres a piece in baord[moveRow][moveCol]
	board[moveRow][moveCol] = board[pieceRow][pieceCol];
	board[pieceRow][pieceCol] = ' ';
	
}if(board[pieceRow][pieceCol] == 'Q'){
	System.out.print("Queen moved from ("+(pieceRow+1)+","+(pieceCol+1)+")");
	System.out.print(" to ("+(moveRow+1)+","+(moveCol+1)+").");
	
	if(board[moveRow][moveCol] == ' '){
		System.out.println(" No capture made.");
	}else{
		if(board[pieceRow][pieceCol] == 'p'){
			System.out.println(" Pawn captured.");
		}if(board[pieceRow][pieceCol] == 'r'){
			System.out.println(" Rook captured.");	
		}if(board[pieceRow][pieceCol] == 'n'){
			System.out.println(" Knight captured.");
		}if(board[pieceRow][pieceCol] == 'b'){
			System.out.println(" Bishop captured.");
		}if(board[pieceRow][pieceCol] == 'k'){
			System.out.println(" King captured.");
			win = true;
		}if(board[pieceRow][pieceCol] == 'q'){
			System.out.println(" Queen captured.");
		}
	}//else if theres a piece in baord[moveRow][moveCol]
	board[moveRow][moveCol] = board[pieceRow][pieceCol];
	board[pieceRow][pieceCol] = ' ';
	
}//Queen
*/
