package PaooGame.inputs;




import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener {
    public static boolean[] pressed=new boolean[12];
    public static int afina_folosita=0;
    public KeyHandler()
    {

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    public enum Taste {
        SPACE, //0
        TASTA1, //1
        TASTA2,//2
        TASTA3,//3
        TASTA4,//4
        W,//5
        A,//6
        S,//7
        D,//8
        E,//9
        P,//10
        TASTA5
    };

    @Override
    public void keyPressed(KeyEvent e) {
        //e verific daca are unu din codurile date din tabel si schimb in vectorul de frecventa

        switch(e.getKeyCode()) {
            case VK_A:
                pressed[Taste.A.ordinal()]= true;
                //System.out.println("A");
                break;
            case VK_W:
                pressed[Taste.W.ordinal()]= true;
                //System.out.println("w");
                break;
            case VK_E:
                pressed[Taste.E.ordinal()]= true;

                break;
            case VK_S:
                pressed[Taste.S.ordinal()]= true;

                break;
            case VK_D:
                pressed[Taste.D.ordinal()]= true;

                break;
            case VK_1:
                pressed[Taste.TASTA1.ordinal()]= true;
                afina_folosita=1;
                break;
            case VK_2:
                pressed[Taste.TASTA2.ordinal()]= true;
                afina_folosita=2;
                break;
            case VK_3:
                pressed[Taste.TASTA3.ordinal()]= true;
                afina_folosita=3;
                break;
            case VK_4:
                pressed[Taste.TASTA4.ordinal()]=true;
                afina_folosita=4;
                break;
            case VK_5:
                pressed[Taste.TASTA5.ordinal()]=true;
                afina_folosita=5;
                break;
            case VK_SPACE:
                pressed[Taste.SPACE.ordinal()]=true;
                break;
            case VK_P:
                pressed[Taste.P.ordinal()]=true;
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()) {
            case VK_A:
                pressed[Taste.A.ordinal()]= false;
                break;
            case VK_W:
                pressed[Taste.W.ordinal()]= false;
                break;
            case VK_E:
                pressed[Taste.E.ordinal()]= false;
                break;
            case VK_S:
                pressed[Taste.S.ordinal()]= false;
                break;
            case VK_D:
                pressed[Taste.D.ordinal()]= false;
                break;
            case VK_1:
                pressed[Taste.TASTA1.ordinal()]= false;
                break;
            case VK_2:
                pressed[Taste.TASTA2.ordinal()]= false;
                break;
            case VK_3:
                pressed[Taste.TASTA3.ordinal()]= false;
                break;
            case VK_4:
                pressed[Taste.TASTA4.ordinal()]=false;
                break;
            case VK_5:
                pressed[Taste.TASTA4.ordinal()]=false;
                break;
            case VK_SPACE:
                pressed[Taste.SPACE.ordinal()]=false;
                break;
            case VK_P:
                pressed[Taste.P.ordinal()]=false;
                break;
            default:
                break;

        }
    }
}














