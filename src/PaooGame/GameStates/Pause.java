package PaooGame.GameStates;

import PaooGame.Game;

public class Pause {
    public static long start;
    public static  long end;
   public static void pause(int n)
   {
       end=System.currentTimeMillis();
        while ((end-start)/1000.0>=n)
            Game.pause=true;
   }

}
