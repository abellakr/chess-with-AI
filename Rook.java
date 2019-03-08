
public class Rook extends ChessPiece {
private char rook;
	
	public Rook(boolean user){
		if(user)
			rook = 'R';
		else
			rook = 'r';
	}
	
	public char getPiece(){
		return rook;
	}
	
	public void print(){
		System.out.print(rook);
	}

	
}
