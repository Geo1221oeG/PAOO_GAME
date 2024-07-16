package PaooGame.util;

import PaooGame.Game;
import PaooGame.Player.Player;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class HelpMethods {
    //returneaza daca personajul poate sau nu sa fac urmatorul pas
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData))
        {

            if (!IsSolid(x + width, y + height, lvlData))
            {

                if (!IsSolid(x + width, y, lvlData))
                {

                    if (!IsSolid(x, y + height, lvlData)) {

                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH+ Player.screenX)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT+Player.screenY)
            return true;

        float xIndex = x / Tile.TILE_WIDTH;
        float yIndex = y / Tile.TILE_WIDTH;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value <= 15)
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Tile.TILE_WIDTH);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Tile.TILE_WIDTH;
            int xOffset = (int) (Tile.TILE_WIDTH - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            // Left
            return currentTile * Tile.TILE_WIDTH;
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Tile.TILE_WIDTH);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Tile.TILE_WIDTH;
            int yOffset = (int) (Tile.TILE_WIDTH - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            // Jumping
            return currentTile * Tile.TILE_WIDTH;

    }

    public static boolean IsEntityOnFloor(Rectangle hitbox, int[][] lvlData) {
        // Check the pixel below bottomleft and bottomright
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        //System.out.println("pe pamant");
        return true;
    }

    public static boolean SpikeColision(int x,int y,int[][] lvlData)
    {
        if (x < 0 || x >= Game.GAME_WIDTH+ Player.screenX)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT+Player.screenY)
            return true;

        float xIndex = x / Tile.TILE_WIDTH;
        float yIndex = y / Tile.TILE_WIDTH;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value == 15)
            return true;
        return false;
    }
    public static boolean isSpikeDownThere(Rectangle hitbox,int x,int y ,int[][] lvlData) //coordonatele personajului
    {
        if (!SpikeColision(hitbox.x+25, hitbox.y + hitbox.height + 1, lvlData))
           if (!SpikeColision(hitbox.x + hitbox.width-20, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
      //  System.out.println("e pe tepi");
        return true;
    }
    public static int afin_poz_x,afin_poz_y;
    public  static boolean afin_Langa_personaj(Rectangle hitbox, int x,int y,int[][] lvlData)
    {
        if (x < 0 || x >= Game.GAME_WIDTH+ Player.screenX)
            return false;

        if (y < 0 || y >= Game.GAME_HEIGHT+Player.screenY)
            return false;

        float xIndex = (x+32) / Tile.TILE_WIDTH;
        float yIndex = (y+64)/ Tile.TILE_WIDTH;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value == 16) {
            lvlData[(int) yIndex][(int) xIndex]=17; //todo:posibile probleme de rezolvat daca inca apar la inceputul lv-ului -> in handleCulesAfine rezolvare da alt bug
            //System.out.println(value);
            afin_poz_x=(int)xIndex;
            afin_poz_y=(int)yIndex;
           // DataBasePlantaAfini.insertCoordinates(afin_poz_x,afin_poz_y);
            return true;
        }
        return false;
    }



}
