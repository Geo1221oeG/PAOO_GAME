package PaooGame.Player;

import PaooGame.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Inventory {

    private BufferedImage afine_culese;
    private BufferedImage img_inventar;

    public Inventory()
        {
        init();

    }
    public void init()
    {
        img_inventar= ImageLoader.LoadImage("/textures/inventory.png");
        afine_culese= ImageLoader.LoadImage("/textures/afina.png");

    }
    public void Draw(Graphics g, int nr_afine)
    {
        g.drawImage(img_inventar, 380, 15, 240, 48, null);
        if(nr_afine>=1)
            g.drawImage(afine_culese,390,20,32,32,null);
        if(nr_afine>=2) {
            g.drawImage(afine_culese, 439, 20, 32, 32, null);
        }
        if(nr_afine>=3) {
            g.drawImage(afine_culese, 487, 20, 32, 32, null);
        }
        if(nr_afine>=4) {
            g.drawImage(afine_culese, 534, 20, 32, 32, null);
        }
        if(nr_afine>=5) {
            g.drawImage(afine_culese, 579, 20, 32, 32, null);
        }
    }
}
