package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class afinTile extends Tile{
    public afinTile(int id)
    {
        super((id == 17) ? Assets.afin_fara_afine : Assets.afin, id);

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
