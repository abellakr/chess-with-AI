
public class Empty extends ChessPiece{
	private char empty;
	
	public Empty(){
		empty = ' ';
	}
	
	public char getPiece(){
		return empty;
	}
	
	public void print(){
		System.out.print(empty);
	}

}
