package org.project;

//import com.github.bhlangonijr.chesslib.*;
//import java.util.*;

class Game {

    private boolean turn; //false means white turn, true means black turn    private boolean victory; //false means game has not been won, true means game is over
    private boolean is_white; //true means AI is white, false means AI is black

    public Game(boolean is_white){
        //sets who is what color
        is_white = is_white;
        if (is_white)
            turn = false;
        else
            turn = true;
    }

}