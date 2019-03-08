public class King extends ChessPiece{
	private char king;
	
	public King(boolean user){
		if(user)
			king = 'K';
		else
			king = 'k';
	}
	
	public char getPiece(){
		return king;
	}
	
	public void print(){
		System.out.print(king);
	}

}
