package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<ChessPiece> captured = new ArrayList<>();
		ChessMatch chessMatch = new ChessMatch();
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.println("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if(chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion: (B/N/R/Q) :");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
			} catch(ChessException e) {
				System.out.println(e.getMessage());
				System.out.println("Press to continue!");
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Press to continue!");
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
