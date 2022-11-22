package org.project;


import com.github.bhlangonijr.chesslib.Board;

public class Main {
    public static void main(String[] args) {

        //define depth of the algo
        int depth = 4;

        // Creates a new chessboard in the standard initial position
        Board board = new Board();


        Minimax IA = new Minimax();
        IA.minimax(board);

    }
}