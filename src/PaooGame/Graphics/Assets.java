package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil;
    public static BufferedImage[][] grass;
    public static BufferedImage trap;
    public static BufferedImage afin;

    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage townGrassDestroyed;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage rockUp;
    public static BufferedImage rockDown;
    public static BufferedImage rockLeft;
    public static BufferedImage rockRight;
    public static BufferedImage tree;
    //planta de afine fara afine
    public static BufferedImage afin_fara_afine;
    //semnele prezente in niveluri
    public static BufferedImage sign_afine;
    public static BufferedImage sign_direction;
    public static BufferedImage sign_finish;
    public static BufferedImage sign_direction_afine;
    //inimi
    public static BufferedImage inima_goala;
    public static BufferedImage inima_plina;
    public static BufferedImage inima_jumatate;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet iarba_sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/sprite_iarba.png"));
        SpriteSheet trap_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/tepi.png"));
        //plante afine
        SpriteSheet afin_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/afin.png"));
        SpriteSheet afin_fara_afine_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/afin_fara_afine.png"));
        //semne
        SpriteSheet sign_afine_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/signs.png"));
        SpriteSheet sign_direction_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/signs.png"));
        SpriteSheet sign_finish_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/signs.png"));
        SpriteSheet sign_direction_afine_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/signs.png"));
        //inimi
        SpriteSheet inima_sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/inimi.png"));


        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass=new BufferedImage[5][3];
        for(int i=0;i<5;++i)
            for(int j=0;j<3;++j)
                grass[i][j]=iarba_sheet.crop(i,j);
        //tepi
        trap=trap_sheet.crop(0,0);

        //plante cu si fara afine
        afin=afin_sheet.crop(0,0);
        afin_fara_afine=afin_fara_afine_sheet.crop(0,0);

        //semne prezente pe parcurs
        sign_direction=sign_direction_sheet.crop(1,0);
        sign_afine=sign_afine_sheet.crop(1,1);
        sign_finish=sign_finish_sheet.crop(0,1);
        sign_direction_afine=sign_direction_afine_sheet.crop(0,0);

        //inimi
        inima_plina=inima_sheet.crop(0,0);
        inima_jumatate=inima_sheet.crop(1,0);
        inima_goala=inima_sheet.crop(2,0);
        /*soil = sheet.crop(1, 0);
        water = sheet.crop(2, 0);
        mountain = sheet.crop(3, 0);
        townGrass = sheet.crop(0, 1);
        townGrassDestroyed = sheet.crop(1, 1);
        townSoil = sheet.crop(2, 1);
        tree = sheet.crop(3, 1);
        playerLeft = sheet.crop(0, 2);
        playerRight = sheet.crop(1, 2);
        rockUp = sheet.crop(2, 2);
        rockDown = sheet.crop(3, 2);
        rockLeft = sheet.crop(0, 3);
        rockRight = sheet.crop(1, 3);*/
    }
}
