
public class Knight extends ChessPiece{
	private char knight;
	
	public Knight(boolean user){
		if(user)
			knight = 'N';
		else
			knight = 'n';
	}
	
	public char getPiece(){
		return knight;
	}
	
	public void print(){
		System.out.print(knight);
	}

}
