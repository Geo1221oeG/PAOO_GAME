package PaooGame.util;

import PaooGame.DataBase.DatabaseForLevelResume;
import PaooGame.Exeptioooons.TimeExeption;

import java.awt.*;
import java.text.DecimalFormat;

public class Timer {
    public static  int timp=0;
    public static  int from_last_game=0;
    private static  long lastSecond=0;
    private static long currentSecond=0;
    public static void init() {
        lastSecond =  System.currentTimeMillis() / 1000;
        from_last_game= DatabaseForLevelResume.getTime();

    }

    public static void Update() {
        currentSecond = System.currentTimeMillis() / 1000;
        try{
            timp=(int)(currentSecond-lastSecond)+from_last_game;
            if(timp<0) //adica diferenta dintre currentSecond si lastSecond trece peste limita int-ului
                //si timp-ul devine negativ
                throw new TimeExeption("err la calcul timp"); //arunca eroarea
        }
        catch (TimeExeption e)
        {
            System.exit(0);
        }

    }
    public static void Draw(Graphics g)
    {
        Font fnt = new Font("DialogInput", Font.BOLD, 15);
        g.setFont(fnt);
        g.setColor(Color.white);
        DecimalFormat dformat = new DecimalFormat("#0.0");
        g.drawString("time:" + dformat.format(timp) + "s", 850, 20);

    }
}
