package PaooGame.GameStates;


import PaooGame.inputs.KeyHandler;

import java.awt.Graphics;

public interface StateMethods {
    public void update(KeyHandler keyHandler);

    public void draw(Graphics g);

}
