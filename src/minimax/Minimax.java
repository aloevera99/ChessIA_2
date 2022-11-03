import com.github.bhlangonijr.chesslib.*;
import java.util.*;

class Minimax {

    int depth;


    ArrayList<Board> possileBoards = new ArrayList<Board>();

    Minimax(int d) {
        depth = d;
    }

    Move minimax(Borad board){

        Move bestMove;
        int bestMoveScore;

        //get legal moves of the current board --> la fonction renvoit les legal moves pour touuutes les pièces
        //filtrer selon la couleur des pièces
        //les stocker dans possible boards --> chaque itération sur 1 pièce de la bonne couleur donne un possible board
        // avec le move de la pièce
        //appel de la fonction récursive evaluate position sur tous les possible board collecter
        //puis récursivement, la fonction va parcourir les board possible avec une profondeur depth (depth appels)
        //puis meilleur évaluation renvoit meilleur move à la fonction minimax

    }



}