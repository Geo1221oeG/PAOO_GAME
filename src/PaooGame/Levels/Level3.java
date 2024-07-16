package PaooGame.Levels;

import PaooGame.Graphics.ImageLoader;


import java.awt.image.BufferedImage;

public class Level3 {
    public static BufferedImage image;
    protected static int[][] map;
    public static int init_x=2;
    public static int init_y=41;
    public Level3() {
        map=null;
    }
    public static void InitLv3()
    {
        try {
            image = ImageLoader.LoadImage("/textures/level_3.png");
            map = LevelManager.GetMap(image);
        }
        catch (NullPointerException e)
        {
            System.out.println("nu s-a gasit imaginea");
        }
    }
  /*  public static void DrawLevel(Graphics g) //prin asta luam harta din imagine sub forma de matrice
    {
        LevelManager.DrawMap(g,image);

    }*/
    public static int[][] getMap()
    {
        return map;
    }
    public static BufferedImage getImg()
    {
        return image;
    }
}
