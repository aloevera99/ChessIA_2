package org.project;

import com.github.bhlangonijr.chesslib.Board;

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
           /* else if ("print".equals(inputString))
            {
                inputPrint();
            }*/
            else if ("quit".equals(inputString))
            {
                break;
            }
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
                Board board = new Board(); //generate intial board / pas la bonne instruction je pense

            }
            else if (input.contains("fen")){
                // j'ai pqs trop compris ce qu'il se passe la
            //générer le board actuel
            }
            else if (input.contains("moves")){
                input = input.substring(input.indexOf("moves")+6); // remove the word "moves"
                // in order to get the string of the position in which to move
                while (input != ""){
                String moves ;

                //récupérer les deux premiers caracteres et les mettre dans dep
                // récupérer les deux caracteres suivants et les mettre dans arr
                // faire le move -> Move (dep,arr);

                //checker la couleur
                    //if IA, faire le move de l'IA
                    // else if JOUEUR, faire le move du joueur
                //faire le move en conséquence
                }
            }
        }
        public static void inputNewGame(){
            Board board = new Board(); // generate new Board for a new game
            // remettre l'engine a zéro -> normalement c'est ok
        }
        public static void inputGo(){
            //IA.minimax(board); //search for the best move
        }

       /*
       //pas sûr que ce soit utile
       public static void inputPrint(){

        }*/

}