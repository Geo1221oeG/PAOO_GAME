package PaooGame.GameStates;

import PaooGame.Graphics.ImageLoader;
import PaooGame.inputs.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FinalState implements StateMethods{ //starea finala, o sa-l numim poate ecranul de final
    BufferedImage img;
    public FinalState()
    {
        img= ImageLoader.LoadImage("/textures/final.png");
    } //incarcarea imaginii de final

    @Override
    public void update(KeyHandler keyHandler) {
          if(keyHandler.pressed[KeyHandler.Taste.SPACE.ordinal()]) { //pentru space se iese din ecranul de final
              GameState.state=GameState.MENU; //si se intra in meniu
          }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,0,0,null);
    } //afisarea pe ecran a ecranului de final
}
