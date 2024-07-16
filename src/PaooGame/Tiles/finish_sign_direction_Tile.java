package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class finish_sign_direction_Tile extends Tile {
    public finish_sign_direction_Tile(int id)
    {
        super((id == 18) ? Assets.sign_direction : Assets.sign_finish, id); //id 18 si 19

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
