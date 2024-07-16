package PaooGame.GameStates;
import PaooGame.inputs.KeyHandler;
import PaooGame.util.Timer;

import java.awt.*;

public class Meniu implements StateMethods {
    public static boolean A_was_pressed=false;
    public Meniu() {

    }
    @Override
    public void update(KeyHandler keyHandler) {
        if(keyHandler.pressed[KeyHandler.Taste.A.ordinal()]) {
            Timer.init();
            //schimbarea game state-ului
            A_was_pressed=true; //pentru a stii cand incepe un nou joc
            GameState.state=GameState.PLAYING;
        }
        else if(keyHandler.pressed[KeyHandler.Taste.S.ordinal()]) { //se schimba starea
            GameState.state = GameState.PLAYING;
            Timer.init();
        }
        else if(keyHandler.pressed[KeyHandler.Taste.D.ordinal()]) { //pentru a deschide tabela de scoruri
            GameState.state = GameState.SCORE;
            Timer.init();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black); //imaginea se deseneaza in Draw din Game
    }


        //todo|:pentru meniu
}
