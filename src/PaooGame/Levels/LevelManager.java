package PaooGame.Levels;



import PaooGame.Player.Player;
import PaooGame.Tiles.*;


import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    protected static int[][] map;

    //private int Level1;
    public LevelManager() {
        map = null;
    }

    public static int[][] GetMap(BufferedImage image) {

        int h = image.getHeight();
        int w = image.getWidth();
        //System.out.println(h + " " + w);
        map = new int[h][w];
        for (int j = 0; j < h; ++j) {
            for (int i = 0; i < w; ++i) {
                try {
                    Color color = new Color(image.getRGB(i, j));
                    int value = color.getRed();
                    if (value >= 48)
                        value = 16;
                    map[j][i] = value;
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public static int getSpriteIndex(int x, int y) {
        return map[y][x];
    }

    public static void DrawMap(Graphics g,int level) {
        //map=GetMap(img);
        BufferedImage img=null;
       switch (level)
        {
            case 1:
                img=Level1.getImg();
                break;
            case 2:
                img=Level2.getImg();
                break;
            case 3:
                img=Level3.getImg();
                break;
        }
        for (int j = 0; j < img.getHeight(); ++j) {
            for (int i = 0; i < img.getWidth(); ++i) {
                int index = getSpriteIndex(i, j);
                if (index >= 0 && index <= 14) {
                    Tile grassTile = new GrassTile(index);
                    grassTile.Draw(g, i * 64, j * 64);
                } else if (index == 15) {
                    Tile tepiTile = new TepiTile(index);
                    tepiTile.Draw(g, i * 64, j * 64);
                } else if (index == 16) {
                    Tile afin = new afinTile(index);
                    afin.Draw(g, i * 64, j * 64);
                } else if (index == 17) {
                    Tile afin = new afinTile(index);
                    afin.Draw(g, i * 64, j * 64);
                } else if (index == 19) {
                    Tile finish_sign = new finish_sign_direction_Tile(index);
                    finish_sign.Draw(g, i * 64, j * 64);
                } else if (index == 18) {
                    Tile direction_sign = new finish_sign_direction_Tile(index);
                    direction_sign.Draw(g, i * 64, j * 64);
                } else if (index == 20) {
                    Tile afine_sign = new sign_afine_Tile(index);
                    afine_sign.Draw(g, i * 64, j * 64);
                } else if (index == 21) {
                    Tile direction_sign_afine = new sign_afine_Tile(index);
                    direction_sign_afine.Draw(g, i * 64, j * 64);
                }
                //  System.out.print(index+" ");
            } //System.out.println("\n");} System.out.println("\n");
        }
    }

    public static void loadLevel(int level, Player player) {
        switch (level) {
            case 1:
                Level1.InitLv1();
               // player.setPosition(Level1.init_x, Level1.init_y);
                player.loadMap(Level1.getMap());
              /*  HelpMethods.setareAfine_din_datebase(player);//todo:AICI*/

                break;
            case 2:
                Level2.InitLv2();
                //player.setPosition(Level2.init_x, Level2.init_y);
                player.loadMap(Level2.getMap());/* HelpMethods.setareAfine_din_datebase(player);*/

                break;
            case 3:
                Level3.InitLv3();
                //player.setPosition(Level3.init_x, Level3.init_y);
                player.loadMap(Level3.getMap());/* HelpMethods.setareAfine_din_datebase(player);*/
                break;
        }

    }
   public static void loadMapInPlayer(int level,Player player)
   {
       switch (level) {
           case 1:
               player.loadMap(Level1.getMap());
               break;
           case 2:
               player.loadMap(Level2.getMap());
               break;
           case 3:
               player.loadMap(Level3.getMap());
               break;
       }
   }
}
