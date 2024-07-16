package PaooGame.Player;

import PaooGame.Animation.Animation;
import PaooGame.Animation.PlayerSprite;
import PaooGame.DataBase.ScoreDataBase;
import PaooGame.Game;
import PaooGame.GameStates.GameState;
import PaooGame.GameStates.Pause;
import PaooGame.Levels.Level2;
import PaooGame.Levels.Level3;
import PaooGame.Levels.LevelManager;
import PaooGame.Tiles.Tile;
import PaooGame.util.Timer;
import PaooGame.inputs.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.util.HelpMethods.*;


public class Player extends Entity {
    public static final int PLAYER_WIDTH = 64;                       /*!< Latimea unei dale.*/
    public static final int PLAYER_HEIGHT = 64;
    protected BufferedImage img;
    public static Animation a; //animatia personajului
    public static int[][] map; //harta pt verificare pt hitbox
    private int id;
    private int playerSpeed = 3;
    private boolean jump, left, right;

    //for gravity/jump
    private float airSpeed = 0f;
    private float gravity = 0.15f;
    private float jumpSpeed = -5.7f;

    private float fallSpeedAfterCollision = 0.5f;
    private boolean inAir = false;

    //pt calcuarea pozitiilor pentru camera
    public static int screenX;
    public static int screenY;

    //date initiale
    private int initX;
    private int initY;
    //killing the player
    private boolean dying = false;// daca o sa moara

    //viata
    private int hp=600;

    //cules afine
    private boolean cules=false; //daca se intampla actiunea
    //lv curent
    public int level=1;
    private int afine=0;
    private boolean lose_hp;



    public Player(int x, int y, int id)// constructor
    {
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        initX = 2*64;
        initY = 41*64;
        a = new Animation();
    }

    public void update(KeyHandler keyHandler) {
        Key_Animation(keyHandler);
        screenX = x - Game.GAME_WIDTH / 2 + PLAYER_WIDTH;
        screenY = y - Game.GAME_HEIGHT / 2 + PLAYER_WIDTH;
        int xSpeed = 0, ySpeed = 0;
        if(isSpikeDownThere(hitbox,x,y,map)) //todo: aici o sa fie chestia pentru timp
            hp=0;
        if (jump) jump();
        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        if(cules)
        {
            handleCulesAfine();
        }
        if(finishLevel() && level!=3)
        {
            level++;
            LevelManager.loadLevel(level,this);
            changePosition(level);

        }
        else if(finishLevel() && level==3)
        {
            ScoreDataBase.insertScore(Timer.timp);
            GameState.state=GameState.FINISH;
        }
       // System.out.println(x+" "+y);

      /*  if(afin_Langa_personaj(hitbox,x,y,map))
            System.out.println("e langa personaj");*/

        else if (Timer.timp%7==0 && lose_hp==false && Timer.timp!=0) //daca o trecut 15 si nu i s-a scazut inca din viata
        {
            lose_hp=true;
            hp-=100; //i se scade hp

        }
        else if(Timer.timp%7!=0) //daca s-a ajuns la sec 16 sau multiplu de 16
            lose_hp=false; //facem variabila de verificare egala cu false pentru a putea fi verifica cu ea la urmatorul multiplu de 15

        if(hp==0)
            handleDying();

        else {
            handleJump(xSpeed, ySpeed);
        }
            updateHitbox();
        //todo: de rezolvat cu double jump-ul care nu trebuie sa fie double jump

    }

    private void handleCulesAfine() {
       // map[((y+64) / Tile.TILE_WIDTH)][(x+32) / Tile.TILE_WIDTH]=17;
        img=a.loadAnimation(PlayerSprite.take_afina_sprite, PlayerSprite.lungime_cules);
        if(a.hasEnded(PlayerSprite.lungime_cules)) {
            cules=false;
            if(afine<5)afine++;
        }
    }

    private void handleDying() {
        if(!dying)
        {
            dying=true;
            a.reset();
            img=a.loadAnimation(PlayerSprite.dead_sprite, PlayerSprite.lungime_dead);
        }
        else if(a.hasEnded(PlayerSprite.lungime_dead)) {
            Pause.start = System.currentTimeMillis();
            Pause.pause(2);
            if (Game.pause == false) {
                dying = false;
                x = initX;
                y = initY;
                afine=0;
                LevelManager.loadLevel(level,this);
                hp=600;
             /*   Level3.InitLv3();
                loadMap(Level3.getMap());*/
            }
        }
        else img=a.loadAnimation(PlayerSprite.dead_sprite, PlayerSprite.lungime_dead);
    }

    private void handleJump(int xSpeed, int ySpeed) {
        if (!inAir ) {
            if (!IsEntityOnFloor(hitbox, map)) {
                inAir = true;
                //System.out.println("nu in aer");
            }
        }
        //if (IsEntityOnFloor(hitbox, map))
            //System.out.println("on the floor");
        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, map) ) {
                y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);

            } else {
                hitbox.y = (int) GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (IsEntityOnFloor(hitbox,map)/*airSpeed > 0*/)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        } else
            updateXPos(xSpeed);
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;

    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(int xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, HITBOX_WIDTH, HITBOX_HIGHT - 3, map)) //-3 ca fara nu merge @_@
        {
            x += xSpeed;
        } else {
            hitbox.x = (int) GetEntityXPosNextToWall(hitbox, xSpeed);
        }

    }


    public void Key_Animation(KeyHandler keyHandler) {
        if (!isSpikeDownThere(hitbox, x, y, map) && !dying && Game.pause == false) {
            //A merge stanga +animatie
            if (keyHandler.pressed[KeyHandler.Taste.A.ordinal()] &&
                    !keyHandler.pressed[KeyHandler.Taste.D.ordinal()]) {
                left = true;
                img = a.loadAnimation(PlayerSprite.run_left_sprite, PlayerSprite.lungime_run);
            }
            //D merge dreapta + animatie
            if (keyHandler.pressed[KeyHandler.Taste.D.ordinal()] &&
                    !keyHandler.pressed[KeyHandler.Taste.A.ordinal()]) {
                right = true;
                img = a.loadAnimation(PlayerSprite.run_sprite, PlayerSprite.lungime_run);
            }
            //W sare + animatie
            if (keyHandler.pressed[KeyHandler.Taste.W.ordinal()]) {
                jump = true;
                img = a.loadAnimation(PlayerSprite.jump_sprite, PlayerSprite.lungime_jump);
            }
            if (keyHandler.pressed[KeyHandler.Taste.E.ordinal()]
                    && afin_Langa_personaj(hitbox,x,y,map)) {
                cules = true;

            }

            //0 taste -> idle animatie
            if (!keyHandler.pressed[KeyHandler.Taste.W.ordinal()] &&
                    !keyHandler.pressed[KeyHandler.Taste.A.ordinal()] &&
                    !keyHandler.pressed[KeyHandler.Taste.D.ordinal()]) {
                img = a.loadAnimation(PlayerSprite.idle_sprite, PlayerSprite.lungime_idle);
            }

            if (!keyHandler.pressed[KeyHandler.Taste.A.ordinal()]) left = false;
            if (!keyHandler.pressed[KeyHandler.Taste.D.ordinal()]) right = false;
            if (!keyHandler.pressed[KeyHandler.Taste.W.ordinal()]) jump = false;


           //in cazul in care s-a apasat tasta 1/2/3/4/5 atunci in functie de
           //casuta daca exista se va consuma o afina
            if(keyHandler.afina_folosita==1 && afine>=1)//ex daca s-a apasat tasta 1 atunci se verifica daca exista mai mult de o afina in inventar
                //adica daca primul loc din inventar este ocupat, si daca da atunci
            {
                afine--; //scadem din numarul de afina
                hp+=100; //crestem viata
                keyHandler.afina_folosita=0; //operatiunea fiind gata se revine la nici o tasta pentru afine apasata
            }if(keyHandler.afina_folosita==2 && afine>=2)// se va face acelas lucru si pentru cazul in care s-au ales afinele de pe tasta 2 3 4 sau 5
            {   afine--;
                hp+=100;
                keyHandler.afina_folosita=0;
            }if(keyHandler.afina_folosita==3 && afine>=3) {
                afine--;
                hp+=100;
                keyHandler.afina_folosita=0;
            }if(keyHandler.afina_folosita==4 && afine>=4) {
                afine--;
                hp+=100;
                keyHandler.afina_folosita=0;
            }if(keyHandler.afina_folosita>=5 && afine>=5) {
                afine--;
                hp+=100;
                keyHandler.afina_folosita=0;
            }

        }
    }

    public static void loadMap(int[][] mapa) //se incarca harta pentru a verifica mai tarziu coliziunile
    {
        map = new int[Game.GAME_WIDTH][Game.GAME_HEIGHT];
        map = mapa;

    }

    public void Draw(Graphics g) //desenarea personajului pe ecran
    {
        g.drawImage(img, (int) x - screenX, (int) y - screenY, PLAYER_WIDTH, PLAYER_HEIGHT, null);//schimbat asta ca sa incercam sa facem camera
    }
    public void setPosition(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public boolean finishLevel()
    {
        if( map[ (y+64) / Tile.TILE_WIDTH][x / Tile.TILE_WIDTH]==18) {
            hp=600;
            return true;
        }
        return false;
    }
    public void changePosition(int level)
    {
        switch (level) {
            case 2:
                setPosition(Level2.init_x*64, Level2.init_y*64);
            case 3:
                setPosition(Level3.init_x*64, Level3.init_y*64);

        }
    }

    public void setHp(int hp) {
        this.hp=hp;
    }

    public void setAfine(int nrAfine) {
        this.afine=nrAfine;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHp()
    {
        return hp;
    }
    public int getAfine()
    {
        return afine;
    }

    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }


}