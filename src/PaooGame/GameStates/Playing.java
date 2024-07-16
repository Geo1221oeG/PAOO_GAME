package PaooGame.GameStates;

import PaooGame.Animation.PlayerSprite;
import PaooGame.DataBase.DatabaseForLevelResume;
import PaooGame.DataBase.ScoreDataBase;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.HP.inimi;
import PaooGame.Levels.LevelManager;
import PaooGame.Player.Inventory;
import PaooGame.Player.Player;
import PaooGame.inputs.KeyHandler;
import PaooGame.util.PauseText;
import PaooGame.util.Referinta;
import PaooGame.util.SetPlayer;
import PaooGame.util.Timer;

import java.awt.*;

public class Playing implements StateMethods {
    //todo:pentru meniu
    private Player player;
    private SetPlayer db_set_player;
    private Inventory inventar;
    public Playing()
    {
        Init();
    }

    public void Init()
    {
        Assets.Init();//assets-urile din joc
        PlayerSprite.Init(); //sprite-urile personajului
        inventar=new Inventory();

        db_set_player=new SetPlayer(); //variabila pentru setarea player-ului
        DatabaseForLevelResume.MakeDatabase(); //crearea bazei de date
        ScoreDataBase.createDatabase();
        //inializarea personajului
        player = new Player(DatabaseForLevelResume.getPlayerX() , DatabaseForLevelResume.getPlayerY() , 5);
        player=db_set_player.SetPlayerFromDB2(); //luarea informatiilor din baza de date
        Referinta.player = player;
        inimi.loadImg();
    }

    @Override
    public void update(KeyHandler keyHandler) {
        if(Meniu.A_was_pressed==true)
        {
            Meniu.A_was_pressed=false;
            DatabaseForLevelResume.insertData(1, 2 * 64, 41 * 64, 600, 0, 1);
            player=db_set_player.SetPlayerFromDB2();
            Referinta.player = player;
        }
        if(Game.pause==false) {
            player.update(keyHandler);
            Timer.Update();
        }
        if(keyHandler.pressed[KeyHandler.Taste.P.ordinal()]) {
            GameState.state = GameState.MENU;
            DatabaseForLevelResume.insertData(Referinta.player.level,Referinta.player.getX(),Referinta.player.getY(),Referinta.player.getHp(),Referinta.player.getAfine(),Timer.timp);
        }
    }

    @Override
    public void draw(Graphics g) {
        LevelManager.DrawMap(g,player.level);
        player.Draw(g);
        inimi.Draw(g,player);
        Timer.Draw(g);
        inventar.Draw(g,player.getAfine());
        PauseText.Draw(g);
    }
    public Player GetPlayer()
    {
        return player;
    }
}
