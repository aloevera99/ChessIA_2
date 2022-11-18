package org.example;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.*;


class Minimax {

    private static final int DEPTH = 1;
    Side IA_Side = Side.WHITE; //false means the AI is black, true means it is white

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
            System.out.println("L'evaluation est de : ");
            int eval = evaluateMove(b);
            System.out.println(eval);
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




        int evaluateMove(Board board){

        int IAScore = 0;

        Square square = Square.A1;

        for (int i = 0; i < 64; i++){
            Piece piece = null;
            Square curr = square.squareAt(i);
            System.out.println();
            piece = board.getPiece(curr);
            PieceType pieceType = piece.getPieceType();


            if(piece != Piece.NONE) {
                System.out.println(curr);
                System.out.println(piece);
                System.out.println(pieceType);

                //Idée 1
                switch (pieceType) {
                    case PAWN:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 10;
                        else
                            IAScore -= 10;
                    case KNIGHT:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 40;
                        else
                            IAScore -= 40;
                    case BISHOP:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 40;
                        else
                            IAScore -= 40;
                    case ROOK:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 60;
                        else
                            IAScore -= 60;
                    case QUEEN:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 100;
                        else
                            IAScore -= 100;
                    case KING:
                        if (piece.getPieceSide() == IA_Side)
                            IAScore += 1000;
                        else
                            IAScore -= 1000;
                }
            }
        }
            /*
            Ou bien, je récupère d'abord le side qui est en train de jouer
            Et après on se focalise que sur les pièces de la couleur opposée.

        //Idée 2
        Side side = Side.WHITE ;//c'est aribtraire pour le moment

            if (side == Side.WHITE) {
                if (piece.getPieceSide() == Side.BLACK ){
                    //faire le switch case pour voir le type de la pièce et les pts associés
                }
            }
            else{
                if (piece.getPieceSide() == Side.WHITE ){
                    //faire le switch case pour voir le type de la pièce et les pts associés
                }
            }*/


        return IAScore;
    }

}