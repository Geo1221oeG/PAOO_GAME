package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class sign_afine_Tile extends Tile{
    public sign_afine_Tile(int id)
    {
        super((id == 20) ? Assets.sign_afine : Assets.sign_direction_afine, id); //id 20 si 21

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
