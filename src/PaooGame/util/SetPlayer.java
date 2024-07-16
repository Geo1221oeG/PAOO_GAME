package PaooGame.util;

import PaooGame.DataBase.DatabaseForLevelResume;
import PaooGame.Levels.LevelManager;
import PaooGame.Player.BuildPlayer;
import PaooGame.Player.Player;


public class SetPlayer {
    BuildPlayer bp;

    public Player SetPlayerFromDB2()
    {
        bp=new BuildPlayer();
        Player player=bp.makePlayer(DatabaseForLevelResume.getPlayerX(),
                DatabaseForLevelResume.getPlayerY(),
                DatabaseForLevelResume.getHp(),
                DatabaseForLevelResume.getLevel(),
                DatabaseForLevelResume.getNrAfine());


        LevelManager.loadLevel(player.level,player);
      //  player.setHp(DatabaseForLevelResume.getHp());
      //  player.setAfine(DatabaseForLevelResume.getNrAfine());
        Timer.from_last_game=DatabaseForLevelResume.getTime();
        return player;
    }
}
