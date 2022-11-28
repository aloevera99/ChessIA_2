package org.project;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.*;


class Minimax {

    private static final int[] PAWN_PST = {
            0,  0,   0,   0,   0,   0,  0,  0,
            50, 50,  50,  50,  50,  50, 50, 50,
            10, 10,  20,  30,  30,  20, 10, 10,
            5,  5,  10,  25,  25,  10,  5,  5,
            0,  0,   0,  20,  20,   0,  0,  0,
            5, -5, -10,   0,   0, -10, -5,  5,
            5, 10,  10, -20, -20,  10, 10,  5,
            0,  0,   0,   0,   0,   0,  0,  0
    };

    private static final int[] KNIGHT_PST = {
            -50, -40, -30, -30, -30, -30, -40, -50,
            -40, -20,   0,   0,   0,   0, -20, -40,
            -30,   0,  10,  15,  15,  10,   0, -30,
            -30,   5,  15,  20,  20,  15,   5, -30,
            -30,   0,  15,  20,  20,  15,   0, -30,
            -30,   5,  10,  15,  15,  10,   5, -30,
            -40, -20,   0,   5,   5,   0, -20, -40,
            -50, -40, -30, -30, -30, -30, -40, -50,
    };

    private static final int[] BISHOP_PST = {
            -20, -10, -10, -10, -10, -10, -10, -20,
            -10,   0,   0,   0,   0,   0,   0, -10,
            -10,   0,   5,  10,  10,   5,   0, -10,
            -10,   5,   5,  10,  10,   5,   5, -10,
            -10,   0,  10,  10,  10,  10,   0, -10,
            -10,  10,  10,  10,  10,  10,  10, -10,
            -10,   5,   0,   0,   0,   0,   5, -10,
            -20, -10, -10, -10, -10, -10, -10, -20,
    };

    private static final int[] ROOK_PST = {
            0,  0,  0,  0,  0,  0,  0,  0,
            5, 10, 10, 10, 10, 10, 10,  5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            0,  0,  0,  5,  5,  0,  0,  0
    };

    private static final int[] QUEEN_PST = {
            -20, -10, -10, -5, -5, -10, -10, -20,
            -10,   0,   0,  0,  0,   0,   0, -10,
            -10,   0,   5,  5,  5,   5,   0, -10,
            -5,   0,   5,  5,  5,   5,   0,  -5,
            0,   0,   5,  5,  5,   5,   0,  -5,
            -10,   5,   5,  5,  5,   5,   0, -10,
            -10,   0,   5,  0,  0,   0,   0, -10,
            -20, -10, -10, -5, -5, -10, -10, -20
    };

    // king middle game
    private static final int[] KING_PST = {
            -30, -40, -40, -50, -50, -40, -40, -30,
            -30, -40, -40, -50, -50, -40, -40, -30,
            -30, -40, -40, -50, -50, -40, -40, -30,
            -30, -40, -40, -50, -50, -40, -40, -30,
            -20, -30, -30, -40, -40, -30, -30, -20,
            -10, -20, -20, -20, -20, -20, -20, -10,
            20,  20,   0,   0,   0,   0,  20,  20,
            20,  30,  10,   0,   0,  10,  30,  20
    };
    private static final int DEPTH = 3;
    public static final int KING_VALUE = 10000;

    //minmax algo that returns the best move to make according to the color set in attributes
    Move minimax(Board board) {

        Move bestMove;
        int bestMoveScore;
        ArrayList<Board> possibleBoards = new ArrayList<Board>();

        //on récupère les legal moves du board à t
        List<Move> moves = board.legalMoves();

        //on applique tous les legal moves au board possible qui devient board t+1
        for (Move move : moves) {
            Board newBoard = board.clone();
            newBoard.doMove(move);

            possibleBoards.add(newBoard);
        }
        //initializes bestMove to the first move in the
        bestMove = moves.get(0);
        bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, true);


        //call evaluateposition on each move
        //keep track of the move with the best score
        for (int i = 1; i < possibleBoards.size(); i++) {

            int j = evaluatePosition(possibleBoards.get(i), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, true);
            if (j >= bestMoveScore) {
                bestMove = moves.get(i);
                bestMoveScore = j;
            }
        }
        return bestMove; //doMove performs the move on the original board and returns a string of that move

    }

    //fonction récursive qui permet de créer l'arbre et d'évaluer le meilleur move
    public int evaluatePosition(Board b, int alpha, int beta, int depth, boolean isMinimizimg){

        // condition d'arret
        if (depth == 0){
            //appel de la fonction d'évaluation du board
            int eval = evaluateMove(b);
            return eval;
        }

        if (isMinimizimg) { //minimizing
            //on récupère les legal moves du board à t
            List<Move> moves = b.legalMoves();
            int newBeta = beta;
            for(Move move : moves){ //for child in node
                Board nextBoard = b.clone();
                nextBoard.doMove(move);
                newBeta = Math.min(newBeta, evaluatePosition(nextBoard, alpha, beta, depth -1, false)); //think about how to change moves
                if(newBeta <= alpha) break;
            }
            return newBeta; //returns the highest score of the possible moves
        }
        else { //maximizing
            //on récupère les legal moves du board à t
            List<Move> moves = b.legalMoves();
            int newAlpha = alpha;

            for(Move move : moves){ //for child in node
                Board nextBoard = b.clone();
                nextBoard.doMove(move);
                newAlpha = Math.max(newAlpha, evaluatePosition(nextBoard, alpha, beta, depth -1, true)); //think about how to change moves
                if(beta <= newAlpha) break;
            }
            return newAlpha; //returns the highest score of the possible moves
        }
    }
    public static int evaluateMove(Board board){

        Side IA_Side = board.getSideToMove();
        // Initialisation
        int IAScore = 0;
        Square square = Square.A1;
        //Parcours du board
        for (int i = 0; i < 64; i++){
            Piece piece = null;
            Square curr = square.squareAt(i);
            piece = board.getPiece(curr);
            PieceType pieceType = piece.getPieceType();

            if(piece != Piece.NONE) {
                //Attribution de points selon le type de la pièce
                switch (pieceType) {
                    case PAWN:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += 100;
                            if (IA_Side == Side.WHITE)
                                IAScore += PAWN_PST[63 - i];
                            else // noir
                                IAScore += PAWN_PST[i];
                        }
                        else {
                            IAScore -= 100;
                            if (IA_Side == Side.WHITE)
                                IAScore -= PAWN_PST[i];
                            else // noir
                                IAScore -= PAWN_PST[63 - i];
                        }

                        break;
                    case KNIGHT:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += 300;
                            if (IA_Side == Side.WHITE)
                                IAScore += KNIGHT_PST[63 - i];
                            else // noir
                                IAScore += KNIGHT_PST[i];
                        }
                        else {
                        IAScore -= 300;
                        if (IA_Side == Side.WHITE)
                            IAScore -= KNIGHT_PST[63 - i];
                        else // noir
                            IAScore -= KNIGHT_PST[i];
                    }
                        break;
                    case BISHOP:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += 350;
                            if (IA_Side == Side.WHITE)
                                IAScore += BISHOP_PST[63 - i];
                            else // noir
                                IAScore += BISHOP_PST[i];
                        }
                        else {
                            IAScore -= 350;

                            if (IA_Side == Side.WHITE)
                                IAScore -= BISHOP_PST[63 - i];
                            else // noir
                                IAScore -= BISHOP_PST[i];
                        }
                        break;
                    case ROOK:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += 500;

                            if (IA_Side == Side.WHITE)
                                IAScore += ROOK_PST[63 - i];
                            else // noir
                                IAScore += ROOK_PST[i];
                        }
                        else {
                            IAScore -= 500;

                            if (IA_Side == Side.WHITE)
                                IAScore -= ROOK_PST[63 - i];
                            else // noir
                                IAScore -= ROOK_PST[i];
                        }
                        break;
                    case QUEEN:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += 1000;

                            if (IA_Side == Side.WHITE)
                                IAScore += QUEEN_PST[63 - i];
                            else // noir
                                IAScore += QUEEN_PST[i];
                        }
                        else {
                            IAScore -= 1000;
                            if (IA_Side == Side.WHITE)
                                IAScore -= QUEEN_PST[63 - i];
                            else // noir
                                IAScore -= QUEEN_PST[i];
                        }
                        break;
                    case KING:
                        if (piece.getPieceSide() == IA_Side) {
                            IAScore += KING_VALUE;
                            if (IA_Side == Side.WHITE)
                                IAScore += KING_PST[63 - i];
                            else // noir
                                IAScore += KING_PST[i];
                        }
                        else {
                            IAScore -= KING_VALUE;
                            if (IA_Side == Side.WHITE)
                                IAScore -= KING_PST[63 - i];
                            else // noir
                                IAScore -= KING_PST[i];
                        }
                        break;
                }
            }
        }
        return IAScore;
    }

}