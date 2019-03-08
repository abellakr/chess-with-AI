
public class Bishop extends ChessPiece{
	private char bishop;
	
	public Bishop(boolean user){
		if(user)
			bishop = 'B';
		else
			bishop = 'b';
	}
	
	public char getPiece(){
		return bishop;
	}
	
	public void print(){
		System.out.print(bishop);
	}

}
