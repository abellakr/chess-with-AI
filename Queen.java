
public class Queen extends ChessPiece {
	private char queen;
	
	public Queen(boolean user){
		if(user)
			queen = 'Q';
		else
			queen = 'q';
	}
	
	public char getPiece(){
		return queen;
	}
	
	public void print(){
		System.out.print(queen);
	}

}
