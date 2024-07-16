package PaooGame.Player;

import PaooGame.DataBase.DatabaseForLevelResume;

public class BuildPlayer implements Builder { //implementarea unui Builder pentru Player
    private Player player;

    @Override
    public void setHp(int hp) {
        player.setHp(hp);
    }

    @Override
    public void setAfine(int afine) {
        player.setAfine(afine);
    }

    @Override
    public void setLevel(int level) {
        player.level=level;

    }
    public Player makePlayer(int x,int y,int hp,int level,int afine) {
        // Inițializează player-ul cu coordonatele x și y specificate
        player = new Player(DatabaseForLevelResume.getPlayerX(), DatabaseForLevelResume.getPlayerY(), 5);

        // Setează afine, hp și level
        setAfine(afine);
        setHp(hp);
        setLevel(level);
        return player;
    }

}
