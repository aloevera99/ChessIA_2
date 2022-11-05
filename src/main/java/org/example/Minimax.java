package org.example;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.*;

class Minimax {

    int depth;
    boolean color; //false means the AI is black, true means it is white


    ArrayList<Board> possileBoards = new ArrayList<Board>();

    Minimax(int d) {
        depth = d;
    }

    //minmax algo that returns the best move to make according to the color set in attributes
    Move minimax(Board board){

        Move bestMove;
        int bestMoveScore;
        //créer tableau de possible boards vide

        //1// get legal moves of the current board --> la fonction renvoit les legal moves pour touuutes les pièces


        //print the chessboard in a human-readable form
        System.out.println(board.toString());

        //on récupère les legal moves du board à t
        List<Move> moves = board.legalMoves();
        System.out.println("Legal moves: " + moves);

        //on applique tous les legal moves au board possible qui devient board t+1
        for (Move move : moves) {
            System.out.println("piece moving is : " + board.getPiece(move.getFrom()));
/*

            System.out.println("side of the piece moving :");
            System.out.println(board.getPiece(move.getTo()).getPieceSide());*/

            board.doMove(move);
            //créer le board avec cette disposition
            //le stocker dans tableau de boards
            //UNDOOO le move
            board.undoMove();
        }

        //print the chessboard in a human-readable form
        System.out.println(board.toString());

        //2// filtrer selon la couleur des pièces

        //3// les stocker dans possible boards --> chaque itération sur 1 pièce de la bonne couleur donne un possible board
        // avec le move de la pièce

        //4// appel de la fonction récursive evaluate position sur tous les possible board collecter

        //5// puis récursivement, la fonction va parcourir les board possible avec une profondeur depth (depth appels)

        //6// puis meilleur évaluation renvoit meilleur move à la fonction minimax

        return moves.get(0);
    }

    int evaluatePosition(Board board){
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