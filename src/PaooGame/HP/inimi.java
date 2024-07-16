package PaooGame.HP;

import PaooGame.Graphics.Assets;
import PaooGame.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class inimi {
    public static BufferedImage inima_plina;
    public static BufferedImage inima_goala;
    public static BufferedImage inima_jumatate;
    public static void loadImg()
    {
        inima_goala= Assets.inima_goala;
        inima_plina= Assets.inima_plina;
        inima_jumatate= Assets.inima_jumatate;
    }
    public static void Draw(Graphics g, Player player)
    {//in functie de procentul de viata al personajului se va afisa inimile corespunzatoare
        if(player.getHp()>=600) {
            g.drawImage(inima_plina, 20, 20, 32, 32, null); //viata full 3 inimi full
            g.drawImage(inima_plina, 60, 20, 32, 32, null);
            g.drawImage(inima_plina, 100, 20, 32, 32, null);
        }
         else if(player.getHp()==500)
        {//viata 5/6 2 inimi full + o jumatate
            g.drawImage(inima_plina, 20, 20, 32, 32, null);
            g.drawImage(inima_plina, 60, 20, 32, 32, null);
            g.drawImage(inima_jumatate, 100, 20, 32, 32, null);
        }
         else if(player.getHp()==400)
        {//viata 4/6 2 inimi full + una goala
            g.drawImage(inima_plina, 20, 20, 32, 32, null);
            g.drawImage(inima_plina, 60, 20, 32, 32, null);
            g.drawImage(inima_goala, 100, 20, 32, 32, null);
        }  else if(player.getHp()==300)
        {//viata 3/6 1 plina + 1 jumatate + 1 goala
            g.drawImage(inima_plina, 20, 20, 32, 32, null);
            g.drawImage(inima_jumatate, 60, 20, 32, 32, null);
            g.drawImage(inima_goala, 100, 20, 32, 32, null);
        }  else if(player.getHp()==200)
        {//viata 2/6 1 plina + 2 goale
            g.drawImage(inima_plina, 20, 20, 32, 32, null);
            g.drawImage(inima_goala, 60, 20, 32, 32, null);
            g.drawImage(inima_goala, 100, 20, 32, 32, null);
        }  else if(player.getHp()==100)
        {//viata 1/6 1 jumatate + 2 goale
            g.drawImage(inima_jumatate, 20, 20, 32, 32, null);
            g.drawImage(inima_goala, 60, 20, 32, 32, null);
            g.drawImage(inima_goala, 100, 20, 32, 32, null);
        }
        else if(player.getHp()==0)
        {//viata 0/6 3 goale
            g.drawImage(inima_goala, 20, 20, 32, 32, null);
            g.drawImage(inima_goala, 60, 20, 32, 32, null);
            g.drawImage(inima_goala, 100, 20, 32, 32, null);
        }

    }
}
