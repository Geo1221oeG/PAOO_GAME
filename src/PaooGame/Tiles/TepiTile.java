package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class TepiTile extends Tile {
    Rectangle hitbox;
    public TepiTile(int id)
    {

        super(Assets.trap, id);
        hitbox=new Rectangle(50,64);
       /* hitbox.x=50;
        hitbox.y=64;*/

    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
