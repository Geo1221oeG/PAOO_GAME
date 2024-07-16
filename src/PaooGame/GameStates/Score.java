package PaooGame.GameStates;

import PaooGame.DataBase.ScoreDataBase;
import PaooGame.Graphics.ImageLoader;
import PaooGame.inputs.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Score implements StateMethods{
    BufferedImage img;
    public Score()
    {
        img= ImageLoader.LoadImage("/textures/tabela_scor.png");
    }

    @Override
    public void update(KeyHandler keyHandler) {
        if(keyHandler.pressed[KeyHandler.Taste.SPACE.ordinal()]) {
            GameState.state=GameState.MENU;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,0,0,null);
        ScoreDataBase.DrawTopScores(g);
    }
}
