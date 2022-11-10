package org.example;

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
            else if ("ucinewgame".equals(inputString)){
                inputNewGame();
            }
            else if (inputString.startsWith("position"))
            {
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
            if (input.contains ("startpos")){
                //générer le board intial
            }
            else if (input.contains ("fen")){
            //générer le board actuel
            }
            else if (input.contains ("moves")){
            //faire le move
                //parser pour avoir le 3e élément (il peut y avoir plus que 3 éléments?)
                String moves ;
                //checker la couleur
                //faire le move en conséquence (vérifier la notation du truc)

            }
        }
        public static void inputNewGame(){
            //génération d'un board
        }
        public static void inputGo(){
        //search for the best move

        }
       /*
       //pas sûr que ce soit utile
       public static void inputPrint(){

        }*/

}