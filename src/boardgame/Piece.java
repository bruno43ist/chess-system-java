package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() { //TABULEIRO SÓ SERA ACESSADO PELA CLASSE boardgame
		return board;
	}
}
