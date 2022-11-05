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



}