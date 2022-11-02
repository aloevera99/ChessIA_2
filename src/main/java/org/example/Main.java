package org.example;


import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creates a new chessboard in the standard initial position
        Board board = new Board();

        //Make a move from E2 to E4 squares
        board.doMove(new Move(Square.E2, Square.E4));

        //print the chessboard in a human-readable form
        System.out.println(board.toString());

        //Find the square locations of black bishops
        List<Square> blackBishopSquares = board.getPieceLocation(Piece.BLACK_BISHOP);

        //Get the piece at A1 square...
        Piece piece = board.getPiece(Square.A1);

        System.out.println("la position des bishops noir est la suivante :\n");

        for (int i = 0; i < blackBishopSquares.size(); i++){
            System.out.println(blackBishopSquares.get(i));
        }

        System.out.println("la pièce en A1 est la suivante :\n");
        System.out.println(piece);

        // Generate legal chess moves for the current position
        List<Move> moves = board.legalMoves();
        System.out.println("Legal moves: " + moves);

    }
}