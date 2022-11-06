package org.example;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.*;

class Minimax {

    private static final int DEPTH = 1;
    Side IA_Side; //false means the AI is black, true means it is white

    //minmax algo that returns the best move to make according to the color set in attributes
    Move minimax(Board board){

        Move bestMove;
        int bestMoveScore;
        ArrayList<Board> possibleBoards = new ArrayList<Board>();

        //print the chessboard in a human-readable form
        System.out.println(board.toString());

        //on récupère les legal moves du board à t
        List<Move> moves = board.legalMoves();
        System.out.println("Legal moves: " + moves);

        //on applique tous les legal moves au board possible qui devient board t+1
        for (Move move : moves) {
            Board newBoard = board.clone();

            System.out.println("piece moving is : " + newBoard.getPiece(move.getFrom()));
            System.out.print("side of the piece moving : ");
            System.out.println(newBoard.getPiece(move.getFrom()).getPieceSide());

            newBoard.doMove(move);

            //print the chessboard in a human-readable form
            System.out.println("New possible board is : " + newBoard.toString());
            possibleBoards.add(newBoard);
        }
        //initializes bestMove to the first move in the
        bestMove = moves.get(0);
        bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH);

        return moves.get(0);
    }

    //fonction récursive qui permet de créer l'arbre et d'évaluer le meilleur move
    public int evaluatePosition(Board b, int alpha, int beta, int depth){
        System.out.println("Evaluation en cours...");

        // condition d'arret
        if (depth == 0){
            //appel de la fonction d'évaluation du board
            int eval = evaluateBoard(b);
            System.out.println("L'evaluation est de : " + eval);
            return eval;
        }

        if (b.getSideToMove() != IA_Side) { //minimizing
            //on récupère les legal moves du board à t
            List<Move> moves = b.legalMoves();
            int newBeta = beta;
            for(Move move : moves){ //for child in node
                System.out.println("Move to be evaluated: " + move.toString());
                Board nextBoard = b.clone();
                nextBoard.doMove(move);
                newBeta = Math.min(newBeta, evaluatePosition(nextBoard, alpha, beta, depth -1)); //think about how to change moves
                if(newBeta <= alpha) break;
            }
            return newBeta; //returns the highest score of the possible moves
        }
        else { //maximizing
            //on récupère les legal moves du board à t
            List<Move> moves = b.legalMoves();
            int newAlpha = alpha;

            for(Move move : moves){ //for child in node
                System.out.println("Move to be evaluated: " + move.toString());
                Board nextBoard = b.clone();
                nextBoard.doMove(move);
                newAlpha = Math.max(newAlpha, evaluatePosition(nextBoard, alpha, beta, depth -1)); //think about how to change moves
                if(beta<= newAlpha) break;
            }
            return newAlpha; //returns the highest score of the possible moves
        }

    }

        int evaluateBoard(Board board){
        /*
        Etudie le score potentiel de chacun des joueurs selon les pièces qu'il prend à l'autre
        En se focalisant sur le score de l'IA (les whites), s'il prend une pièce noire son score augmente
        S'il les blacks prennent des pièces blanches, le score de l'IA diminue
         */
        int whiteScore = 0;

        //si je suis sur une pièce
        //je récupère la case du board associée
        //puis le type de la pièce qui est sur la case
        Square square = Square.A1 ; // ca c'est pas bon, mais il faudra faire un boucle for qui parcourt toute la grille
        Piece piece = board.getPiece(square);
        PieceType pieceType = piece.getPieceType();
        switch (pieceType){
            case PAWN :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 10;
                else
                    whiteScore -= 10;
            case KNIGHT :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 40;
                else
                    whiteScore -= 40;
            case BISHOP :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 40;
                else
                    whiteScore -= 40;
            case ROOK :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 60;
                else
                    whiteScore -= 60;
            case QUEEN :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 100;
                else
                    whiteScore -= 100;
            case KING :
                if(piece.getPieceSide() == Side.BLACK)
                    whiteScore += 1000;
                else
                    whiteScore -= 1000;
        }

        return whiteScore;
    }

}