package PaooGame.Levels;

import PaooGame.Graphics.ImageLoader;


import java.awt.image.BufferedImage;

public class Level1 {

    public static BufferedImage image;
    protected static int[][] map;
    public static int init_x=2;
    public static int init_y=41;

    public Level1() {
        map=null;
    }
    public static void InitLv1()
    {
        image=ImageLoader.LoadImage("/textures/level_1.png");
        map=LevelManager.GetMap(image);
    }
/*    public static void DrawLevel(Graphics g) //prin asta luam harta din imagine sub forma de matrice
    {
        LevelManager.DrawMap(g,image);

    }*/
    public static BufferedImage getImg()
    {
        return image;
    }
    public static int[][] getMap()
    {
        return map;
    }

}
