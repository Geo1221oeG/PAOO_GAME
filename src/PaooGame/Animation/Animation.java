package PaooGame.Animation;

import PaooGame.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage img;
    static public boolean animation_end;
    private BufferedImage[] img_animation;
    private int animation_length;
    public static int animation_tick=0;

    public static int animation_index=0,animation_speed=10;
    private PlayerSprite sprites;
    public Animation() {
    }

    public BufferedImage loadAnimation(SpriteSheet sprite,int lungime) { //returneaza sprite-l curent in animatie
        animation_tick++;
        animation_end=false;
        //System.out.println(lungime);

        if (animation_tick >= animation_speed) {
            animation_tick = 0;
            animation_index++;}
        if (animation_index > lungime-1) {
            animation_index = 0;
            animation_end=true;
        }
        //System.out.println(animation_index+" "+lungime);
        return sprite.crop(animation_index,0);
    }
    public boolean hasEnded(int lungime) {
        return animation_index >= lungime - 1;
    } //daca animatia s-a terminat

    public void reset() { //reset la animatie
        animation_tick = 0;
        animation_index = 0;
    }
}