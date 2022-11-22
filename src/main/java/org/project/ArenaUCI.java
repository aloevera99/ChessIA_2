package org.project;

import com.github.bhlangonijr.chesslib.*;
<<<<<<< Updated upstream
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
=======

>>>>>>> Stashed changes

import java.util.*;


class ArenaUCI {
    static String ENGINENAME = "ChessIA";
    public static void ArenaCommunication() {
        Scanner input = new Scanner(System.in);

        while (true) {
            //boucle infinie qui dure tant que l'engine est lancé
            // on récupère l'input puis on en fait une String
            String inputString = input.nextLine();

            if ("uci".equals(inputString)) {
                inputUCI();
            }
            else if ("isready".equals(inputString)){
                inputIsReady();
            }
            else if ("ucinewgame".equals(inputString)){
                inputNewGame();
            }
            else if (inputString.startsWith("position")) {
                inputPosition(inputString);
            }
            else if ("go".equals(inputString))
            {
                inputGo();
            }
            else if ("quit".equals(inputString))
            {
                break;
            }
            /* else if ("print".equals(inputString))
            {
                inputPrint();
            }*/
        }
    }

        public static void inputUCI() {
            System.out.println("id name "+ ENGINENAME);
            //System.out.println("id author Jonathan");
            System.out.println("uciok");
        }
        public static void inputIsReady() {
            System.out.println("readyok");
        }

        public static void inputPosition(String input){
            input = input.substring(9).concat("");

            if (input.contains("startpos")){
                input= input.substring(9); //remove the word "position"
            }
            else if (input.contains("fen")){
                // On a besoin de ca ?
            }
            else if (input.contains("moves")){
                input = input.substring(input.indexOf("moves")+6); // remove the word "moves"
                // in order to get the string of the position in which to move
                MoveList list = new MoveList();
                list.loadFromSan(input);
                board.loadFromFen(list.getFen());
            }
        }
        public static void inputNewGame(){
            Board board = new Board(); // generate new Board for a new game
            // remettre l'engine a zéro
        }
        public static void inputGo(){
<<<<<<< Updated upstream
            Move bestmove = IA.minimax(board); //search for the best move
            System.out.println("bestmove" + bestmove);
=======

            //IA.minimax(board); //search for the best move
>>>>>>> Stashed changes
        }

       /*
       //pas sûr que ce soit utile
       public static void inputPrint(){
        }*/
}