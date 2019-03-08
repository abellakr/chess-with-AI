
public class Pawn extends ChessPiece{
	private char pawn;
	
	public Pawn(boolean user){
		if(user)
			pawn = 'P';
		else
			pawn = 'p';
	}
	
	public char getPiece(){
		return pawn;
	}
	public void print(){
		System.out.print(pawn);
	}

}
