package PaooGame.Animation;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class PlayerSprite {
    public static SpriteSheet jump_sprite;
    public static int lungime_jump=9,lungime_dead=5,lungime_hurt=2,lungime_run=8,lungime_idle=5,lungime_cules=5;
    public static SpriteSheet dead_sprite ;
    public static SpriteSheet hurt_sprite;
    public static SpriteSheet run_sprite;
    public static SpriteSheet idle_sprite;
    public static SpriteSheet dead_left_sprite;
    public static SpriteSheet hurt_left_sprite;
    public static SpriteSheet run_left_sprite;
    public static SpriteSheet idle_left_sprite;
    public static SpriteSheet jump_left_sprite;
    public static SpriteSheet take_afina_sprite;
    public static void Init() { //sprite-urile personajului
        jump_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/jump.png"));
        dead_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/death.png"));
        hurt_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/hurt.png"));
        run_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/run.png"));
        idle_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/idle.png"));

        run_left_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/run_left.png"));
        jump_left_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/jump_left.png"));
        dead_left_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/death_left.png"));
        hurt_left_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/hurt_left.png"));
        take_afina_sprite = new SpriteSheet(ImageLoader.LoadImage("/textures/cules_afine.png"));

    }
}
