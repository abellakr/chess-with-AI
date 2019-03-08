
public interface GameDisplay {
		public int promptForOpponentDifficulty(int maxDifficulty);
		public void displayBoard();
		public void summarizeMove(int pieceRow, int pieceCol, int moveRow, int moveCol);//...pass the last move & whether a piece was captured...
		public void gameOver(int winner);
}
