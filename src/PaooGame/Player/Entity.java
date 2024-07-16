package PaooGame.Player;


import java.awt.*;

public abstract class Entity {
    protected int x,y;
    protected int width,height;
    protected Rectangle hitbox;
    protected static int HITBOX_WIDTH=35;
    protected static int HITBOX_HIGHT=64;

    public Entity(int x,int y, int width,int height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        initHitbox();
    }
    private void initHitbox() {
        hitbox=new Rectangle((int)x+13,(int)y,HITBOX_WIDTH,HITBOX_HIGHT-2);
    }
    protected void updateHitbox()
    {
        hitbox.x=(int)x+13;
        hitbox.y=(int)y+2;
    }
    protected void drawHitbox(Graphics g) //pentru debbuging
    {
        //pentru debugg hitbox
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
